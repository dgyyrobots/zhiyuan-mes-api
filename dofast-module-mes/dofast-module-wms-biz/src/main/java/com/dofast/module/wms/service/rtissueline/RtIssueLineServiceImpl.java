package com.dofast.module.wms.service.rtissueline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.rtissueline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtissueline.RtIssueLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.rtissueline.RtIssueLineConvert;
import com.dofast.module.wms.dal.mysql.rtissueline.RtIssueLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 生产退料单行 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RtIssueLineServiceImpl implements RtIssueLineService {

    @Resource
    private RtIssueLineMapper rtIssueLineMapper;

    @Override
    public List<RtIssueLineDO> selectList(RtIssueLineListVO lineListVO) {
        return rtIssueLineMapper.selectList(lineListVO);
    }

    @Override
    public int deleteByRtId(Long rtId) {
        return rtIssueLineMapper.deleteByRtId(rtId);
    }

    @Override
    public Long createRtIssueLine(RtIssueLineCreateReqVO createReqVO) {
        // 插入
        RtIssueLineDO rtIssueLine = RtIssueLineConvert.INSTANCE.convert(createReqVO);
        rtIssueLineMapper.insert(rtIssueLine);
        // 返回
        return rtIssueLine.getId();
    }

    @Override
    public void updateRtIssueLine(RtIssueLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateRtIssueLineExists(updateReqVO.getId());
        // 更新
        RtIssueLineDO updateObj = RtIssueLineConvert.INSTANCE.convert(updateReqVO);
        rtIssueLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteRtIssueLine(Long id) {
        // 校验存在
        validateRtIssueLineExists(id);
        // 删除
        rtIssueLineMapper.deleteById(id);
    }

    private void validateRtIssueLineExists(Long id) {
        if (rtIssueLineMapper.selectById(id) == null) {
            throw exception(RT_ISSUE_LINE_NOT_EXISTS);
        }
    }

    @Override
    public RtIssueLineDO getRtIssueLine(Long id) {
        return rtIssueLineMapper.selectById(id);
    }

    @Override
    public List<RtIssueLineDO> getRtIssueLineList(Collection<Long> ids) {
        return rtIssueLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RtIssueLineDO> getRtIssueLinePage(RtIssueLinePageReqVO pageReqVO) {
        return rtIssueLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RtIssueLineDO> getRtIssueLineList(RtIssueLineExportReqVO exportReqVO) {
        return rtIssueLineMapper.selectList(exportReqVO);
    }

    @Override
    public void updateRtIssueLineBatch(List<RtIssueLineDO> list){
        rtIssueLineMapper.updateBatch(list);
    }

    @Override
    public void insertRtIssueLineBatch(List<RtIssueLineDO> list){
        rtIssueLineMapper.insertBatch(list);
    }


}
