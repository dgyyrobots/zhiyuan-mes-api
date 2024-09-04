package com.dofast.module.mes.service.mdunitmeasure;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.mdunitmeasure.vo.*;
import com.dofast.module.mes.dal.dataobject.mdunitmeasure.MdUnitMeasureDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.mdunitmeasure.MdUnitMeasureConvert;
import com.dofast.module.mes.dal.mysql.mdunitmeasure.MdUnitMeasureMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 单位 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MdUnitMeasureServiceImpl implements MdUnitMeasureService {

    @Resource
    private MdUnitMeasureMapper mdUnitMeasureMapper;

    @Override
    public Long createMdUnitMeasure(MdUnitMeasureCreateReqVO createReqVO) {
        // 插入
        MdUnitMeasureDO mdUnitMeasure = MdUnitMeasureConvert.INSTANCE.convert(createReqVO);
        mdUnitMeasureMapper.insert(mdUnitMeasure);
        // 返回
        return mdUnitMeasure.getId();
    }

    @Override
    public void updateMdUnitMeasure(MdUnitMeasureUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdUnitMeasureExists(updateReqVO.getId());
        // 更新
        MdUnitMeasureDO updateObj = MdUnitMeasureConvert.INSTANCE.convert(updateReqVO);
        mdUnitMeasureMapper.updateById(updateObj);
    }

    @Override
    public void deleteMdUnitMeasure(Long id) {
        // 校验存在
        validateMdUnitMeasureExists(id);
        // 删除
        mdUnitMeasureMapper.deleteById(id);
    }

    private void validateMdUnitMeasureExists(Long id) {
        if (mdUnitMeasureMapper.selectById(id) == null) {
            throw exception(MD_UNIT_MEASURE_NOT_EXISTS);
        }
    }

    @Override
    public MdUnitMeasureDO getMdUnitMeasure(Long id) {
        return mdUnitMeasureMapper.selectById(id);
    }

    @Override
    public List<MdUnitMeasureDO> getMdUnitMeasureList(Collection<Long> ids) {
        return mdUnitMeasureMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MdUnitMeasureDO> getMdUnitMeasurePage(MdUnitMeasurePageReqVO pageReqVO) {
        return mdUnitMeasureMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdUnitMeasureDO> getMdUnitMeasureList(MdUnitMeasureExportReqVO exportReqVO) {
        return mdUnitMeasureMapper.selectList(exportReqVO);
    }

    @Override
    public List<MdUnitMeasureDO> getMdUnitMeasureList(MdUnitMeasureListVO exportReqVO) {
        return mdUnitMeasureMapper.selectList(exportReqVO);
    }

}
