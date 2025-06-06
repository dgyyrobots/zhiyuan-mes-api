package com.dofast.module.trade.controller.admin.brokerage;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.member.api.user.MemberUserApi;
import com.dofast.module.member.api.user.dto.MemberUserRespDTO;
import com.dofast.module.trade.controller.admin.brokerage.vo.user.*;
import com.dofast.module.trade.convert.brokerage.BrokerageUserConvert;
import com.dofast.module.trade.dal.dataobject.brokerage.BrokerageUserDO;
import com.dofast.module.trade.enums.brokerage.BrokerageRecordBizTypeEnum;
import com.dofast.module.trade.enums.brokerage.BrokerageRecordStatusEnum;
import com.dofast.module.trade.enums.brokerage.BrokerageWithdrawStatusEnum;
import com.dofast.module.trade.service.brokerage.BrokerageRecordService;
import com.dofast.module.trade.service.brokerage.BrokerageUserService;
import com.dofast.module.trade.service.brokerage.BrokerageWithdrawService;
import com.dofast.module.trade.service.brokerage.bo.UserBrokerageSummaryRespBO;
import com.dofast.module.trade.service.brokerage.bo.BrokerageWithdrawSummaryRespBO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertMap;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertSet;

@Tag(name = "管理后台 - 分销用户")
@RestController
@RequestMapping("/trade/brokerage-user")
@Validated
public class BrokerageUserController {

    @Resource
    private BrokerageUserService brokerageUserService;
    @Resource
    private BrokerageRecordService brokerageRecordService;
    @Resource
    private BrokerageWithdrawService brokerageWithdrawService;

    @Resource
    private MemberUserApi memberUserApi;

    @PutMapping("/update-bind-user")
    @Operation(summary = "修改推广员")
    @PreAuthorize("@ss.hasPermission('trade:brokerage-user:update-bind-user')")
    public CommonResult<Boolean> updateBindUser(@Valid @RequestBody BrokerageUserUpdateBrokerageUserReqVO updateReqVO) {
        brokerageUserService.updateBrokerageUserId(updateReqVO.getId(), updateReqVO.getBindUserId());
        return success(true);
    }

    @PutMapping("/clear-bind-user")
    @Operation(summary = "清除推广员")
    @PreAuthorize("@ss.hasPermission('trade:brokerage-user:clear-bind-user')")
    public CommonResult<Boolean> clearBindUser(@Valid @RequestBody BrokerageUserClearBrokerageUserReqVO updateReqVO) {
        brokerageUserService.updateBrokerageUserId(updateReqVO.getId(), null);
        return success(true);
    }

    @PutMapping("/update-brokerage-enable")
    @Operation(summary = "修改推广资格")
    @PreAuthorize("@ss.hasPermission('trade:brokerage-user:update-brokerage-enable')")
    public CommonResult<Boolean> updateBrokerageEnabled(@Valid @RequestBody BrokerageUserUpdateBrokerageEnabledReqVO updateReqVO) {
        brokerageUserService.updateBrokerageUserEnabled(updateReqVO.getId(), updateReqVO.getEnabled());
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得分销用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:brokerage-user:query')")
    public CommonResult<BrokerageUserRespVO> getBrokerageUser(@RequestParam("id") Long id) {
        BrokerageUserDO brokerageUser = brokerageUserService.getBrokerageUser(id);
        BrokerageUserRespVO respVO = BrokerageUserConvert.INSTANCE.convert(brokerageUser);
        return success(BrokerageUserConvert.INSTANCE.copyTo(memberUserApi.getUser(id), respVO));
    }

    @GetMapping("/page")
    @Operation(summary = "获得分销用户分页")
    @PreAuthorize("@ss.hasPermission('trade:brokerage-user:query')")
    public CommonResult<PageResult<BrokerageUserRespVO>> getBrokerageUserPage(@Valid BrokerageUserPageReqVO pageVO) {
        // 分页查询
        PageResult<BrokerageUserDO> pageResult = brokerageUserService.getBrokerageUserPage(pageVO);

        // 查询用户信息
        Set<Long> userIds = convertSet(pageResult.getList(), BrokerageUserDO::getId);
        Map<Long, MemberUserRespDTO> userMap = memberUserApi.getUserMap(userIds);
        // 合计分佣的推广订单
        Map<Long, UserBrokerageSummaryRespBO> brokerageOrderSummaryMap = brokerageRecordService.getUserBrokerageSummaryMapByUserId(
                userIds, BrokerageRecordBizTypeEnum.ORDER.getType(), BrokerageRecordStatusEnum.SETTLEMENT.getStatus());
        // 合计分佣的推广用户
        // TODO @疯狂：转成 map 批量读取
        Map<Long, Long> brokerageUserCountMap = convertMap(userIds,
                userId -> userId,
                userId -> brokerageUserService.getBrokerageUserCountByBindUserId(userId, null));
        // 合计分佣的提现
        // TODO @疯狂：如果未来支持了打款这个动作，可能 status 会不对；
        Map<Long, BrokerageWithdrawSummaryRespBO> withdrawMap = brokerageWithdrawService.getWithdrawSummaryMapByUserId(
                userIds, BrokerageWithdrawStatusEnum.AUDIT_SUCCESS);
        // 拼接返回
        return success(BrokerageUserConvert.INSTANCE.convertPage(pageResult, userMap, brokerageUserCountMap,
                brokerageOrderSummaryMap, withdrawMap));
    }

}
