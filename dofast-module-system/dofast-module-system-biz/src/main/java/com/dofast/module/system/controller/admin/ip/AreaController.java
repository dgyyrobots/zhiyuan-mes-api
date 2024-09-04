package com.dofast.module.system.controller.admin.ip;

import cn.hutool.core.lang.Assert;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.ip.core.Area;
import com.dofast.framework.ip.core.utils.AreaUtils;
import com.dofast.framework.ip.core.utils.IPUtils;
import com.dofast.module.system.controller.admin.ip.vo.AreaNodeRespVO;
import com.dofast.module.system.convert.ip.AreaConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.dofast.framework.common.exception.enums.GlobalErrorCodeConstants.NOT_FOUND;

import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 地区")
@RestController
@RequestMapping("/system/area")
@Validated
public class AreaController {

    @GetMapping("/tree")
    @Operation(summary = "获得地区树")
    public CommonResult<List<AreaNodeRespVO>> getAreaTree() {
        Area area = AreaUtils.getArea(Area.ID_CHINA);
        Assert.notNull(area, "获取不到中国");
        return success(AreaConvert.INSTANCE.convertList(area.getChildren()));
    }

    @GetMapping("/get-by-ip")
    @Operation(summary = "获得 IP 对应的地区名")
    @Parameter(name = "ip", description = "IP", required = true)
    public CommonResult<String> getAreaByIp(@RequestParam("ip") String ip) {
        // 获得城市
        Area area = IPUtils.getArea(ip);
        if (area == null) {
            return success("未知");
        }
        // 格式化返回
        return success(AreaUtils.format(area.getId()));
    }


    @GetMapping("/get-by-keyword")
    @Operation(summary = "获得关键词对应的地区名")
    @Parameter(name = "keyword", description = "关键词", required = true)
    @Parameter(name = "level", description = "区域级别", required = true)
    public CommonResult<AreaNodeRespVO> getAreaByKeyword(@RequestParam("keyword") String keyword, @RequestParam("level") Integer level) {
        // 获得城市
        Integer area_id = AreaUtils.search(keyword, level);
        if (area_id == 0) {
            return CommonResult.error(NOT_FOUND.getCode(), "未找到区域信息");
        }
        Area area = AreaUtils.getArea(area_id);
        // 格式化返回
        return success(AreaConvert.INSTANCE.convert(area));
    }

}
