package com.dofast.module.mes.convert.Autocoderule;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.Autocoderule.vo.*;
import com.dofast.module.mes.dal.dataobject.Autocoderule.AutoCodeRuleDO;

/**
 * 编码生成规则 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface AutoCodeRuleConvert {

    AutoCodeRuleConvert INSTANCE = Mappers.getMapper(AutoCodeRuleConvert.class);

    AutoCodeRuleDO convert(AutoCodeRuleCreateReqVO bean);

    AutoCodeRuleDO convert(AutoCodeRuleUpdateReqVO bean);

    AutoCodeRuleRespVO convert(AutoCodeRuleDO bean);

    List<AutoCodeRuleRespVO> convertList(List<AutoCodeRuleDO> list);

    PageResult<AutoCodeRuleRespVO> convertPage(PageResult<AutoCodeRuleDO> page);

    List<AutoCodeRuleExcelVO> convertList02(List<AutoCodeRuleDO> list);

}
