package com.dofast.module.qms.controller.admin.oqc;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.security.core.LoginUser;
import com.dofast.framework.security.core.util.SecurityFrameworkUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.qms.controller.admin.oqcline.vo.OqcLineCreateReqVO;
import com.dofast.module.qms.controller.admin.templateindex.vo.TemplateIndexListVO;
import com.dofast.module.qms.controller.admin.templateproduct.vo.TemplateProductListVO;
import com.dofast.module.qms.dal.dataobject.templateindex.TemplateIndexDO;
import com.dofast.module.qms.dal.dataobject.templateproduct.TemplateProductDO;
import com.dofast.module.qms.enums.ErrorCodeConstants;
import com.dofast.module.qms.service.oqcline.OqcLineService;
import com.dofast.module.qms.service.templateindex.TemplateIndexService;
import com.dofast.module.qms.service.templateproduct.TemplateProductService;
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
import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.qms.controller.admin.oqc.vo.*;
import com.dofast.module.qms.dal.dataobject.oqc.OqcDO;
import com.dofast.module.qms.convert.oqc.OqcConvert;
import com.dofast.module.qms.service.oqc.OqcService;

@Tag(name = "质量管理 - 出货检验单")
@RestController
@RequestMapping("/mes/qms/oqc")
@Validated
public class OqcController {

    @Resource
    private OqcService oqcService;
    @Resource
    private OqcLineService oqcLineService;
    @Resource
    private TemplateProductService templateProductService;
    @Resource
    private TemplateIndexService templateIndexService;

    @Resource
    private AdminUserApi adminUserApi;
    @PostMapping("/create")
    @Operation(summary = "创建出货检验单")
    @PreAuthorize("@ss.hasPermission('qms:oqc:create')")
    public CommonResult<Long> createOqc(@Valid @RequestBody OqcCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(oqcService.checkOqcCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.OQC_CODE_EXISTS);
        }
        //自动获取对应的检测模板
        TemplateProductListVO param = new TemplateProductListVO();
        param.setItemId(createReqVO.getItemId());
        List<TemplateProductDO> templates = templateProductService.getTemplateProductList(param);
        if(CollUtil.isNotEmpty(templates)){
            createReqVO.setTemplateId(templates.get(0).getTemplateId());
        }else{
            return error(ErrorCodeConstants.PRODUCT_NOT_HAS_OQC_TEMPLATE);
        }
        //设置检测人
        LoginUser user = SecurityFrameworkUtils.getLoginUser();
        AdminUserRespDTO adminUserRespDTO = adminUserApi.getUser(user.getId());
        createReqVO.setInspector(adminUserRespDTO.getNickname());

        Long id = oqcService.createOqc(createReqVO);
        createReqVO.setId(id);
        oqcService.generateLine(createReqVO);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新出货检验单")
    @PreAuthorize("@ss.hasPermission('qms:oqc:update')")
    public CommonResult<Boolean> updateOqc(@Valid @RequestBody OqcUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(oqcService.checkOqcCodeUnique(updateReqVO))){
            return error(ErrorCodeConstants.OQC_CODE_EXISTS);
        }
        oqcService.updateOqc(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除出货检验单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('qms:oqc:delete')")
    public CommonResult<Boolean> deleteOqc(@RequestParam("id") Long id) {
        OqcDO oqc = oqcService.getOqc(id);
        if(!Constant.ORDER_STATUS_PREPARE.equals(oqc.getStatus())){
            return error(ErrorCodeConstants.CAN_NOT_DELETE);
        }
        oqcLineService.deleteByOqcId(id);
        oqcService.deleteOqc(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得出货检验单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('qms:oqc:query')")
    public CommonResult<OqcRespVO> getOqc(@RequestParam("id") Long id) {
        OqcDO oqc = oqcService.getOqc(id);
        return success(OqcConvert.INSTANCE.convert(oqc));
    }

    @GetMapping("/list")
    @Operation(summary = "获得出货检验单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('qms:oqc:query')")
    public CommonResult<List<OqcRespVO>> getOqcList(@RequestParam("ids") Collection<Long> ids) {
        List<OqcDO> list = oqcService.getOqcList(ids);
        return success(OqcConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得出货检验单分页")
    @PreAuthorize("@ss.hasPermission('qms:oqc:query')")
    public CommonResult<PageResult<OqcRespVO>> getOqcPage(@Valid OqcPageReqVO pageVO) {
        PageResult<OqcDO> pageResult = oqcService.getOqcPage(pageVO);
        return success(OqcConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出出货检验单 Excel")
    @PreAuthorize("@ss.hasPermission('qms:oqc:export')")
    @OperateLog(type = EXPORT)
    public void exportOqcExcel(@Valid OqcExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OqcDO> list = oqcService.getOqcList(exportReqVO);
        // 导出 Excel
        List<OqcExcelVO> datas = OqcConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "出货检验单.xls", "数据", OqcExcelVO.class, datas);
    }

}
