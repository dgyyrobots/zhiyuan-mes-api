package com.dofast.module.cmms.service.dvmachinerytype;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.cmms.controller.admin.dvmachinerytype.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvmachinerytype.DvMachineryTypeDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.cmms.convert.dvmachinerytype.DvMachineryTypeConvert;
import com.dofast.module.cmms.dal.mysql.dvmachinerytype.DvMachineryTypeMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.cmms.enums.ErrorCodeConstants.*;

/**
 * 设备类型 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DvMachineryTypeServiceImpl implements DvMachineryTypeService {

    @Resource
    private DvMachineryTypeMapper dvMachineryTypeMapper;

    @Override
    public DvMachineryTypeDO selectDvMachineryTypeByMachineryTypeId(Long machineryTypeId) {
        return dvMachineryTypeMapper.selectDvMachineryTypeByMachineryTypeId(machineryTypeId);
    }

    @Override
    public Long createDvMachineryType(DvMachineryTypeCreateReqVO createReqVO) {
        if(createReqVO.getParentTypeId()!= null){
            DvMachineryTypeDO parent = selectDvMachineryTypeByMachineryTypeId(createReqVO.getParentTypeId());
            if(null != (parent)){
                createReqVO.setAncestors(parent.getAncestors()+","+parent.getId());
            }
            else {
                createReqVO.setAncestors(createReqVO.getParentTypeId()+"");
            }
        }
        // 插入
        DvMachineryTypeDO dvMachineryType = DvMachineryTypeConvert.INSTANCE.convert(createReqVO);
        dvMachineryTypeMapper.insert(dvMachineryType);

        // 返回
        return dvMachineryType.getId();
    }

    @Override
    public void updateDvMachineryType(DvMachineryTypeUpdateReqVO updateReqVO) {
        // 校验存在
        validateDvMachineryTypeExists(updateReqVO.getMachineryTypeId());
        // 更新
        DvMachineryTypeDO updateObj = DvMachineryTypeConvert.INSTANCE.convert(updateReqVO);
        dvMachineryTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteDvMachineryType(Long id) {
        // 校验存在
        validateDvMachineryTypeExists(id);
        // 删除
        dvMachineryTypeMapper.deleteById(id);
    }

    private void validateDvMachineryTypeExists(Long id) {
        if (dvMachineryTypeMapper.selectById(id) == null) {
            throw exception(DV_MACHINERY_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public DvMachineryTypeDO getDvMachineryType(Long id) {
        return dvMachineryTypeMapper.selectById(id);
    }

    @Override
    public List<DvMachineryTypeDO> getDvMachineryTypeList(Collection<Long> ids) {
        if (ids == null){
            return dvMachineryTypeMapper.selectList();
        }else{
            return dvMachineryTypeMapper.selectBatchIds(ids);
        }
    }

    @Override
    public PageResult<DvMachineryTypeDO> getDvMachineryTypePage(DvMachineryTypePageReqVO pageReqVO) {
        return dvMachineryTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DvMachineryTypeDO> getDvMachineryTypeList(DvMachineryTypeExportReqVO exportReqVO) {
        return dvMachineryTypeMapper.selectList(exportReqVO);
    }

    @Override
    public List<DvMachineryTypeSimpleVO> getDvMachineryTypeSimpleList() {
        return dvMachineryTypeMapper.selectSimpleList();
    }
}
