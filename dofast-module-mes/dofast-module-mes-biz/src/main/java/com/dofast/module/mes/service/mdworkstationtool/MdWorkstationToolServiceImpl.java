package com.dofast.module.mes.service.mdworkstationtool;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.mdworkstationtool.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationtool.MdWorkstationToolDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.mdworkstationtool.MdWorkstationToolConvert;
import com.dofast.module.mes.dal.mysql.mdworkstationtool.MdWorkstationToolMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 工装夹具资源 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MdWorkstationToolServiceImpl implements MdWorkstationToolService {

    @Resource
    private MdWorkstationToolMapper mdWorkstationToolMapper;

    @Override
    public int deleteByWorkstationId(Long workstationId) {
        return mdWorkstationToolMapper.deleteByWorkstationId(workstationId);
    }

    @Override
    public String checkToolTypeExists(MdWorkstationToolBaseVO mdWorkstationTool) {
        MdWorkstationToolDO workstationTool = mdWorkstationToolMapper.checkToolTypeExists(mdWorkstationTool);
        Long workstationToolId = mdWorkstationTool.getId()==null?-1L:mdWorkstationTool.getId();
        if(StrUtils.isNotNull(workstationTool)&&workstationTool.getId().longValue()!=workstationToolId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createMdWorkstationTool(MdWorkstationToolCreateReqVO createReqVO) {
        // 插入
        MdWorkstationToolDO mdWorkstationTool = MdWorkstationToolConvert.INSTANCE.convert(createReqVO);
        mdWorkstationToolMapper.insert(mdWorkstationTool);
        // 返回
        return mdWorkstationTool.getId();
    }

    @Override
    public void updateMdWorkstationTool(MdWorkstationToolUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdWorkstationToolExists(updateReqVO.getId());
        // 更新
        MdWorkstationToolDO updateObj = MdWorkstationToolConvert.INSTANCE.convert(updateReqVO);
        mdWorkstationToolMapper.updateById(updateObj);
    }

    @Override
    public void deleteMdWorkstationTool(Long id) {
        // 校验存在
        validateMdWorkstationToolExists(id);
        // 删除
        mdWorkstationToolMapper.deleteById(id);
    }

    private void validateMdWorkstationToolExists(Long id) {
        if (mdWorkstationToolMapper.selectById(id) == null) {
            throw exception(MD_WORKSTATION_TOOL_NOT_EXISTS);
        }
    }

    @Override
    public MdWorkstationToolDO getMdWorkstationTool(Long id) {
        return mdWorkstationToolMapper.selectById(id);
    }

    @Override
    public List<MdWorkstationToolDO> getMdWorkstationToolList(Collection<Long> ids) {
        return mdWorkstationToolMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MdWorkstationToolDO> getMdWorkstationToolPage(MdWorkstationToolPageReqVO pageReqVO) {
        return mdWorkstationToolMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdWorkstationToolDO> getMdWorkstationToolList(MdWorkstationToolExportReqVO exportReqVO) {
        return mdWorkstationToolMapper.selectList(exportReqVO);
    }

}
