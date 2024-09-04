package com.dofast.module.system.convert.form;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormDO;

/**
 * 系统表单 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface FormConvert {

    FormConvert INSTANCE = Mappers.getMapper(FormConvert.class);

    FormDO convert(FormCreateReqVO bean);

    FormDO convert(FormUpdateReqVO bean);

    FormRespVO convert(FormDO bean);

//    @Mappings(
//            value = {
//                    @Mapping(source = "name", target = "name"),
//                    @Mapping(source = "id", target = "id"),
//            }
//    )
    FormSimpleRespVO convert3(FormDO bean);

    List<FormRespVO> convertList(List<FormDO> list);

    List<FormSimpleRespVO> convertList3(List<FormDO> list);

    PageResult<FormRespVO> convertPage(PageResult<FormDO> page);

    List<FormExcelVO> convertList02(List<FormDO> list);

}
