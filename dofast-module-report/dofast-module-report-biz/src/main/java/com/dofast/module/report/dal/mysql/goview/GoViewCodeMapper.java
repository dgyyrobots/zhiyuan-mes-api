package com.dofast.module.report.dal.mysql.goview;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeExportReqVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodePageReqVO;
import com.dofast.module.report.dal.dataobject.goview.GoViewCodeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * GoView登录 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface GoViewCodeMapper extends BaseMapperX<GoViewCodeDO> {

    default PageResult<GoViewCodeDO> selectPage(GoViewCodePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GoViewCodeDO>()
                .eqIfPresent(GoViewCodeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(GoViewCodeDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(GoViewCodeDO::getPdaUserId, reqVO.getPdaUserId())
                .eqIfPresent(GoViewCodeDO::getPdaToken, reqVO.getPdaToken())
                .eqIfPresent(GoViewCodeDO::getGoviewToken, reqVO.getGoviewToken())
                .betweenIfPresent(GoViewCodeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GoViewCodeDO::getId));
    }

    default List<GoViewCodeDO> selectList(GoViewCodeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<GoViewCodeDO>()
                .eqIfPresent(GoViewCodeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(GoViewCodeDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(GoViewCodeDO::getPdaUserId, reqVO.getPdaUserId())
                .eqIfPresent(GoViewCodeDO::getPdaToken, reqVO.getPdaToken())
                .eqIfPresent(GoViewCodeDO::getGoviewToken, reqVO.getGoviewToken())
                .betweenIfPresent(GoViewCodeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GoViewCodeDO::getId));
    }

}
