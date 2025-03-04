package com.dofast.module.qms.controller.admin.index;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.qms.enums.ErrorCodeConstants;
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

import com.dofast.module.qms.controller.admin.index.vo.*;
import com.dofast.module.qms.dal.dataobject.index.IndexDO;
import com.dofast.module.qms.convert.index.IndexConvert;
import com.dofast.module.qms.service.index.IndexService;

@Tag(name = "质量管理 - 检测项")
@RestController
@RequestMapping("/mes/qms/index")
@Validated
public class IndexController {

    @Resource
    private IndexService indexService;

    @PostMapping("/create")
    @Operation(summary = "创建检测项")
    @PreAuthorize("@ss.hasPermission('qms:index:create')")
    public CommonResult<Long> createIndex(@Valid @RequestBody IndexCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(indexService.checkIndexCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.INDEX_CODE_EXISTS);
        }
        if(Constant.NOT_UNIQUE.equals(indexService.checkIndexNameUnique(createReqVO))){
            return error(ErrorCodeConstants.INDEX_NAME_EXISTS);
        }
        return success(indexService.createIndex(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新检测项")
    @PreAuthorize("@ss.hasPermission('qms:index:update')")
    public CommonResult<Boolean> updateIndex(@Valid @RequestBody IndexUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(indexService.checkIndexCodeUnique(updateReqVO))){
            return error(ErrorCodeConstants.INDEX_CODE_EXISTS);
        }
        if(Constant.NOT_UNIQUE.equals(indexService.checkIndexNameUnique(updateReqVO))){
            return error(ErrorCodeConstants.INDEX_NAME_EXISTS);
        }
        indexService.updateIndex(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除检测项")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('qms:index:delete')")
    public CommonResult<Boolean> deleteIndex(@RequestParam("id") Long id) {
        indexService.deleteIndex(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得检测项")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('qms:index:query')")
    public CommonResult<IndexRespVO> getIndex(@RequestParam("id") Long id) {
        IndexDO index = indexService.getIndex(id);
        return success(IndexConvert.INSTANCE.convert(index));
    }

    @GetMapping("/getByProcessCode")
    @Operation(summary = "获得检测项")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('qms:index:query')")
    public CommonResult<List<IndexRespVO>> getIndex(@RequestParam("processCode") String processCode) {
        List<IndexDO> indexList = indexService.getIndexByProcessCode(processCode);
        return success(IndexConvert.INSTANCE.convertList(indexList));
    }

    @GetMapping("/list")
    @Operation(summary = "获得检测项列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('qms:index:query')")
    public CommonResult<List<IndexRespVO>> getIndexList(@RequestParam("ids") Collection<Long> ids) {
        List<IndexDO> list = indexService.getIndexList(ids);
        return success(IndexConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得检测项分页")
    @PreAuthorize("@ss.hasPermission('qms:index:query')")
    public CommonResult<PageResult<IndexRespVO>> getIndexPage(@Valid IndexPageReqVO pageVO) {
        PageResult<IndexDO> pageResult = indexService.getIndexPage(pageVO);
        return success(IndexConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出检测项 Excel")
    @PreAuthorize("@ss.hasPermission('qms:index:export')")
    @OperateLog(type = EXPORT)
    public void exportIndexExcel(@Valid IndexExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<IndexDO> list = indexService.getIndexList(exportReqVO);
        // 导出 Excel
        List<IndexExcelVO> datas = IndexConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "检测项.xls", "数据", IndexExcelVO.class, datas);
    }

}
