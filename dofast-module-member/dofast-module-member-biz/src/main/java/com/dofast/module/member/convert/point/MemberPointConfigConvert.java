package com.dofast.module.member.convert.point;

import com.dofast.module.member.api.point.dto.MemberPointConfigRespDTO;
import com.dofast.module.member.controller.admin.point.vo.config.MemberPointConfigRespVO;
import com.dofast.module.member.controller.admin.point.vo.config.MemberPointConfigSaveReqVO;
import com.dofast.module.member.dal.dataobject.point.MemberPointConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 会员积分配置 Convert
 *
 * @author QingX
 */
@Mapper
public interface MemberPointConfigConvert {

    MemberPointConfigConvert INSTANCE = Mappers.getMapper(MemberPointConfigConvert.class);

    MemberPointConfigRespVO convert(MemberPointConfigDO bean);

    MemberPointConfigDO convert(MemberPointConfigSaveReqVO bean);

    MemberPointConfigRespDTO convert01(MemberPointConfigDO pointConfig);
}
