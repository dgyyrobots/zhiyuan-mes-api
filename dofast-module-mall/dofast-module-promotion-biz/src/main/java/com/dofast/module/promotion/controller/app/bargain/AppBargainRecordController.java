package com.dofast.module.promotion.controller.app.bargain;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageParam;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.member.api.user.MemberUserApi;
import com.dofast.module.member.api.user.dto.MemberUserRespDTO;
import com.dofast.module.product.api.spu.ProductSpuApi;
import com.dofast.module.product.api.spu.dto.ProductSpuRespDTO;
import com.dofast.module.promotion.controller.app.bargain.vo.record.AppBargainRecordCreateReqVO;
import com.dofast.module.promotion.controller.app.bargain.vo.record.AppBargainRecordDetailRespVO;
import com.dofast.module.promotion.controller.app.bargain.vo.record.AppBargainRecordRespVO;
import com.dofast.module.promotion.controller.app.bargain.vo.record.AppBargainRecordSummaryRespVO;
import com.dofast.module.promotion.convert.bargain.BargainRecordConvert;
import com.dofast.module.promotion.dal.dataobject.bargain.BargainActivityDO;
import com.dofast.module.promotion.dal.dataobject.bargain.BargainRecordDO;
import com.dofast.module.promotion.enums.bargain.BargainRecordStatusEnum;
import com.dofast.module.promotion.service.bargain.BargainActivityService;
import com.dofast.module.promotion.service.bargain.BargainHelpService;
import com.dofast.module.promotion.service.bargain.BargainRecordService;
import com.dofast.module.trade.api.order.TradeOrderApi;
import com.dofast.module.trade.api.order.dto.TradeOrderRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertSet;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 App - 砍价记录")
@RestController
@RequestMapping("/promotion/bargain-record")
@Validated
public class AppBargainRecordController {

    @Resource
    private BargainRecordService bargainRecordService;
    @Resource
    private BargainActivityService bargainActivityService;
    @Resource
    private BargainHelpService bargainHelpService;

    @Resource
    private MemberUserApi memberUserApi;
    @Resource
    private ProductSpuApi productSpuApi;
    @Resource
    @Lazy
    private TradeOrderApi tradeOrderApis;





    @GetMapping("/get-summary")
    @Operation(summary = "获得砍价记录的概要信息", description = "用于小程序首页")
    public CommonResult<AppBargainRecordSummaryRespVO> getBargainRecordSummary() {
        // 砍价成功的用户数量
        Integer successUserCount = bargainRecordService.getBargainRecordUserCount(
                BargainRecordStatusEnum.SUCCESS.getStatus());
        if (successUserCount == 0) {
            return success(new AppBargainRecordSummaryRespVO().setSuccessUserCount(0)
                    .setSuccessList(Collections.emptyList()));
        }
        // 砍价成功的用户列表
        List<BargainRecordDO> successList = bargainRecordService.getBargainRecordList(
                BargainRecordStatusEnum.SUCCESS.getStatus(), 7);
        List<BargainActivityDO> activityList = bargainActivityService.getBargainActivityList(
                convertSet(successList, BargainRecordDO::getActivityId));
        Map<Long, MemberUserRespDTO> userMap = memberUserApi.getUserMap(
                convertSet(successList, BargainRecordDO::getUserId));
        // 拼接返回
        return success(BargainRecordConvert.INSTANCE.convert(successUserCount, successList, activityList, userMap));
    }

    @GetMapping("/get-detail")
    @Operation(summary = "获得砍价记录的明细")
    @Parameters({
            @Parameter(name = "id", description = "砍价记录编号", example = "111"), // 场景一：查看指定的砍价记录
            @Parameter(name = "activityId", description = "砍价活动编号", example = "222") // 场景二：查看指定的砍价活动
    })
    public CommonResult<AppBargainRecordDetailRespVO> getBargainRecordDetail(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "activityId", required = false) Long activityId) {
        // 1. 查询砍价记录 + 砍价活动
        Assert.isTrue(id != null || activityId != null, "砍价记录编号和活动编号不能同时为空");
        BargainRecordDO record = id != null ? bargainRecordService.getBargainRecord(id)
                : bargainRecordService.getLastBargainRecord(getLoginUserId(), activityId);
        if (activityId == null || record != null) {
            activityId = record.getActivityId();
        }
        // 2. 查询助力记录
        Long userId = getLoginUserId();
        Integer helpAction = getHelpAction(userId, record, activityId);
        // 3. 如果是自己的订单，则查询订单信息
        TradeOrderRespDTO order = record != null && record.getOrderId() != null && record.getUserId().equals(getLoginUserId())
                ? tradeOrderApis.getOrder(record.getOrderId()) : null;
        // TODO 继续查询别的字段

        // 拼接返回
        return success(BargainRecordConvert.INSTANCE.convert02(record, helpAction, order));
    }

    private Integer getHelpAction(Long userId, BargainRecordDO record, Long activityId) {
        // 0.1 如果没有活动，无法帮砍
        if (activityId == null) {
            return null;
        }
        // 0.2 如果是自己的砍价记录，无法帮砍
        if (record != null && record.getUserId().equals(userId)) {
            return null;
        }

        // 1. 判断是否已经助力
        if (record != null
            && bargainHelpService.getBargainHelp(record.getId(), userId) != null) {
            return AppBargainRecordDetailRespVO.HELP_ACTION_SUCCESS;
        }
        // 2. 判断是否满助力
        BargainActivityDO activity = bargainActivityService.getBargainActivity(activityId);
        if (activity != null
            && bargainHelpService.getBargainHelpCountByActivity(activityId, userId) >= activity.getBargainCount()) {
            return AppBargainRecordDetailRespVO.HELP_ACTION_FULL;
        }
        // 3. 允许助力
        return AppBargainRecordDetailRespVO.HELP_ACTION_NONE;
    }

    @GetMapping("/page")
    @Operation(summary = "获得砍价记录的分页")
    public CommonResult<PageResult<AppBargainRecordRespVO>> getBargainRecordPage(PageParam pageParam) {
        PageResult<BargainRecordDO> pageResult = bargainRecordService.getBargainRecordPage(getLoginUserId(), pageParam);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }

        // 拼接数据
        List<BargainActivityDO> activityList = bargainActivityService.getBargainActivityList(
                convertSet(pageResult.getList(), BargainRecordDO::getActivityId));
        List<ProductSpuRespDTO> spuList = productSpuApi.getSpuList(
                convertSet(pageResult.getList(), BargainRecordDO::getSpuId));
        List<TradeOrderRespDTO> orderList = tradeOrderApis.getOrderList(
                convertSet(pageResult.getList(), BargainRecordDO::getOrderId));
        return success(BargainRecordConvert.INSTANCE.convertPage02(pageResult, activityList, spuList, orderList));
    }

    @PostMapping("/create")
    @Operation(summary = "创建砍价记录", description = "参与砍价活动")
    @PreAuthenticated
    public CommonResult<Long> createBargainRecord(@RequestBody AppBargainRecordCreateReqVO reqVO) {
        Long recordId = bargainRecordService.createBargainRecord(getLoginUserId(), reqVO);
        return success(recordId);
    }

}
