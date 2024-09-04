package com.dofast.module.promotion.dal.dataobject.banner;

 
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.module.promotion.enums.banner.BannerPositionEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

// TODO @puhui999：表名改成 promotion_banner，然后有序加下；另外，sql 给我下哈；还有那个 position 字典，嘿嘿。

/**
 * banner DO
 *
 * @author xia
 */
@TableName("promotion_banner")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerDO extends BaseDO {

    /**
     * 编号
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 跳转链接
     */
    private String url;
    /**
     * 图片链接
     */
    private String picUrl;
    /**
     * 排序
     */
    private Integer sort;

    /**
 
     * 状态 {@link com.dofast.framework.common.enums.CommonStatusEnum}
     */
    private Integer status;


    /**
     * 定位 {@link BannerPositionEnum}
     */
    private Integer position;


    /**
     * 备注
     */
    private String memo;

 
    // TODO 芋艿 点击次数。&& 其他数据相关
    /**
     * 点击次数
     */
    private Integer browseCount;


}
