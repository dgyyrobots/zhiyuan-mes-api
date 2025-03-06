package com.dofast.module.wms.controller.admin.issueline;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.framework.web.core.util.WebFrameworkUtils;
import com.dofast.module.pro.api.TaskApi.dto.TaskDTO;
import com.dofast.module.system.api.user.AdminUserApi;
import com.dofast.module.system.api.user.dto.AdminUserRespDTO;
import com.dofast.module.wms.controller.admin.allocatedheader.vo.AllocatedHeaderExportReqVO;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueHeaderDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.service.issueheader.IssueHeaderService;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import com.dofast.module.wms.enums.ErrorCodeConstants;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;
import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.wms.controller.admin.issueline.vo.*;
import com.dofast.module.wms.dal.dataobject.issueline.IssueLineDO;
import com.dofast.module.wms.convert.issueline.IssueLineConvert;
import com.dofast.module.wms.service.issueline.IssueLineService;
import org.springframework.web.servlet.View;

@Tag(name = "仓储管理 - 生产领料单行")
@RestController
@RequestMapping("/mes/wms/issue-line")
@Validated
public class IssueLineController {

    @Resource
    private IssueLineService issueLineService;

    @Resource
    private IssueHeaderService issueHeaderService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @Resource
    private AdminUserApi adminUserApi;

    @PostMapping("/create")
    @Operation(summary = "创建生产领料单行")
    @PreAuthorize("@ss.hasPermission('wms:issue-line:create')")
    public CommonResult<Long> createIssueLine(@Valid @RequestBody IssueLineCreateReqVO createReqVO) {

        // 获取当前单身信息
        IssueHeaderDO issueHeaderDO = issueHeaderService.getIssueHeader(createReqVO.getIssueId());
        if(issueHeaderDO.getMachineryCode() != null){
            createReqVO.setMachineryCode(issueHeaderDO.getMachineryCode());
        }
        if(issueHeaderDO.getMachineryName() != null){
            createReqVO.setMachineryName(issueHeaderDO.getMachineryName());
        }
        if(issueHeaderDO.getMachineryId() != null){
            createReqVO.setMachineryId(String.valueOf(issueHeaderDO.getMachineryId()));
        }

        if(StrUtils.isNotNull(createReqVO.getWarehouseId())){
            WarehouseDO warehouseDO = warehouseService.getWarehouse(createReqVO.getWarehouseId());
            createReqVO.setWarehouseCode(warehouseDO.getWarehouseCode());
            createReqVO.setWarehouseName(warehouseDO.getWarehouseName());
        }
        if(StrUtils.isNotNull(createReqVO.getLocationId())){
            StorageLocationDO storageLocationDO = storageLocationService.getStorageLocation(createReqVO.getLocationId());
            createReqVO.setLocationCode(storageLocationDO.getLocationCode());
            createReqVO.setLocationName(storageLocationDO.getLocationName());

            // 追加校验, 无法添加非库存的数据
            String processCode = issueHeaderDO.getProcessCode();
            if(!processCode.equals(storageLocationDO.getProcessCode()) &&  !"AM007".equals(storageLocationDO.getProcessCode())){
                return error(ErrorCodeConstants.ISSUE_HEADER_NO_PROCESS);
            }
        }
        if(StrUtils.isNotNull(createReqVO.getAreaId())){
            StorageAreaDO storageAreaDO = storageAreaService.getStorageArea(createReqVO.getAreaId());
            createReqVO.setAreaCode(storageAreaDO.getAreaCode());
            createReqVO.setAreaName(storageAreaDO.getAreaName());
        }
        return success(issueLineService.createIssueLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产领料单行")
    @PreAuthorize("@ss.hasPermission('wms:issue-line:update')")
    public CommonResult<Boolean> updateIssueLine(@Valid @RequestBody IssueLineUpdateReqVO updateReqVO) {
        if(StrUtils.isNotNull(updateReqVO.getWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(updateReqVO.getWarehouseId());
            updateReqVO.setWarehouseCode(warehouse.getWarehouseCode());
            updateReqVO.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StrUtils.isNotNull(updateReqVO.getLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(updateReqVO.getLocationId());
            updateReqVO.setLocationCode(location.getLocationCode());
            updateReqVO.setLocationName(location.getLocationName());
        }
        if(StrUtils.isNotNull(updateReqVO.getAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(updateReqVO.getAreaId());
            updateReqVO.setAreaCode(area.getAreaCode());
            updateReqVO.setAreaName(area.getAreaName());
        }
        issueLineService.updateIssueLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除生产领料单行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:issue-line:delete')")
    public CommonResult<Boolean> deleteIssueLine(@RequestParam("id") Long id) {
        issueLineService.deleteIssueLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得生产领料单行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:issue-line:query')")
    public CommonResult<IssueLineRespVO> getIssueLine(@RequestParam("id") Long id) {
        IssueLineDO issueLine = issueLineService.getIssueLine(id);
        return success(IssueLineConvert.INSTANCE.convert(issueLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得生产领料单行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:issue-line:query')")
    public CommonResult<List<IssueLineRespVO>> getIssueLineList(@RequestParam("ids") Collection<Long> ids) {
        List<IssueLineDO> list = issueLineService.getIssueLineList(ids);
        return success(IssueLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得生产领料单行分页")
    @PreAuthorize("@ss.hasPermission('wms:issue-line:query')")
    public CommonResult<PageResult<IssueLineRespVO>> getIssueLinePage(@Valid IssueLinePageReqVO pageVO) {
        PageResult<IssueLineDO> pageResult = issueLineService.getIssueLinePage(pageVO);
        List<IssueLineDO> voList = pageResult.getList();
        // 循环列表, 每一列数据的创建人为id, 去用户表转换为用户昵称
        for (IssueLineDO vo : voList) {
            AdminUserRespDTO adminUserRespDTO = adminUserApi.getUser(Long.valueOf(vo.getCreator()));
            adminUserRespDTO.getNickname();
            vo.setCreator(adminUserRespDTO.getNickname());
        }
        System.out.println(voList);
        System.out.println(pageResult);

        return success(IssueLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出生产领料单行 Excel")
    @PreAuthorize("@ss.hasPermission('wms:issue-line:export')")
    @OperateLog(type = EXPORT)
    public void exportIssueLineExcel(@Valid IssueLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<IssueLineDO> list = issueLineService.getIssueLineList(exportReqVO);
        // 导出 Excel
        List<IssueLineExcelVO> datas = IssueLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "生产领料单行.xls", "数据", IssueLineExcelVO.class, datas);
    }

}
