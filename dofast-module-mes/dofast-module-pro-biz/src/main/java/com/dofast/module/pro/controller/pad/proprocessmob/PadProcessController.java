package com.dofast.module.pro.controller.pad.proprocessmob;

import com.dofast.framework.common.pad.controller.PadBaseController;
import com.dofast.framework.common.pad.util.PadStringUtils;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.framework.tenant.core.aop.TenantIgnore;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.controller.admin.process.vo.*;
import com.dofast.module.pro.convert.process.ProcessConvert;
import com.dofast.module.pro.dal.dataobject.process.ProcessDO;
import com.dofast.module.pro.enums.ErrorCodeConstants;
import com.dofast.module.pro.service.process.ProcessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "PAD生产管理 - 生产工序")
@RestController
@RequestMapping("/mobile/pro/process")
@Validated
public class PadProcessController extends PadBaseController {

    @Resource
    private ProcessService processService;

    @GetMapping("/getProcessList")
    @PreAuthenticated
    @PermitAll
    @TenantIgnore
    public AjaxResult getProcessList(){
        ProcessListVO process = new ProcessListVO();
        process.setEnableFlag("Y");
        List<ProcessDO> list =processService.selectAll (process);
        return AjaxResult.success(list);
    }

    /**
     * 获取工序详细信息
     */
    @PreAuthenticated
    @GetMapping(value = "/{processId}")
    @TenantIgnore
    @PermitAll
    public AjaxResult getInfo(@PathVariable("processId") Long processId)
    {
        ProcessDO p = processService.getcess(processId);
        if(PadStringUtils.isNotNull(p)){
            return AjaxResult.success(p);
        }else{
            return AjaxResult.error("未查询到当前工序信息");
        }

    }
}
