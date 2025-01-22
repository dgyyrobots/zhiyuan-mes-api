package com.dofast.module.wms.service.rtvendor;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.dal.dataobject.rtvendor.RtVendorTxBean;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.rtvendor.vo.*;
import com.dofast.module.wms.dal.dataobject.rtvendor.RtVendorDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.rtvendor.RtVendorConvert;
import com.dofast.module.wms.dal.mysql.rtvendor.RtVendorMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 供应商退货 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RtVendorServiceImpl implements RtVendorService {

    @Resource
    private RtVendorMapper rtVendorMapper;

    @Override
    public List<RtVendorTxBean> getTxBeans(Long rtId) {
        return rtVendorMapper.getTxBeans(rtId);
    }

    @Override
    public String checkCodeUnique(RtVendorBaseVO wmRtVendor) {
        RtVendorDO rt = rtVendorMapper.checkCodeUnique(wmRtVendor);
        Long rtId = wmRtVendor.getId() ==null?-1L:wmRtVendor.getId();
        if(StrUtils.isNotNull(rt) && rt.getId().longValue() != rtId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createRtVendor(RtVendorCreateReqVO createReqVO) {
        // 插入
        RtVendorDO rtVendor = RtVendorConvert.INSTANCE.convert(createReqVO);
        rtVendorMapper.insert(rtVendor);
        // 返回
        return rtVendor.getId();
    }

    @Override
    public void updateRtVendor(RtVendorUpdateReqVO updateReqVO) {
        // 校验存在
        validateRtVendorExists(updateReqVO.getId());
        // 更新
        RtVendorDO updateObj = RtVendorConvert.INSTANCE.convert(updateReqVO);
        rtVendorMapper.updateById(updateObj);
    }

    @Override
    public void deleteRtVendor(Long id) {
        // 校验存在
        validateRtVendorExists(id);
        // 删除
        rtVendorMapper.deleteById(id);
    }

    private void validateRtVendorExists(Long id) {
        if (rtVendorMapper.selectById(id) == null) {
            throw exception(RT_VENDOR_NOT_EXISTS);
        }
    }

    @Override
    public RtVendorDO getRtVendor(Long id) {
        return rtVendorMapper.selectById(id);
    }

    @Override
    public RtVendorDO getRtVendor(String vendorCode){
        return rtVendorMapper.selectOne(RtVendorDO::getVendorCode,vendorCode);
    }


    @Override
    public List<RtVendorDO> getRtVendorList(Collection<Long> ids) {
        return rtVendorMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RtVendorDO> getRtVendorPage(RtVendorPageReqVO pageReqVO) {
        return rtVendorMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RtVendorDO> getRtVendorList(RtVendorExportReqVO exportReqVO) {
        return rtVendorMapper.selectList(exportReqVO);
    }

}
