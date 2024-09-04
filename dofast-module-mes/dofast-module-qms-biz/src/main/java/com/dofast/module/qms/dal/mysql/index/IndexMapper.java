package com.dofast.module.qms.dal.mysql.index;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.qms.dal.dataobject.index.IndexDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.qms.controller.admin.index.vo.*;

/**
 * 检测项 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface IndexMapper extends BaseMapperX<IndexDO> {
    default IndexDO checkIndexCodeUnique(IndexBaseVO baseVO){
        return selectOne(new LambdaQueryWrapperX<IndexDO>().eq(IndexDO::getIndexCode,baseVO.getIndexCode()));
    }
    default IndexDO checkIndexNameUnique(IndexBaseVO baseVO){
        return selectOne(new LambdaQueryWrapperX<IndexDO>().eq(IndexDO::getIndexName,baseVO.getIndexName()));
    }
    default PageResult<IndexDO> selectPage(IndexPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IndexDO>()
                .eqIfPresent(IndexDO::getIndexCode, reqVO.getIndexCode())
                .likeIfPresent(IndexDO::getIndexName, reqVO.getIndexName())
                .eqIfPresent(IndexDO::getIndexType, reqVO.getIndexType())
                .eqIfPresent(IndexDO::getQcTool, reqVO.getQcTool())
                .eqIfPresent(IndexDO::getRemark, reqVO.getRemark())
                .eqIfPresent(IndexDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(IndexDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(IndexDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(IndexDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(IndexDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IndexDO::getId));
    }

    default List<IndexDO> selectList(IndexExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<IndexDO>()
                .eqIfPresent(IndexDO::getIndexCode, reqVO.getIndexCode())
                .likeIfPresent(IndexDO::getIndexName, reqVO.getIndexName())
                .eqIfPresent(IndexDO::getIndexType, reqVO.getIndexType())
                .eqIfPresent(IndexDO::getQcTool, reqVO.getQcTool())
                .eqIfPresent(IndexDO::getRemark, reqVO.getRemark())
                .eqIfPresent(IndexDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(IndexDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(IndexDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(IndexDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(IndexDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IndexDO::getId));
    }

}
