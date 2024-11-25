package com.dofast.module.pro.api.FeedbackApi;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;
import com.dofast.module.pro.api.RouteApi.RouteDTO;
import com.dofast.module.pro.dal.dataobject.feedback.FeedbackDO;
import com.dofast.module.pro.service.feedback.FeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FeedbackApiImpl implements FeedbackApi{

    @Resource
    private FeedbackService feedbackService;

    @Override
    public FeedbackDTO getFeedBack(Long id) {
        FeedbackDO feedbackDO = feedbackService.getFeedback(id);
        FeedbackDTO feedbackDTO = BeanUtil.toBean(feedbackDO, FeedbackDTO.class);
        return feedbackDTO;
    }

}
