package com.dofast.module.trade.controller.admin.delivery;

import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.trade.controller.admin.delivery.vo.express.*;
import com.dofast.module.trade.convert.delivery.DeliveryExpressConvert;
import com.dofast.module.trade.dal.dataobject.delivery.DeliveryExpressDO;
import com.dofast.module.trade.service.delivery.DeliveryExpressService;
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
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 快递公司")
@RestController
@RequestMapping("/trade/delivery/express")
@Validated
public class DeliveryExpressController {

    @Resource
    private DeliveryExpressService deliveryExpressService;

    @PostMapping("/create")
    @Operation(summary = "创建快递公司")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express:create')")
    public CommonResult<Long> createDeliveryExpress(@Valid @RequestBody DeliveryExpressCreateReqVO createReqVO) {
        return success(deliveryExpressService.createDeliveryExpress(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新快递公司")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express:update')")
    public CommonResult<Boolean> updateDeliveryExpress(@Valid @RequestBody DeliveryExpressUpdateReqVO updateReqVO) {
        deliveryExpressService.updateDeliveryExpress(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除快递公司")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('trade:delivery:express:delete')")
    public CommonResult<Boolean> deleteDeliveryExpress(@RequestParam("id") Long id) {
        deliveryExpressService.deleteDeliveryExpress(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得快递公司")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express:query')")
    public CommonResult<DeliveryExpressRespVO> getDeliveryExpress(@RequestParam("id") Long id) {
        DeliveryExpressDO deliveryExpress = deliveryExpressService.getDeliveryExpress(id);
        return success(DeliveryExpressConvert.INSTANCE.convert(deliveryExpress));
    }

    @GetMapping("/list-all-simple")
    @Operation(summary = "获取快递公司精简信息列表", description = "主要用于前端的下拉选项")
    public CommonResult<List<DeliveryExpressSimpleRespVO>> getSimpleDeliveryExpressList() {
        List<DeliveryExpressDO> list = deliveryExpressService.getDeliveryExpressListByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(DeliveryExpressConvert.INSTANCE.convertList1(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得快递公司分页")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express:query')")
    public CommonResult<PageResult<DeliveryExpressRespVO>> getDeliveryExpressPage(@Valid DeliveryExpressPageReqVO pageVO) {
        PageResult<DeliveryExpressDO> pageResult = deliveryExpressService.getDeliveryExpressPage(pageVO);
        return success(DeliveryExpressConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出快递公司 Excel")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express:export')")
    @OperateLog(type = EXPORT)
    public void exportDeliveryExpressExcel(@Valid DeliveryExpressExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DeliveryExpressDO> list = deliveryExpressService.getDeliveryExpressList(exportReqVO);
        // 导出 Excel
        List<DeliveryExpressExcelVO> dataList = DeliveryExpressConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "快递公司.xls", "数据", DeliveryExpressExcelVO.class, dataList);
    }
}
