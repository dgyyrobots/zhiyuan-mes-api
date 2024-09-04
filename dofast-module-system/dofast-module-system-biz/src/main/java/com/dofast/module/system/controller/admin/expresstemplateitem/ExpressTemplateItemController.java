package com.dofast.module.system.controller.admin.expresstemplateitem;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.*;
import com.dofast.module.system.convert.expresstemplateitem.ExpressTemplateItemConvert;
import com.dofast.module.system.dal.dataobject.expresstemplateitem.ExpressTemplateItemDO;
import com.dofast.module.system.service.expresstemplateitem.ExpressTemplateItemService;
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

@Tag(name = "管理后台 - 运费模板细节")
@RestController
@RequestMapping("/system/express-template-item")
@Validated
public class ExpressTemplateItemController {

    @Resource
    private ExpressTemplateItemService expressTemplateItemService;

    @PostMapping("/create")
    @Operation(summary = "创建运费模板细节")
    @PreAuthorize("@ss.hasPermission('system:express-template-item:create')")
    public CommonResult<Long> createExpressTemplateItem(@Valid @RequestBody ExpressTemplateItemCreateReqVO createReqVO) {
        return success(expressTemplateItemService.createExpressTemplateItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新运费模板细节")
    @PreAuthorize("@ss.hasPermission('system:express-template-item:update')")
    public CommonResult<Boolean> updateExpressTemplateItem(@Valid @RequestBody ExpressTemplateItemUpdateReqVO updateReqVO) {
        expressTemplateItemService.updateExpressTemplateItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除运费模板细节")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:express-template-item:delete')")
    public CommonResult<Boolean> deleteExpressTemplateItem(@RequestParam("id") Long id) {
        expressTemplateItemService.deleteExpressTemplateItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得运费模板细节")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:express-template-item:query')")
    public CommonResult<ExpressTemplateItemRespVO> getExpressTemplateItem(@RequestParam("id") Long id) {
        ExpressTemplateItemDO expressTemplateItem = expressTemplateItemService.getExpressTemplateItem(id);
        return success(ExpressTemplateItemConvert.INSTANCE.convert(expressTemplateItem));
    }

    @GetMapping("/list")
    @Operation(summary = "获得运费模板细节列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('system:express-template-item:query')")
    public CommonResult<List<ExpressTemplateItemRespVO>> getExpressTemplateItemList(@RequestParam("ids") Collection<Long> ids) {
        List<ExpressTemplateItemDO> list = expressTemplateItemService.getExpressTemplateItemList(ids);
        return success(ExpressTemplateItemConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得运费模板细节分页")
    @PreAuthorize("@ss.hasPermission('system:express-template-item:query')")
    public CommonResult<PageResult<ExpressTemplateItemRespVO>> getExpressTemplateItemPage(@Valid ExpressTemplateItemPageReqVO pageVO) {
        PageResult<ExpressTemplateItemDO> pageResult = expressTemplateItemService.getExpressTemplateItemPage(pageVO);
        return success(ExpressTemplateItemConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出运费模板细节 Excel")
    @PreAuthorize("@ss.hasPermission('system:express-template-item:export')")
    @OperateLog(type = EXPORT)
    public void exportExpressTemplateItemExcel(@Valid ExpressTemplateItemExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ExpressTemplateItemDO> list = expressTemplateItemService.getExpressTemplateItemList(exportReqVO);
        // 导出 Excel
        List<ExpressTemplateItemExcelVO> datas = ExpressTemplateItemConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "运费模板细节.xls", "数据", ExpressTemplateItemExcelVO.class, datas);
    }

}
