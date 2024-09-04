package com.dofast.module.system.controller.admin.systemconfig;

import org.springframework.transaction.annotation.Transactional;
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

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;
import static com.dofast.module.infra.enums.ErrorCodeConstants.CONFIG_NOT_EXISTS;

import com.dofast.module.system.controller.admin.systemconfig.vo.*;
import com.dofast.module.system.dal.dataobject.systemconfig.SystemConfigDO;
import com.dofast.module.system.convert.systemconfig.SystemConfigConvert;
import com.dofast.module.system.service.systemconfig.SystemConfigService;

@Tag(name = "管理后台 - 租户配置")
@RestController
@RequestMapping("/system/config")
@Validated
public class SystemConfigController {

    @Resource
    private SystemConfigService configService;

    @PostMapping("/create")
    @Operation(summary = "创建参数配置")
    @PreAuthorize("@ss.hasPermission('system:config:create')")
    public CommonResult<List<Integer>> createConfig(@Valid @RequestBody Collection<SystemConfigCreateReqVO> createReqVOs) {
        List<Integer> list = new ArrayList<>();
        for (SystemConfigCreateReqVO createReqVO: createReqVOs){
            Integer id = configService.createConfig(createReqVO);
            list.add(id);
        }
        return success(list);
    }

    @PostMapping("/create-all")
    @Operation(summary = "批量创建参数配置")
    @PreAuthorize("@ss.hasPermission('system:config:create')")
    public CommonResult<Boolean> createAllConfig(@Valid @RequestBody SystemConfigAllReqVO systemConfigAllReqVO) {
        String Key = systemConfigAllReqVO.getKey();
        List<Value> value = systemConfigAllReqVO.getValue();
        Collection<SystemConfigDO> collection=new ArrayList<>();
        for (Value value1 : value) {
            SystemConfigDO configKey = new SystemConfigDO();
            configKey.setAppModule(Key);
            configKey.setConfigKey(value1.getConfigKey());
            configKey.setValue(value1.getValue());
            configKey.setConfigDesc(value1.getConfigDesc());
            collection.add(configKey);
        }
        configService.createAllConfig(collection);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新参数配置")
    @PreAuthorize("@ss.hasPermission('system:config:update')")
    public CommonResult<Boolean> updateConfig(@Valid @RequestBody Collection<SystemConfigUpdateReqVO> updateReqVOs) {
        for (SystemConfigUpdateReqVO updateReqVO: updateReqVOs){
            configService.updateConfig(updateReqVO);
        }
        return success(true);
    }

    @PutMapping("/update-All")
    @Operation(summary = "批量更新参数配置")
    @PreAuthorize("@ss.hasPermission('system:config:update')")
    public CommonResult<Boolean> updateAllConfig(@Valid @RequestBody SystemConfigAllReqVO systemConfigAllReqVO) {
        String Key = systemConfigAllReqVO.getKey();
        List<Value> value = systemConfigAllReqVO.getValue();
        Collection<SystemConfigDO> collection=new ArrayList<>();
        for (Value value1 : value) {
            SystemConfigDO configKey = configService.getConfigByConfigKey(value1.getConfigKey());
            if (configKey==null){
                throw exception(CONFIG_NOT_EXISTS);
            }
            configKey.setAppModule(Key);
            configKey.setConfigKey(value1.getConfigKey());
            configKey.setValue(value1.getValue());
            configKey.setConfigDesc(value1.getConfigDesc());
            collection.add(configKey);
        }
        configService.updateAllConfig(collection);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除参数配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:config:delete')")
    public CommonResult<Boolean> deleteConfig(@RequestParam("id") Integer id) {
        configService.deleteConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得参数配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:config:query')")
    public CommonResult<SystemConfigRespVO> getConfig(@RequestParam("id") Integer id) {
        SystemConfigDO config = configService.getConfig(id);
        return success(SystemConfigConvert.INSTANCE.convert(config));
    }

    @GetMapping("/key/{key}")
    @Operation(summary = "获得租户配置")
    @Parameter(name = "key", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:config:query')")
    public CommonResult<SystemConfigRespVO> getConfigByKey(@PathVariable("key") String key) {
        SystemConfigDO config = configService.getConfigByKey(key);
        return success(SystemConfigConvert.INSTANCE.convert(config));
    }

    @GetMapping("/list")
    @Operation(summary = "获得参数配置列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('system:config:query')")
    public CommonResult<List<SystemConfigRespVO>> getConfigList(@RequestParam("ids") Collection<Integer> ids) {
        List<SystemConfigDO> list = configService.getConfigList(ids);
        return success(SystemConfigConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得参数配置分页")
    @PreAuthorize("@ss.hasPermission('system:config:query')")
    public CommonResult<PageResult<SystemConfigRespVO>> getConfigPage(@Valid SystemConfigPageReqVO pageVO) {
        PageResult<SystemConfigDO> pageResult = configService.getConfigPage(pageVO);
        return success(SystemConfigConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出参数配置 Excel")
    @PreAuthorize("@ss.hasPermission('system:config:export')")
    @OperateLog(type = EXPORT)
    public void exportConfigExcel(@Valid SystemConfigExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<SystemConfigDO> list = configService.getConfigList(exportReqVO);
        // 导出 Excel
        List<SystemConfigExcelVO> datas = SystemConfigConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "参数配置.xls", "数据", SystemConfigExcelVO.class, datas);
    }

}
