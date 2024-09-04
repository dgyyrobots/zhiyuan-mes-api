package com.dofast.module.cmms.service.dvchecksubject;

import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.cmms.controller.admin.dvchecksubject.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvchecksubject.DvCheckSubjectDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.cmms.convert.dvchecksubject.DvCheckSubjectConvert;
import com.dofast.module.cmms.dal.mysql.dvchecksubject.DvCheckSubjectMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.cmms.enums.ErrorCodeConstants.*;

/**
 * 点检项目 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DvCheckSubjectServiceImpl implements DvCheckSubjectService {

    @Resource
    private DvCheckSubjectMapper dvCheckSubjectMapper;

    @Override
    public String checkSubjectUnique(DvCheckSubjectBaseVO dvCheckSubject) {
        DvCheckSubjectDO subject = dvCheckSubjectMapper.checkSubjectUnique(dvCheckSubject);
        Long recordId = dvCheckSubject.getId()==null?-1L:dvCheckSubject.getId();
        if(null != (subject) && subject.getId().longValue() != recordId.longValue()){
            return Constant.NOT_UNIQUE;
        }

        return Constant.UNIQUE;
    }

    @Override
    public Long createDvCheckSubject(DvCheckSubjectCreateReqVO createReqVO) {
        // 插入
        DvCheckSubjectDO dvCheckSubject = DvCheckSubjectConvert.INSTANCE.convert(createReqVO);
        dvCheckSubjectMapper.insert(dvCheckSubject);
        // 返回
        return dvCheckSubject.getId();
    }

    @Override
    public void updateDvCheckSubject(DvCheckSubjectUpdateReqVO updateReqVO) {
        // 校验存在
        validateDvCheckSubjectExists(updateReqVO.getId());
        // 更新
        DvCheckSubjectDO updateObj = DvCheckSubjectConvert.INSTANCE.convert(updateReqVO);
        dvCheckSubjectMapper.updateById(updateObj);
    }

    @Override
    public void deleteDvCheckSubject(Long id) {
        // 校验存在
        validateDvCheckSubjectExists(id);
        // 删除
        dvCheckSubjectMapper.deleteById(id);
    }

    private void validateDvCheckSubjectExists(Long id) {
        if (dvCheckSubjectMapper.selectById(id) == null) {
            throw exception(DV_CHECK_SUBJECT_NOT_EXISTS);
        }
    }

    @Override
    public DvCheckSubjectDO getDvCheckSubject(Long id) {
        return dvCheckSubjectMapper.selectById(id);
    }

    @Override
    public List<DvCheckSubjectDO> getDvCheckSubjectList(Collection<Long> ids) {
        return dvCheckSubjectMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DvCheckSubjectDO> getDvCheckSubjectPage(DvCheckSubjectPageReqVO pageReqVO) {
        return dvCheckSubjectMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DvCheckSubjectDO> getDvCheckSubjectList(DvCheckSubjectExportReqVO exportReqVO) {
        return dvCheckSubjectMapper.selectList(exportReqVO);
    }

}
