package com.dofast.module.channel.dian3logisticspojo.stdTpl;

import com.dofast.module.channel.dian3logisticspojo.waybill.Dian3WayBillReqSend;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@Schema(description = "Dian3 打印模板查询 Response VO")
public class Dian3StdTplRes {
    @Schema(description = "标准打印模板ID，若为平台模板，则为平台的标准模板ID",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer templateId;

    @Schema(description = "标准打印模板编码，和templateId 两者至少有一个",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String templateCode;

    @Schema(description = "打印模板来源", requiredMode = Schema.RequiredMode.REQUIRED)
    private String templateSource;

    @Schema(description = "三方平台类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private String platform;

    @Schema(description = "三方平台业务类型。对于物流单模板来说，其对应的则是上游的电子面单业务类型",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String platformType;

    @Schema(description = "打印模板的名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String templateName;

    @Schema(description = "打印模板的在线URL", requiredMode = Schema.RequiredMode.REQUIRED)
    private String templateUrl;

    @Schema(description = "打印模板预览图片的URL，可能为空", requiredMode = Schema.RequiredMode.REQUIRED)
    private String previewImg;

    @Schema(description = "物流单会有该字段：打印模板对应物流公司的平台编码，不同平台不一样",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String cpCode;

    @Schema(description = "模板的总宽度，单位mm。值为平台提供，不一定准确，仅供参考",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String width;

    @Schema(description = "模板的总高度，单位mm。值为平台提供，不一定准确，仅供参考",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String height;

    @Schema(description = "OMS系统物流ID，物流单打印模板专属。", requiredMode = Schema.RequiredMode.REQUIRED)
    private String logisticsId;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.REQUIRED)
    private String remark;
}
