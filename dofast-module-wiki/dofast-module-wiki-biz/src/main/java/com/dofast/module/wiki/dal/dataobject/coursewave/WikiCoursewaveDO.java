package com.dofast.module.wiki.dal.dataobject.coursewave;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 首页知识列表的信息	 DO
 *
 * @author 惠智造
 */
@TableName("wiki_coursewave")
@KeySequence("wiki_coursewave_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WikiCoursewaveDO extends BaseDO {

    /**
     * 课件id
     */
    @TableId
    private Long id;
    /**
     * 种类id
     */
    private Long categoryId;

    /**
     * 讲师ID
     */
    private Long lecturerId;
    /**
     * 课件名称
     */
    private String coursewareName;

}
