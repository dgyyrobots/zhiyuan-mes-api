package com.dofast.module.channel.controller.admin.shop.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;
import java.time.LocalDateTime;
import lombok.Data;


/**
 * 店铺授权 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ShopExcelVO {

    @ExcelProperty("ID")
    private String id;

    @ExcelProperty("店铺名称")
    private String displayName;

    @ExcelProperty("店铺标识")
    private String name;

    @ExcelProperty("掌柜昵称")
    private String shopNick;

    @ExcelProperty("店铺地址")
    private String shopUrl;

    @ExcelProperty("请求posId")
    private String shopId;

    @ExcelProperty("请求posCode")
    private String shopCode;

    @ExcelProperty(value = "渠道", converter = DictConvert.class)
    @DictFormat("order_from_channel") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String channel;

    @ExcelProperty("状态")
    private Integer status;

    @ExcelProperty("添加时间")
    private LocalDateTime createTime;

    @ExcelProperty("上次拉去时间")
    private LocalDateTime lastTime;

    @ExcelProperty("备注")
    private String remark;

}
