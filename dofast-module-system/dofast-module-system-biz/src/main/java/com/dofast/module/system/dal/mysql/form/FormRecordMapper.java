package com.dofast.module.system.dal.mysql.form;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.system.dal.dataobject.form.FormRecordDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.system.controller.admin.form.vo.*;

/**
 * 表单历史 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface FormRecordMapper extends BaseMapperX<FormRecordDO> {

    default PageResult<FormRecordDO> selectPage(FormRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FormRecordDO>()
                .eqIfPresent(FormRecordDO::getFormId, reqVO.getFormId())
                .eqIfPresent(FormRecordDO::getValue, reqVO.getValue())
                .betweenIfPresent(FormRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FormRecordDO::getId));
    }

    default List<FormRecordDO> selectList(FormRecordExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FormRecordDO>()
                .eqIfPresent(FormRecordDO::getFormId, reqVO.getFormId())
                .eqIfPresent(FormRecordDO::getValue, reqVO.getValue())
                .betweenIfPresent(FormRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FormRecordDO::getId));
    }

}
