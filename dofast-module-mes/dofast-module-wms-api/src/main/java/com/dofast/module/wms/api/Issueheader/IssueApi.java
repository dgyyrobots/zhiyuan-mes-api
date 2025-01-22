package com.dofast.module.wms.api.Issueheader;

import com.dofast.module.wms.api.Issueheader.dto.IssueLineDTO;
import com.dofast.module.wms.api.Issueheader.dto.IssueheaderDTO;

import java.util.List;

public interface IssueApi {

    List<IssueheaderDTO> listIssueHeader(IssueheaderDTO issueheaderDTO);

    List<IssueLineDTO> listIssueLine(IssueLineDTO issueLineDTO);

}
