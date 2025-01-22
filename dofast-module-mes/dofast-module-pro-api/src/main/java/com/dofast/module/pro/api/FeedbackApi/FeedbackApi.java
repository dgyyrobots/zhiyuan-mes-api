package com.dofast.module.pro.api.FeedbackApi;

import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;

public interface FeedbackApi {

    FeedbackDTO getFeedBack(Long id);

    FeedbackDTO getFeedBackByTaskCode(String taskCode);
}
