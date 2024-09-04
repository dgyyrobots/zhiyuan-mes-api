package com.dofast.module.mes.dal.mysql.mdclient;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.mes.dal.dataobject.mdclient.MdClientDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.mes.controller.admin.mdclient.vo.*;

/**
 * 客户 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MdClientMapper extends BaseMapperX<MdClientDO> {
    default MdClientDO checkClientCodeUnique(MdClientBaseVO mdClient){
        return selectOne(new LambdaQueryWrapperX<MdClientDO>().eq(MdClientDO::getClientCode,mdClient.getClientCode()));
    }

    default MdClientDO checkClientNameUnique(MdClientBaseVO mdClient){
        return selectOne(new LambdaQueryWrapperX<MdClientDO>().eq(MdClientDO::getClientName,mdClient.getClientName()));
    }

    default MdClientDO checkClientNickUnique(MdClientBaseVO mdClient){
        return selectOne(new LambdaQueryWrapperX<MdClientDO>().eq(MdClientDO::getClientNick,mdClient.getClientNick()));
    }
    default PageResult<MdClientDO> selectPage(MdClientPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MdClientDO>()
                .eqIfPresent(MdClientDO::getClientCode, reqVO.getClientCode())
                .likeIfPresent(MdClientDO::getClientName, reqVO.getClientName())
                .eqIfPresent(MdClientDO::getClientNick, reqVO.getClientNick())
                .eqIfPresent(MdClientDO::getClientEn, reqVO.getClientEn())
                .eqIfPresent(MdClientDO::getClientDes, reqVO.getClientDes())
                .eqIfPresent(MdClientDO::getClientLogo, reqVO.getClientLogo())
                .eqIfPresent(MdClientDO::getClientType, reqVO.getClientType())
                .eqIfPresent(MdClientDO::getAddress, reqVO.getAddress())
                .eqIfPresent(MdClientDO::getWebsite, reqVO.getWebsite())
                .eqIfPresent(MdClientDO::getEmail, reqVO.getEmail())
                .eqIfPresent(MdClientDO::getTel, reqVO.getTel())
                .eqIfPresent(MdClientDO::getContact1, reqVO.getContact1())
                .eqIfPresent(MdClientDO::getContact1Tel, reqVO.getContact1Tel())
                .eqIfPresent(MdClientDO::getContact1Email, reqVO.getContact1Email())
                .eqIfPresent(MdClientDO::getContact2, reqVO.getContact2())
                .eqIfPresent(MdClientDO::getContact2Tel, reqVO.getContact2Tel())
                .eqIfPresent(MdClientDO::getContact2Email, reqVO.getContact2Email())
                .eqIfPresent(MdClientDO::getCreditCode, reqVO.getCreditCode())
                .eqIfPresent(MdClientDO::getEnableFlag, reqVO.getEnableFlag())
                .eqIfPresent(MdClientDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MdClientDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(MdClientDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(MdClientDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(MdClientDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(MdClientDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MdClientDO::getId));
    }

    default List<MdClientDO> selectList(MdClientExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MdClientDO>()
                .eqIfPresent(MdClientDO::getClientCode, reqVO.getClientCode())
                .likeIfPresent(MdClientDO::getClientName, reqVO.getClientName())
                .eqIfPresent(MdClientDO::getClientNick, reqVO.getClientNick())
                .eqIfPresent(MdClientDO::getClientEn, reqVO.getClientEn())
                .eqIfPresent(MdClientDO::getClientDes, reqVO.getClientDes())
                .eqIfPresent(MdClientDO::getClientLogo, reqVO.getClientLogo())
                .eqIfPresent(MdClientDO::getClientType, reqVO.getClientType())
                .eqIfPresent(MdClientDO::getAddress, reqVO.getAddress())
                .eqIfPresent(MdClientDO::getWebsite, reqVO.getWebsite())
                .eqIfPresent(MdClientDO::getEmail, reqVO.getEmail())
                .eqIfPresent(MdClientDO::getTel, reqVO.getTel())
                .eqIfPresent(MdClientDO::getContact1, reqVO.getContact1())
                .eqIfPresent(MdClientDO::getContact1Tel, reqVO.getContact1Tel())
                .eqIfPresent(MdClientDO::getContact1Email, reqVO.getContact1Email())
                .eqIfPresent(MdClientDO::getContact2, reqVO.getContact2())
                .eqIfPresent(MdClientDO::getContact2Tel, reqVO.getContact2Tel())
                .eqIfPresent(MdClientDO::getContact2Email, reqVO.getContact2Email())
                .eqIfPresent(MdClientDO::getCreditCode, reqVO.getCreditCode())
                .eqIfPresent(MdClientDO::getEnableFlag, reqVO.getEnableFlag())
                .eqIfPresent(MdClientDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MdClientDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(MdClientDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(MdClientDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(MdClientDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(MdClientDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MdClientDO::getId));
    }

}
