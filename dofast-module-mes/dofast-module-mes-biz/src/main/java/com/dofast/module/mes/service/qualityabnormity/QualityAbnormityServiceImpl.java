package com.dofast.module.mes.service.qualityabnormity;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.qualityabnormity.vo.*;
import com.dofast.module.mes.dal.dataobject.qualityabnormity.QualityAbnormityDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.qualityabnormity.QualityAbnormityConvert;
import com.dofast.module.mes.dal.mysql.qualityabnormity.QualityAbnormityMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 品质异常信息 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class QualityAbnormityServiceImpl implements QualityAbnormityService {

    @Resource
    private QualityAbnormityMapper qualityAbnormityMapper;

    @Override
    public Long createQualityAbnormity(QualityAbnormityCreateReqVO createReqVO) {
        // 插入
        QualityAbnormityDO qualityAbnormity = QualityAbnormityConvert.INSTANCE.convert(createReqVO);
        qualityAbnormityMapper.insert(qualityAbnormity);
        // 返回
        return qualityAbnormity.getId();
    }

    @Override
    public void updateQualityAbnormity(QualityAbnormityUpdateReqVO updateReqVO) {
        // 校验存在
        validateQualityAbnormityExists(updateReqVO.getId());
        // 更新
        QualityAbnormityDO updateObj = QualityAbnormityConvert.INSTANCE.convert(updateReqVO);
        qualityAbnormityMapper.updateById(updateObj);
    }

    @Override
    public void deleteQualityAbnormity(Long id) {
        // 校验存在
        validateQualityAbnormityExists(id);
        // 删除
        qualityAbnormityMapper.deleteById(id);
    }

    private void validateQualityAbnormityExists(Long id) {
        if (qualityAbnormityMapper.selectById(id) == null) {
            throw exception(QUALITY_ABNORMITY_NOT_EXISTS);
        }
    }

    @Override
    public QualityAbnormityDO getQualityAbnormity(Long id) {
        return qualityAbnormityMapper.selectById(id);
    }

    @Override
    public List<QualityAbnormityDO> getQualityAbnormityList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return qualityAbnormityMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<QualityAbnormityDO> getQualityAbnormityPage(QualityAbnormityPageReqVO pageReqVO) {
        return qualityAbnormityMapper.selectPage(pageReqVO);
    }

    @Override
    public List<QualityAbnormityDO> getQualityAbnormityList(QualityAbnormityExportReqVO exportReqVO) {
        return qualityAbnormityMapper.selectList(exportReqVO);
    }

}
