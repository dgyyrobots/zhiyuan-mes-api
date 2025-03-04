package com.dofast.module.wms.service.electroformheader;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.electroformheader.vo.*;
import com.dofast.module.wms.dal.dataobject.electroformheader.ElectroformHeaderDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.electroformheader.ElectroformHeaderConvert;
import com.dofast.module.wms.dal.mysql.electroformheader.ElectroformHeaderMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 制版房领料单头 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ElectroformHeaderServiceImpl implements ElectroformHeaderService {

    @Resource
    private ElectroformHeaderMapper electroformHeaderMapper;

    @Override
    public Long createElectroformHeader(ElectroformHeaderCreateReqVO createReqVO) {
        // 插入
        ElectroformHeaderDO electroformHeader = ElectroformHeaderConvert.INSTANCE.convert(createReqVO);
        electroformHeaderMapper.insert(electroformHeader);
        // 返回
        return electroformHeader.getId();
    }

    @Override
    public void updateElectroformHeader(ElectroformHeaderUpdateReqVO updateReqVO) {
        // 校验存在
        validateElectroformHeaderExists(updateReqVO.getId());
        // 更新
        ElectroformHeaderDO updateObj = ElectroformHeaderConvert.INSTANCE.convert(updateReqVO);
        electroformHeaderMapper.updateById(updateObj);
    }

    @Override
    public void deleteElectroformHeader(Long id) {
        // 校验存在
        validateElectroformHeaderExists(id);
        // 删除
        electroformHeaderMapper.deleteById(id);
    }

    private void validateElectroformHeaderExists(Long id) {
        if (electroformHeaderMapper.selectById(id) == null) {
            throw exception(ELECTROFORM_HEADER_NOT_EXISTS);
        }
    }

    @Override
    public ElectroformHeaderDO getElectroformHeader(Long id) {
        return electroformHeaderMapper.selectById(id);
    }

    @Override
    public List<ElectroformHeaderDO> getElectroformHeaderList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return electroformHeaderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ElectroformHeaderDO> getElectroformHeaderPage(ElectroformHeaderPageReqVO pageReqVO) {
        return electroformHeaderMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ElectroformHeaderDO> getElectroformHeaderList(ElectroformHeaderExportReqVO exportReqVO) {
        return electroformHeaderMapper.selectList(exportReqVO);
    }

}
