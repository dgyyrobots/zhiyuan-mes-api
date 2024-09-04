package com.dofast.module.qms.service.defectrecord;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import com.dofast.module.qms.controller.admin.defectrecord.vo.*;
import com.dofast.module.qms.dal.dataobject.defectrecord.DefectRecordDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.qms.convert.defectrecord.DefectRecordConvert;
import com.dofast.module.qms.dal.mysql.defectrecord.DefectRecordMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.qms.enums.ErrorCodeConstants.*;

/**
 * 检验单缺陷记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DefectRecordServiceImpl implements DefectRecordService {

    @Resource
    private DefectRecordMapper defectRecordMapper;

    @Override
    public int deleteByQcIdAndType(DefectRecordBaseVO qcDefectRecord) {
        return defectRecordMapper.deleteByQcIdAndType(qcDefectRecord);
    }

    @Override
    public Long createDefectRecord(DefectRecordCreateReqVO createReqVO) {
        // 插入
        DefectRecordDO defectRecord = DefectRecordConvert.INSTANCE.convert(createReqVO);
        defectRecordMapper.insert(defectRecord);
        // 返回
        return defectRecord.getId();
    }

    @Override
    public void updateDefectRecord(DefectRecordUpdateReqVO updateReqVO) {
        // 校验存在
        validateDefectRecordExists(updateReqVO.getId());
        // 更新
        DefectRecordDO updateObj = DefectRecordConvert.INSTANCE.convert(updateReqVO);
        defectRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteDefectRecord(Long id) {
        // 校验存在
        validateDefectRecordExists(id);
        // 删除
        defectRecordMapper.deleteById(id);
    }

    private void validateDefectRecordExists(Long id) {
        if (defectRecordMapper.selectById(id) == null) {
            throw exception(DEFECT_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public DefectRecordDO getDefectRecord(Long id) {
        return defectRecordMapper.selectById(id);
    }

    @Override
    public List<DefectRecordDO> getDefectRecordList(Collection<Long> ids) {
        return defectRecordMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DefectRecordDO> getDefectRecordPage(DefectRecordPageReqVO pageReqVO) {
        return defectRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DefectRecordDO> getDefectRecordList(DefectRecordExportReqVO exportReqVO) {
        return defectRecordMapper.selectList(exportReqVO);
    }

    @Override
    public void updateAllDefectRecord(List<DefectRecordUpdateReqVO> updateReqVO) {
        List<Long> ids = updateReqVO.stream().map(v -> v.getId()).collect(Collectors.toList());

        if (ids.size()>0) {
            // 校验存在
            validateAllDefectRecordExists(ids);

            // 更新
            List<DefectRecordDO> defectRecordDOS = DefectRecordConvert.INSTANCE.coverList(updateReqVO);
            defectRecordMapper.updateBatch(defectRecordDOS);
        }
    }

    @Override
    public void updateOrCreateDefectRecord(List<DefectRecordUpdateReqVO> updateReqVOS) {
        //批量新增
        List<DefectRecordUpdateReqVO> collectAdd = updateReqVOS.stream().filter(v -> v.getId() == null).collect(Collectors.toList());
        defectRecordMapper.insertBatch(DefectRecordConvert.INSTANCE.coverList(collectAdd),collectAdd.size());
        //批量修改
        List<DefectRecordUpdateReqVO> collectUpdate = updateReqVOS.stream().filter(v -> v.getId() != null).collect(Collectors.toList());
        updateAllDefectRecord(collectUpdate);
    }

    private void validateAllDefectRecordExists(List<Long> ids) {

        if (defectRecordMapper.selectBatchIds(ids) == null) {
            throw exception(DEFECT_RECORD_NOT_EXISTS);
        }
    }

}
