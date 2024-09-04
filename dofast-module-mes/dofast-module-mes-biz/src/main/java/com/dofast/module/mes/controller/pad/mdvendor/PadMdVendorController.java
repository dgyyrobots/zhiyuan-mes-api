package com.dofast.module.mes.controller.pad.mdvendor;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.framework.common.pad.controller.PadBaseController;
import com.dofast.framework.common.pad.page.PadTableDataInfo;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.mes.controller.admin.mdvendor.vo.MdVendorExportReqVO;
import com.dofast.module.mes.dal.dataobject.mdvendor.MdVendorDO;
import com.dofast.module.mes.service.mdvendor.PadMdVendorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.List;

@Tag(name = "PAD管理后台 - 供应商")
@RestController
@RequestMapping("/mobile/md/vendor")
@Validated
public class PadMdVendorController extends PadBaseController {

    @Resource
    private PadMdVendorService padMdVendorService;

    @GetMapping("/list")
    @Operation(summary = "查询供应商清单（分页）")
    @PreAuthenticated
    @PermitAll
    public PadTableDataInfo list(MdVendorDO mdVendorDO) {
        startPage();
        MdVendorExportReqVO mdVendorExportReqVO = BeanUtil.toBean(mdVendorDO, MdVendorExportReqVO.class);
        List<MdVendorDO> list = padMdVendorService.getMdVendorList(mdVendorExportReqVO);
        return getDataTable(list);
    }
}
