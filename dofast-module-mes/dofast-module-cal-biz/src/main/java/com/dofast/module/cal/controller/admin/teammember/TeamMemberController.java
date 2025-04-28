package com.dofast.module.cal.controller.admin.teammember;

import com.dofast.framework.common.util.bean.BeanUtils;
import com.dofast.framework.web.core.util.WebFrameworkUtils;
import com.dofast.module.cal.dal.dataobject.team.TeamDO;
import com.dofast.module.cal.service.team.TeamService;
import com.dofast.module.system.api.user.AdminUserApi;
import com.dofast.module.system.api.user.dto.AdminUserRespDTO;
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

import com.dofast.module.cal.controller.admin.teammember.vo.*;
import com.dofast.module.cal.dal.dataobject.teammember.TeamMemberDO;
import com.dofast.module.cal.convert.teammember.TeamMemberConvert;
import com.dofast.module.cal.service.teammember.TeamMemberService;

@Tag(name = "管理后台 - 班组成员")
@RestController
@RequestMapping("/cal/team-member")
@Validated
public class TeamMemberController {

    @Resource
    private TeamMemberService teamMemberService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private TeamService teamSerice;
    @PostMapping("/create")
    @Operation(summary = "创建班组成员")
    @PreAuthorize("@ss.hasPermission('cal:team-member:create')")
    public CommonResult<Long> createTeamMember(@Valid @RequestBody TeamMemberCreateReqVO createReqVO) {
        return success(teamMemberService.createTeamMember(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新班组成员")
    @PreAuthorize("@ss.hasPermission('cal:team-member:update')")
    public CommonResult<Boolean> updateTeamMember(@Valid @RequestBody TeamMemberUpdateReqVO updateReqVO) {
        teamMemberService.updateTeamMember(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除班组成员")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cal:team-member:delete')")
    public CommonResult<Boolean> deleteTeamMember(@RequestParam("id") Long id) {
        teamMemberService.deleteTeamMember(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得班组成员")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cal:team-member:query')")
    public CommonResult<TeamMemberRespVO> getTeamMember(@RequestParam("id") Long id) {
        TeamMemberDO teamMember = teamMemberService.getTeamMember(id);
        return success(TeamMemberConvert.INSTANCE.convert(teamMember));
    }

    @GetMapping("/getByTeamCode")
    @Operation(summary = "获得班组成员")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cal:team-member:query')")
    public CommonResult<List<Map<String, Object>>> getTeamMemberByTeamCode(@RequestParam("teamCode") String teamCode) {
        TeamDO team = teamSerice.getTeam(teamCode);
        List<TeamMemberDO> teamMember = teamMemberService.getTeamMemberByTeamId(team.getId());
        List<Map<String, Object>> result = new ArrayList<>();
        for (TeamMemberDO member : teamMember) {
            Map<String, Object> map = new HashMap<>();
            BeanUtils.copyProperties(member, map);
            map.put("teamId", member.getTeamId());
            map.put("nickName",member.getNickName());
            map.put("userName",member.getUserName());
            map.put("userId",member.getUserId());
            map.put("id",member.getId());
            map.put("principalId", team.getPrincipalId());
            map.put("principalName", team.getPrincipalName());
            result.add(map);
        }
        return success(result);
    }

    @GetMapping("/getByTeamCodeAndShiftInfo")
    @Operation(summary = "获得班组成员")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @Parameter(name = "shiftInfo", description = "班次信息", required = true, example = "1-2-3")
    @PreAuthorize("@ss.hasPermission('cal:team-member:query')")
    public CommonResult<List<Map<String, Object>>> getByTeamCodeAndShiftInfo(@RequestParam("teamCode") String teamCode, @RequestParam("shiftInfo") String shiftInfo) {
        TeamDO team = teamSerice.getTeam(teamCode);
        TeamMemberExportReqVO exportReqVO = new TeamMemberExportReqVO();
        exportReqVO.setTeamId(team.getId());
        if(shiftInfo!="default"&& !"default".equals(shiftInfo)){
            exportReqVO.setShiftInfo(shiftInfo);
        }
        List<TeamMemberDO> teamMember = teamMemberService.getTeamMemberList(exportReqVO);
        List<Map<String, Object>> result = new ArrayList<>();
        for (TeamMemberDO member : teamMember) {
            // 追加当前的岗位信息
            AdminUserRespDTO adminUserRespDTO = adminUserApi.getUser(member.getUserId());
            Set<Long> postIds = adminUserRespDTO.getPostIds();
            Map<String, Object> map = new HashMap<>();
            BeanUtils.copyProperties(member, map);
            map.put("teamId", member.getTeamId());
            map.put("nickName",member.getNickName());
            map.put("userName",member.getUserName());
            map.put("userId",member.getUserId());
            map.put("id",member.getId());
            map.put("principalId", team.getPrincipalId());
            map.put("principalName", team.getPrincipalName());
            map.put("postIds" , postIds);
            result.add(map);
        }
        return success(result);
    }


    @GetMapping("/list")
    @Operation(summary = "获得班组成员列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cal:team-member:query')")
    public CommonResult<List<TeamMemberRespVO>> getTeamMemberList(@RequestParam("ids") Collection<Long> ids) {
        List<TeamMemberDO> list = teamMemberService.getTeamMemberList(ids);
        return success(TeamMemberConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得班组成员分页")
    @PreAuthorize("@ss.hasPermission('cal:team-member:query')")
    public CommonResult<PageResult<TeamMemberRespVO>> getTeamMemberPage(@Valid TeamMemberPageReqVO pageVO) {
        PageResult<TeamMemberDO> pageResult = teamMemberService.getTeamMemberPage(pageVO);
        return success(TeamMemberConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出班组成员 Excel")
    @PreAuthorize("@ss.hasPermission('cal:team-member:export')")
    @OperateLog(type = EXPORT)
    public void exportTeamMemberExcel(@Valid TeamMemberExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<TeamMemberDO> list = teamMemberService.getTeamMemberList(exportReqVO);
        // 导出 Excel
        List<TeamMemberExcelVO> datas = TeamMemberConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "班组成员.xls", "数据", TeamMemberExcelVO.class, datas);
    }

}
