package com.dofast.module.promotion.dal.mysql.diy;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.promotion.controller.admin.diy.vo.template.DiyTemplatePageReqVO;
import com.dofast.module.promotion.dal.dataobject.diy.DiyTemplatesDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 装修模板 Mapper
 *
 * @author owen
 */
@Mapper
public interface DiyTemplateMappers extends BaseMapperX<DiyTemplatesDO> {

    default PageResult<DiyTemplatesDO> selectPage(DiyTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DiyTemplatesDO>()
                .likeIfPresent(DiyTemplatesDO::getName, reqVO.getName())
                .eqIfPresent(DiyTemplatesDO::getUsed, reqVO.getUsed())
                .betweenIfPresent(DiyTemplatesDO::getUsedTime, reqVO.getUsedTime())
                .betweenIfPresent(DiyTemplatesDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DiyTemplatesDO::getUsed) // 排序规则1：已使用的排到最前面
                .orderByDesc(DiyTemplatesDO::getId)); // 排序规则2：新创建的排到前面
    }

    default DiyTemplatesDO selectByUsed(boolean used) {
        return selectOne(DiyTemplatesDO::getUsed, used);
    }

    default DiyTemplatesDO selectByName(String name) {
        return selectOne(DiyTemplatesDO::getName, name);
    }

}
