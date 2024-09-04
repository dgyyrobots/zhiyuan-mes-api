package com.dofast.module.wms.controller.pad.wmwarehousemob;

import com.dofast.framework.common.pad.controller.PadBaseController;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Tag(name = "PAD管理后台 - 仓库")
@RestController
@RequestMapping("/mobile/wm/warehouse")
@Validated
public class PadWarehouseController extends PadBaseController {

    @Resource
    private WarehouseService warehouseService;

    /**
     * 查询树型的列表
     * @return
     */
    @GetMapping("/getTreeList")
    public AjaxResult getTreeList(){
        return AjaxResult.success(warehouseService.getTreeList());
    }

}
