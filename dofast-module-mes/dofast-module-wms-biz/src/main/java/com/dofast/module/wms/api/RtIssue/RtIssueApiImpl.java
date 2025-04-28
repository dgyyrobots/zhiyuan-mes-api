package com.dofast.module.wms.api.RtIssue;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.wms.api.Issueheader.dto.IssueLineDTO;
import com.dofast.module.wms.api.Issueheader.dto.IssueheaderDTO;
import com.dofast.module.wms.api.RtIssue.dto.RtIssueDTO;
import com.dofast.module.wms.api.RtIssue.dto.RtIssueLineDTO;
import com.dofast.module.wms.controller.admin.issueheader.vo.IssueHeaderExportReqVO;
import com.dofast.module.wms.controller.admin.rtissue.vo.RtIssueExportReqVO;
import com.dofast.module.wms.controller.admin.rtissueline.vo.RtIssueLineExportReqVO;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueHeaderDO;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueDO;
import com.dofast.module.wms.dal.dataobject.rtissueline.RtIssueLineDO;
import com.dofast.module.wms.service.rtissue.RtIssueService;
import com.dofast.module.wms.service.rtissueline.RtIssueLineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RtIssueApiImpl implements RtIssueApi{

    @Resource
    private RtIssueService rtIssueService;

    @Resource
    private RtIssueLineService rtIssueLineService;

    @Override
    public List<RtIssueDTO> listRtIssueHeader(RtIssueDTO rtIssueDTO) {
        RtIssueExportReqVO req = new RtIssueExportReqVO();
        BeanUtil.copyProperties(rtIssueDTO, req);
        List<RtIssueDO> rtIssueHeaderList = rtIssueService.getRtIssueList(req);
        List<RtIssueDTO> finList = new ArrayList<>();
        for (RtIssueDO rtissueHeaderDO : rtIssueHeaderList) {
            RtIssueDTO addDTO = new RtIssueDTO();
            BeanUtil.copyProperties(rtissueHeaderDO, addDTO);
            finList.add(addDTO);
        }
        return finList;
    }

    @Override
    public List<RtIssueLineDTO> listRtIssueLine(RtIssueLineDTO rtIssueLineDTO) {
        RtIssueLineExportReqVO req = new RtIssueLineExportReqVO();
        BeanUtil.copyProperties(rtIssueLineDTO, req);
        List<RtIssueLineDO> rtIssueHeaderList = rtIssueLineService.getRtIssueLineList(req);
        List<RtIssueLineDTO> finList = new ArrayList<>();
        for (RtIssueLineDO rtissueLineDO : rtIssueHeaderList) {
            RtIssueLineDTO addDTO = new RtIssueLineDTO();
            BeanUtil.copyProperties(rtissueLineDO, addDTO);
            finList.add(addDTO);
        }
        return finList;
    }

}
