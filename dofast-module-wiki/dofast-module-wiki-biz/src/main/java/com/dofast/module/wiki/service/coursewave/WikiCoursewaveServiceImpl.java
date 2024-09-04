package com.dofast.module.wiki.service.coursewave;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wiki.controller.admin.coursewave.vo.*;
import com.dofast.module.wiki.dal.dataobject.coursewave.WikiCoursewaveDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wiki.convert.coursewave.WikiCoursewaveConvert;
import com.dofast.module.wiki.dal.mysql.coursewave.WikiCoursewaveMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wiki.enums.ErrorCodeConstants.*;

/**
 * 首页知识列表的信息	 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class WikiCoursewaveServiceImpl implements WikiCoursewaveService {

    @Resource
    private WikiCoursewaveMapper coursewaveMapper;

    @Override
    public Long createCoursewave(WikiCoursewaveCreateReqVO createReqVO) {
        // 插入
        WikiCoursewaveDO coursewave = WikiCoursewaveConvert.INSTANCE.convert(createReqVO);
        coursewaveMapper.insert(coursewave);
        // 返回
        return coursewave.getId();
    }

    @Override
    public void updateCoursewave(WikiCoursewaveUpdateReqVO updateReqVO) {
        // 校验存在
        validateCoursewaveExists(updateReqVO.getId());
        // 更新
        WikiCoursewaveDO updateObj = WikiCoursewaveConvert.INSTANCE.convert(updateReqVO);
        coursewaveMapper.updateById(updateObj);
    }

    @Override
    public void deleteCoursewave(Long id) {
        // 校验存在
        validateCoursewaveExists(id);
        // 删除
        coursewaveMapper.deleteById(id);
    }

    private void validateCoursewaveExists(Long id) {
        if (coursewaveMapper.selectById(id) == null) {
            throw exception(COURSEWAVE_NOT_EXISTS);
        }
    }

    @Override
    public WikiCoursewaveDO getCoursewave(Long id) {
        return coursewaveMapper.selectById(id);
    }

    @Override
    public List<WikiCoursewaveDO> getCoursewaveList(Collection<Long> ids) {
        return coursewaveMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<WikiCoursewaveDO> getCoursewavePage(WikiCoursewavePageReqVO pageReqVO) {
        return coursewaveMapper.selectPage(pageReqVO);
    }

    @Override
    public List<WikiCoursewaveDO> getCoursewaveList(WikiCoursewaveExportReqVO exportReqVO) {
        return coursewaveMapper.selectList(exportReqVO);
    }

}
