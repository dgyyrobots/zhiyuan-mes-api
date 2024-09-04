package com.dofast.module.mes.service.mdworkstation;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.mdworkstation.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstation.MdWorkstationDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.mdworkstation.MdWorkstationConvert;
import com.dofast.module.mes.dal.mysql.mdworkstation.MdWorkstationMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 工作站 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MdWorkstationServiceImpl implements MdWorkstationService {

    @Resource
    private MdWorkstationMapper mdWorkstationMapper;

    @Override
    public String checkWorkStationCodeUnique(MdWorkstationBaseVO mdWorkstation) {
        MdWorkstationDO workstation = mdWorkstationMapper.checkWorkStationCodeUnique(mdWorkstation);
        Long workstationId = mdWorkstation.getId()==null? -1L:mdWorkstation.getId();
        if(StrUtils.isNotNull(workstation) && workstation.getId().longValue() != workstationId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public String checkWorkStationNameUnique(MdWorkstationBaseVO mdWorkstation) {
        MdWorkstationDO workstation = mdWorkstationMapper.checkWorkStationNameUnique(mdWorkstation);
        Long workstationId = mdWorkstation.getId()==null? -1L:mdWorkstation.getId();
        if(StrUtils.isNotNull(workstation) && workstation.getId().longValue() != workstationId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createMdWorkstation(MdWorkstationCreateReqVO createReqVO) {
        // 插入
        MdWorkstationDO mdWorkstation = MdWorkstationConvert.INSTANCE.convert(createReqVO);
        mdWorkstationMapper.insert(mdWorkstation);
        // 返回
        return mdWorkstation.getId();
    }

    @Override
    public void updateMdWorkstation(MdWorkstationUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdWorkstationExists(updateReqVO.getId());
        // 更新
        MdWorkstationDO updateObj = MdWorkstationConvert.INSTANCE.convert(updateReqVO);
        mdWorkstationMapper.updateById(updateObj);
    }

    @Override
    public void deleteMdWorkstation(Long id) {
        // 校验存在
        validateMdWorkstationExists(id);
        // 删除
        mdWorkstationMapper.deleteById(id);
    }

    private void validateMdWorkstationExists(Long id) {
        if (mdWorkstationMapper.selectById(id) == null) {
            throw exception(MD_WORKSTATION_NOT_EXISTS);
        }
    }

    @Override
    public MdWorkstationDO getMdWorkstation(Long id) {
        return mdWorkstationMapper.selectById(id);
    }

    @Override
    public List<MdWorkstationDO> getMdWorkstationList(Collection<Long> ids) {
        return mdWorkstationMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MdWorkstationDO> getMdWorkstationPage(MdWorkstationPageReqVO pageReqVO) {
        return mdWorkstationMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdWorkstationDO> getMdWorkstationList(MdWorkstationExportReqVO exportReqVO) {
        return mdWorkstationMapper.selectList(exportReqVO);
    }

    @Override
    public List<MdWorkstationDO> getMdWorkstationListByworkstationIds(Collection<Long> workstationIds) {
        return mdWorkstationMapper.select(workstationIds);
    }

}
