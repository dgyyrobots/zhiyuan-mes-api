package com.dofast.module.wms.controller.admin.allocatedheader;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.dofast.module.cal.api.team.TeamApi;
import com.dofast.module.cal.api.team.dto.TeamDTO;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.api.ProcessApi.ProcessApi;
import com.dofast.module.pro.api.ProcessApi.dto.ProcessDTO;
import com.dofast.module.pro.api.TaskApi.TaskApi;
import com.dofast.module.pro.api.TaskApi.dto.TaskDTO;
import com.dofast.module.wms.api.MaterialStockApi.MaterialStockERPAPI;
import com.dofast.module.wms.controller.admin.allocatedline.vo.AllocatedLineExportReqVO;
import com.dofast.module.wms.controller.admin.allocatedline.vo.AllocatedLineRespVO;
import com.dofast.module.wms.controller.admin.allocatedrecord.vo.AllocatedRecordExportReqVO;
import com.dofast.module.wms.controller.admin.issueheader.vo.IssueHeaderCreateReqVO;
import com.dofast.module.wms.controller.admin.issueheader.vo.IssueHeaderExportReqVO;
import com.dofast.module.wms.controller.admin.issueheader.vo.IssueHeaderUpdateReqVO;
import com.dofast.module.wms.controller.admin.issueline.vo.IssueLineListVO;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockExportReqVO;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockUpdateReqVO;
import com.dofast.module.wms.convert.issueheader.IssueHeaderConvert;
import com.dofast.module.wms.dal.dataobject.allocatedline.AllocatedLineDO;
import com.dofast.module.wms.dal.dataobject.allocatedrecord.AllocatedRecordDO;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueHeaderDO;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueTxBean;
import com.dofast.module.wms.dal.dataobject.issueline.IssueLineDO;
import com.dofast.module.wms.dal.dataobject.materialstock.MaterialStockDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.mysql.allocatedline.AllocatedLineMapper;
import com.dofast.module.wms.dal.mysql.issueheader.IssueHeaderMapper;
import com.dofast.module.wms.dal.mysql.issueline.IssueLineMapper;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.allocatedline.AllocatedLineService;
import com.dofast.module.wms.service.allocatedrecord.AllocatedRecordService;
import com.dofast.module.wms.service.issueheader.IssueHeaderService;
import com.dofast.module.wms.service.issueline.IssueLineService;
import com.dofast.module.wms.service.materialstock.MaterialStockService;
import com.dofast.module.wms.service.storagecore.StorageCoreService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;

import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.wms.controller.admin.allocatedheader.vo.*;
import com.dofast.module.wms.dal.dataobject.allocatedheader.AllocatedHeaderDO;
import com.dofast.module.wms.convert.allocatedheader.AllocatedHeaderConvert;
import com.dofast.module.wms.service.allocatedheader.AllocatedHeaderService;
import com.dofast.module.wms.dal.dataobject.allocatedheader.AllocatedTxBean;


@Tag(name = "管理后台 - 调拨单头")
@RestController
@RequestMapping("/wms/allocated-header")
@Validated
public class AllocatedHeaderController {

    @Resource
    private AllocatedHeaderService allocatedHeaderService;

    @Resource
    private MaterialStockService materialStockService;

    @Resource
    private AllocatedLineService allocatedLineService;

    @Resource
    private AllocatedRecordService allocatedRecordService;

    @Resource
    private AllocatedLineMapper allocatedLineMapper;

    @Resource
    private StorageCoreService storageCoreService;

    @Resource
    private IssueHeaderService issueHeaderService;

    @Resource
    private IssueHeaderMapper issueHeaderMapper;

    @Resource
    private IssueLineMapper issueLineMapper;

    @Resource
    private MaterialStockERPAPI materialStockERPAPI;

    @Resource
    private ProcessApi processApi;

    @Resource
    private TaskApi taskApi;

    @Resource
    private TeamApi teamApi;

    @Resource
    private StorageLocationService storageLocationService;


    @PostMapping("/create")
    @Operation(summary = "创建调拨单头")
    @PreAuthorize("@ss.hasPermission('wms:allocated-header:create')")
    public CommonResult<Long> createAllocatedHeader(@RequestBody AllocatedHeaderCreateReqVO createReqVO) {
        System.out.println(createReqVO);
        // String类型的true转为boolean类型的true
        boolean bindWorkorder = Boolean.parseBoolean(createReqVO.getBindWorkorder());

        AllocatedHeaderExportReqVO exportReqVO = new AllocatedHeaderExportReqVO();
        LocalDate localDate = LocalDate.now();
        String dateStr = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 生成3位随机数
        int random = (int) ((Math.random() * 9 + 1) * 100);
        if(bindWorkorder){
            // 校验当前的任务单是否已创建调拨单
            String taskCode = createReqVO.getTaskCode();
            TaskDTO taskDTO = taskApi.getTask(taskCode);
            if (taskDTO == null) {
                return error(ErrorCodeConstants.ALLOCATED_TASK_NOT_EXISTS);
            }

            // 班组编码
            String attr1 = taskDTO.getAttr1();
            if(attr1 == null){
                return error(ErrorCodeConstants.ALLOCATED_HEADER_NEED_TASK_TEAM);
            }
            exportReqVO.setTaskCode(taskCode);

            createReqVO.setAllocatedName(taskDTO.getProcessName() + "调拨单" + dateStr + random);
        }else{
            StorageLocationDO location = storageLocationService.getStorageLocation(createReqVO.getLocationId());
            String processName = "";
            if(location!= null){
                ProcessDTO process =  processApi.getcess(location.getProcessCode());
                processName = process.getProcessName();
            }
            createReqVO.setAllocatedName(processName + "调拨单" + dateStr + random);
        }

        // 2025-3-18 调拨改为可分批次调拨, 无需管控在一张调拨单内
        /*List<AllocatedHeaderDO> allocatedHeaderList = allocatedHeaderService.getAllocatedHeaderList(exportReqVO);
        if(!allocatedHeaderList.isEmpty()){
            return error(ErrorCodeConstants.ALLOCATED_TASK_EXISTS);
        }*/
        return success(allocatedHeaderService.createAllocatedHeader(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新调拨单头")
    @PreAuthorize("@ss.hasPermission('wms:allocated-header:update')")
    public CommonResult<Boolean> updateAllocatedHeader(@RequestBody AllocatedHeaderUpdateReqVO updateReqVO) {
        // 更新单头信息
        allocatedHeaderService.updateAllocatedHeader(updateReqVO);
        Long headId = updateReqVO.getId();
        List<AllocatedLineDO> addList = new ArrayList<>();
        List<AllocatedLineDO> editList = new ArrayList<>();
        // 基于单头ID, bomList更新对应单身信息
        List<Map<String, Object>> bomList = updateReqVO.getBomList();
        if (!bomList.isEmpty()) {
            for (Map<String, Object> map : bomList) {
                // 基于物料料号与单头Id校验单身信息是否存在
                String itemCode = (String) map.get("itemCode");
                AllocatedLineDO line = allocatedLineMapper.selectOne(AllocatedLineDO::getItemCode, itemCode, AllocatedLineDO::getAllocatedId, headId);
                if (line == null) {
                    // 新增单头信息
                    AllocatedLineDO allocatedLineDO = new AllocatedLineDO();
                    allocatedLineDO.setAllocatedId(headId);
                    allocatedLineDO.setItemCode(itemCode);
                    allocatedLineDO.setItemName((String) map.get("itemName"));
                    allocatedLineDO.setSpecification((String) map.get("specification"));
                    Object quantityAllocatedObj = map.get("quantityAllocated");
                    if (quantityAllocatedObj instanceof Integer) {
                        allocatedLineDO.setQuantityAllocated(((Integer) quantityAllocatedObj).doubleValue());
                    } else if (quantityAllocatedObj instanceof Double) {
                        allocatedLineDO.setQuantityAllocated((Double) quantityAllocatedObj);
                    } else {
                        // 处理其他类型或默认值
                        allocatedLineDO.setQuantityAllocated(0.0);
                    }
                    allocatedLineDO.setUnitOfMeasure((String) map.get("unitOfMeasure"));
                    allocatedLineDO.setBatchCode((String) map.get("batchCode"));
                    Integer warehouseId = (Integer) map.get("warehouseId");
                    allocatedLineDO.setWarehouseId(warehouseId.longValue());
                    allocatedLineDO.setWarehouseCode((String) map.get("warehouseCode"));
                    allocatedLineDO.setWarehouseName((String) map.get("warehouseName"));
                    Integer locationId = (Integer) map.get("locationId");
                    allocatedLineDO.setLocationId(locationId.longValue());
                    allocatedLineDO.setLocationCode((String) map.get("locationCode"));
                    allocatedLineDO.setLocationName((String) map.get("locationName"));
                    Integer areaId = (Integer) map.get("areaId");
                    allocatedLineDO.setAreaId(areaId.longValue());
                    allocatedLineDO.setAreaCode((String) map.get("areaCode"));
                    allocatedLineDO.setAreaName((String) map.get("areaName"));
                    addList.add(allocatedLineDO);
                } else {
                    // 修改单头信息
                    line.setItemName((String) map.get("itemName"));
                    line.setSpecification((String) map.get("specification"));
                    Object quantityAllocatedObj = map.get("quantityAllocated");
                    if (quantityAllocatedObj instanceof Integer) {
                        line.setQuantityAllocated(((Integer) quantityAllocatedObj).doubleValue());
                    } else if (quantityAllocatedObj instanceof Double) {
                        line.setQuantityAllocated((Double) quantityAllocatedObj);
                    } else {
                        // 处理其他类型或默认值
                        line.setQuantityAllocated(0.0);
                    }
                    line.setUnitOfMeasure((String) map.get("unitOfMeasure"));
                    line.setBatchCode((String) map.get("batchCode"));
                    Integer warehouseId = (Integer) map.get("warehouseId");
                    line.setWarehouseId(warehouseId.longValue());
                    line.setWarehouseCode((String) map.get("warehouseCode"));
                    line.setWarehouseName((String) map.get("warehouseName"));
                    Integer locationId = (Integer) map.get("locationId");
                    line.setLocationId(locationId.longValue());
                    line.setLocationCode((String) map.get("locationCode"));
                    line.setLocationName((String) map.get("locationName"));
                    Integer areaId = (Integer) map.get("areaId");
                    line.setAreaId(areaId.longValue());
                    line.setAreaCode((String) map.get("areaCode"));
                    line.setAreaName((String) map.get("areaName"));
                    editList.add(line);
                }
            }
            if (!addList.isEmpty()) {
                allocatedLineMapper.insertBatch(addList);
            }
            if (!editList.isEmpty()) {
                allocatedLineMapper.updateBatch(editList);
            }
        }
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除调拨单头")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:allocated-header:delete')")
    public CommonResult<Boolean> deleteAllocatedHeader(@RequestParam("id") Long id) {
        allocatedHeaderService.deleteAllocatedHeader(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得调拨单头")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:allocated-header:query')")
    public CommonResult<AllocatedHeaderRespVO> getAllocatedHeader(@RequestParam("id") Long id) {
        AllocatedHeaderDO allocatedHeader = allocatedHeaderService.getAllocatedHeader(id);
        // 初始化单身信息
        AllocatedLineExportReqVO allocatedLine = new AllocatedLineExportReqVO();
        allocatedLine.setAllocatedId(id);
        List<AllocatedLineDO> bomList = allocatedLineService.getAllocatedLineList(allocatedLine);
        List<Map<String, Object>> finList = new ArrayList<>();
        if (!bomList.isEmpty()) {
            for (AllocatedLineDO bom : bomList) {
                Map<String, Object> map = new HashMap<>();
                map.put("itemCode", bom.getItemCode());
                map.put("itemName", bom.getItemName());
                map.put("specification", bom.getSpecification());
                map.put("quantityAllocated", bom.getQuantityAllocated());
                map.put("unitOfMeasure", bom.getUnitOfMeasure());
                map.put("batchCode", bom.getBatchCode());
                map.put("warehouseId", bom.getWarehouseId());
                map.put("warehouseCode", bom.getWarehouseCode());
                map.put("warehouseName", bom.getWarehouseName());
                map.put("locationId", bom.getLocationId());
                map.put("locationCode", bom.getLocationCode());
                map.put("locationName", bom.getLocationName());
                map.put("areaId", bom.getAreaId());
                map.put("areaCode", bom.getAreaCode());
                map.put("areaName", bom.getAreaName());
                map.put("sufficient", "inSufficient");
                finList.add(map);
            }
        }
        AllocatedHeaderRespVO resp = AllocatedHeaderConvert.INSTANCE.convert(allocatedHeader);
        resp.setBomList(finList);
        System.out.println(resp);
        return success(resp);
    }

    @GetMapping("/list")
    @Operation(summary = "获得调拨单头列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:allocated-header:query')")
    public CommonResult<List<AllocatedHeaderRespVO>> getAllocatedHeaderList(@RequestParam("ids") Collection<Long> ids) {
        List<AllocatedHeaderDO> list = allocatedHeaderService.getAllocatedHeaderList(ids);
        return success(AllocatedHeaderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得调拨单头分页")
    @PreAuthorize("@ss.hasPermission('wms:allocated-header:query')")
    public CommonResult<PageResult<AllocatedHeaderRespVO>> getAllocatedHeaderPage(@Valid AllocatedHeaderPageReqVO pageVO) {
        PageResult<AllocatedHeaderDO> pageResult = allocatedHeaderService.getAllocatedHeaderPage(pageVO);
        return success(AllocatedHeaderConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出调拨单头 Excel")
    @PreAuthorize("@ss.hasPermission('wms:allocated-header:export')")
    @OperateLog(type = EXPORT)
    public void exportAllocatedHeaderExcel(@Valid AllocatedHeaderExportReqVO exportReqVO,
                                           HttpServletResponse response) throws IOException {
        List<AllocatedHeaderDO> list = allocatedHeaderService.getAllocatedHeaderList(exportReqVO);
        // 导出 Excel
        List<AllocatedHeaderExcelVO> datas = AllocatedHeaderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "调拨单头.xls", "数据", AllocatedHeaderExcelVO.class, datas);
    }

    @PostMapping("/initBom")
    @Operation(summary = "获得调拨单BOM信息")
    //@PreAuthorize("@ss.hasPermission('wms:allocated-header:query')")
    public CommonResult<List<Map<String, Object>>> initBomList(@Valid @RequestBody String workOrderNo) {
        List<Map<String, Object>> finList = new ArrayList<>();
        List<Map<String, Object>> bomList = allocatedHeaderService.initWorkOrderBom(workOrderNo);
        if (bomList.isEmpty()) {
            return success(bomList);
        }
        for (int i = 0; i < bomList.size(); i++) {
            String itemCode = (String) bomList.get(i).get("itemCode");
            String itemName = (String) bomList.get(i).get("itemName");
            String specification = (String) bomList.get(i).get("specification");
            String unit = (String) bomList.get(i).get("unitOfMeasure");
            Double requiredQuantity = (Double) bomList.get(i).get("requiredQuantity");
            BigDecimal quantity = new BigDecimal(requiredQuantity.toString());

            Map<String, Object> map = new HashMap<>();
            map.put("itemCode", itemCode);
            map.put("itemName", itemName);
            map.put("specification", specification);
            map.put("quantityAllocated", quantity);
            map.put("unitOfMeasure", unit);
          /*  map.put("batchCode", item.getBatchCode());
            map.put("warehouseId", item.getWarehouseId());
            map.put("warehouseCode", item.getWarehouseCode());
            map.put("warehouseName", item.getWarehouseName());
            map.put("locationId", item.getLocationId());
            map.put("locationCode", item.getLocationCode());
            map.put("locationName", item.getLocationName());
            map.put("areaId", item.getAreaId());
            map.put("areaCode", item.getAreaCode());
            map.put("areaName", item.getAreaName());
            map.put("quantityOnhand", item.getQuantityOnhand());
            map.put("sufficient", "inSufficient");*/
            finList.add(map);

         /*   // 获取库存信息, 优先匹配齐套
            MaterialStockExportReqVO materialStockExportReqVO = new MaterialStockExportReqVO();
            materialStockExportReqVO.setItemCode(itemCode);
            materialStockExportReqVO.setUnitOfMeasure(unit);
            materialStockExportReqVO.setWarehouseCode(Constant.WAREHOUSE_CODE);
            List<MaterialStockDO> stockList = materialStockService.getMaterialStockList(materialStockExportReqVO);
            // 按数量从大到小排序
            stockList = stockList.stream()
                    .sorted(Comparator.comparing(MaterialStockDO::getQuantityOnhand, Comparator.reverseOrder()))
                    .collect(Collectors.toList());

            if (stockList.isEmpty()) {
                // 当前bom没有库存信息
                Map<String, Object> map = new HashMap<>();
                map.put("itemCode", itemCode);
                map.put("itemName", itemName);
                map.put("specification", specification);
                map.put("quantityAllocated", quantity);
                map.put("unitOfMeasure", unit);
                map.put("sufficient", "inSufficient");
                finList.add(map);
                continue;
            }
            MaterialStockDO item = stockList.get(0);
            BigDecimal onhand = Optional.ofNullable((item.getQuantityOnhand())).orElse(BigDecimal.ZERO);*/
            /*if (onhand.compareTo(quantity) >= 0) {
                // 满足需求
                Map<String, Object> map = new HashMap<>();
                map.put("itemCode", item.getItemCode());
                map.put("itemName", item.getItemName());
                map.put("specification", item.getSpecification());
                map.put("quantityAllocated", quantity);
                map.put("unitOfMeasure", unit);
                map.put("batchCode", item.getBatchCode());
                map.put("warehouseId", item.getWarehouseId());
                map.put("warehouseCode", item.getWarehouseCode());
                map.put("warehouseName", item.getWarehouseName());
                map.put("locationId", item.getLocationId());
                map.put("locationCode", item.getLocationCode());
                map.put("locationName", item.getLocationName());
                map.put("areaId", item.getAreaId());
                map.put("areaCode", item.getAreaCode());
                map.put("areaName", item.getAreaName());
                map.put("quantityOnhand", item.getQuantityOnhand());
                map.put("sufficient", "sufficient");
                finList.add(map);
                continue;
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("itemCode", item.getItemCode());
                map.put("itemName", item.getItemName());
                map.put("specification", item.getSpecification());
                map.put("quantityAllocated", quantity);
                map.put("unitOfMeasure", unit);
                map.put("batchCode", item.getBatchCode());
                map.put("warehouseId", item.getWarehouseId());
                map.put("warehouseCode", item.getWarehouseCode());
                map.put("warehouseName", item.getWarehouseName());
                map.put("locationId", item.getLocationId());
                map.put("locationCode", item.getLocationCode());
                map.put("locationName", item.getLocationName());
                map.put("areaId", item.getAreaId());
                map.put("areaCode", item.getAreaCode());
                map.put("areaName", item.getAreaName());
                map.put("quantityOnhand", item.getQuantityOnhand());
                map.put("sufficient", "inSufficient");
                finList.add(map);
                continue;
            }*/
        }
        return success(finList);
    }


    /**
     * 执行调拨
     *
     * @return
     *
     */
    @PreAuthorize("@ss.hasPermission('wms:issue-header:update')")
    @Transactional
    @PutMapping("/{allocatedId}")
    @Operation(summary = "执行调拨")
    public CommonResult execute(@PathVariable Long allocatedId) {

        // 查询调拨单头
        AllocatedHeaderDO allocated = allocatedHeaderService.getAllocatedHeader(allocatedId);
        boolean bindWorkorder = Boolean.parseBoolean(allocated.getBindWorkorder());
        if(bindWorkorder){
            TaskDTO taskDTO = taskApi.getTask(allocated.getTaskId());
            if (taskDTO == null) {
                return error(ErrorCodeConstants.ALLOCATED_TASK_NOT_EXISTS);
            }
        }

        Map<String, Object> params = new HashMap<>(); // 用于回传ERP接口
        params.put("allocatedId", allocatedId);

        // ERP暂时没有仓库, 只有库区与库位暂时不传递仓库信息
        params.put("inLocationId" , allocated.getLocationId());
        params.put("inLocationCode", allocated.getLocationCode());
        params.put("inLocationName", allocated.getLocationName());
        params.put("inAreaId"    , allocated.getAreaId());
        params.put("inAreaCode"   , allocated.getAreaCode());
        params.put("inAreaName"   , allocated.getAreaName());
       /* AllocatedLineExportReqVO param = new AllocatedLineExportReqVO();
        param.setAllocatedId(allocatedId);
        List<AllocatedLineDO> lines = allocatedLineService.getAllocatedLineList(param);*/
        // 获取当前的调拨单身
        AllocatedRecordExportReqVO param = new AllocatedRecordExportReqVO();
        param.setAllocatedId(allocatedId);
        param.setAllocatedFlag("N"); // 未执行的调拨单身
        List<AllocatedRecordDO> lines = allocatedRecordService.getAllocatedRecordList(param);

        if(lines.isEmpty() || CollUtil.isEmpty(lines)){
            return error(ErrorCodeConstants.ALLOCATED_HEADER_NEED_PROCESS_LINE);
        }
        List<Map<String, Object>> erpRequestList = new ArrayList<>(); // 用于回传ERP接口
        for (AllocatedRecordDO line : lines) {
            Map<String, Object> map = new HashMap<>();
            map.put("itemCode", line.getItemCode());
            map.put("itemName", line.getItemName());
            map.put("specification", line.getSpecification());
            map.put("quantityAllocated", line.getQuantityAllocated());
            map.put("unitOfMeasure", line.getUnitOfMeasure());
            map.put("batchCode", line.getBatchCode());
            map.put("warehouseId", line.getWarehouseId());
            map.put("warehouseCode", line.getWarehouseCode());
            map.put("warehouseName", line.getWarehouseName());
            map.put("locationId", line.getLocationId());
            map.put("locationCode", line.getLocationCode());
            map.put("locationName", line.getLocationName());
            map.put("areaId", line.getAreaId());
            map.put("areaCode", line.getAreaCode());
            map.put("areaName", line.getAreaName());
            erpRequestList.add(map);
        }
        params.put("allocatedList", erpRequestList);

        List<AllocatedTxBean> beans = allocatedHeaderService.getTxBeans(allocatedId);

        //调用库存核心
        storageCoreService.processAllocated(beans);

        //更新单据状态
        allocated.setStatus(Constant.ORDER_STATUS_CONFIRMED);
        AllocatedHeaderUpdateReqVO updateReqVO = AllocatedHeaderConvert.INSTANCE.convert01(allocated);
        allocatedHeaderService.updateAllocatedHeader(updateReqVO);

        // 更新调拨单单身记录
        for (int i = 0; i < lines.size(); i++) {
            AllocatedRecordDO recordDO = lines.get(i);
            recordDO.setAllocatedFlag("Y");
            recordDO.setUpdateTime(LocalDateTime.now());
        }
        allocatedRecordService.updateAllocatedRecordBatch(lines);

        //String result = materialStockERPAPI.requisitionNoteCreate(params);

        return success(true);

    }

    /**
     * 完成调拨
     * 追加领料单信息
     *
     * @param allocatedId
     * @return
     */
    @GetMapping("/finsh")
    @Operation(summary = "更新调拨单头")
    @PreAuthorize("@ss.hasPermission('wms:allocated-header:create')")
    public CommonResult<Boolean> finshAllocatedHeader(@RequestParam("id") Long allocatedId) {
        AllocatedHeaderDO reqVO = allocatedHeaderService.getAllocatedHeader(allocatedId);
        if(!reqVO.getStatus().equals(Constant.ORDER_STATUS_CONFIRMED)){
            return error(ErrorCodeConstants.ALLOCATED_LINE_STATUS_ERROR);
        }

        boolean bindWorkorder = Boolean.parseBoolean(reqVO.getBindWorkorder());
        if(bindWorkorder){
            TaskDTO taskDTO = taskApi.getTask(reqVO.getTaskId());
            if (taskDTO == null) {
                return error(ErrorCodeConstants.ALLOCATED_TASK_NOT_EXISTS);
            }
            // 班组编码
            String attr1 = taskDTO.getAttr1();
            if(attr1 == null){
                return error(ErrorCodeConstants.ALLOCATED_HEADER_NEED_TASK_TEAM);
            }
        }

        AllocatedRecordExportReqVO param = new AllocatedRecordExportReqVO();
        param.setAllocatedId(allocatedId);
        List<AllocatedRecordDO> bomList = allocatedRecordService.getAllocatedRecordList(param);
        // 将对应库存入库状态改为已入库
        for (AllocatedRecordDO bom : bomList) {
            MaterialStockExportReqVO exportReqVO = new MaterialStockExportReqVO();
            exportReqVO.setItemCode(bom.getItemCode());
            exportReqVO.setBatchCode(bom.getBatchCode());
            List<MaterialStockDO> materialStock = materialStockService.getMaterialStockList(exportReqVO);
            materialStock.get(0).setRecptStatus("Y");
            materialStockService.updateMaterialStock(BeanUtil.toBean(materialStock.get(0), MaterialStockUpdateReqVO.class));
        }
        // 更新单头信息
        reqVO.setStatus(Constant.ORDER_STATUS_FINISHED);
        AllocatedHeaderUpdateReqVO updateReqVO = AllocatedHeaderConvert.INSTANCE.convert01(reqVO);
        allocatedHeaderService.updateAllocatedHeader(updateReqVO);
        Long headId = updateReqVO.getId();




       /* TeamDTO teamDTO = teamApi.getTeamByCode(attr1);
        String machineryCode = null;
        String machineryName = null;
        Long machineryId =  null;
        if(teamDTO!=null){
            machineryCode = teamDTO.getMachineryCode();
            machineryName = teamDTO.getMachineryName();
            machineryId =  teamDTO.getMachineryId();
        }*/
        // TODO 追加ERP调拨单接口
        return success(true);
    }

    /**
     * 追加领料单信息
     *
     * @param allocatedId
     * @return
     */
    @GetMapping("/createIssue")
    @Operation(summary = "更新调拨单头")
    @PreAuthorize("@ss.hasPermission('wms:allocated-header:create')")
    public CommonResult<Boolean> createIssue(@RequestParam("id") Long allocatedId) {
        AllocatedHeaderDO reqVO = allocatedHeaderService.getAllocatedHeader(allocatedId);
        if(!Boolean.parseBoolean(reqVO.getBindWorkorder())){
            return error(ErrorCodeConstants.ALLOCATED_HEADER_NEED_BIND_WORKORDER);
        }

        // 若当前领料单状态不是已完成 , 则提示错误
        if(!reqVO.getStatus().equals(Constant.ORDER_STATUS_FINISHED)){
            return error(ErrorCodeConstants.ALLOCATED_HEADER_NEED_NOT_FINISHED);
        }

        TaskDTO taskDTO = taskApi.getTask(reqVO.getTaskCode());
        if (taskDTO == null) {
            return error(ErrorCodeConstants.ALLOCATED_TASK_NOT_EXISTS);
        }

        // 校验当前任务单是否创建领料单, 若已创建则进行友好提示
        IssueHeaderExportReqVO exportReqVO = new IssueHeaderExportReqVO();

        exportReqVO.setTaskId(reqVO.getTaskId());
        List<IssueHeaderDO> issueHeaderList = issueHeaderService.getIssueHeaderList(exportReqVO);
        if(issueHeaderList!=null && !issueHeaderList.isEmpty()){
            return error(ErrorCodeConstants.ALLOCATED_HEADER_NEED_NOT_CREATE_ISSUE);
        }

        ProcessDTO reqDTO = processApi.getcess(taskDTO.getProcessCode());

        // 班组编码
        String attr1 = taskDTO.getAttr1();
        if(attr1 == null){
            return error(ErrorCodeConstants.ALLOCATED_HEADER_NEED_TASK_TEAM);
        }
        TeamDTO teamDTO = teamApi.getTeamByCode(attr1);
        String machineryCode = null;
        String machineryName = null;
        Long machineryId =  null;
        if(teamDTO!=null){
            machineryCode = teamDTO.getMachineryCode();
            machineryName = teamDTO.getMachineryName();
            machineryId =  teamDTO.getMachineryId();
        }

        // 追加领料单信息
        IssueHeaderDO issueHeaderDO = new IssueHeaderDO();
        issueHeaderDO.setStatus(Constant.ORDER_STATUS_PREPARE);
        issueHeaderDO.setWorkorderId(reqVO.getWorkorderId());
        issueHeaderDO.setWorkorderCode(reqVO.getWorkorderCode());
        issueHeaderDO.setClientCode(reqVO.getClientCode());
        issueHeaderDO.setClientName(reqVO.getClientName());
        issueHeaderDO.setClientId(reqVO.getClientId());
        issueHeaderDO.setWarehouseId(reqVO.getWarehouseId());
        issueHeaderDO.setWarehouseCode(reqVO.getWarehouseCode());
        issueHeaderDO.setWarehouseName(reqVO.getWarehouseName());
        issueHeaderDO.setLocationId(reqVO.getLocationId());
        issueHeaderDO.setLocationCode(reqVO.getLocationCode());
        issueHeaderDO.setLocationName(reqVO.getLocationName());
        issueHeaderDO.setAreaId(reqVO.getAreaId());
        issueHeaderDO.setAreaCode(reqVO.getAreaCode());
        issueHeaderDO.setAreaName(reqVO.getAreaName());
        issueHeaderDO.setIssueDate(LocalDateTime.now());
        issueHeaderDO.setTaskId(reqVO.getTaskId());
        issueHeaderDO.setTaskCode(reqVO.getTaskCode());
        issueHeaderDO.setWorkstationCode(reqVO.getWorkstationCode());
        issueHeaderDO.setWorkstationName(reqVO.getWorkstationName());
        issueHeaderDO.setWorkstationId(reqVO.getWorkstationId());
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String issueCode = "ISSUE" + currentDate.format(formatter) + ThreadLocalRandom.current().nextInt(100, 1000);
        issueHeaderDO.setIssueCode(issueCode);
        issueHeaderDO.setIssueName(reqVO.getAllocatedCode());
        issueHeaderDO.setProcessCode(reqDTO.getProcessCode());
        issueHeaderDO.setProcessName(reqDTO.getProcessName());

        issueHeaderDO.setMachineryId(machineryId);
        issueHeaderDO.setMachineryCode(machineryCode);
        issueHeaderDO.setMachineryName(machineryName);

        IssueHeaderCreateReqVO issueHeader = IssueHeaderConvert.INSTANCE.convert02(issueHeaderDO);
        issueHeaderService.createIssueHeader(issueHeader);


        return success(true);
    }



}
