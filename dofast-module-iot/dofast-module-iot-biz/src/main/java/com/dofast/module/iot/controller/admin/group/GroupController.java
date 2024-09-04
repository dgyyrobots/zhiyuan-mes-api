package com.dofast.module.iot.controller.admin.group;

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

import com.dofast.module.iot.controller.admin.group.vo.*;
import com.dofast.module.iot.dal.dataobject.group.GroupDO;
import com.dofast.module.iot.convert.group.GroupConvert;
import com.dofast.module.iot.service.group.GroupService;

@Tag(name = "管理后台 - 设备分组")
@RestController
@RequestMapping("/iot/group")
@Validated
public class GroupController {

    @Resource
    private GroupService groupService;

    @PostMapping("/create")
    @Operation(summary = "创建设备分组")
    @PreAuthorize("@ss.hasPermission('iot:group:create')")
    public CommonResult<Long> createGroup(@Valid @RequestBody GroupCreateReqVO createReqVO) {
        return success(groupService.createGroup(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备分组")
    @PreAuthorize("@ss.hasPermission('iot:group:update')")
    public CommonResult<Boolean> updateGroup(@Valid @RequestBody GroupUpdateReqVO updateReqVO) {
        groupService.updateGroup(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备分组")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:group:delete')")
    public CommonResult<Boolean> deleteGroup(@RequestParam("id") Long id) {
        groupService.deleteGroup(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备分组")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:group:query')")
    public CommonResult<GroupRespVO> getGroup(@RequestParam("id") Long id) {
        GroupDO group = groupService.getGroup(id);
        return success(GroupConvert.INSTANCE.convert(group));
    }

    @GetMapping("/list")
    @Operation(summary = "获得设备分组列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('iot:group:query')")
    public CommonResult<List<GroupRespVO>> getGroupList(@RequestParam("ids") Collection<Long> ids) {
        List<GroupDO> list = groupService.getGroupList(ids);
        return success(GroupConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备分组分页")
    @PreAuthorize("@ss.hasPermission('iot:group:query')")
    public CommonResult<PageResult<GroupRespVO>> getGroupPage(@Valid GroupPageReqVO pageVO) {
        PageResult<GroupDO> pageResult = groupService.getGroupPage(pageVO);
        return success(GroupConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备分组 Excel")
    @PreAuthorize("@ss.hasPermission('iot:group:export')")
    @OperateLog(type = EXPORT)
    public void exportGroupExcel(@Valid GroupExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<GroupDO> list = groupService.getGroupList(exportReqVO);
        // 导出 Excel
        List<GroupExcelVO> datas = GroupConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "设备分组.xls", "数据", GroupExcelVO.class, datas);
    }

}
