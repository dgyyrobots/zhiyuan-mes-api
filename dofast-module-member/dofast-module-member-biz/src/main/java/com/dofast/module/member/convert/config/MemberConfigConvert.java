package com.dofast.module.member.convert.config;

import com.dofast.module.member.api.config.dto.MemberConfigRespDTO;
import com.dofast.module.member.controller.admin.config.vo.MemberConfigRespVO;
import com.dofast.module.member.controller.admin.config.vo.MemberConfigSaveReqVO;
import com.dofast.module.member.dal.dataobject.config.MemberConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 会员配置 Convert
 *
 * @author QingX
 */
@Mapper
public interface MemberConfigConvert {

    MemberConfigConvert INSTANCE = Mappers.getMapper(MemberConfigConvert.class);

    MemberConfigRespVO convert(MemberConfigDO bean);

    MemberConfigDO convert(MemberConfigSaveReqVO bean);

    MemberConfigRespDTO convert01(MemberConfigDO config);
}
