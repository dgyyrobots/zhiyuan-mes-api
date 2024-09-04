package com.dofast.module.wms.service.rtsalseline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.rtsalseline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtsalseline.RtSalseLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.rtsalseline.RtSalseLineConvert;
import com.dofast.module.wms.dal.mysql.rtsalseline.RtSalseLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 产品销售退货行 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RtSalseLineServiceImpl implements RtSalseLineService {

    @Resource
    private RtSalseLineMapper rtSalseLineMapper;

    @Override
    public List<RtSalseLineDO> selectWmRtSalseLineList(RtSalseLineListVO lineListVO) {
        return rtSalseLineMapper.selectList(lineListVO);
    }

    @Override
    public int deleteByRtId(Long rtId) {
        return rtSalseLineMapper.deleteByRtId(rtId);
    }

    @Override
    public Long createRtSalseLine(RtSalseLineCreateReqVO createReqVO) {
        // 插入
        RtSalseLineDO rtSalseLine = RtSalseLineConvert.INSTANCE.convert(createReqVO);
        rtSalseLineMapper.insert(rtSalseLine);
        // 返回
        return rtSalseLine.getId();
    }

    @Override
    public void updateRtSalseLine(RtSalseLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateRtSalseLineExists(updateReqVO.getId());
        // 更新
        RtSalseLineDO updateObj = RtSalseLineConvert.INSTANCE.convert(updateReqVO);
        rtSalseLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteRtSalseLine(Long id) {
        // 校验存在
        validateRtSalseLineExists(id);
        // 删除
        rtSalseLineMapper.deleteById(id);
    }

    private void validateRtSalseLineExists(Long id) {
        if (rtSalseLineMapper.selectById(id) == null) {
            throw exception(RT_SALSE_LINE_NOT_EXISTS);
        }
    }

    @Override
    public RtSalseLineDO getRtSalseLine(Long id) {
        return rtSalseLineMapper.selectById(id);
    }

    @Override
    public List<RtSalseLineDO> getRtSalseLineList(Collection<Long> ids) {
        return rtSalseLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RtSalseLineDO> getRtSalseLinePage(RtSalseLinePageReqVO pageReqVO) {
        return rtSalseLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RtSalseLineDO> getRtSalseLineList(RtSalseLineExportReqVO exportReqVO) {
        return rtSalseLineMapper.selectList(exportReqVO);
    }

}
