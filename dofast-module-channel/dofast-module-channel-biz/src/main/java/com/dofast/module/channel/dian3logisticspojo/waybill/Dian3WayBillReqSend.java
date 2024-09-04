package com.dofast.module.channel.dian3logisticspojo.waybill;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
@Schema(description = "Dian3 获取电子面单 Request VO")
public class Dian3WayBillReqSend extends Dian3WayBillReqPackagesReceipt{

}
