package com.dofast.module.system.controller.admin.expresscompany;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.system.controller.admin.expresscompany.vo.*;
import com.dofast.module.system.convert.expresscompany.ExpressCompanyConvert;
import com.dofast.module.system.dal.dataobject.expresscompany.ExpressCompanyDO;
import com.dofast.module.system.service.expresscompany.ExpressCompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 租户物流公司")
@RestController
@RequestMapping("/system/express-company")
@Validated
public class ExpressCompanyController {

    @Resource
    private ExpressCompanyService expressCompanyService;

    @PostMapping("/create")
    @Operation(summary = "创建租户物流公司")
    @PreAuthorize("@ss.hasPermission('system:express-company:create')")
    public CommonResult<Integer> createExpressCompany(@Valid @RequestBody ExpressCompanyCreateReqVO createReqVO) {
        return success(expressCompanyService.createExpressCompany(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新租户物流公司")
    @PreAuthorize("@ss.hasPermission('system:express-company:update')")
    public CommonResult<Boolean> updateExpressCompany(@Valid @RequestBody ExpressCompanyUpdateReqVO updateReqVO) {
        expressCompanyService.updateExpressCompany(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除租户物流公司")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:express-company:delete')")
    public CommonResult<Boolean> deleteExpressCompany(@RequestParam("id") Integer id) {
        expressCompanyService.deleteExpressCompany(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得租户物流公司")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:express-company:query')")
    public CommonResult<ExpressCompanyRespVO> getExpressCompany(@RequestParam("id") String code) {
        ExpressCompanyDO expressCompany = expressCompanyService.getExpressCompanyByCode(code);
        return success(ExpressCompanyConvert.INSTANCE.convert(expressCompany));
    }

    @GetMapping("/list")
    @Operation(summary = "获得租户物流公司列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('system:express-company:query')")
    public CommonResult<List<ExpressCompanyRespVO>> getExpressCompanyList(@RequestParam("ids") Collection<Integer> ids) {
        List<ExpressCompanyDO> list = expressCompanyService.getExpressCompanyList(ids);
        return success(ExpressCompanyConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/get-list")
    @Operation(summary = "获得租户物流公司列表")
    @PreAuthorize("@ss.hasPermission('system:express-company:query')")
    public CommonResult<List<ExpressCompanyRespVO>> getExpressCompanyList() {
        List<ExpressCompanyDO> list = expressCompanyService.getExpressCompanyList();
        return success(ExpressCompanyConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得租户物流公司分页")
    @PreAuthorize("@ss.hasPermission('system:express-company:query')")
    public CommonResult<PageResult<ExpressCompanyRespVO>> getExpressCompanyPage(@Valid ExpressCompanyPageReqVO pageVO) {
        PageResult<ExpressCompanyDO> pageResult = expressCompanyService.getExpressCompanyPage(pageVO);
        return success(ExpressCompanyConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出租户物流公司 Excel")
    @PreAuthorize("@ss.hasPermission('system:express-company:export')")
    @OperateLog(type = EXPORT)
    public void exportExpressCompanyExcel(@Valid ExpressCompanyExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ExpressCompanyDO> list = expressCompanyService.getExpressCompanyList(exportReqVO);
        // 导出 Excel
        List<ExpressCompanyExcelVO> datas = ExpressCompanyConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "租户物流公司.xls", "数据", ExpressCompanyExcelVO.class, datas);
    }

}
