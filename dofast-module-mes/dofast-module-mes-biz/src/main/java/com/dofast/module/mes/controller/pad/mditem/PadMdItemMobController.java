package com.dofast.module.mes.controller.pad.mditem;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.framework.common.pad.controller.PadBaseController;
import com.dofast.framework.common.pad.page.PadTableDataInfo;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.controller.admin.mditem.vo.*;
import com.dofast.module.mes.convert.mditem.MdItemConvert;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;
import com.dofast.module.mes.dal.dataobject.mditemtype.MdItemTypeDO;
import com.dofast.module.mes.enums.ErrorCodeConstants;
import com.dofast.module.mes.service.mditem.MdItemService;
import com.dofast.module.mes.service.mditemtype.MdItemTypeService;
import com.dofast.module.wms.api.BarcodeApi.BarCodeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "PAD管理后台 - 物料产品")
@RestController
@RequestMapping("/mobile/md/item")
@Validated
public class PadMdItemMobController extends PadBaseController {
    @Resource
    private MdItemService mdItemService;


    @GetMapping("/list")
    @Operation(summary = "查询物料清单（分页）")
    @PreAuthorize("@ss.hasPermission('mes:md-item:query')")
    public PadTableDataInfo getMdItemList(MdItemDO mdItem) {
        startPage();
        MdItemExportReqVO mdItemExportReqVO = BeanUtil.toBean(mdItem, MdItemExportReqVO.class);
        return getDataTable(mdItemService.getMdItemList(mdItemExportReqVO));
    }
}
