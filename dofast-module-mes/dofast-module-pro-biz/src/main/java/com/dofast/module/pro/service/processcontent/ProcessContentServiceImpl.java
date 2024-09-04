package com.dofast.module.pro.service.processcontent;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.pro.controller.admin.processcontent.vo.*;
import com.dofast.module.pro.dal.dataobject.processcontent.ProcessContentDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.processcontent.ProcessContentConvert;
import com.dofast.module.pro.dal.mysql.processcontent.ProcessContentMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;

/**
 * 生产工序内容 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProcessContentServiceImpl implements ProcessContentService {

    @Resource
    private ProcessContentMapper cessContentMapper;

    @Override
    public List<ProcessContentDO> selectList(ProcessContentListVO listVO) {
        return cessContentMapper.selectList(listVO);
    }

    @Override
    public Long createcessContent(ProcessContentCreateReqVO createReqVO) {
        // 插入
        ProcessContentDO cessContent = ProcessContentConvert.INSTANCE.convert(createReqVO);
        cessContentMapper.insert(cessContent);
        // 返回
        return cessContent.getId();
    }

    @Override
    public void updatecessContent(ProcessContentUpdateReqVO updateReqVO) {
        // 校验存在
        validatecessContentExists(updateReqVO.getId());
        // 更新
        ProcessContentDO updateObj = ProcessContentConvert.INSTANCE.convert(updateReqVO);
        cessContentMapper.updateById(updateObj);
    }

    @Override
    public void deletecessContent(Long id) {
        // 校验存在
        validatecessContentExists(id);
        // 删除
        cessContentMapper.deleteById(id);
    }

    private void validatecessContentExists(Long id) {
        if (cessContentMapper.selectById(id) == null) {
            throw exception(CESS_CONTENT_NOT_EXISTS);
        }
    }

    @Override
    public ProcessContentDO getcessContent(Long id) {
        return cessContentMapper.selectById(id);
    }

    @Override
    public List<ProcessContentDO> getcessContentList(Collection<Long> ids) {
        return cessContentMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProcessContentDO> getcessContentPage(ProcessContentPageReqVO pageReqVO) {
        return cessContentMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProcessContentDO> getcessContentList(ProcessContentExportReqVO exportReqVO) {
        return cessContentMapper.selectList(exportReqVO);
    }

}
