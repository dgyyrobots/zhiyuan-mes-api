package com.dofast.module.wms.controller.admin.sn;

import com.dofast.module.mes.api.autocode.AutoCodeApi;
import com.dofast.module.mes.constant.Constant;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
import java.time.LocalDateTime;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.wms.controller.admin.sn.vo.*;
import com.dofast.module.wms.dal.dataobject.sn.SnDO;
import com.dofast.module.wms.convert.sn.SnConvert;
import com.dofast.module.wms.service.sn.SnService;

@Tag(name = "仓储管理 - SN码")
@RestController
@RequestMapping("/mes/wms/sn")
@Validated
public class SnController {

    @Resource
    private SnService snService;
    @Resource
    private AutoCodeApi autoCodeApi;

    @PostMapping("/create")
    @Operation(summary = "创建SN码")
    @PreAuthorize("@ss.hasPermission('wms:sn:create')")
    public CommonResult<Boolean> createSn(@Valid @RequestBody SnCreateReqVO createReqVO) {
        LocalDateTime now = LocalDateTime.now();
        createReqVO.setGenDate(now);

        String SNCode= null;

        if(createReqVO.getSnNum()>0){
            for(int i=0;i<createReqVO.getSnNum();i++){
                SNCode = autoCodeApi.genSerialCode(Constant.SN_CODE,createReqVO.getItemCode());
                createReqVO.setSnCode(SNCode);
                snService.createSn(createReqVO);
            }
        }

        return success(true);

    }

    @PutMapping("/update")
    @Operation(summary = "更新SN码")
    @PreAuthorize("@ss.hasPermission('wms:sn:update')")
    public CommonResult<Boolean> updateSn(@Valid @RequestBody SnUpdateReqVO updateReqVO) {
        snService.updateSn(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除SN码")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:sn:delete')")
    public CommonResult<Boolean> deleteSn(@RequestParam("id") Long id) {
        snService.deleteSn(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得SN码")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:sn:query')")
    public CommonResult<SnRespVO> getSn(@RequestParam("id") Long id) {
        SnDO sn = snService.getSn(id);
        return success(SnConvert.INSTANCE.convert(sn));
    }

    @GetMapping("/list")
    @Operation(summary = "获得SN码列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:sn:query')")
    public CommonResult<List<SnRespVO>> getSnList(@RequestParam("ids") Collection<Long> ids) {
        List<SnDO> list = snService.getSnList(ids);
        return success(SnConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得SN码分页")
    @PreAuthorize("@ss.hasPermission('wms:sn:query')")
    public CommonResult<PageResult<SnRespVO>> getSnPage(@Valid SnPageReqVO pageVO) {
        PageResult<SnDO> pageResult = snService.getSnPage(pageVO);
        return success(SnConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/pageSn")
    @Operation(summary = "获得SN码分页1")
    @PreAuthorize("@ss.hasPermission('wms:sn:query')")
    public CommonResult<PageResult<SnRespVO>> getSnPageSn(@Valid SnPageReqVO pageVO) {
        PageResult<SnDO> pageResult = snService.getSnPageSn(pageVO);
        return success(SnConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出SN码 Excel")
    @PreAuthorize("@ss.hasPermission('wms:sn:export')")
    @OperateLog(type = EXPORT)
    public void exportSnExcel(@Valid SnExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<SnDO> list = snService.getSnList(exportReqVO);
        // 导出 Excel
        List<SnExcelVO> datas = SnConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "SN码.xls", "数据", SnExcelVO.class, datas);
    }

}
