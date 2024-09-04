package com.dofast.module.pro.controller.pad.workorder;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.mes.api.ProductBomApi.ProductBomApi;
import com.dofast.module.mes.api.ProductBomApi.dto.MdProductBomDTO;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.controller.admin.workorder.vo.*;
import com.dofast.module.pro.controller.admin.workorderbom.vo.WorkorderBomCreateReqVO;
import com.dofast.module.pro.convert.workorder.WorkorderConvert;
import com.dofast.module.pro.dal.dataobject.workorder.WorkorderDO;
import com.dofast.module.pro.enums.ErrorCodeConstants;
import com.dofast.module.pro.service.workorder.WorkorderService;
import com.dofast.module.pro.service.workorderbom.WorkorderBomService;
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
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "PAD生产管理 - 生产工单")
@RestController
@RequestMapping("/mobile/pro/workorder")
@Validated
public class PadWorkorderController {

    @Resource
    private WorkorderService workorderService;

    /**
     * 获取生产工单详细信息
     */
    @Operation(summary = "查询生产工单详情接口")
    @PreAuthenticated
    @GetMapping(value = "/{workOrderId}")
    public AjaxResult getInfo(@PathVariable("workOrderId") Long workOrderId)
    {
        return AjaxResult.success(workorderService.getWorkorder(workOrderId));
    }
}
