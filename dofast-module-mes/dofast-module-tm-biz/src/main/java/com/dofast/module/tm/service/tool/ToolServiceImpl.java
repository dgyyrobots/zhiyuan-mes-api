package com.dofast.module.tm.service.tool;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.tm.controller.admin.tool.vo.*;
import com.dofast.module.tm.dal.dataobject.tool.ToolDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.tm.convert.tool.ToolConvert;
import com.dofast.module.tm.dal.mysql.tool.ToolMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.tm.enums.ErrorCodeConstants.*;

/**
 * 工装夹具清单 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ToolServiceImpl implements ToolService {

    @Resource
    private ToolMapper toolMapper;

    @Override
    public Long createTool(ToolCreateReqVO createReqVO) {
        // 插入
        ToolDO tool = ToolConvert.INSTANCE.convert(createReqVO);
        toolMapper.insert(tool);
        // 返回
        return tool.getId();
    }

    @Override
    public void updateTool(ToolUpdateReqVO updateReqVO) {
        // 校验存在
        validateToolExists(updateReqVO.getId());
        // 更新
        ToolDO updateObj = ToolConvert.INSTANCE.convert(updateReqVO);
        toolMapper.updateById(updateObj);
    }

    @Override
    public void deleteTool(Long id) {
        // 校验存在
        validateToolExists(id);
        // 删除
        toolMapper.deleteById(id);
    }

    private void validateToolExists(Long id) {
        if (toolMapper.selectById(id) == null) {
            throw exception(TOOL_NOT_EXISTS);
        }
    }

    @Override
    public ToolDO getTool(Long id) {
        return toolMapper.selectById(id);
    }

    @Override
    public List<ToolDO> getToolList(Collection<Long> ids) {
        return toolMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ToolDO> getToolPage(ToolPageReqVO pageReqVO) {
        return toolMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ToolDO> getToolList(ToolExportReqVO exportReqVO) {
        return toolMapper.selectList(exportReqVO);
    }

}
