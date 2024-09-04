package com.dofast.module.cal.controller.admin.team;

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

import com.dofast.module.cal.controller.admin.team.vo.*;
import com.dofast.module.cal.dal.dataobject.team.TeamDO;
import com.dofast.module.cal.convert.team.TeamConvert;
import com.dofast.module.cal.service.team.TeamService;

@Tag(name = "管理后台 - 班组")
@RestController
@RequestMapping("/cal/team")
@Validated
public class TeamController {

    @Resource
    private TeamService teamService;

    @PostMapping("/create")
    @Operation(summary = "创建班组")
    @PreAuthorize("@ss.hasPermission('cal:team:create')")
    public CommonResult<Long> createTeam(@Valid @RequestBody TeamCreateReqVO createReqVO) {
        return success(teamService.createTeam(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新班组")
    @PreAuthorize("@ss.hasPermission('cal:team:update')")
    public CommonResult<Boolean> updateTeam(@Valid @RequestBody TeamUpdateReqVO updateReqVO) {
        teamService.updateTeam(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除班组")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cal:team:delete')")
    public CommonResult<Boolean> deleteTeam(@RequestParam("id") Long id) {
        teamService.deleteTeam(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得班组")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cal:team:query')")
    public CommonResult<TeamRespVO> getTeam(@RequestParam("id") Long id) {
        TeamDO team = teamService.getTeam(id);
        return success(TeamConvert.INSTANCE.convert(team));
    }

    @GetMapping("/list")
    @Operation(summary = "获得班组列表")
    @Parameter(name = "ids", description = "编号列表", example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cal:team:query')")
    public CommonResult<List<TeamRespVO>> getTeamList(@RequestParam(name = "ids", required = false) Collection<Long> ids) {
        List<TeamDO> list = teamService.getTeamList(ids);
        return success(TeamConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得班组分页")
    @PreAuthorize("@ss.hasPermission('cal:team:query')")
    public CommonResult<PageResult<TeamRespVO>> getTeamPage(@Valid TeamPageReqVO pageVO) {
        PageResult<TeamDO> pageResult = teamService.getTeamPage(pageVO);
        return success(TeamConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出班组 Excel")
    @PreAuthorize("@ss.hasPermission('cal:team:export')")
    @OperateLog(type = EXPORT)
    public void exportTeamExcel(@Valid TeamExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<TeamDO> list = teamService.getTeamList(exportReqVO);
        // 导出 Excel
        List<TeamExcelVO> datas = TeamConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "班组.xls", "数据", TeamExcelVO.class, datas);
    }

}
