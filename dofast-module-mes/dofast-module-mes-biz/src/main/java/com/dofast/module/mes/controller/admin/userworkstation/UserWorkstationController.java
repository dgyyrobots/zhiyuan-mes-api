package com.dofast.module.mes.controller.admin.userworkstation;

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

import com.dofast.module.mes.controller.admin.userworkstation.vo.*;
import com.dofast.module.mes.dal.dataobject.userworkstation.UserWorkstationDO;
import com.dofast.module.mes.convert.userworkstation.UserWorkstationConvert;
import com.dofast.module.mes.service.userworkstation.UserWorkstationService;

@Tag(name = "管理后台 - 用户工作站绑定关系")
@RestController
@RequestMapping("/mes/user-workstation")
@Validated
public class UserWorkstationController {

    @Resource
    private UserWorkstationService userWorkstationService;

    @PostMapping("/create")
    @Operation(summary = "创建用户工作站绑定关系")
    @PreAuthorize("@ss.hasPermission('mes:user-workstation:create')")
    public CommonResult<Long> createUserWorkstation(@Valid @RequestBody UserWorkstationCreateReqVO createReqVO) {
        return success(userWorkstationService.createUserWorkstation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户工作站绑定关系")
    @PreAuthorize("@ss.hasPermission('mes:user-workstation:update')")
    public CommonResult<Boolean> updateUserWorkstation(@Valid @RequestBody UserWorkstationUpdateReqVO updateReqVO) {
        userWorkstationService.updateUserWorkstation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户工作站绑定关系")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:user-workstation:delete')")
    public CommonResult<Boolean> deleteUserWorkstation(@RequestParam("id") Long id) {
        userWorkstationService.deleteUserWorkstation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户工作站绑定关系")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:user-workstation:query')")
    public CommonResult<UserWorkstationRespVO> getUserWorkstation(@RequestParam("id") Long id) {
        UserWorkstationDO userWorkstation = userWorkstationService.getUserWorkstation(id);
        return success(UserWorkstationConvert.INSTANCE.convert(userWorkstation));
    }

    @GetMapping("/list")
    @Operation(summary = "获得用户工作站绑定关系列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:user-workstation:query')")
    public CommonResult<List<UserWorkstationRespVO>> getUserWorkstationList(@RequestParam("ids") Collection<Long> ids) {
        List<UserWorkstationDO> list = userWorkstationService.getUserWorkstationList(ids);
        return success(UserWorkstationConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户工作站绑定关系分页")
    @PreAuthorize("@ss.hasPermission('mes:user-workstation:query')")
    public CommonResult<PageResult<UserWorkstationRespVO>> getUserWorkstationPage(@Valid UserWorkstationPageReqVO pageVO) {
        PageResult<UserWorkstationDO> pageResult = userWorkstationService.getUserWorkstationPage(pageVO);
        return success(UserWorkstationConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用户工作站绑定关系 Excel")
    @PreAuthorize("@ss.hasPermission('mes:user-workstation:export')")
    @OperateLog(type = EXPORT)
    public void exportUserWorkstationExcel(@Valid UserWorkstationExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<UserWorkstationDO> list = userWorkstationService.getUserWorkstationList(exportReqVO);
        // 导出 Excel
        List<UserWorkstationExcelVO> datas = UserWorkstationConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "用户工作站绑定关系.xls", "数据", UserWorkstationExcelVO.class, datas);
    }

}
