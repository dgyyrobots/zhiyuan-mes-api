package com.dofast.module.promotion.controller.admin.reward;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.promotion.controller.admin.reward.vo.RewardActivityCreateReqVO;
import com.dofast.module.promotion.controller.admin.reward.vo.RewardActivityPageReqVO;
import com.dofast.module.promotion.controller.admin.reward.vo.RewardActivityRespVO;
import com.dofast.module.promotion.controller.admin.reward.vo.RewardActivityUpdateReqVO;
import com.dofast.module.promotion.convert.reward.RewardActivityConvert;
import com.dofast.module.promotion.dal.dataobject.reward.RewardActivityDO;
import com.dofast.module.promotion.service.reward.RewardActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.dofast.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 满减送活动")
@RestController
@RequestMapping("/promotion/reward-activity")
@Validated
public class RewardActivityController {

    @Resource
    private RewardActivityService rewardActivityService;

    @PostMapping("/create")
    @Operation(summary = "创建满减送活动")
    @PreAuthorize("@ss.hasPermission('promotion:reward-activity:create')")
    public CommonResult<Long> createRewardActivity(@Valid @RequestBody RewardActivityCreateReqVO createReqVO) {
        return success(rewardActivityService.createRewardActivity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新满减送活动")
    @PreAuthorize("@ss.hasPermission('promotion:reward-activity:update')")
    public CommonResult<Boolean> updateRewardActivity(@Valid @RequestBody RewardActivityUpdateReqVO updateReqVO) {
        rewardActivityService.updateRewardActivity(updateReqVO);
        return success(true);
    }

    @PutMapping("/close")
    @Operation(summary = "关闭满减送活动")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('promotion:reward-activity:close')")
    public CommonResult<Boolean> closeRewardActivity(@RequestParam("id") Long id) {
        rewardActivityService.closeRewardActivity(id);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除满减送活动")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('promotion:reward-activity:delete')")
    public CommonResult<Boolean> deleteRewardActivity(@RequestParam("id") Long id) {
        rewardActivityService.deleteRewardActivity(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得满减送活动")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('promotion:reward-activity:query')")
    public CommonResult<RewardActivityRespVO> getRewardActivity(@RequestParam("id") Long id) {
        RewardActivityDO rewardActivity = rewardActivityService.getRewardActivity(id);
        return success(RewardActivityConvert.INSTANCE.convert(rewardActivity));
    }

    @GetMapping("/page")
    @Operation(summary = "获得满减送活动分页")
    @PreAuthorize("@ss.hasPermission('promotion:reward-activity:query')")
    public CommonResult<PageResult<RewardActivityRespVO>> getRewardActivityPage(@Valid RewardActivityPageReqVO pageVO) {
        PageResult<RewardActivityDO> pageResult = rewardActivityService.getRewardActivityPage(pageVO);
        return success(RewardActivityConvert.INSTANCE.convertPage(pageResult));
    }

}
