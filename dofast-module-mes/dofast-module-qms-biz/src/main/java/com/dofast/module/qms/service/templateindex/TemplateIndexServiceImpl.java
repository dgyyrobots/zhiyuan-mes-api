package com.dofast.module.qms.service.templateindex;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.qms.controller.admin.templateindex.vo.*;
import com.dofast.module.qms.dal.dataobject.templateindex.TemplateIndexDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.qms.convert.templateindex.TemplateIndexConvert;
import com.dofast.module.qms.dal.mysql.templateindex.TemplateIndexMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.qms.enums.ErrorCodeConstants.*;

/**
 * 检测模板-检测项 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TemplateIndexServiceImpl implements TemplateIndexService {

    @Resource
    private TemplateIndexMapper templateIndexMapper;

    @Override
    public int deleteByTemplateId(Long templateId) {
        return templateIndexMapper.deleteByTemplateId(templateId);
    }

    @Override
    public Long createTemplateIndex(TemplateIndexCreateReqVO createReqVO) {
        // 插入
        TemplateIndexDO templateIndex = TemplateIndexConvert.INSTANCE.convert(createReqVO);
        templateIndexMapper.insert(templateIndex);
        // 返回
        return templateIndex.getId();
    }

    @Override
    public void updateTemplateIndex(TemplateIndexUpdateReqVO updateReqVO) {
        // 校验存在
        validateTemplateIndexExists(updateReqVO.getId());
        // 更新
        TemplateIndexDO updateObj = TemplateIndexConvert.INSTANCE.convert(updateReqVO);
        templateIndexMapper.updateById(updateObj);
    }

    @Override
    public void deleteTemplateIndex(Long id) {
        // 校验存在
        validateTemplateIndexExists(id);
        // 删除
        templateIndexMapper.deleteById(id);
    }

    private void validateTemplateIndexExists(Long id) {
        if (templateIndexMapper.selectById(id) == null) {
            throw exception(TEMPLATE_INDEX_NOT_EXISTS);
        }
    }

    @Override
    public TemplateIndexDO getTemplateIndex(Long id) {
        return templateIndexMapper.selectById(id);
    }

    @Override
    public List<TemplateIndexDO> getTemplateIndexList(Collection<Long> ids) {
        return templateIndexMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TemplateIndexDO> getTemplateIndexPage(TemplateIndexPageReqVO pageReqVO) {
        return templateIndexMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TemplateIndexDO> getTemplateIndexList(TemplateIndexExportReqVO exportReqVO) {
        return templateIndexMapper.selectList(exportReqVO);
    }

    @Override
    public List<TemplateIndexDO> getTemplateIndexList(TemplateIndexListVO exportReqVO) {
        return templateIndexMapper.selectList(exportReqVO);
    }

}
