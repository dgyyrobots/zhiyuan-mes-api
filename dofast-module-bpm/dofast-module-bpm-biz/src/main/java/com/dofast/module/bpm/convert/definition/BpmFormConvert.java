package com.dofast.module.bpm.convert.definition;

import com.dofast.module.bpm.controller.admin.definition.vo.form.BpmFormCreateReqVO;
import com.dofast.module.bpm.controller.admin.definition.vo.form.BpmFormRespVO;
import com.dofast.module.bpm.controller.admin.definition.vo.form.BpmFormSimpleRespVO;
import com.dofast.module.bpm.controller.admin.definition.vo.form.BpmFormUpdateReqVO;
import com.dofast.module.bpm.dal.dataobject.definition.BpmFormDO;
import com.dofast.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 动态表单 Convert
 *
 * @author 芋艿
 */
@Mapper
public interface BpmFormConvert {

    BpmFormConvert INSTANCE = Mappers.getMapper(BpmFormConvert.class);

    BpmFormDO convert(BpmFormCreateReqVO bean);

    BpmFormDO convert(BpmFormUpdateReqVO bean);

    BpmFormRespVO convert(BpmFormDO bean);

    List<BpmFormSimpleRespVO> convertList2(List<BpmFormDO> list);

    PageResult<BpmFormRespVO> convertPage(PageResult<BpmFormDO> page);

}
