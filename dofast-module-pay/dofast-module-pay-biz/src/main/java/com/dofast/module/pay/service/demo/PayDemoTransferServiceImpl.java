package com.dofast.module.pay.service.demo;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.dofast.framework.common.pojo.PageParam;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.pay.core.enums.transfer.PayTransferTypeEnum;
import com.dofast.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import com.dofast.module.pay.convert.demo.PayDemoTransferConvert;
import com.dofast.module.pay.convert.transfer.PayTransferConvert;
import com.dofast.module.pay.dal.dataobject.demo.PayDemoTransferDO;
import com.dofast.module.pay.dal.mysql.demo.PayDemoTransferMapper;
import com.dofast.module.pay.service.transfer.PayTransferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.pay.core.enums.transfer.PayTransferTypeEnum.*;
import static com.dofast.module.pay.enums.ErrorCodeConstants.PAY_TRANSFER_ALIPAY_ACCOUNT_NAME_IS_EMPTY;
import static com.dofast.module.pay.enums.ErrorCodeConstants.PAY_TRANSFER_ALIPAY_LOGIN_ID_IS_EMPTY;
import static com.dofast.module.pay.enums.transfer.PayTransferStatusEnum.WAITING;

/**
 * 示例转账业务 Service 实现类
 *
 * @author jason
 */
@Service
@Validated
public class PayDemoTransferServiceImpl implements PayDemoTransferService {

    /**
     * 接入的实力应用编号

     * 从 [支付管理 -> 应用信息] 里添加
     */
    private static final Long TRANSFER_APP_ID = 8L;
    @Resource
    private PayDemoTransferMapper demoTransferMapper;
    @Resource
    private Validator validator;

    @Override
    public Long createDemoTransfer(@Valid PayDemoTransferCreateReqVO vo) {
        // 1 校验参数
        vo.validate(validator);
        // 2 保存示例转账业务表
        PayDemoTransferDO demoTransfer = PayDemoTransferConvert.INSTANCE.convert(vo)
                .setAppId(TRANSFER_APP_ID).setTransferStatus(WAITING.getStatus());
        demoTransferMapper.insert(demoTransfer);
        return demoTransfer.getId();
    }

    @Override
    public PageResult<PayDemoTransferDO> getDemoTransferPage(PageParam pageVO) {
        return demoTransferMapper.selectPage(pageVO);
    }



}
