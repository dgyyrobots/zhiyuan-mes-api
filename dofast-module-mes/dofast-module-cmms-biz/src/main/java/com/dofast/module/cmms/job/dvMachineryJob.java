package com.dofast.module.cmms.job;

import com.dofast.module.cmms.dal.dataobject.dvmachinery.DvMachineryDO;
import com.dofast.module.cmms.dal.mysql.dvmachinery.DvMachineryMapper;
import com.dofast.module.cmms.service.dvmachinery.DvMachineryOracleService;
import com.dofast.module.cmms.service.dvmachinery.DvMachineryService;
import org.springframework.stereotype.Component;
import com.dofast.framework.quartz.core.handler.JobHandler;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


@Component
public class dvMachineryJob  implements JobHandler {

    @Resource
    private DvMachineryOracleService dvMachineryOracleService;

    @Resource
    private DvMachineryMapper dvMachineryMapper;

    /**
     * 从ERP获取设备台账信息
     * @param param 参数
     * @return
     * @throws Exception
     */
    @Override
    public String execute(String param) throws Exception {
        // TODO: 从ERP获取设备台账信息
        List<Map<String, Object>> machineryList = dvMachineryOracleService.initMachineryList();
        if (machineryList.size() == 0) {
            return "没有找到设备台账信息!";
        }
        // 设备信息
        List<DvMachineryDO> machineryToInsert = new ArrayList<>(); // 待插入的设备信息
        List<DvMachineryDO> machineryToUp = new ArrayList<>(); // 待更新的设备信息
        for (Map<String, Object> machinery : machineryList) {
            // TODO: 插入设备台账数据
            String machineryCode = (String) machinery.get("MACHINERYCODE");
            // 校验是否存在
            DvMachineryDO exist = dvMachineryMapper.selectOne(DvMachineryDO::getMachineryCode, machineryCode);
            if (exist == null) {
                DvMachineryDO machineryDO = new DvMachineryDO(); // TODO: 转换为DO
                machineryDO.setMachineryCode(machineryCode);
                machineryDO.setMachineryName((String) machinery.get("MACHINERYNAME"));
                machineryDO.setMachinerySpec((String) machinery.get("MACHINERYSPEC"));
                machineryDO.setMachineryBrand((String) machinery.get("MACHINERYBRAND"));
                machineryToInsert.add(machineryDO);
            } else {
                // TODO: 更新设备台账数据
                exist.setMachineryName((String) machinery.get("MACHINERYNAME"));
                exist.setMachinerySpec((String) machinery.get("MACHINERYSPEC"));
                exist.setMachineryBrand((String) machinery.get("MACHINERYBRAND"));
                machineryToUp.add(exist);
            }
        }
        if(!machineryToInsert.isEmpty()){
            dvMachineryMapper.insertBatch(machineryToInsert);
        }
        if (!machineryToUp.isEmpty()){
            dvMachineryMapper.updateBatch(machineryToUp);
        }
        return "设备台账信息更新成功!";
    }



}
