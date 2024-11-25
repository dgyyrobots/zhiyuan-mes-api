package com.dofast.module.system.controller.admin.user.vo.usersFace;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 用户人脸数据 Excel VO
 *
 * @author 惠智造
 */
@Data
public class UsersFaceExcelVO {

    @ExcelProperty("自增编号")
    private Long id;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("人脸地址")
    private String faceImg;

}
