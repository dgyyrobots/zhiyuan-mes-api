package com.dofast.module.mes.service.mdworkstationmachine;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.mdworkstationmachine.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationmachine.MdWorkstationMachineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.mdworkstationmachine.MdWorkstationMachineConvert;
import com.dofast.module.mes.dal.mysql.mdworkstationmachine.MdWorkstationMachineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 设备资源 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MdWorkstationMachineServiceImpl implements MdWorkstationMachineService {

    @Resource
    private MdWorkstationMachineMapper mdWorkstationMachineMapper;

    @Override
    public MdWorkstationMachineDO checkMachineryExists(MdWorkstationMachineBaseVO mdWorkstationMachine) {
        return mdWorkstationMachineMapper.checkMachineryExists(mdWorkstationMachine);
    }

    @Override
    public int deleteByWorkstationId(Long workstationId) {
        return mdWorkstationMachineMapper.deleteByWorkstationId(workstationId);
    }

    @Override
    public Long createMdWorkstationMachine(MdWorkstationMachineCreateReqVO createReqVO) {
        // 插入
        MdWorkstationMachineDO mdWorkstationMachine = MdWorkstationMachineConvert.INSTANCE.convert(createReqVO);
        mdWorkstationMachineMapper.insert(mdWorkstationMachine);
        // 返回
        return mdWorkstationMachine.getId();
    }

    @Override
    public void updateMdWorkstationMachine(MdWorkstationMachineUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdWorkstationMachineExists(updateReqVO.getId());
        // 更新
        MdWorkstationMachineDO updateObj = MdWorkstationMachineConvert.INSTANCE.convert(updateReqVO);
        mdWorkstationMachineMapper.updateById(updateObj);
    }

    @Override
    public void deleteMdWorkstationMachine(Long id) {
        // 校验存在
        validateMdWorkstationMachineExists(id);
        // 删除
        mdWorkstationMachineMapper.deleteById(id);
    }

    private void validateMdWorkstationMachineExists(Long id) {
        if (mdWorkstationMachineMapper.selectById(id) == null) {
            throw exception(MD_WORKSTATION_MACHINE_NOT_EXISTS);
        }
    }

    @Override
    public MdWorkstationMachineDO getMdWorkstationMachine(Long id) {
        return mdWorkstationMachineMapper.selectById(id);
    }

    @Override
    public List<MdWorkstationMachineDO> getMdWorkstationMachineList(Collection<Long> ids) {
        return mdWorkstationMachineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MdWorkstationMachineDO> getMdWorkstationMachinePage(MdWorkstationMachinePageReqVO pageReqVO) {
        return mdWorkstationMachineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdWorkstationMachineDO> getMdWorkstationMachineList(MdWorkstationMachineExportReqVO exportReqVO) {
        return mdWorkstationMachineMapper.selectList(exportReqVO);
    }

}
