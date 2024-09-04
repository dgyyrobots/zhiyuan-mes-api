package com.dofast.module.trade.dal.dataobject.calculate;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 计价记录 DO
 *
 * @author 惠智造
 */
@TableName("trade_calculate_record")
@KeySequence("trade_calculate_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateRecordDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 数据
     */
    private Long recordId;
    /**
     * 类型
     */
    private Long typeId;
    /**
     * 结果
     */
    private String result;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 客户端
     */
    private String client;
    /**
     * 设备信息
     */
    private String device;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 备注
     */
    private String remark;

}
