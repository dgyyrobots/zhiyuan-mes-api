package com.dofast.module.wiki.dal.dataobject.lecturer;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 讲师的信息	 DO
 *
 * @author 惠智造
 */
@TableName("wiki_lecturer")
@KeySequence("wiki_lecturer_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WikiLecturerDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 讲师名称
     */
    private String name;
    /**
     * 讲师授课方向
     */
    private String direction;
    /**
     * 讲师照片
     */
    private String picture;

}
