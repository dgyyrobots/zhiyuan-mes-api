package com.dofast.module.qms.controller.pad.templateindex;

import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.module.qms.controller.admin.templateindex.vo.TemplateIndexExportReqVO;
import com.dofast.module.qms.dal.dataobject.templateindex.TemplateIndexDO;
import com.dofast.module.qms.service.templateindex.TemplateIndexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/mobile/qc/templateindex")
@Validated
@Tag(name = "PAD管理后台 - 质量管理 - 检测模板 - 检测项")
public class PadTemplateIndexController {
    @Resource
    private TemplateIndexService templateIndexService;


    @Operation(summary = "检测模板 - 检测项")
    @GetMapping("/getLines")
    public AjaxResult getTemplateIndexLines() {
        TemplateIndexExportReqVO req = new TemplateIndexExportReqVO();
//        req.setQcTool()
        List<TemplateIndexDO> list = templateIndexService.getTemplateIndexList(req);
        return AjaxResult.success(list);
    }

}
