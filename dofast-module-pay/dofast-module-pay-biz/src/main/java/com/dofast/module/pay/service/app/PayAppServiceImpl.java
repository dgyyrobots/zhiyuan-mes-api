package com.dofast.module.pay.service.app;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.pay.controller.admin.app.vo.PayAppCreateReqVO;
import com.dofast.module.pay.controller.admin.app.vo.PayAppPageReqVO;
import com.dofast.module.pay.controller.admin.app.vo.PayAppUpdateReqVO;
import com.dofast.module.pay.controller.admin.merchant.vo.app.PayAppExportReqVO;
import com.dofast.module.pay.convert.app.PayAppConvert;
import com.dofast.module.pay.dal.dataobject.app.PayAppDO;
import com.dofast.module.pay.dal.dataobject.merchant.PayMerchantDO;
import com.dofast.module.pay.dal.mysql.app.PayAppMapper;
import com.dofast.module.pay.dal.mysql.merchant.PayMerchantMapper;
import com.dofast.module.pay.dal.mysql.order.PayOrderMapper;
import com.dofast.module.pay.dal.mysql.refund.PayRefundMapper;
import com.dofast.module.pay.enums.ErrorCodeConstants;
import com.dofast.module.pay.enums.order.PayOrderStatusEnum;
import com.dofast.module.pay.enums.refund.PayRefundStatusEnum;
import com.dofast.module.pay.service.order.PayOrderService;
import com.dofast.module.pay.service.refund.PayRefundService;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.*;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertSet;
import static com.dofast.module.pay.enums.ErrorCodeConstants.*;

/**
 * 支付应用信息 Service 实现类
 *
 * @author aquan
 */
@Service
@Validated
public class PayAppServiceImpl implements PayAppService {

    @Resource
    private PayAppMapper appMapper;
    // TODO @aquan：使用对方的 Service。模块与模块之间，避免直接调用对方的 mapper
    @Resource
    private PayMerchantMapper merchantMapper;
    @Resource
    private PayOrderMapper orderMapper;
    @Resource
    private PayRefundMapper refundMapper;


    @Resource
    @Lazy // 延迟加载，避免循环依赖报错
    private PayOrderService orderService;
    @Resource
    @Lazy // 延迟加载，避免循环依赖报错
    private PayRefundService refundService;


    @Override
    public Long createApp(PayAppCreateReqVO createReqVO) {
        // 插入
        PayAppDO app = PayAppConvert.INSTANCE.convert(createReqVO);
        appMapper.insert(app);
        // 返回
        return app.getId();
    }

    @Override
    public void updateApp(PayAppUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateAppExists(updateReqVO.getId());
        // 更新
        PayAppDO updateObj = PayAppConvert.INSTANCE.convert(updateReqVO);
        appMapper.updateById(updateObj);
    }

    @Override
    public void deleteApp(Long id) {
        // 校验存在
        this.validateAppExists(id);
        this.validateOrderTransactionExist(id);

        // 删除
        appMapper.deleteById(id);
    }

    private void validateAppExists(Long id) {
        if (appMapper.selectById(id) == null) {
            throw exception(PAY_APP_NOT_FOUND);
        }
    }

    @Override
    public PayAppDO getApp(Long id) {
        return appMapper.selectById(id);
    }

    @Override
    public List<PayAppDO> getAppList(Collection<Long> ids) {
        return appMapper.selectBatchIds(ids);
    }


    @Override
    public List<PayAppDO> getAppList() {
        return null;
    }

    @Override
    public PageResult<PayAppDO> getAppPage(PayAppPageReqVO pageReqVO) {
        Set<Long> merchantIdList = this.getMerchantCondition(pageReqVO.getMerchantName());
        if (StrUtil.isNotBlank(pageReqVO.getMerchantName()) && CollectionUtil.isEmpty(merchantIdList)) {
            return new PageResult<>();
        }
        return appMapper.selectPage1(pageReqVO, merchantIdList);
    }

    /*@Override
    public List<PayAppDO> getAppList1(PayAppExportReqVO exportReqVO) {
        Set<Long> merchantIdList = this.getMerchantCondition(exportReqVO.getMerchantName());
        if (StrUtil.isNotBlank(exportReqVO.getMerchantName()) && CollectionUtil.isEmpty(merchantIdList)) {
            return new ArrayList<>();
        }
        return appMapper.selectList1(exportReqVO, merchantIdList);
    }*/

    /**
     * 获取商户编号集合，根据商户名称模糊查询得到所有的商户编号集合
     *
     * @param merchantName 商户名称
     * @return 商户编号集合
     */
    private Set<Long> getMerchantCondition(String merchantName) {
        if (StrUtil.isBlank(merchantName)) {
            return Collections.emptySet();
        }
        return convertSet(merchantMapper.getMerchantListByName(merchantName), PayMerchantDO::getId);
    }

    @Override
    public void updateAppStatus(Long id, Integer status) {
        // 校验商户存在
        this.checkAppExists(id);
        // 更新状态
        PayAppDO app = new PayAppDO();
        app.setId(id);
        app.setStatus(status);
        appMapper.updateById(app);
    }

    @Override
    public List<PayAppDO> getListByMerchantId(Long merchantId) {
        return appMapper.getListByMerchantId1(merchantId);
    }

    /**
     * 检查商户是否存在
     *
     * @param id 商户编号
     */
    @VisibleForTesting
    public void checkAppExists(Long id) {
        if (id == null) {
            return;
        }
        PayAppDO payApp = appMapper.selectById(id);
        if (payApp == null) {
            throw exception(PAY_APP_NOT_FOUND);
        }
    }

    /**
     * 验证是否存在交易中或者退款中等处理中状态的订单
     *
     * @param appId 应用 ID
     */
    private void validateOrderTransactionExist(Long appId) {
        // 查看交易订单
        if (orderMapper.selectCount(appId, PayOrderStatusEnum.WAITING.getStatus()) > 0) {
            throw exception(PAY_APP_EXIST_TRANSACTION_ORDER_CANT_DELETE);
        }
        // 查看退款订单
        if (refundMapper.selectCount(appId, PayRefundStatusEnum.CREATE.getStatus()) > 0) {
            throw exception(PAY_APP_EXIST_TRANSACTION_ORDER_CANT_DELETE);
        }
    }

    @Override
    public PayAppDO validPayApp(Long id) {
        PayAppDO app = appMapper.selectById(id);
        // 校验是否存在
        if (app == null) {
            throw exception(PAY_APP_NOT_FOUND);
        }
        // 校验是否禁用
        if (CommonStatusEnum.DISABLE.getStatus().equals(app.getStatus())) {
            throw exception(ErrorCodeConstants.PAY_APP_IS_DISABLE);
        }
        return app;
    }

/*
    @Override
    public Long createApp(PayAppCreateReqVO createReqVO) {
        // 插入
        PayAppDO app = PayAppConvert.INSTANCE.convert(createReqVO);
        appMapper.insert(app);
        // 返回
        return app.getId();
    }

    @Override
    public void updateApp(PayAppUpdateReqVO updateReqVO) {
        // 校验存在
        validateAppExists(updateReqVO.getId());
        // 更新
        PayAppDO updateObj = PayAppConvert.INSTANCE.convert(updateReqVO);
        appMapper.updateById(updateObj);
    }

    @Override
    public void updateAppStatus(Long id, Integer status) {
        // 校验商户存在
        validateAppExists(id);
        // 更新状态
        appMapper.updateById(new PayAppDO().setId(id).setStatus(status));
    }

    @Override
    public List<PayAppDO> getListByMerchantId(Long merchantId) {
        return appMapper.getListByMerchantId1(merchantId);
    }*/

    /*@Override
    public void deleteApp(Long id) {
        // 校验存在
        validateAppExists(id);
        // 校验关联数据是否存在
        if (orderService.getOrderCountByAppId(id) > 0) {
            throw exception(APP_EXIST_ORDER_CANT_DELETE);
        }
        if (refundService.getRefundCountByAppId(id) > 0) {
            throw exception(APP_EXIST_REFUND_CANT_DELETE);
        }

        // 删除
        appMapper.deleteById(id);
    }*/
/*

    private void validateAppExists(Long id) {
        if (appMapper.selectById(id) == null) {
            throw exception(APP_NOT_FOUND);
        }
    }
*/

   /* @Override
    public PayAppDO getApp(Long id) {
        return appMapper.selectById(id);
    }
*/
   /* @Override
    public List<PayAppDO> getAppList(Collection<Long> ids) {
        return appMapper.selectBatchIds(ids);
    }
*/
   /* private Set<Long> getMerchantCondition(String merchantName) {
        if (StrUtil.isBlank(merchantName)) {
            return Collections.emptySet();
        }
        return convertSet(merchantMapper.getMerchantListByName(merchantName), PayMerchantDO::getId);
    }*/
    @Override
    public List<PayAppDO> getAppList10(PayAppExportReqVO exportReqVO) {
        Set<Long> merchantIdList = this.getMerchantCondition(exportReqVO.getMerchantName());
        if (StrUtil.isNotBlank(exportReqVO.getMerchantName()) && CollectionUtil.isEmpty(merchantIdList)) {
            return new ArrayList<>();
        }
        return appMapper.selectList(exportReqVO, merchantIdList);
    }

/*
    @Override
    public List<PayAppDO> getAppList() {
        return appMapper.selectList();
    }
*/
/*

    @Override
    public PageResult<PayAppDO> getAppPage(PayAppPageReqVO pageReqVO) {
        return appMapper.selectPage(pageReqVO);
    }
*/

    @Override
    public Map<Long, PayAppDO> getAppMap(Collection<Long> ids) {
        return PayAppService.super.getAppMap(ids);
    }
/*
    @Override
    public PayAppDO validPayApp(Long id) {
        PayAppDO app = appMapper.selectById(id);
        // 校验是否存在
        if (app == null) {
            throw exception(ErrorCodeConstants.APP_NOT_FOUND);
        }
        // 校验是否禁用
        if (CommonStatusEnum.DISABLE.getStatus().equals(app.getStatus())) {
            throw exception(ErrorCodeConstants.APP_IS_DISABLE);
        }
        return app;
    }*/

}