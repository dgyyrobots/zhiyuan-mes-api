package com.dofast.module.wms.controller.pad.wmmaterialstockmob;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.framework.common.pad.controller.PadBaseController;
import com.dofast.framework.common.pad.page.PadTableDataInfo;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockExportReqVO;
import com.dofast.module.wms.dal.dataobject.materialstock.MaterialStockDO;
import com.dofast.module.wms.service.materialstock.MaterialStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Tag(name = "PAD仓储管理 - 库存记录")
@RestController
@RequestMapping("/mobile/wm/wmstock")
@Validated
public class PadMaterialStockController extends PadBaseController {

    @Resource
    private MaterialStockService materialStockService;

    /**
     * 查询库存记录列表
     */
    @Operation(summary = "查询库存现有量")
    @PreAuthenticated
    @GetMapping("/list")
    public PadTableDataInfo list(MaterialStockDO wmMaterialStock)
    {
        startPage();
        MaterialStockExportReqVO materialStockExportReqVO = BeanUtil.toBean(wmMaterialStock, MaterialStockExportReqVO.class);
        List<MaterialStockDO> list = materialStockService.getMaterialStockList(materialStockExportReqVO);
        return getDataTable(list);
    }

    /**
     * 获取库存记录详细信息
     */
    @Operation(summary = "查询库存现有量明细")
    @PreAuthenticated
    @GetMapping(value = "/{materialStockId}")
    public AjaxResult getInfo(@PathVariable("materialStockId") Long materialStockId)
    {
        return AjaxResult.success(materialStockService.getMaterialStock(materialStockId));
    }
}
