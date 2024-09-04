package com.dofast.module.wms.service.rtsalse;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.dal.dataobject.rtsalse.RtSalseTxBean;
import dm.jdbc.desc.Const;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.rtsalse.vo.*;
import com.dofast.module.wms.dal.dataobject.rtsalse.RtSalseDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.rtsalse.RtSalseConvert;
import com.dofast.module.wms.dal.mysql.rtsalse.RtSalseMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 产品销售退货单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RtSalseServiceImpl implements RtSalseService {

    @Resource
    private RtSalseMapper rtSalseMapper;

    @Override
    public List<RtSalseTxBean> getTxBeans(Long rtId) {
        return rtSalseMapper.getTxBeans(rtId);
    }

    @Override
    public String checkUnique(RtSalseBaseVO wmRtSalse) {
        RtSalseDO salse = rtSalseMapper.checkUnique(wmRtSalse);
        Long rtId = wmRtSalse.getId() == null? -1L: wmRtSalse.getId();
        if(StrUtils.isNotNull(salse) && rtId.longValue() != salse.getId().longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createRtSalse(RtSalseCreateReqVO createReqVO) {
        // 插入
        RtSalseDO rtSalse = RtSalseConvert.INSTANCE.convert(createReqVO);
        rtSalseMapper.insert(rtSalse);
        // 返回
        return rtSalse.getId();
    }

    @Override
    public void updateRtSalse(RtSalseUpdateReqVO updateReqVO) {
        // 校验存在
        validateRtSalseExists(updateReqVO.getId());
        // 更新
        RtSalseDO updateObj = RtSalseConvert.INSTANCE.convert(updateReqVO);
        rtSalseMapper.updateById(updateObj);
    }

    @Override
    public void deleteRtSalse(Long id) {
        // 校验存在
        validateRtSalseExists(id);
        // 删除
        rtSalseMapper.deleteById(id);
    }

    private void validateRtSalseExists(Long id) {
        if (rtSalseMapper.selectById(id) == null) {
            throw exception(RT_SALSE_NOT_EXISTS);
        }
    }

    @Override
    public RtSalseDO getRtSalse(Long id) {
        return rtSalseMapper.selectById(id);
    }

    @Override
    public List<RtSalseDO> getRtSalseList(Collection<Long> ids) {
        return rtSalseMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RtSalseDO> getRtSalsePage(RtSalsePageReqVO pageReqVO) {
        return rtSalseMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RtSalseDO> getRtSalseList(RtSalseExportReqVO exportReqVO) {
        return rtSalseMapper.selectList(exportReqVO);
    }

}
