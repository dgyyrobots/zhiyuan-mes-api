package com.dofast.module.sales.dal.dataobject.orderafter;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 售后流程表单 DO
 *
 * @author a1
 */
@TableName("sales_order_after")
@KeySequence("sales_order_after_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderAfterDO extends BaseDO {

    /**
     * 售后表单主键
     */
    @TableId
    private Long id;
    /**
     * 编码
     */
    private String code;

    /**
     * 关联订单id
     */
    private String associatedBusiness;
    /**
     * 业务数据
     */
    private String businessData;
    /**
     * 关联对象
     */
    private String associatedObjects;
    /**
     * 对象名称
     */
    private String objectName;
    /**
     * 关联店铺
     */
    private String associatedStores;
    /**
     * 关联仓库
     */
    private String associatedRepository;
    /**
     * 关联金额
     */
    private Double associatedAmounts;
    /**
     * 事务类别
     */
    private String transactionCategory;
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 添加抄送
     */
    private String addCopy;
    /**
     * 主要负责人
     */
    private String mainPersonResponsible;
    /**
     * 截止时间
     */
    private LocalDateTime deadline;
    /**
     * 标记
     */
    private String remark;
    /**
     * 事务标题
     */
    private String transactionTitle;
    /**
     * 事务内容
     */
    private String transactionContent;
    /**
     * 图片
     */
    private String picture;
    /**
     * 售后结果
     */
    private String status;
    /**
     * 流程实例的编号
     */
    private String processInstanceId;

}
