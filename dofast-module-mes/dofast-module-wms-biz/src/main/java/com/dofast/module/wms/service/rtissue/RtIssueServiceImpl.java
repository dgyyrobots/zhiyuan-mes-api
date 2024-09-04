package com.dofast.module.wms.service.rtissue;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueTxBean;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.rtissue.vo.*;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.rtissue.RtIssueConvert;
import com.dofast.module.wms.dal.mysql.rtissue.RtIssueMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 生产退料单头 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RtIssueServiceImpl implements RtIssueService {

    @Resource
    private RtIssueMapper rtIssueMapper;

    @Override
    public List<RtIssueTxBean> getTxBeans(Long rtId) {
        return rtIssueMapper.getTxBeans(rtId);
    }

    @Override
    public String checkUnique(RtIssueBaseVO baseVO) {
        RtIssueDO issue = rtIssueMapper.checkUnique(baseVO);
        Long rtId = baseVO.getId() == null? -1L: baseVO.getId();
        if(StrUtils.isNotNull(issue) && issue.getId().longValue() != rtId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createRtIssue(RtIssueCreateReqVO createReqVO) {
        // 插入
        RtIssueDO rtIssue = RtIssueConvert.INSTANCE.convert(createReqVO);
        rtIssueMapper.insert(rtIssue);
        // 返回
        return rtIssue.getId();
    }

    @Override
    public void updateRtIssue(RtIssueUpdateReqVO updateReqVO) {
        // 校验存在
        validateRtIssueExists(updateReqVO.getId());
        // 更新
        RtIssueDO updateObj = RtIssueConvert.INSTANCE.convert(updateReqVO);
        rtIssueMapper.updateById(updateObj);
    }

    @Override
    public void deleteRtIssue(Long id) {
        // 校验存在
        validateRtIssueExists(id);
        // 删除
        rtIssueMapper.deleteById(id);
    }

    private void validateRtIssueExists(Long id) {
        if (rtIssueMapper.selectById(id) == null) {
            throw exception(RT_ISSUE_NOT_EXISTS);
        }
    }

    @Override
    public RtIssueDO getRtIssue(Long id) {
        return rtIssueMapper.selectById(id);
    }

    @Override
    public List<RtIssueDO> getRtIssueList(Collection<Long> ids) {
        return rtIssueMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RtIssueDO> getRtIssuePage(RtIssuePageReqVO pageReqVO) {
        return rtIssueMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RtIssueDO> getRtIssueList(RtIssueExportReqVO exportReqVO) {
        return rtIssueMapper.selectList(exportReqVO);
    }

}
