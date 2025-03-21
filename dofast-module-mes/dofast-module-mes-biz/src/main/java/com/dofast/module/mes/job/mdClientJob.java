package com.dofast.module.mes.job;

import com.dofast.framework.quartz.core.handler.JobHandler;
import com.dofast.module.mes.convert.mdvendor.PadMdVendorConvert;
import com.dofast.module.mes.dal.dataobject.mdclient.MdClientDO;
import com.dofast.module.mes.dal.dataobject.mdvendor.MdVendorDO;
import com.dofast.module.mes.dal.mysql.mdclient.MdClientMapper;
import com.dofast.module.mes.dal.mysql.mdvendor.PadMdVendorMapper;
import com.dofast.module.mes.service.mdclient.MdClientOracleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component()
public class mdClientJob implements JobHandler {

    @Resource
    private MdClientOracleService mdClientOracleService;

    @Resource
    private MdClientMapper mdClientMapper;

    @Resource
    private PadMdVendorMapper mdVendorMapper;
    @Override
    public String execute(String param) throws Exception {
        // 初始化信息
        // 1-供应商 2-客户 3-二者皆是
        List<Map<String, Object>> vendors = mdClientOracleService.initClient("1"); //
        List<Map<String, Object>> clients = mdClientOracleService.initClient("2"); //
        List<Map<String, Object>> all = mdClientOracleService.initClient("3"); //

        // 追加供应商信息
        List<MdVendorDO> addVendor = new ArrayList<>();
        List<MdVendorDO> updateVendor = new ArrayList<>();
        for (int i = 0; i < vendors.size(); i++) {
            String vendorCode = (String) vendors.get(i).get("CODE"); // 供应商编码
            String vendorName = (String) vendors.get(i).get("NAME"); // 供应商名称
            String vendorFullName = (String) vendors.get(i).get("FULL_NAME"); // 供应商全称
            String vendorAddress = (String) vendors.get(i).get("ADDRESS"); // 供应商地址
            String vendorTel = (String) vendors.get(i).get("PHONE"); // 供应商联系电话
            // 校验当前供应商编码是否已经存在
            MdVendorDO vendorInfo = mdVendorMapper.selectOne(MdVendorDO::getVendorCode, vendorCode, MdVendorDO::getVendorNick, vendorName);
            if (vendorInfo == null) {
                MdVendorDO add = new MdVendorDO();
                add.setVendorCode(vendorCode);
                add.setVendorNick(vendorName);
                add.setVendorName(vendorFullName);
                add.setAddress(vendorAddress);
                add.setTel(vendorTel);
                addVendor.add(add);
            } else {
                vendorInfo.setVendorNick((String) vendors.get(i).get("NAME"));
                vendorInfo.setVendorName((String) vendors.get(i).get("FULL_NAME"));
                vendorInfo.setAddress((String) vendors.get(i).get("ADDRESS"));
                vendorInfo.setTel((String) vendors.get(i).get("PHONE"));
                updateVendor.add(vendorInfo);
            }
        }
        // 追加客户信息
        List<MdClientDO> addClient = new ArrayList<>();
        List<MdClientDO> updateClient = new ArrayList<>();
        for (int i = 0; i < clients.size(); i++) {
            String clientCode = (String) clients.get(i).get("CODE"); // 客户编码
            String clientName = (String) clients.get(i).get("NAME"); // 客户名称
            String clientFullName = (String) clients.get(i).get("FULL_NAME"); // 客户全称
            String clientAddress = (String) clients.get(i).get("ADDRESS"); // 客户地址
            String clientTel = (String) clients.get(i).get("PHONE"); // 客户联系电话
            // 校验当前客户编码是否已经存在
            MdClientDO clientInfo = mdClientMapper.selectOne(MdClientDO::getClientCode, clientCode, MdClientDO::getClientNick, clientName, MdClientDO::getClientName, clientFullName);
            if (clientInfo == null) {
                MdClientDO add = new MdClientDO();
                add.setClientCode(clientCode);
                add.setClientNick(clientName);
                add.setClientName(clientFullName);
                add.setAddress(clientAddress);
                add.setTel(clientTel);
                addClient.add(add);
            } else {
                clientInfo.setClientNick((String) clients.get(i).get("NAME"));
                clientInfo.setClientName((String) clients.get(i).get("FULL_NAME"));
                clientInfo.setAddress((String) clients.get(i).get("ADDRESS"));
                clientInfo.setTel((String) clients.get(i).get("PHONE"));
                updateClient.add(clientInfo);
            }
        }

        // 追加二者皆是信息
        for (int i = 0; i < all.size(); i++) {
            String code = (String) all.get(i).get("CODE"); // 编码
            String name = (String) all.get(i).get("NAME"); // 客户名称
            String fullName = (String) all.get(i).get("FULL_NAME"); // 客户全称
            String address = (String) all.get(i).get("ADDRESS"); // 客户地址
            String tel = (String) all.get(i).get("PHONE"); // 客户联系电话
            // 需要分别校验当前编码在客户信息, 供应商信息中是否存在
            MdClientDO clientInfo = mdClientMapper.selectOne(MdClientDO::getClientCode, code, MdClientDO::getClientNick, name, MdClientDO::getClientName, fullName);
            MdVendorDO vendorInfo = mdVendorMapper.selectOne(MdVendorDO::getVendorCode, code, MdVendorDO::getVendorNick, name, MdVendorDO::getVendorName, fullName);

            // 追加客户信息
            if (clientInfo == null) {
                MdClientDO add = new MdClientDO();
                add.setClientCode(code);
                add.setClientNick(name);
                add.setClientName(fullName);
                add.setAddress(address);
                add.setTel(tel);
                //addClient.add(add);
            } else {
                clientInfo.setClientNick(name);
                clientInfo.setClientName(fullName);
                clientInfo.setAddress(address);
                clientInfo.setTel(tel);
                //updateClient.add(clientInfo);
            }

            // 追加供应商信息
            if (vendorInfo == null) {
                MdVendorDO add = new MdVendorDO();
                add.setVendorCode(code);
                add.setVendorNick(name);
                add.setVendorName(fullName);
                add.setAddress(address);
                add.setTel(tel);
                addVendor.add(add);
            } else {
                vendorInfo.setVendorNick(name);
                vendorInfo.setVendorName(fullName);
                vendorInfo.setAddress(address);
                vendorInfo.setTel(tel);
                updateVendor.add(vendorInfo);
            }
        }
        // 开始处理所有数据
        if (!addClient.isEmpty()) {
            mdClientMapper.insertBatch(addClient);
        }
        if (!updateClient.isEmpty()) {
            mdClientMapper.updateBatch(updateClient);
        }
        if (!addVendor.isEmpty()) {
            mdVendorMapper.insertBatch(addVendor);
        }
        if (!updateVendor.isEmpty()) {
            mdVendorMapper.updateBatch(updateVendor);
        }
        return "success";
    }


}
