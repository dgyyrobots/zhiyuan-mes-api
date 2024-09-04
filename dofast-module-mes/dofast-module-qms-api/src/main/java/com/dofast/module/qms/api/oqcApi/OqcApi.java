package com.dofast.module.qms.api.oqcApi;

import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;
import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;
import com.dofast.module.qms.api.oqcApi.dto.OqcDTO;

public interface OqcApi {
    OqcDTO generateOqc(FeedbackDTO feedback, WorkorderDTO workorderDTO);
}
