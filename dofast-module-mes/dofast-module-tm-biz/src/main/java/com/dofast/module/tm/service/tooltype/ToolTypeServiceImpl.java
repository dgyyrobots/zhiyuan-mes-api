package com.dofast.module.tm.service.tooltype;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.tm.controller.admin.tooltype.vo.*;
import com.dofast.module.tm.dal.dataobject.tooltype.ToolTypeDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.tm.convert.tooltype.ToolTypeConvert;
import com.dofast.module.tm.dal.mysql.tooltype.ToolTypeMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.tm.enums.ErrorCodeConstants.*;

/**
 * 工装夹具类型 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ToolTypeServiceImpl implements ToolTypeService {

    @Resource
    private ToolTypeMapper toolTypeMapper;

    @Override
    public Long createToolType(ToolTypeCreateReqVO createReqVO) {
        // 插入
        ToolTypeDO toolType = ToolTypeConvert.INSTANCE.convert(createReqVO);
        toolTypeMapper.insert(toolType);
        // 返回
        return toolType.getId();
    }

    @Override
    public void updateToolType(ToolTypeUpdateReqVO updateReqVO) {
        // 校验存在
        validateToolTypeExists(updateReqVO.getId());
        // 更新
        ToolTypeDO updateObj = ToolTypeConvert.INSTANCE.convert(updateReqVO);
        toolTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteToolType(Long id) {
        // 校验存在
        validateToolTypeExists(id);
        // 删除
        toolTypeMapper.deleteById(id);
    }

    private void validateToolTypeExists(Long id) {
        if (toolTypeMapper.selectById(id) == null) {
            throw exception(TOOL_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public ToolTypeDO getToolType(Long id) {
        return toolTypeMapper.selectById(id);
    }

    @Override
    public List<ToolTypeDO> getToolTypeList(Collection<Long> ids) {
        return toolTypeMapper.selectBatchIds(ids);
    }

    @Override
    public List<ToolTypeSimpleVO> getToolTypeSimpleList() {
        List<ToolTypeSimpleVO> toolTypeSimpleVOS = toolTypeMapper.selectSimpleList();
        return toolTypeSimpleVOS;
    }

    @Override
    public PageResult<ToolTypeDO> getToolTypePage(ToolTypePageReqVO pageReqVO) {
        return toolTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ToolTypeDO> getToolTypeList(ToolTypeExportReqVO exportReqVO) {
        return toolTypeMapper.selectList(exportReqVO);
    }
}
