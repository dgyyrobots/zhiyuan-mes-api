package com.dofast.module.channel.controller.admin;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.channel.kndpojo.eorder.KDNEOrderReq;
import com.dofast.module.channel.kndpojo.eorder.KDNEOrderRes;
import com.dofast.module.channel.kndpojo.searchmonitor.KDNSearchMonitorReq;
import com.dofast.module.channel.kndpojo.searchmonitor.KDNSearchMonitorRes;
import com.dofast.module.channel.serv.KDNEOrderServ;
import com.dofast.module.channel.serv.KDNSearchMonitorServ;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.CHANNEL_ORDER_GET;

@Tag(name = "管理后台 - 快递鸟")
@RestController
@RequestMapping("/kdn/order")
public class KDNController {
    @Autowired
    KDNSearchMonitorServ kdnSearchMonitorServ;

    @Autowired
    KDNEOrderServ kdneOrderServ;

    @PostMapping("/get-way-bill")
    @Operation(summary = "快递鸟创建电子面单")
    @PermitAll
    @OperateLog(type = CHANNEL_ORDER_GET, userId = -100)
    public CommonResult<KDNEOrderRes> getWayBill(@RequestBody @Valid KDNEOrderReq kdneOrderReq) throws Exception {
        KDNEOrderRes kdneOrderRes= kdneOrderServ.getKDNEOrder(kdneOrderReq);
        return success(kdneOrderRes);
    }

    @PostMapping("/get-monitor-search")
    @Operation(summary = "轨迹查询")
    @PermitAll
    @OperateLog(type = CHANNEL_ORDER_GET, userId = -100)
    public CommonResult<KDNSearchMonitorRes> getMonitorSearch(@RequestBody @Valid KDNSearchMonitorReq kdneOrderReq) throws Exception {
        KDNSearchMonitorRes kdneSearchMonitor = kdnSearchMonitorServ.getKDNESearchMonitor(kdneOrderReq);
        return success(kdneSearchMonitor);
    }

}
