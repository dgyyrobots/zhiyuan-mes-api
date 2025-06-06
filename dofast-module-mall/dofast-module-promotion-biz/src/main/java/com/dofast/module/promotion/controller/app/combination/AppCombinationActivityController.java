package com.dofast.module.promotion.controller.app.combination;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageParam;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.product.api.spu.ProductSpuApi;
import com.dofast.module.product.api.spu.dto.ProductSpuRespDTO;
import com.dofast.module.promotion.controller.app.combination.vo.activity.AppCombinationActivityDetailRespVO;
import com.dofast.module.promotion.controller.app.combination.vo.activity.AppCombinationActivityRespVO;
import com.dofast.module.promotion.convert.combination.CombinationActivityConvert;
import com.dofast.module.promotion.dal.dataobject.combination.CombinationActivityDO;
import com.dofast.module.promotion.dal.dataobject.combination.CombinationProductDO;
import com.dofast.module.promotion.service.combination.CombinationActivityService;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.common.util.cache.CacheUtils.buildAsyncReloadingCache;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertList;

@Tag(name = "用户 APP - 拼团活动")
@RestController
@RequestMapping("/promotion/combination-activity")
@Validated
public class AppCombinationActivityController {

    /**
     * {@link AppCombinationActivityRespVO} 缓存，通过它异步刷新 {@link #getCombinationActivityList0(Integer)} 所要的首页数据
     */
    private final LoadingCache<Integer, List<AppCombinationActivityRespVO>> combinationActivityListCache = buildAsyncReloadingCache(Duration.ofSeconds(10L),
            new CacheLoader<Integer, List<AppCombinationActivityRespVO>>() {

                @Override
                public List<AppCombinationActivityRespVO> load(Integer count) {
                    return getCombinationActivityList0(count);
                }

            });

    @Resource
    private CombinationActivityService combinationActivityService;

    @Resource
    private ProductSpuApi spuApi;

    @GetMapping("/list")
    @Operation(summary = "获得拼团活动列表", description = "用于小程序首页")
    @Parameter(name = "count", description = "需要展示的数量", example = "6")
    public CommonResult<List<AppCombinationActivityRespVO>> getCombinationActivityList(
            @RequestParam(name = "count", defaultValue = "6") Integer count) {
        return success(combinationActivityListCache.getUnchecked(count));
    }

    private List<AppCombinationActivityRespVO> getCombinationActivityList0(Integer count) {
        List<CombinationActivityDO> activityList = combinationActivityService.getCombinationActivityListByCount(count);
        if (CollUtil.isEmpty(activityList)) {
            return Collections.emptyList();
        }
        // 拼接返回
        List<CombinationProductDO> productList = combinationActivityService.getCombinationProductListByActivityIds(
                convertList(activityList, CombinationActivityDO::getId));
        List<ProductSpuRespDTO> spuList = spuApi.getSpuList(convertList(activityList, CombinationActivityDO::getSpuId));
        return CombinationActivityConvert.INSTANCE.convertAppList(activityList, productList, spuList);
    }

    @GetMapping("/page")
    @Operation(summary = "获得拼团活动分页")
    public CommonResult<PageResult<AppCombinationActivityRespVO>> getCombinationActivityPage(PageParam pageParam) {
        PageResult<CombinationActivityDO> pageResult = combinationActivityService.getCombinationActivityPage(pageParam);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }
        // 拼接返回
        List<CombinationProductDO> productList = combinationActivityService.getCombinationProductListByActivityIds(
                convertList(pageResult.getList(), CombinationActivityDO::getId));
        List<ProductSpuRespDTO> spuList = spuApi.getSpuList(convertList(pageResult.getList(), CombinationActivityDO::getSpuId));
        return success(CombinationActivityConvert.INSTANCE.convertAppPage(pageResult, productList, spuList));
    }

    @GetMapping("/get-detail")
    @Operation(summary = "获得拼团活动明细")
    @Parameter(name = "id", description = "活动编号", required = true, example = "1024")
    public CommonResult<AppCombinationActivityDetailRespVO> getCombinationActivityDetail(@RequestParam("id") Long id) {
        // 1. 获取活动
        CombinationActivityDO activity = combinationActivityService.getCombinationActivity(id);
        if (activity == null
                || ObjectUtil.equal(activity.getStatus(), CommonStatusEnum.DISABLE.getStatus())) {
            return success(null);
        }

        // 2. 获取活动商品
        List<CombinationProductDO> products = combinationActivityService.getCombinationProductsByActivityId(activity.getId());
        return success(CombinationActivityConvert.INSTANCE.convert3(activity, products));
    }

}
