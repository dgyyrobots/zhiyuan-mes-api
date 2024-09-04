package com.dofast.module.promotion.service.combination;

import cn.hutool.core.collection.CollUtil;




import cn.hutool.core.util.ObjectUtil;
import com.dofast.framework.common.core.KeyValue;
import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.pojo.PageResult;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.dofast.framework.common.core.KeyValue;
import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.json.JsonUtils;


import com.dofast.framework.common.util.date.LocalDateTimeUtils;

import com.dofast.module.member.api.user.MemberUserApi;
import com.dofast.module.member.api.user.dto.MemberUserRespDTO;
import com.dofast.module.product.api.sku.ProductSkuApi;
import com.dofast.module.product.api.sku.dto.ProductSkuRespDTO;
import com.dofast.module.product.api.spu.ProductSpuApi;
import com.dofast.module.product.api.spu.dto.ProductSpuRespDTO;
import com.dofast.module.promotion.api.combination.dto.CombinationRecordCreateReqDTO;
import com.dofast.module.promotion.api.combination.dto.CombinationRecordCreateRespDTO;
import com.dofast.module.promotion.api.combination.dto.CombinationValidateJoinRespDTO;
import com.dofast.module.promotion.controller.admin.combination.vo.recrod.CombinationRecordReqPageVO;
import com.dofast.module.promotion.convert.combination.CombinationActivityConvert;
import com.dofast.module.promotion.dal.dataobject.combination.CombinationActivityDO;
import com.dofast.module.promotion.dal.dataobject.combination.CombinationProductDO;
import com.dofast.module.promotion.dal.dataobject.combination.CombinationRecordDO;
import com.dofast.module.promotion.dal.mysql.combination.CombinationRecordMapper;
import com.dofast.module.promotion.enums.combination.CombinationRecordStatusEnum;
import com.dofast.module.trade.api.order.TradeOrderApi;

import lombok.extern.slf4j.Slf4j;


import com.dofast.module.trade.enums.order.TradeOrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.time.LocalDateTime;




import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.util.collection.CollectionUtils.*;

import java.util.*;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.util.collection.CollectionUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.afterNow;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.beforeNow;



import static com.dofast.module.promotion.enums.ErrorCodeConstants.*;

// TODO 芋艿：等拼团记录做完，完整 review 下

/**
 * 拼团记录 Service 实现类
 *
 * @author HUIHUI
 */
@Service

@Slf4j


@Validated
public class CombinationRecordServiceImpl implements CombinationRecordService {

    @Resource
    @Lazy
    private CombinationActivityService combinationActivityService;
    @Resource
    private CombinationRecordMapper recordMapper;


    @Resource
    private MemberUserApi memberUserApi;
    @Resource
    @Lazy
    private ProductSpuApi productSpuApi;
    @Resource
    @Lazy
    private ProductSkuApi productSkuApi;
    @Resource
    @Lazy
    private TradeOrderApi tradeOrderApisn;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCombinationRecordStatusByUserIdAndOrderId(Integer status, Long userId, Long orderId) {
        // 校验拼团是否存在
        CombinationRecordDO record = validateCombinationRecord(userId, orderId);

        // 更新状态
        record.setStatus(status);
        recordMapper.updateById(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRecordStatusAndStartTimeByUserIdAndOrderId(Integer status, Long userId, Long orderId, LocalDateTime startTime) {
        CombinationRecordDO record = validateCombinationRecord(userId, orderId);
        // 更新状态
        record.setStatus(status);
        // 更新开始时间
        record.setStartTime(startTime);
        recordMapper.updateById(record);

        // 更新拼团参入人数
        List<CombinationRecordDO> records = recordMapper.selectListByHeadIdAndStatus(record.getHeadId(), status);
        if (CollUtil.isNotEmpty(records)) {
            records.forEach(item -> {
                item.setUserCount(records.size());
                // 校验拼团是否满足要求
                if (ObjectUtil.equal(records.size(), record.getUserSize())) {
                    item.setStatus(CombinationRecordStatusEnum.SUCCESS.getStatus());
                }
            });
            recordMapper.updateBatch(records);
        }
    }

    private CombinationRecordDO validateCombinationRecord(Long userId, Long orderId) {
        // 校验拼团是否存在
        CombinationRecordDO recordDO = recordMapper.selectByUserIdAndOrderId(userId, orderId);
        if (recordDO == null) {
            throw exception(COMBINATION_RECORD_NOT_EXISTS);
        }
        return recordDO;
    }

    // TODO @芋艿：在详细预览下；
    @Override
    public KeyValue<CombinationActivityDO, CombinationProductDO> validateCombinationRecord(
            Long activityId, Long userId, Long skuId, Integer count) {
        // 1.1 校验拼团活动是否存在
        CombinationActivityDO activity = combinationActivityService.validateCombinationActivityExists(activityId);
        // 1.2 校验活动是否开启
        if (ObjectUtil.equal(activity.getStatus(), CommonStatusEnum.DISABLE.getStatus())) {
            throw exception(COMBINATION_ACTIVITY_STATUS_DISABLE);
        }
        // 2 校验是否超出单次限购数量
        if (count > activity.getSingleLimitCount()) {
            throw exception(COMBINATION_RECORD_FAILED_SINGLE_LIMIT_COUNT_EXCEED);
        }
        // 2.1、校验活动商品是否存在
        CombinationProductDO product = combinationActivityService.selectByActivityIdAndSkuId(activityId, skuId);
        if (product == null) {
            throw exception(COMBINATION_JOIN_ACTIVITY_PRODUCT_NOT_EXISTS);
        }
        // 2.2、校验 sku 是否存在
        ProductSkuRespDTO sku = productSkuApi.getSku(skuId);
        if (sku == null) {
            throw exception(COMBINATION_JOIN_ACTIVITY_PRODUCT_NOT_EXISTS);
        }
        // 2.3、 校验库存是否充足
        if (count > sku.getStock()) {
            throw exception(COMBINATION_ACTIVITY_UPDATE_STOCK_FAIL);
        }
        // 3、校验是否有拼团记录
        List<CombinationRecordDO> recordList = getCombinationRecordListByUserIdAndActivityId(userId, activityId);
        if (CollUtil.isEmpty(recordList)) {
            return new KeyValue<>(activity, product);
        }
        // 4、校验是否超出总限购数量
        Integer sumValue = getSumValue(convertList(recordList, CombinationRecordDO::getCount,
                item -> ObjectUtil.equals(item.getStatus(), CombinationRecordStatusEnum.SUCCESS.getStatus())), i -> i, Integer::sum);
        if ((sumValue + count) > activity.getTotalLimitCount()) {
            throw exception(COMBINATION_RECORD_FAILED_TOTAL_LIMIT_COUNT_EXCEED);
        }
        // 5、校验拼团记录是否存在未支付的订单（如果存在未支付的订单则不允许发起新的拼团）
        CombinationRecordDO record = findFirst(recordList, item -> ObjectUtil.equals(item.getStatus(), null));
        if (record == null) {
            return new KeyValue<>(activity, product);
        }
        // 5.1、查询关联的订单是否已经支付
        // 当前 activityId 已经有未支付的订单，不允许在发起新的；要么支付，要么去掉先；
        // TODO 芋艿：看看是不是可以删除掉；
        Integer orderStatus = tradeOrderApisn.getOrderStatus(record.getOrderId());
        if (ObjectUtil.equal(orderStatus, TradeOrderStatusEnum.UNPAID.getStatus())) {
            throw exception(COMBINATION_RECORD_FAILED_ORDER_STATUS_UNPAID);
        }

        return new KeyValue<>(activity, product);
    }

    // TODO 芋艿：在详细 review 下；
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createCombinationRecord(CombinationRecordCreateReqDTO reqDTO) {
        // 1、校验拼团活动
        KeyValue<CombinationActivityDO, CombinationProductDO> keyValue = validateCombinationRecord(
                reqDTO.getActivityId(), reqDTO.getUserId(), reqDTO.getSkuId(), reqDTO.getCount());
        CombinationActivityDO activity = keyValue.getKey();
        // 2、校验用户是否参加了其它拼团
        List<CombinationRecordDO> recordDOList = recordMapper.selectListByUserIdAndStatus(reqDTO.getUserId(), CombinationRecordStatusEnum.IN_PROGRESS.getStatus());
        if (CollUtil.isNotEmpty(recordDOList)) {
            throw exception(COMBINATION_RECORD_FAILED_HAVE_JOINED);
        }
        // 3、校验活动是否开启
        if (!LocalDateTimeUtils.beforeNow(activity.getStartTime())) {
            throw exception(COMBINATION_RECORD_FAILED_TIME_NOT_START);
        }
        // 4、校验当前活动是否过期
        if (LocalDateTime.now().isAfter(activity.getEndTime())) {
            throw exception(COMBINATION_RECORD_FAILED_TIME_END);
        }
        // 5、父拼团是否存在,是否已经满了
        if (reqDTO.getHeadId() != null) {
            // 5.1、查询进行中的父拼团
            CombinationRecordDO record = recordMapper.selectOneByHeadId(reqDTO.getHeadId(), CombinationRecordStatusEnum.IN_PROGRESS.getStatus());
            if (record == null) {
                throw exception(COMBINATION_RECORD_HEAD_NOT_EXISTS);
            }
            // 5.2、校验拼团是否满足要求
            if (ObjectUtil.equal(record.getUserCount(), record.getUserSize())) {
                throw exception(COMBINATION_RECORD_USER_FULL);
            }
        }

        // 6. 创建拼团记录
        MemberUserRespDTO user = memberUserApi.getUser(reqDTO.getUserId());
        ProductSpuRespDTO spu = productSpuApi.getSpu(reqDTO.getSpuId());
        ProductSkuRespDTO sku = productSkuApi.getSku(reqDTO.getSkuId());
        recordMapper.insert(CombinationActivityConvert.INSTANCE.convert(reqDTO, activity, user, spu, sku));
    }

    @Override
    public CombinationRecordDO createCombinationRecord1(CombinationRecordCreateReqDTO reqDTO) {
        // 1. 校验拼团活动
        KeyValue<CombinationActivityDO, CombinationProductDO> keyValue = validateCombinationRecord(reqDTO.getUserId(),
                reqDTO.getActivityId(), reqDTO.getHeadId(), reqDTO.getSkuId(), reqDTO.getCount());

        // 2. 组合数据创建拼团记录
        MemberUserRespDTO user = memberUserApi.getUser(reqDTO.getUserId());
        ProductSpuRespDTO spu = productSpuApi.getSpu(reqDTO.getSpuId());
        ProductSkuRespDTO sku = productSkuApi.getSku(reqDTO.getSkuId());
        CombinationRecordDO record = CombinationActivityConvert.INSTANCE.convert(reqDTO, keyValue.getKey(), user, spu, sku);
        // 2.1. 如果是团长需要设置 headId 为 CombinationRecordDO#HEAD_ID_GROUP
        if (record.getHeadId() == null) {
            record.setStartTime(LocalDateTime.now())
                    .setExpireTime(keyValue.getKey().getStartTime().plusHours(keyValue.getKey().getLimitDuration()))
                    .setHeadId(CombinationRecordDO.HEAD_ID_GROUP);
        } else {
            // 2.2.有团长的情况下需要设置开始时间和过期时间为团长的
            CombinationRecordDO headRecord = recordMapper.selectByHeadId(record.getHeadId(),
                    CombinationRecordStatusEnum.IN_PROGRESS.getStatus()); // 查询进行中的父拼团
            record.setStartTime(headRecord.getStartTime()).setExpireTime(headRecord.getExpireTime());
        }
        recordMapper.insert(record);

        // 3. 更新拼团记录
        if (ObjUtil.notEqual(CombinationRecordDO.HEAD_ID_GROUP, record.getHeadId())) {
            updateCombinationRecordWhenCreate(reqDTO.getHeadId(), keyValue.getKey());
        }
        return record;
    }

    @Override
    public CombinationRecordDO getCombinationRecord(Long userId, Long orderId) {
        // TODO puhui999:这里直接获得，不适合调用校验的接口；
        return validateCombinationRecord(userId, orderId);
    }

    @Override
    public List<CombinationRecordDO> getCombinationRecordListByUserIdAndActivityId(Long userId, Long activityId) {
        return recordMapper.selectListByUserIdAndActivityId(userId, activityId);
    }

    @Override
    public CombinationValidateJoinRespDTO validateJoinCombination(Long activityId, Long userId, Long skuId, Integer count) {
        KeyValue<CombinationActivityDO, CombinationProductDO> keyValue = validateCombinationRecord(activityId, userId, skuId, count);
        return new CombinationValidateJoinRespDTO()
                .setActivityId(keyValue.getKey().getId())
                .setName(keyValue.getKey().getName())
                .setCombinationPrice(keyValue.getValue().getCombinationPrice());
    }

    @Override
    public Long getCombinationRecordCount() {
        return recordMapper.selectCount();
    }

    @Override
    public Long getCombinationRecordsSuccessCount() {
        return recordMapper.selectCount(CombinationRecordDO::getStatus, CombinationRecordStatusEnum.SUCCESS.getStatus());
    }

    @Override
    public Long getRecordsVirtualGroupCount() {
        return recordMapper.selectCount(CombinationRecordDO::getVirtualGroup, true);
    }

    @Override
    public Long getCombinationRecordsCountByDateType(Integer dateType) {
        return recordMapper.selectCount(dateType);
    }

    @Override
    public List<CombinationRecordDO> getLatestCombinationRecordList(int count) {
        return recordMapper.selectLatestList(count);
    }

    @Override
    public List<CombinationRecordDO> getCombinationRecordListWithHead(Long activityId, Integer status, Integer count) {
        return recordMapper.selectList(activityId, status, count);
    }

    @Override
    public CombinationRecordDO getCombinationRecordById(Long id) {
        return recordMapper.selectById(id);
    }

    @Override
    public List<CombinationRecordDO> getCombinationRecordListByHeadId(Long headId) {
        return recordMapper.selectList(CombinationRecordDO::getHeadId, headId);
    }

    @Override
    public PageResult<CombinationRecordDO> getCombinationRecordPage(CombinationRecordReqPageVO pageVO) {
        return recordMapper.selectPage(pageVO);
    }

    @Override
    public Map<Long, Integer> getCombinationRecordCountMapByActivity(Collection<Long> activityIds,
                                                                     @Nullable Integer status, @Nullable Integer headId) {
        return recordMapper.selectCombinationRecordCountMapByActivityIdAndStatusAndHeadId(activityIds, status, headId);
    }








    // TODO @芋艿：在详细预览下；
    @Override
    public KeyValue<CombinationActivityDO, CombinationProductDO> validateCombinationRecord(
            Long userId, Long activityId, Long headId, Long skuId, Integer count) {
        // 1. 校验拼团活动是否存在
        CombinationActivityDO activity = combinationActivityService.validateCombinationActivityExists(activityId);
        // 1.1 校验活动是否开启
        if (ObjUtil.equal(activity.getStatus(), CommonStatusEnum.DISABLE.getStatus())) {
            throw exception(COMBINATION_ACTIVITY_STATUS_DISABLE);
        }
        // 1.2. 校验活动开始时间
        if (afterNow(activity.getStartTime())) {
            throw exception(COMBINATION_RECORD_FAILED_TIME_NOT_START);
        }
        // 1.3 校验是否超出单次限购数量
        if (count > activity.getSingleLimitCount()) {
            throw exception(COMBINATION_RECORD_FAILED_SINGLE_LIMIT_COUNT_EXCEED);
        }

        // 2. 父拼团是否存在,是否已经满了
        if (headId != null) {
            // 2.1. 查询进行中的父拼团
            CombinationRecordDO record = recordMapper.selectByHeadId(headId, CombinationRecordStatusEnum.IN_PROGRESS.getStatus());
            if (record == null) {
                throw exception(COMBINATION_RECORD_HEAD_NOT_EXISTS);
            }
            // 2.2. 校验拼团是否已满
            if (ObjUtil.equal(record.getUserCount(), record.getUserSize())) {
                throw exception(COMBINATION_RECORD_USER_FULL);
            }
            // 2.3 校验拼团是否过期（有父拼团的时候只校验父拼团的过期时间）
            if (beforeNow(record.getExpireTime())) {
                throw exception(COMBINATION_RECORD_FAILED_TIME_END);
            }
        } else {
            // 3. 校验当前活动是否结束(自己是父拼团的时候才校验活动是否结束)
            if (beforeNow(activity.getEndTime())) {
                throw exception(COMBINATION_RECORD_FAILED_TIME_END);
            }
        }

        // 4.1 校验活动商品是否存在
        CombinationProductDO product = combinationActivityService.selectByActivityIdAndSkuId(activityId, skuId);
        if (product == null) {
            throw exception(COMBINATION_JOIN_ACTIVITY_PRODUCT_NOT_EXISTS);
        }
        // 4.2 校验 sku 是否存在
        ProductSkuRespDTO sku = productSkuApi.getSku(skuId);
        if (sku == null) {
            throw exception(COMBINATION_JOIN_ACTIVITY_PRODUCT_NOT_EXISTS);
        }
        // 4.3 校验库存是否充足
        if (count > sku.getStock()) {
            throw exception(COMBINATION_ACTIVITY_UPDATE_STOCK_FAIL);
        }

        // 6.1 校验是否有拼团记录
        List<CombinationRecordDO> recordList = recordMapper.selectListByUserIdAndActivityId(userId, activityId);
        recordList.removeIf(record -> CombinationRecordStatusEnum.isFailed(record.getStatus())); // 取消的订单，不算数
        if (CollUtil.isEmpty(recordList)) { // 如果为空，说明可以参与，直接返回
            return new KeyValue<>(activity, product);
        }
        // 6.2 校验用户是否有该活动正在进行的拼团
        CombinationRecordDO inProgressRecord = findFirst(recordList,
                record -> CombinationRecordStatusEnum.isInProgress(record.getStatus()));
        if (inProgressRecord != null) {
            throw exception(COMBINATION_RECORD_FAILED_HAVE_JOINED);
        }
        // 6.3 校验是否超出总限购数量
        Integer sumValue = getSumValue(recordList, CombinationRecordDO::getCount, Integer::sum);
        if (sumValue != null && sumValue + count > activity.getTotalLimitCount()) {
            throw exception(COMBINATION_RECORD_FAILED_TOTAL_LIMIT_COUNT_EXCEED);
        }
        return new KeyValue<>(activity, product);
    }



    /**
     * 当新增拼团时，更新拼团记录的进展
     *
     * @param headId   团长编号
     * @param activity 活动
     */
    private void updateCombinationRecordWhenCreate(Long headId, CombinationActivityDO activity) {
        // 1. 团长 + 团员
        List<CombinationRecordDO> records = getCombinationRecordListByHeadId(headId);
        if (CollUtil.isEmpty(records)) {
            return;
        }
        CombinationRecordDO headRecord = recordMapper.selectById(headId);

        // 2. 批量更新记录
        List<CombinationRecordDO> updateRecords = new ArrayList<>();
        records.add(headRecord); // 加入团长，团长也需要更新
        boolean isFull = records.size() >= activity.getUserSize();
        LocalDateTime now = LocalDateTime.now();
        records.forEach(item -> {
            CombinationRecordDO updateRecord = new CombinationRecordDO();
            updateRecord.setId(item.getId()).setUserCount(records.size());
            if (isFull) {
                updateRecord.setStatus(CombinationRecordStatusEnum.SUCCESS.getStatus());
                updateRecord.setEndTime(now);
            }
            updateRecords.add(updateRecord);
        });
        recordMapper.updateBatch(updateRecords);
    }




    @Override
    public CombinationValidateJoinRespDTO validateJoinCombination(Long userId, Long activityId, Long headId,
                                                                  Long skuId, Integer count) {
        KeyValue<CombinationActivityDO, CombinationProductDO> keyValue = validateCombinationRecord(userId, activityId,
                headId, skuId, count);
        return new CombinationValidateJoinRespDTO().setActivityId(keyValue.getKey().getId())
                .setName(keyValue.getKey().getName()).setCombinationPrice(keyValue.getValue().getCombinationPrice());
    }

    @Override
    public Long getCombinationRecordCount(@Nullable Integer status, @Nullable Boolean virtualGroup, @Nullable Long headId) {
        return recordMapper.selectCountByHeadAndStatusAndVirtualGroup(status, virtualGroup, headId);
    }

    @Override
    public Long getCombinationUserCount() {
        return recordMapper.selectUserCount();
    }



    @Override
    public List<CombinationRecordDO> getHeadCombinationRecordList(Long activityId, Integer status, Integer count) {
        return recordMapper.selectListByActivityIdAndStatusAndHeadId(activityId, status,
                CombinationRecordDO.HEAD_ID_GROUP, count);
    }

    @Override
    public Map<Long, Integer> getCombinationRecordCountMapByActivity1(Collection<Long> activityIds, @org.jetbrains.annotations.Nullable Integer status, @org.jetbrains.annotations.Nullable Long headId) {
        return recordMapper.selectCombinationRecordCountMapByActivityIdAndStatusAndHeadId(activityIds, status, headId);
    }



    @Override
    public CombinationRecordDO getCombinationRecordByIdAndUser(Long userId, Long id) {
        return recordMapper.selectOne(CombinationRecordDO::getUserId, userId, CombinationRecordDO::getId, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelCombinationRecord(Long userId, Long id, Long headId) {
        // 删除记录
        recordMapper.deleteById(id);

        // 需要更新的记录
        List<CombinationRecordDO> updateRecords = new ArrayList<>();
        // 如果它是团长，则顺序（下单时间）继承
        if (Objects.equals(headId, CombinationRecordDO.HEAD_ID_GROUP)) { // 情况一：团长
            // 团员
            List<CombinationRecordDO> list = getCombinationRecordListByHeadId(id);
            if (CollUtil.isEmpty(list)) {
                return;
            }
            // 按照创建时间升序排序
            list.sort(Comparator.comparing(CombinationRecordDO::getCreateTime)); // 影响原 list
            CombinationRecordDO newHead = list.get(0); // 新团长继位
            list.forEach(item -> {
                CombinationRecordDO recordDO = new CombinationRecordDO();
                recordDO.setId(item.getId());
                if (ObjUtil.equal(item.getId(), newHead.getId())) { // 新团长
                    recordDO.setHeadId(CombinationRecordDO.HEAD_ID_GROUP);
                } else {
                    recordDO.setHeadId(newHead.getId());
                }
                recordDO.setUserCount(list.size());
                updateRecords.add(recordDO);
            });
        } else { // 情况二：团员
            // 团长
            CombinationRecordDO recordHead = recordMapper.selectById(headId);
            // 团员
            List<CombinationRecordDO> records = getCombinationRecordListByHeadId(headId);
            if (CollUtil.isEmpty(records)) {
                return;
            }
            records.add(recordHead); // 加入团长，团长数据也需要更新
            records.forEach(item -> {
                CombinationRecordDO recordDO = new CombinationRecordDO();
                recordDO.setId(item.getId());
                recordDO.setUserCount(records.size());
                updateRecords.add(recordDO);
            });
        }

        // 更新拼团记录
        recordMapper.updateBatch(updateRecords);
    }

    @Override
    public KeyValue<Integer, Integer> expireCombinationRecord() {
        // 1. 获取所有正在进行中的过期的父拼团
        List<CombinationRecordDO> headExpireRecords = recordMapper.selectListByHeadIdAndStatusAndExpireTimeLt(
                CombinationRecordDO.HEAD_ID_GROUP, CombinationRecordStatusEnum.IN_PROGRESS.getStatus(), LocalDateTime.now());
        if (CollUtil.isEmpty(headExpireRecords)) {
            return new KeyValue<>(0, 0);
        }

        // 2. 获取拼团活动
        List<CombinationActivityDO> activities = combinationActivityService.getCombinationActivityListByIds(
                convertSet(headExpireRecords, CombinationRecordDO::getActivityId));
        Map<Long, CombinationActivityDO> activityMap = convertMap(activities, CombinationActivityDO::getId);

        // 3. 逐个处理拼团，过期 or 虚拟成团
        KeyValue<Integer, Integer> keyValue = new KeyValue<>(0, 0); // 统计过期拼团和虚拟成团
        for (CombinationRecordDO record : headExpireRecords) {
            try {
                CombinationActivityDO activity = activityMap.get(record.getActivityId());
                if (activity == null || !activity.getVirtualGroup()) { // 取不到活动的或者不是虚拟拼团的
                    // 3.1. 处理过期的拼团
                    getSelf().handleExpireRecord(record);
                    keyValue.setKey(keyValue.getKey() + 1);
                } else {
                    // 3.2. 处理虚拟成团
                    getSelf().handleVirtualGroupRecord(record);
                    keyValue.setValue(keyValue.getValue() + 1);
                }
            } catch (Exception ignored) { // 处理异常继续循环
                log.error("[expireCombinationRecord][record({}) 处理异常，请进行处理！record 数据是：{}]",
                        record.getId(), JsonUtils.toJsonString(record));
            }
        }
        return keyValue;
    }

    /**
     * 处理过期拼团
     *
     * @param headRecord 过期拼团团长记录
     */
    @Transactional(rollbackFor = Exception.class)
    public void handleExpireRecord(CombinationRecordDO headRecord) {
        // 1. 更新拼团记录
        List<CombinationRecordDO> headAndRecords = updateBatchCombinationRecords(headRecord,
                CombinationRecordStatusEnum.FAILED);
        // 2. 订单取消
        headAndRecords.forEach(item -> tradeOrderApisn.cancelPaidOrder(item.getUserId(), item.getOrderId()));
    }

    /**
     * 处理虚拟拼团
     *
     * @param headRecord 虚拟成团团长记录
     */
    @Transactional(rollbackFor = Exception.class)
    public void handleVirtualGroupRecord(CombinationRecordDO headRecord) {
        // 1. 团员补齐
        recordMapper.insertBatch(CombinationActivityConvert.INSTANCE.convertVirtualRecordList(headRecord));
        // 2. 更新拼团记录
        updateBatchCombinationRecords(headRecord, CombinationRecordStatusEnum.SUCCESS);
    }

    /**
     * 更新拼团记录
     *
     * @param headRecord 团长记录
     * @param status     状态-拼团失败 FAILED 成功 SUCCESS
     * @return 整团记录（包含团长和团成员）
     */
    private List<CombinationRecordDO> updateBatchCombinationRecords(CombinationRecordDO headRecord, CombinationRecordStatusEnum status) {
        // 1. 查询团成员（包含团长）
        List<CombinationRecordDO> records = recordMapper.selectListByHeadId(headRecord.getId());
        records.add(headRecord);// 把团长加进去

        // 2. 批量更新拼团记录 status 和 endTime
        List<CombinationRecordDO> updateRecords = new ArrayList<>(records.size());
        LocalDateTime now = LocalDateTime.now();
        records.forEach(item -> {
            CombinationRecordDO updateRecord = new CombinationRecordDO().setId(item.getId())
                    .setStatus(status.getStatus()).setEndTime(now);
            if (CombinationRecordStatusEnum.isSuccess(status.getStatus())) { // 虚拟成团完事更改状态成功后还需要把参与人数修改为成团需要人数
                updateRecord.setUserCount(updateRecord.getUserSize());
            }
            updateRecords.add(updateRecord);
        });
        recordMapper.updateBatch(updateRecords);
        return records;
    }

    /**
     * 获得自身的代理对象，解决 AOP 生效问题
     *
     * @return 自己
     */
    private CombinationRecordServiceImpl getSelf() {
        return SpringUtil.getBean(getClass());
    }


}
