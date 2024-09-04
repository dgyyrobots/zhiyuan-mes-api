package com.dofast.module.mes.controller.admin.mdclient;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.enums.ErrorCodeConstants;
import com.dofast.module.wms.api.BarCodeApi;
import com.dofast.module.wms.api.BarcodeApi.BarCodeUtil;
import com.dofast.module.wms.utils.BarcodeUtil;
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

import com.dofast.module.mes.controller.admin.mdclient.vo.*;
import com.dofast.module.mes.dal.dataobject.mdclient.MdClientDO;
import com.dofast.module.mes.convert.mdclient.MdClientConvert;
import com.dofast.module.mes.service.mdclient.MdClientService;

@Tag(name = "管理后台 - 客户")
@RestController
@RequestMapping("/mes/md-client")
@Validated
public class MdClientController {

    @Resource
    private MdClientService mdClientService;
    @Resource
    private BarCodeUtil barCodeApi;
    @PostMapping("/create")
    @Operation(summary = "创建客户")
    @PreAuthorize("@ss.hasPermission('mes:md-client:create')")
    public CommonResult<Long> createMdClient(@Valid @RequestBody MdClientCreateReqVO createReqVO) throws IOException{
        if(Constant.NOT_UNIQUE.equals(mdClientService.checkClientCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_CLIENT_CODE_NOT_UNIQUE);
        }

        if(Constant.NOT_UNIQUE.equals(mdClientService.checkClientNameUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_CLIENT_NAME_NOT_UNIQUE);
        }

        if(Constant.NOT_UNIQUE.equals(mdClientService.checkClientNickUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_CLIENT_NICK_NOT_UNIQUE);
        }
        Long id = mdClientService.createMdClient(createReqVO);
        barCodeApi.generateBarCode(Constant.BARCODE_TYPE_CLIENT,id,createReqVO.getClientCode(),createReqVO.getClientName());
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户")
    @PreAuthorize("@ss.hasPermission('mes:md-client:update')")
    public CommonResult<Boolean> updateMdClient(@Valid @RequestBody MdClientUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(mdClientService.checkClientCodeUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_CLIENT_CODE_NOT_UNIQUE);
        }

        if(Constant.NOT_UNIQUE.equals(mdClientService.checkClientNameUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_CLIENT_NAME_NOT_UNIQUE);
        }

        if(Constant.NOT_UNIQUE.equals(mdClientService.checkClientNickUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_CLIENT_NICK_NOT_UNIQUE);
        }
        mdClientService.updateMdClient(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-client:delete')")
    public CommonResult<Boolean> deleteMdClient(@RequestParam("id") Long id) {
        mdClientService.deleteMdClient(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-client:query')")
    public CommonResult<MdClientRespVO> getMdClient(@RequestParam("id") Long id) {
        MdClientDO mdClient = mdClientService.getMdClient(id);
        return success(MdClientConvert.INSTANCE.convert(mdClient));
    }

    @GetMapping("/list")
    @Operation(summary = "获得客户列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-client:query')")
    public CommonResult<List<MdClientRespVO>> getMdClientList(@RequestParam("ids") Collection<Long> ids) {
        List<MdClientDO> list = mdClientService.getMdClientList(ids);
        return success(MdClientConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户分页")
    @PreAuthorize("@ss.hasPermission('mes:md-client:query')")
    public CommonResult<PageResult<MdClientRespVO>> getMdClientPage(@Valid MdClientPageReqVO pageVO) {
        PageResult<MdClientDO> pageResult = mdClientService.getMdClientPage(pageVO);
        return success(MdClientConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户 Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-client:export')")
    @OperateLog(type = EXPORT)
    public void exportMdClientExcel(@Valid MdClientExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MdClientDO> list = mdClientService.getMdClientList(exportReqVO);
        // 导出 Excel
        List<MdClientExcelVO> datas = MdClientConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "客户.xls", "数据", MdClientExcelVO.class, datas);
    }

}
