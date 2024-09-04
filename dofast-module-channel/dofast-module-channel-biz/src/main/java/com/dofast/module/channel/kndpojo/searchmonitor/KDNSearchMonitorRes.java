package com.dofast.module.channel.kndpojo.searchmonitor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.mapstruct.Mapping;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Schema(description = "快递鸟物流模块 轨迹查询 Response VO")
@ToString(callSuper = true)
public class KDNSearchMonitorRes {

    @Schema(description = "普通物流状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String State;

    @Schema(description = "物流状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String StateEx;

    @Schema(description = "快递公司编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "YTO")
    private String ShipperCode;

    @Schema(description = "快递单号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String LogisticCode;

    @Schema(description = "下一个城市", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String NextCity;

    @Schema(description = "当前城市", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String Location;

    @Schema(description = "当前城市", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<Trace> Traces;

    @Data
    public class Trace {
        @Schema(description = "节点时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String AcceptTime;

        @Schema(description = "节点状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String Action;

        @Schema(description = "节点城市", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String Location;

        @Schema(description = "节点操作", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String AcceptStation;
    }
}
