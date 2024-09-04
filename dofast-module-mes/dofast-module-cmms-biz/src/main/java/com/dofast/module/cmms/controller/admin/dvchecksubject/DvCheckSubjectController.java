package com.dofast.module.cmms.controller.admin.dvchecksubject;

import com.dofast.module.cmms.enums.ErrorCodeConstants;
import com.dofast.module.mes.constant.Constant;
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

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.cmms.controller.admin.dvchecksubject.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvchecksubject.DvCheckSubjectDO;
import com.dofast.module.cmms.convert.dvchecksubject.DvCheckSubjectConvert;
import com.dofast.module.cmms.service.dvchecksubject.DvCheckSubjectService;

@Tag(name = "设备管理 - 点检项目")
@RestController
@RequestMapping("/mes/cmms/dv-check-subject")
@Validated
public class DvCheckSubjectController {

    @Resource
    private DvCheckSubjectService dvCheckSubjectService;

    @PostMapping("/create")
    @Operation(summary = "创建点检项目")
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-subject:create')")
    public CommonResult<Long> createDvCheckSubject(@Valid @RequestBody DvCheckSubjectCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(dvCheckSubjectService.checkSubjectUnique(createReqVO))){
            return error(ErrorCodeConstants.DV_CHECK_SUBJECT_HAS_EXISTS);
        }
        return success(dvCheckSubjectService.createDvCheckSubject(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新点检项目")
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-subject:update')")
    public CommonResult<Boolean> updateDvCheckSubject(@Valid @RequestBody DvCheckSubjectUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(dvCheckSubjectService.checkSubjectUnique(updateReqVO))){
            return error(ErrorCodeConstants.DV_CHECK_SUBJECT_HAS_EXISTS);
        }
        dvCheckSubjectService.updateDvCheckSubject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除点检项目")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-subject:delete')")
    public CommonResult<Boolean> deleteDvCheckSubject(@RequestParam("id") Long id) {
        dvCheckSubjectService.deleteDvCheckSubject(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得点检项目")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-subject:query')")
    public CommonResult<DvCheckSubjectRespVO> getDvCheckSubject(@RequestParam("id") Long id) {
        DvCheckSubjectDO dvCheckSubject = dvCheckSubjectService.getDvCheckSubject(id);
        return success(DvCheckSubjectConvert.INSTANCE.convert(dvCheckSubject));
    }

    @GetMapping("/list")
    @Operation(summary = "获得点检项目列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-subject:query')")
    public CommonResult<List<DvCheckSubjectRespVO>> getDvCheckSubjectList(@RequestParam("ids") Collection<Long> ids) {
        List<DvCheckSubjectDO> list = dvCheckSubjectService.getDvCheckSubjectList(ids);
        return success(DvCheckSubjectConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得点检项目分页")
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-subject:query')")
    public CommonResult<PageResult<DvCheckSubjectRespVO>> getDvCheckSubjectPage(@Valid DvCheckSubjectPageReqVO pageVO) {
        PageResult<DvCheckSubjectDO> pageResult = dvCheckSubjectService.getDvCheckSubjectPage(pageVO);
        return success(DvCheckSubjectConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出点检项目 Excel")
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-subject:export')")
    @OperateLog(type = EXPORT)
    public void exportDvCheckSubjectExcel(@Valid DvCheckSubjectExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DvCheckSubjectDO> list = dvCheckSubjectService.getDvCheckSubjectList(exportReqVO);
        // 导出 Excel
        List<DvCheckSubjectExcelVO> datas = DvCheckSubjectConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "点检项目.xls", "数据", DvCheckSubjectExcelVO.class, datas);
    }

}
