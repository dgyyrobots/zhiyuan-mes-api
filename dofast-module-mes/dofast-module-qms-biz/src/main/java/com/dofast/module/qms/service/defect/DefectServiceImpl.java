package com.dofast.module.qms.service.defect;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.qms.controller.admin.defect.vo.*;
import com.dofast.module.qms.dal.dataobject.defect.DefectDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.qms.convert.defect.DefectConvert;
import com.dofast.module.qms.dal.mysql.defect.DefectMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.qms.enums.ErrorCodeConstants.*;

/**
 * 常见缺陷 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DefectServiceImpl implements DefectService {

    @Resource
    private DefectMapper defectMapper;

    @Override
    public Long createDefect(DefectCreateReqVO createReqVO) {
        // 插入
        DefectDO defect = DefectConvert.INSTANCE.convert(createReqVO);
        defectMapper.insert(defect);
        // 返回
        return defect.getId();
    }

    @Override
    public void updateDefect(DefectUpdateReqVO updateReqVO) {
        // 校验存在
        validateDefectExists(updateReqVO.getId());
        // 更新
        DefectDO updateObj = DefectConvert.INSTANCE.convert(updateReqVO);
        defectMapper.updateById(updateObj);
    }

    @Override
    public void deleteDefect(Long id) {
        // 校验存在
        validateDefectExists(id);
        // 删除
        defectMapper.deleteById(id);
    }

    private void validateDefectExists(Long id) {
        if (defectMapper.selectById(id) == null) {
            throw exception(DEFECT_NOT_EXISTS);
        }
    }

    @Override
    public DefectDO getDefect(Long id) {
        return defectMapper.selectById(id);
    }

    @Override
    public List<DefectDO> getDefectByCode(String processCode){
        return defectMapper.selectList(DefectDO::getProcessCode, processCode);
    }

    @Override
    public List<DefectDO> getDefectList(Collection<Long> ids) {
        return defectMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DefectDO> getDefectPage(DefectPageReqVO pageReqVO) {
        return defectMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DefectDO> getDefectList(DefectExportReqVO exportReqVO) {
        return defectMapper.selectList(exportReqVO);
    }

}
