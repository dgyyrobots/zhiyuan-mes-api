package com.dofast.module.mes.service.mdvendor;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.convert.mdvendor.PadMdVendorConvert;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.mdvendor.vo.*;
import com.dofast.module.mes.dal.dataobject.mdvendor.MdVendorDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.dal.mysql.mdvendor.PadMdVendorMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 供应商 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PadMdVendorServiceImpl implements PadMdVendorService {

    @Resource
    private PadMdVendorMapper padMdVendorMapper;

    @Override
    public String checkVendorCodeUnique(MdVendorBaseVO mdVendor) {
        MdVendorDO vendor = padMdVendorMapper.checkVendorCodeUnique(mdVendor);
        Long vendorId = mdVendor.getId()==null?-1L:mdVendor.getId();
        if(null != (vendor) && vendor.getId().longValue() !=vendorId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public String checkVendorNameUnique(MdVendorBaseVO mdVendor) {
        MdVendorDO vendor = padMdVendorMapper.checkVendorNameUnique(mdVendor);
        Long vendorId = mdVendor.getId()==null?-1L:mdVendor.getId();
        if(null != (vendor) && vendor.getId().longValue() !=vendorId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public String checkVendorNickUnique(MdVendorBaseVO mdVendor) {
        MdVendorDO vendor = padMdVendorMapper.checkVendorNickUnique(mdVendor);
        Long vendorId = mdVendor.getId()==null?-1L:mdVendor.getId();
        if(null != (vendor) && vendor.getId().longValue() !=vendorId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createMdVendor(MdVendorCreateReqVO createReqVO) {
        // 插入
        MdVendorDO mdVendor = PadMdVendorConvert.INSTANCE.convert(createReqVO);
        padMdVendorMapper.insert(mdVendor);
        // 返回
        return mdVendor.getId();
    }

    @Override
    public void updateMdVendor(MdVendorUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdVendorExists(updateReqVO.getId());
        // 更新
        MdVendorDO updateObj = PadMdVendorConvert.INSTANCE.convert(updateReqVO);
        padMdVendorMapper.updateById(updateObj);
    }

    @Override
    public void deleteMdVendor(Long id) {
        // 校验存在
        validateMdVendorExists(id);
        // 删除
        padMdVendorMapper.deleteById(id);
    }

    private void validateMdVendorExists(Long id) {
        if (padMdVendorMapper.selectById(id) == null) {
            throw exception(MD_VENDOR_NOT_EXISTS);
        }
    }

    @Override
    public MdVendorDO getMdVendor(Long id) {
        return padMdVendorMapper.selectById(id);
    }

    @Override
    public MdVendorDO getMdVendor(String vendorCode){
        return padMdVendorMapper.selectOne(MdVendorDO::getVendorCode,vendorCode);
    }

    @Override
    public List<MdVendorDO> getMdVendorList(Collection<Long> ids) {
        return padMdVendorMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MdVendorDO> getMdVendorPage(MdVendorPageReqVO pageReqVO) {
        return padMdVendorMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdVendorDO> getMdVendorList(MdVendorExportReqVO exportReqVO) {
        return padMdVendorMapper.selectList(exportReqVO);
    }

}
