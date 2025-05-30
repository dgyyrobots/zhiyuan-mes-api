package com.dofast.module.promotion.api.combination;

import com.dofast.module.promotion.api.combination.dto.CombinationRecordCreateReqDTO;


import com.dofast.module.promotion.api.combination.dto.CombinationValidateJoinRespDTO;
import com.dofast.module.promotion.api.combination.dto.CombinationRecordCreateRespDTO;
import com.dofast.module.promotion.api.combination.dto.CombinationValidateJoinRespDTO;
import com.dofast.module.promotion.convert.combination.CombinationActivityConvert;
import com.dofast.module.promotion.dal.dataobject.combination.CombinationRecordDO;


import com.dofast.module.promotion.api.combination.dto.CombinationValidateJoinRespDTO;

import com.dofast.module.promotion.enums.combination.CombinationRecordStatusEnum;
import com.dofast.module.promotion.service.combination.CombinationRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


import java.time.LocalDateTime;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.promotion.enums.ErrorCodeConstants.COMBINATION_RECORD_NOT_EXISTS;



import java.time.LocalDateTime;


/**
 * 拼团活动 API 实现类
 *
 * @author HUIHUI
 */
@Service
public class CombinationRecordApiImpl implements CombinationRecordApi {

    @Resource
    private CombinationRecordService recordService;

    @Override
    public void validateCombinationRecord(Long activityId, Long userId, Long skuId, Integer count) {
        recordService.validateCombinationRecord(activityId, userId, skuId, count);
    }

    @Override
    public void validateCombinationRecord(Long userId, Long activityId, Long headId, Long skuId, Integer count) {
        recordService.validateCombinationRecord(userId, activityId, headId, skuId, count);
    }

    @Override
    public void createCombinationRecord(CombinationRecordCreateReqDTO reqDTO) {
        recordService.createCombinationRecord(reqDTO);
    }

    @Override
    public CombinationRecordCreateRespDTO createCombinationRecord1(CombinationRecordCreateReqDTO reqDTO) {
        return CombinationActivityConvert.INSTANCE.convert4(recordService.createCombinationRecord1(reqDTO));
    }

    @Override
    public boolean isCombinationRecordSuccess(Long userId, Long orderId) {
        return CombinationRecordStatusEnum.isSuccess(recordService.getCombinationRecord(userId, orderId).getStatus());
    }

    @Override
    public void updateRecordStatusToSuccess(Long userId, Long orderId) {
        recordService.updateCombinationRecordStatusByUserIdAndOrderId(CombinationRecordStatusEnum.SUCCESS.getStatus(), userId, orderId);
    }

    @Override
    public void updateRecordStatusToFailed(Long userId, Long orderId) {
        recordService.updateCombinationRecordStatusByUserIdAndOrderId(CombinationRecordStatusEnum.FAILED.getStatus(), userId, orderId);
    }

    @Override
    public void updateRecordStatusToInProgress(Long userId, Long orderId, LocalDateTime startTime) {
        recordService.updateRecordStatusAndStartTimeByUserIdAndOrderId(CombinationRecordStatusEnum.IN_PROGRESS.getStatus(),
                userId, orderId, startTime);
    }

    @Override
    public CombinationValidateJoinRespDTO validateJoinCombination(Long activityId, Long userId, Long skuId, Integer count) {
        return recordService.validateJoinCombination(activityId, userId, skuId, count);
    }

    @Override
    public CombinationValidateJoinRespDTO validateJoinCombination(Long userId, Long activityId, Long headId, Long skuId, Integer count) {
        return recordService.validateJoinCombination(userId, activityId, headId, skuId, count);
    }


}
