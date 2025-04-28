package com.dofast.module.wms.api.RtIssue;

import com.dofast.module.wms.api.Issueheader.dto.IssueLineDTO;
import com.dofast.module.wms.api.Issueheader.dto.IssueheaderDTO;
import com.dofast.module.wms.api.RtIssue.dto.RtIssueDTO;
import com.dofast.module.wms.api.RtIssue.dto.RtIssueLineDTO;

import java.util.List;

public interface RtIssueApi {

    List<RtIssueDTO> listRtIssueHeader(RtIssueDTO rtIssueDTO);

    List<RtIssueLineDTO> listRtIssueLine(RtIssueLineDTO rtIssueLineDTO);


}
