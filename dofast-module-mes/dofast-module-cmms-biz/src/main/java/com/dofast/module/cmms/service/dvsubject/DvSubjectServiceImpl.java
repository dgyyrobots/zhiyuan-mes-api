package com.dofast.module.cmms.service.dvsubject;

import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.cmms.controller.admin.dvsubject.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvsubject.DvSubjectDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.cmms.convert.dvsubject.DvSubjectConvert;
import com.dofast.module.cmms.dal.mysql.dvsubject.DvSubjectMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.cmms.enums.ErrorCodeConstants.*;

/**
 * 设备点检保养项目 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DvSubjectServiceImpl implements DvSubjectService {

    @Resource
    private DvSubjectMapper dvSubjectMapper;

    @Override
    public String checkSubjectCodeUnique(DvSubjectBaseVO baseVO) {
        DvSubjectDO subjectDO = dvSubjectMapper.checkSubjectCodeUnique(baseVO.getSubjectCode());
        Long id = baseVO.getId()==null?-1L:baseVO.getId();
        if(null != subjectDO && subjectDO.getId().longValue()==id.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createDvSubject(DvSubjectCreateReqVO createReqVO) {
        // 插入
        DvSubjectDO dvSubject = DvSubjectConvert.INSTANCE.convert(createReqVO);
        dvSubjectMapper.insert(dvSubject);
        // 返回
        return dvSubject.getId();
    }

    @Override
    public void updateDvSubject(DvSubjectUpdateReqVO updateReqVO) {
        // 校验存在
        validateDvSubjectExists(updateReqVO.getId());
        // 更新
        DvSubjectDO updateObj = DvSubjectConvert.INSTANCE.convert(updateReqVO);
        dvSubjectMapper.updateById(updateObj);
    }

    @Override
    public void deleteDvSubject(Long id) {
        // 校验存在
        validateDvSubjectExists(id);
        // 删除
        dvSubjectMapper.deleteById(id);
    }

    private void validateDvSubjectExists(Long id) {
        if (dvSubjectMapper.selectById(id) == null) {
            throw exception(DV_SUBJECT_NOT_EXISTS);
        }
    }

    @Override
    public DvSubjectDO getDvSubject(Long id) {
        return dvSubjectMapper.selectById(id);
    }

    @Override
    public List<DvSubjectDO> getDvSubjectList(Collection<Long> ids) {
        return dvSubjectMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DvSubjectDO> getDvSubjectPage(DvSubjectPageReqVO pageReqVO) {
        return dvSubjectMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DvSubjectDO> getDvSubjectList(DvSubjectExportReqVO exportReqVO) {
        return dvSubjectMapper.selectList(exportReqVO);
    }

}
