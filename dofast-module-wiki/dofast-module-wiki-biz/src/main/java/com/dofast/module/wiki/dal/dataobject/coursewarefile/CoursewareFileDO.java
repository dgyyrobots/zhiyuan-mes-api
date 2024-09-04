package com.dofast.module.wiki.dal.dataobject.coursewarefile;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 课件文件的保存地址 DO
 *
 * @author 惠智造
 */
@TableName("wiki_courseware_file")
@KeySequence("wiki_courseware_file_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursewareFileDO extends BaseDO {

    /**
     * 文件id
     */
    @TableId
    private Long id;
    /**
     * 课件id
     */
    private Long coursewareId;
    /**
     * 文件名称
     */
    private String filename;
    /**
     * 文件路径
     */
    private String filepath;
    /**
     * 文件大小
     */
    private String fileSize;

}
