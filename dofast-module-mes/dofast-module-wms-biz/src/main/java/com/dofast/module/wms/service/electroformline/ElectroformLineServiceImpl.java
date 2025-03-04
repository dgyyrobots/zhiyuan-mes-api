package com.dofast.module.wms.service.electroformline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.electroformline.vo.*;
import com.dofast.module.wms.dal.dataobject.electroformline.ElectroformLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.electroformline.ElectroformLineConvert;
import com.dofast.module.wms.dal.mysql.electroformline.ElectroformLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 制版房领料单身体 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ElectroformLineServiceImpl implements ElectroformLineService {

    @Resource
    private ElectroformLineMapper electroformLineMapper;

    @Override
    public Long createElectroformLine(ElectroformLineCreateReqVO createReqVO) {
        // 插入
        ElectroformLineDO electroformLine = ElectroformLineConvert.INSTANCE.convert(createReqVO);
        electroformLineMapper.insert(electroformLine);
        // 返回
        return electroformLine.getId();
    }

    @Override
    public void updateElectroformLine(ElectroformLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateElectroformLineExists(updateReqVO.getId());
        // 更新
        ElectroformLineDO updateObj = ElectroformLineConvert.INSTANCE.convert(updateReqVO);
        electroformLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteElectroformLine(Long id) {
        // 校验存在
        validateElectroformLineExists(id);
        // 删除
        electroformLineMapper.deleteById(id);
    }

    private void validateElectroformLineExists(Long id) {
        if (electroformLineMapper.selectById(id) == null) {
            throw exception(ELECTROFORM_LINE_NOT_EXISTS);
        }
    }

    @Override
    public ElectroformLineDO getElectroformLine(Long id) {
        return electroformLineMapper.selectById(id);
    }

    @Override
    public List<ElectroformLineDO> getElectroformLineList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return electroformLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ElectroformLineDO> getElectroformLinePage(ElectroformLinePageReqVO pageReqVO) {
        return electroformLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ElectroformLineDO> getElectroformLineList(ElectroformLineExportReqVO exportReqVO) {
        return electroformLineMapper.selectList(exportReqVO);
    }

}
