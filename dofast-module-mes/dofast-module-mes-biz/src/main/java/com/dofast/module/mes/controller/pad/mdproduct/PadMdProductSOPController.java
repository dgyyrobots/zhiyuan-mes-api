package com.dofast.module.mes.controller.pad.mdproduct;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.framework.common.pad.controller.PadBaseController;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.mes.controller.admin.mdproductsop.vo.MdProductSopExportReqVO;
import com.dofast.module.mes.dal.dataobject.mdproductsop.MdProductSopDO;
import com.dofast.module.mes.service.mdproductsop.MdProductSopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mobile/md/sop")
@Validated
@Tag(name = "PAD管理后台 - 产品SOP")
public class PadMdProductSOPController extends PadBaseController {

    @Autowired
    private MdProductSopService mdProductSopService;

    /**
     * 查询产品SOP列表
     */
    @Operation(summary = "查询产品SOP信息")
    @PreAuthorize("@ss.hasPermission('mes:md-product-sop:query')")
    @GetMapping("/list")
    public AjaxResult list(MdProductSopDO mdProdutSop)
    {
        startPage();
        MdProductSopExportReqVO mdProductSopExportReqVO = BeanUtil.toBean(mdProdutSop, MdProductSopExportReqVO.class);
        List<MdProductSopDO> list = mdProductSopService.getMdProductSopList(mdProductSopExportReqVO);
        return AjaxResult.success(list);
    }
}
