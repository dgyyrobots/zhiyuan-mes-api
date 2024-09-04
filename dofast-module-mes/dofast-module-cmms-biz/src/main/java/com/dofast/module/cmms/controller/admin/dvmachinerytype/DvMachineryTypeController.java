package com.dofast.module.cmms.controller.admin.dvmachinerytype;

import cn.hutool.core.collection.CollUtil;
import com.dofast.module.cmms.controller.admin.dvmachinery.vo.DvMachineryExportReqVO;
import com.dofast.module.cmms.dal.dataobject.dvmachinery.DvMachineryDO;
import com.dofast.module.cmms.enums.ErrorCodeConstants;
import com.dofast.module.cmms.service.dvmachinery.DvMachineryService;
import com.dofast.module.mes.api.autocode.AutoCodeApi;
import com.dofast.module.mes.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.dofast.module.cmms.controller.admin.dvmachinerytype.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvmachinerytype.DvMachineryTypeDO;
import com.dofast.module.cmms.convert.dvmachinerytype.DvMachineryTypeConvert;
import com.dofast.module.cmms.service.dvmachinerytype.DvMachineryTypeService;

@Tag(name = "设备管理 - 设备类型")
@RestController
@RequestMapping("/mes/cmms/dv-machinery-type")
@Validated
public class DvMachineryTypeController {

    @Resource
    private DvMachineryTypeService dvMachineryTypeService;

    @Autowired
    private DvMachineryService dvMachineryService;

    @Resource
    AutoCodeApi autoCodeApi;

    @PostMapping("/create")
    @Operation(summary = "创建设备类型")
    @PreAuthorize("@ss.hasPermission('cmms:dv-machinery-type:create')")
    public CommonResult<Long> createDvMachineryType(@Valid @RequestBody DvMachineryTypeCreateReqVO createReqVO) {
        createReqVO.setMachineryTypeCode(autoCodeApi.genSerialCode(Constant.MACHINERY_TYPE_CODE,null));
        return success(dvMachineryTypeService.createDvMachineryType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备类型")
    @PreAuthorize("@ss.hasPermission('cmms:dv-machinery-type:update')")
    public CommonResult<Boolean> updateDvMachineryType(@Valid @RequestBody DvMachineryTypeUpdateReqVO updateReqVO) {
        dvMachineryTypeService.updateDvMachineryType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备类型")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cmms:dv-machinery-type:delete')")
    public CommonResult<Boolean> deleteDvMachineryType(@RequestParam("id") Long id) {
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        DvMachineryExportReqVO vo = new DvMachineryExportReqVO();
        vo.setMachineryTypeId(id);
        List<DvMachineryDO> dvMachineryDOS = dvMachineryService.getDvMachineryList(vo);
        if(dvMachineryDOS !=null || !dvMachineryDOS.isEmpty()){
            return error(ErrorCodeConstants.DV_MACHINERY_BIND_TYPE);
        }
        dvMachineryTypeService.deleteDvMachineryType(id);
        return success(true);
    }


    @GetMapping("/get")
    @Operation(summary = "获得设备类型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cmms:dv-machinery-type:query')")
    public CommonResult<DvMachineryTypeRespVO> getDvMachineryType(@RequestParam("id") Long id) {
        DvMachineryTypeDO dvMachineryType = dvMachineryTypeService.getDvMachineryType(id);
        return success(DvMachineryTypeConvert.INSTANCE.convert(dvMachineryType));
    }

    @GetMapping("/list")
    @Operation(summary = "获得设备类型列表")
    @Parameter(name = "ids", description = "编号列表", example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cmms:dv-machinery-type:query')")
    public CommonResult<List<DvMachineryTypeRespVO>> getDvMachineryTypeList(@RequestParam(name = "ids", required = false) Collection<Long> ids) {
        List<DvMachineryTypeDO> list = dvMachineryTypeService.getDvMachineryTypeList(ids);
        return success(DvMachineryTypeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list-all-simple")
    @Operation(summary = "获得设备类型简单列表")
    @PreAuthorize("@ss.hasPermission('cmms:dv-machinery-type:query')")
    public CommonResult<List<DvMachineryTypeSimpleVO>> getDvMachineryTypeSimpleList() {
        List<DvMachineryTypeSimpleVO> list = dvMachineryTypeService.getDvMachineryTypeSimpleList();
        return success(list);
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备类型分页")
    @PreAuthorize("@ss.hasPermission('cmms:dv-machinery-type:query')")
    public CommonResult<PageResult<DvMachineryTypeRespVO>> getDvMachineryTypePage(@Valid DvMachineryTypePageReqVO pageVO) {
        PageResult<DvMachineryTypeDO> pageResult = dvMachineryTypeService.getDvMachineryTypePage(pageVO);
        return success(DvMachineryTypeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备类型 Excel")
    @PreAuthorize("@ss.hasPermission('cmms:dv-machinery-type:export')")
    @OperateLog(type = EXPORT)
    public void exportDvMachineryTypeExcel(@Valid DvMachineryTypeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DvMachineryTypeDO> list = dvMachineryTypeService.getDvMachineryTypeList(exportReqVO);
        // 导出 Excel
        List<DvMachineryTypeExcelVO> datas = DvMachineryTypeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "设备类型.xls", "数据", DvMachineryTypeExcelVO.class, datas);
    }

}
