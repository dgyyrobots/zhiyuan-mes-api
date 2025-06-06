package com.dofast.module.promotion.api.combination;

import com.dofast.module.promotion.api.combination.dto.CombinationRecordCreateReqDTO;
import com.dofast.module.promotion.api.combination.dto.CombinationValidateJoinRespDTO;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;

// TODO @芋艿：后面也再撸撸这几个接口

import com.dofast.module.promotion.api.combination.dto.CombinationRecordCreateRespDTO;
import com.dofast.module.promotion.api.combination.dto.CombinationValidateJoinRespDTO;
import com.dofast.module.promotion.enums.combination.CombinationRecordStatusEnum;

import javax.validation.Valid;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.promotion.enums.ErrorCodeConstants.COMBINATION_RECORD_NOT_EXISTS;


/**
 * 拼团记录 API 接口
 *
 * @author HUIHUI
 */
public interface CombinationRecordApi {

    /**
     * 校验是否满足拼团条件
     *
     * @param activityId 活动编号
     * @param userId     用户编号
     * @param skuId      sku 编号
     * @param count      数量
     */
    void validateCombinationRecord(Long activityId, Long userId, Long skuId, Integer count);


    /**
     * 校验是否满足拼团条件
     *
     * @param userId     用户编号
     * @param activityId 活动编号
     * @param headId     团长编号
     * @param skuId      sku 编号
     * @param count      数量
     */
    void validateCombinationRecord(Long userId, Long activityId, Long headId, Long skuId, Integer count);

    /**
     * 创建开团记录
     *
     * @param reqDTO 请求 DTO
     */
    void createCombinationRecord(@Valid CombinationRecordCreateReqDTO reqDTO);

    /**
     * 创建开团记录
     *
     * @param reqDTO 请求 DTO
     * @return 拼团信息
     */
    CombinationRecordCreateRespDTO createCombinationRecord1(@Valid CombinationRecordCreateReqDTO reqDTO);




    /**
     * 查询拼团记录是否成功
     *
     * @param userId  用户编号
     * @param orderId 订单编号
     * @return 拼团是否成功
     */
    boolean isCombinationRecordSuccess(Long userId, Long orderId);

    /**
     * 更新拼团状态为【成功】
     *
     * @param userId  用户编号
     * @param orderId 订单编号
     */
    void updateRecordStatusToSuccess(Long userId, Long orderId);

    /**
     * 更新拼团状态为【失败】
     *
     * @param userId  用户编号
     * @param orderId 订单编号
     */
    void updateRecordStatusToFailed(Long userId, Long orderId);

    /**
     * 更新拼团状态为 进行中
     *
     * @param userId    用户编号
     * @param orderId   订单编号
     * @param startTime 开始时间
     */
    void updateRecordStatusToInProgress(Long userId, Long orderId, LocalDateTime startTime);

    /**
     * 【下单前】校验是否满足拼团活动条件
     *
     * 如果校验失败，则抛出业务异常
     *
     * @param activityId 活动编号
     * @param userId     用户编号
     * @param skuId      sku 编号
     * @param count      数量
     * @return 拼团信息
     */
    // TODO @puhui：userId 放最前面；然后应该还有个 headId 参数；
    CombinationValidateJoinRespDTO validateJoinCombination(Long activityId, Long userId, Long skuId, Integer count);


    /**
     * 【下单前】校验是否满足拼团活动条件
     *
     * 如果校验失败，则抛出业务异常
     *
     * @param userId     用户编号
     * @param activityId 活动编号
     * @param headId     团长编号
     * @param skuId      sku 编号
     * @param count      数量
     * @return 拼团信息
     */
    CombinationValidateJoinRespDTO validateJoinCombination(Long userId, Long activityId, Long headId,
                                                           Long skuId, Integer count);

}
