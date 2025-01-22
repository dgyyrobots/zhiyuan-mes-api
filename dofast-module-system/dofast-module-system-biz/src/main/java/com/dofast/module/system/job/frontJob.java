package com.dofast.module.system.job;

import com.dofast.framework.quartz.core.handler.JobHandler;

import com.dofast.module.system.convert.dict.DictTypeConvert;
import com.dofast.module.system.dal.dataobject.dict.DictDataDO;
import com.dofast.module.system.dal.dataobject.dict.DictTypeDO;
import com.dofast.module.system.dal.dataobject.user.AdminUserDO;
import com.dofast.module.system.service.dict.DictDataOracleService;
import com.dofast.module.system.service.dict.DictDataService;
import com.dofast.module.system.service.dict.DictTypeService;
import com.dofast.module.system.service.user.AdminUserOracleService;
import com.dofast.module.system.service.user.AdminUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class frontJob implements JobHandler {

    @Resource
    private AdminUserOracleService adminUserOracleService;

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private DictTypeService dictTypeService;

    @Resource
    private DictDataOracleService dictDataOracleService;

    @Resource
    private DictDataService dictDataService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public String execute(String params) {
        List<Map<String, Object>> erpList = adminUserOracleService.initUser(); // 从ERP获取人员信息
        List<Map<String, Object>> wareHouseReasonList = dictDataOracleService.initWarehouseReasonData(); // 从ERP获取仓退原因码
        List<Map<String, Object>> docTypeList = dictDataOracleService.initDocType(); // 从ERP获取单别

        if (!erpList.isEmpty()) {
            List<AdminUserDO> addUserList = new ArrayList<>();
            List<AdminUserDO> updateUserList = new ArrayList<>();
            for (Map<String, Object> map : erpList) {
                String username = (String) map.get("ERPNAME");
                String erpCOde = (String) map.get("ERPCODE");
                String dept = (String) map.get("DEPT");
                // 校验当前ERP编码在MES的人员表中是否存在
                AdminUserDO userDO = adminUserService.getUserByUsername(erpCOde);
                if (userDO == null) {
                    AdminUserDO addUserDO = new AdminUserDO();
                    addUserDO.setUsername(erpCOde); // 配置ERP编码
                    addUserDO.setNickname(username); // 配置ERP名称
                    addUserDO.setErpDept(dept); // 配置ERP部门
                    addUserDO.setPassword(passwordEncoder.encode("AA123123")); // 配置默认密码
                    addUserDO.setStatus(0);
                    addUserList.add(addUserDO);
                } else {
                    userDO.setNickname(username); // 配置ERP名称
                    userDO.setErpDept(dept); // 配置ERP部门
                    userDO.setStatus(0);
                    updateUserList.add(userDO);
                }
            }
            if (!addUserList.isEmpty()) {
                adminUserService.createUserBatch(addUserList);
            }
            if (!updateUserList.isEmpty()) {
                adminUserService.updateUserBatch(updateUserList);
            }
        }
        System.out.println("用户导入成功！");

        /*System.out.println("开始同步仓退/入库原因码");
        List<DictDataDO> addData = new ArrayList<>();
        List<DictDataDO> editData = new ArrayList<>();
        DictTypeDO warehouseReasonDO = dictTypeService.getDictType("erp_warehouse_reason");
        if (warehouseReasonDO == null) {
            // 追加数据字典信息
            warehouseReasonDO = new DictTypeDO();
            warehouseReasonDO.setStatus(0); // 启用
            warehouseReasonDO.setType("erp_warehouse_reason"); // 类型名称
            warehouseReasonDO.setName("仓退/入库原因码"); // 类型名称
            warehouseReasonDO.setRemark("仓退/入库原因码"); // 备注
            dictTypeService.createDictType(DictTypeConvert.INSTANCE.convert01(warehouseReasonDO));
        }

        // 填充ERP仓库原因码信息
        if (!wareHouseReasonList.isEmpty()) {
            Integer dataSerial = 0;
            for (Map<String, Object> map : wareHouseReasonList) {
                String value = (String) map.get("REASON_CODE");
                String label = (String) map.get("CONTENT");
                DictDataDO dataDO = dictDataService.getDictData("erp_warehouse_reason", value);
                if (dataDO == null) {
                    dataDO = new DictDataDO();
                    dataDO.setDictType("erp_warehouse_reason"); // 类型名称
                    dataDO.setLabel(label); // 标签名称
                    dataDO.setValue(value); // 数据值
                    dataDO.setSort(dataSerial);
                    dataSerial++;
                    addData.add(dataDO);
                } else {
                    // 存在则更新标签
                    dataDO.setLabel(label); // 标签名称
                    editData.add(dataDO);
                }
            }
        }
        System.out.println("仓退/入库原因码加载完成!");

        System.out.println("开始追加ER单别");
        DictTypeDO docTypeDO = dictTypeService.getDictType("erp_doc_type");
        if (docTypeDO == null) {
            docTypeDO = new DictTypeDO();
            // 追加数据字典信息
            docTypeDO.setStatus(0); // 启用
            docTypeDO.setType("erp_doc_type"); // 类型名称
            docTypeDO.setName("ERP单别"); // 类型名称
            docTypeDO.setRemark("ERP单别"); // 备注
            dictTypeService.createDictType(DictTypeConvert.INSTANCE.convert01(docTypeDO));
        }

        // 校验是否存在单别信息
        if (!docTypeList.isEmpty()) {
            Integer dataSerial = 0;
            for (Map<String, Object> map : docTypeList) {
                String value = (String) map.get("DOC_TYPE");
                String label = (String) map.get("CONTENT");
                DictDataDO dataDO = dictDataService.getDictData("erp_doc_type", value);
                if (dataDO == null) {
                    dataDO = new DictDataDO();
                    dataDO.setDictType("erp_doc_type");
                    dataDO.setLabel(label); // 标签名称
                    dataDO.setSort(dataSerial);
                    dataDO.setValue(value); // 数据值
                    dataSerial++;
                    addData.add(dataDO);
                } else {
                    dataDO.setLabel(label); // 标签名称
                    editData.add(dataDO);
                }
            }
        }
        // 追加数据字典信息
        if (!addData.isEmpty()) {
            dictDataService.createBatch(addData);
        }
        if (!editData.isEmpty()) {
            dictDataService.updateBatch(editData);
        }
        System.out.print("完成数据字典内容追加!");*/
        return "success";
    }

}
