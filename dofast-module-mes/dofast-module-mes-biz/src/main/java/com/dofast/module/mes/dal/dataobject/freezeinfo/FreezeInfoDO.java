package com.dofast.module.mes.dal.dataobject.freezeinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品冻结信息 DO
 *
 * @author 惠智造
 */
@TableName("mes_freeze_info")
@KeySequence("mes_freeze_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FreezeInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 产品物料编码
     */
    private String itemCode;
    /**
     * 产品物料名称
     */
    private String itemName;
    /**
     * 产品物料数量
     */
    private Long itenQty;
    /**
     * 状态(1-解冻, 2-冻结)
     */
    private Long state;
    /**
     * 仓库编号
     */
    private String whCode;
    /**
     * 库位
     */
    private String storageCode;
    /**
     * 冻结原因
     */
    private String freezeMemo;
    /**
     * 冻结人员
     */
    private String freezer;
    /**
     * 冻结时间
     */
    private LocalDateTime freezeTime;
    /**
     * 解冻人员
     */
    private String thawPerson;
    /**
     * 解冻原因
     */
    private String thawMemo;
    /**
     * 解冻时间
     */
    private LocalDateTime thawTime;
    /**
     * 产品物料SN
     */
    private String itenSn;

}
