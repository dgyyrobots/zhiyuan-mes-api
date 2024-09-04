package com.dofast.module.iot.controller.admin.scene.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 场景联动 Excel VO
 *
 * @author 惠智造
 */
@Data
public class SceneExcelVO {

    @ExcelProperty("场景ID")
    private Long id;

    @ExcelProperty("场景名称")
    private String sceneName;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户名称")
    private String userName;

    @ExcelProperty("触发器")
    private String triggers;

    @ExcelProperty("执行动作")
    private String actions;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
