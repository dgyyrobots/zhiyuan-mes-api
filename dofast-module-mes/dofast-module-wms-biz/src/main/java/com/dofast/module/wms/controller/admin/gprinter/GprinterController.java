package com.dofast.module.wms.controller.admin.gprinter;

import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.module.wms.service.gprinter.GprinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/mes/wms/print")
public class GprinterController {

    @Autowired
    private GprinterService gprinterService;

    @PostMapping("/label")
    public AjaxResult printLabel(@RequestBody Map<String, Object> labelInfo) {
        System.out.println(labelInfo);
        String labelContent = (String) labelInfo.get("itemCode");
        try {
            //gprinterService.printLabel(labelContent);
            return AjaxResult.success("打印成功");
        } catch (Exception e) {
            return AjaxResult.error("打印失败：" + e.getMessage());
        }
    }


}
