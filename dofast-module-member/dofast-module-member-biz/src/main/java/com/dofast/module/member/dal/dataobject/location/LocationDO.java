package com.dofast.module.member.dal.dataobject.location;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 三级位置信息 DO
 *
 * @author 惠智造
 */
@TableName("locations")
@KeySequence("locations_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDO {

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 省市区名称
     */
    private String name;
    /**
     * 上级ID
     */
    private Integer parentid;
    /**
     * 简称
     */
    private String shortname;
    /**
     * 级别:0,中国；1，省分；2，市；3，区、县
     */
    private Byte leveltype;
    /**
     * 城市代码
     */
    private String citycode;
    /**
     * 邮编
     */
    private String zipcode;
    /**
     * 经度
     */
    private String lng;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 拼音
     */
    private String pinyin;
    /**
     * 枚举
     */
    private String status;

}
