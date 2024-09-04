package com.dofast.module.iot.controller.admin.scene;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.iot.controller.admin.scene.vo.*;
import com.dofast.module.iot.dal.dataobject.scene.SceneDO;
import com.dofast.module.iot.convert.scene.SceneConvert;
import com.dofast.module.iot.service.scene.SceneService;

@Tag(name = "管理后台 - 场景联动")
@RestController
@RequestMapping("/iot/scene")
@Validated
public class SceneController {

    @Resource
    private SceneService sceneService;

    @PostMapping("/create")
    @Operation(summary = "创建场景联动")
    @PreAuthorize("@ss.hasPermission('iot:scene:create')")
    public CommonResult<Long> createScene(@Valid @RequestBody SceneCreateReqVO createReqVO) {
        return success(sceneService.createScene(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新场景联动")
    @PreAuthorize("@ss.hasPermission('iot:scene:update')")
    public CommonResult<Boolean> updateScene(@Valid @RequestBody SceneUpdateReqVO updateReqVO) {
        sceneService.updateScene(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除场景联动")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:scene:delete')")
    public CommonResult<Boolean> deleteScene(@RequestParam("id") Long id) {
        sceneService.deleteScene(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得场景联动")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:scene:query')")
    public CommonResult<SceneRespVO> getScene(@RequestParam("id") Long id) {
        SceneDO scene = sceneService.getScene(id);
        return success(SceneConvert.INSTANCE.convert(scene));
    }

    @GetMapping("/list")
    @Operation(summary = "获得场景联动列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('iot:scene:query')")
    public CommonResult<List<SceneRespVO>> getSceneList(@RequestParam("ids") Collection<Long> ids) {
        List<SceneDO> list = sceneService.getSceneList(ids);
        return success(SceneConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得场景联动分页")
    @PreAuthorize("@ss.hasPermission('iot:scene:query')")
    public CommonResult<PageResult<SceneRespVO>> getScenePage(@Valid ScenePageReqVO pageVO) {
        PageResult<SceneDO> pageResult = sceneService.getScenePage(pageVO);
        return success(SceneConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出场景联动 Excel")
    @PreAuthorize("@ss.hasPermission('iot:scene:export')")
    @OperateLog(type = EXPORT)
    public void exportSceneExcel(@Valid SceneExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<SceneDO> list = sceneService.getSceneList(exportReqVO);
        // 导出 Excel
        List<SceneExcelVO> datas = SceneConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "场景联动.xls", "数据", SceneExcelVO.class, datas);
    }

}
