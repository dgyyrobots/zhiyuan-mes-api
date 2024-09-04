package com.dofast.module.wiki.service.lecturer;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wiki.controller.admin.lecturer.vo.*;
import com.dofast.module.wiki.dal.dataobject.lecturer.WikiLecturerDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wiki.convert.lecturer.WikiLecturerConvert;
import com.dofast.module.wiki.dal.mysql.lecturer.WikiLecturerMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wiki.enums.ErrorCodeConstants.*;

/**
 * 讲师的信息	 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class WikiLecturerServiceImpl implements WikiLecturerService {

    @Resource
    private WikiLecturerMapper lecturerMapper;

    @Override
    public Long createLecturer(WikiLecturerCreateReqVO createReqVO) {
        // 插入
        WikiLecturerDO lecturer = WikiLecturerConvert.INSTANCE.convert(createReqVO);
        lecturerMapper.insert(lecturer);
        // 返回
        return lecturer.getId();
    }

    @Override
    public void updateLecturer(WikiLecturerUpdateReqVO updateReqVO) {
        // 校验存在
        validateLecturerExists(updateReqVO.getId());
        // 更新
        WikiLecturerDO updateObj = WikiLecturerConvert.INSTANCE.convert(updateReqVO);
        lecturerMapper.updateById(updateObj);
    }

    @Override
    public void deleteLecturer(Long id) {
        // 校验存在
        validateLecturerExists(id);
        // 删除
        lecturerMapper.deleteById(id);
    }

    private void validateLecturerExists(Long id) {
        if (lecturerMapper.selectById(id) == null) {
            throw exception(LECTURER_NOT_EXISTS);
        }
    }

    @Override
    public WikiLecturerDO getLecturer(Long id) {
        return lecturerMapper.selectById(id);
    }

    @Override
    public List<WikiLecturerDO> getLecturerList(Collection<Long> ids) {
        return lecturerMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<WikiLecturerDO> getLecturerPage(WikiLecturerPageReqVO pageReqVO) {
        return lecturerMapper.selectPage(pageReqVO);
    }

    @Override
    public List<WikiLecturerDO> getLecturerList(WikiLecturerExportReqVO exportReqVO) {
        return lecturerMapper.selectList(exportReqVO);
    }

}
