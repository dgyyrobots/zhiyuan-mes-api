package com.dofast.module.system.dal.mysql.form;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.system.dal.dataobject.form.FormItemDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.system.controller.admin.form.vo.*;

/**
 * 字段 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface FormItemMapper extends BaseMapperX<FormItemDO> {

    default PageResult<FormItemDO> selectPage(FormItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FormItemDO>()
                .likeIfPresent(FormItemDO::getName, reqVO.getName())
                .eqIfPresent(FormItemDO::getField, reqVO.getField())
                .eqIfPresent(FormItemDO::getFormId, reqVO.getFormId())
                .eqIfPresent(FormItemDO::getValidator, reqVO.getValidator())
                .eqIfPresent(FormItemDO::getType, reqVO.getType())
                .betweenIfPresent(FormItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FormItemDO::getId));
    }

    default List<FormItemDO> selectList(FormItemExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FormItemDO>()
                .likeIfPresent(FormItemDO::getName, reqVO.getName())
                .eqIfPresent(FormItemDO::getField, reqVO.getField())
                .eqIfPresent(FormItemDO::getFormId, reqVO.getFormId())
                .eqIfPresent(FormItemDO::getValidator, reqVO.getValidator())
                .eqIfPresent(FormItemDO::getType, reqVO.getType())
                .betweenIfPresent(FormItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FormItemDO::getId));
    }

}
