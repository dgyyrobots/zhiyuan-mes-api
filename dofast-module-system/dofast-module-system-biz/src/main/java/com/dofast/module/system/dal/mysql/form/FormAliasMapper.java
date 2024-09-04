package com.dofast.module.system.dal.mysql.form;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.system.dal.dataobject.form.FormDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.system.controller.admin.form.vo.*;

/**
 * 系统表单 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface FormAliasMapper extends BaseMapperX<FormDO> {

    default PageResult<FormDO> selectPage(FormPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FormDO>()
                .likeIfPresent(FormDO::getName, reqVO.getName())
                .eqIfPresent(FormDO::getType, reqVO.getType())
                .eqIfPresent(FormDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(FormDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FormDO::getId));
    }

    default List<FormDO> selectList(FormExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FormDO>()
                .likeIfPresent(FormDO::getName, reqVO.getName())
                .eqIfPresent(FormDO::getType, reqVO.getType())
                .eqIfPresent(FormDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(FormDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FormDO::getId));
    }

}
