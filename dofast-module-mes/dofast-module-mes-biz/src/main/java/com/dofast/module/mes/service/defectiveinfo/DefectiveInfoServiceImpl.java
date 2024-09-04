package com.dofast.module.mes.service.defectiveinfo;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.defectiveinfo.vo.*;
import com.dofast.module.mes.dal.dataobject.defectiveinfo.DefectiveInfoDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.defectiveinfo.DefectiveInfoConvert;
import com.dofast.module.mes.dal.mysql.defectiveinfo.DefectiveInfoMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 不良品信息管理 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class DefectiveInfoServiceImpl implements DefectiveInfoService {

    @Resource
    private DefectiveInfoMapper defectiveInfoMapper;

    @Override
    public Long createDefectiveInfo(DefectiveInfoCreateReqVO createReqVO) {
        // 插入
        DefectiveInfoDO defectiveInfo = DefectiveInfoConvert.INSTANCE.convert(createReqVO);
        defectiveInfoMapper.insert(defectiveInfo);
        // 返回
        return defectiveInfo.getId();
    }

    @Override
    public void updateDefectiveInfo(DefectiveInfoUpdateReqVO updateReqVO) {
        // 校验存在
        validateDefectiveInfoExists(updateReqVO.getId());
        // 更新
        DefectiveInfoDO updateObj = DefectiveInfoConvert.INSTANCE.convert(updateReqVO);
        defectiveInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteDefectiveInfo(Long id) {
        // 校验存在
        validateDefectiveInfoExists(id);
        // 删除
        defectiveInfoMapper.deleteById(id);
    }

    private void validateDefectiveInfoExists(Long id) {
        if (defectiveInfoMapper.selectById(id) == null) {
            throw exception(DEFECTIVE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public DefectiveInfoDO getDefectiveInfo(Long id) {
        return defectiveInfoMapper.selectById(id);
    }

    @Override
    public List<DefectiveInfoDO> getDefectiveInfoList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return defectiveInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DefectiveInfoDO> getDefectiveInfoPage(DefectiveInfoPageReqVO pageReqVO) {
        return defectiveInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DefectiveInfoDO> getDefectiveInfoList(DefectiveInfoExportReqVO exportReqVO) {
        return defectiveInfoMapper.selectList(exportReqVO);
    }

}
