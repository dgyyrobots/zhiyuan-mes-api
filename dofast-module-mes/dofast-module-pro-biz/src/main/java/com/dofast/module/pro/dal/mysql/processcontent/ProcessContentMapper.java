package com.dofast.module.pro.dal.mysql.processcontent;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.pro.dal.dataobject.processcontent.ProcessContentDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.pro.controller.admin.processcontent.vo.*;

/**
 * 生产工序内容 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProcessContentMapper extends BaseMapperX<ProcessContentDO> {

    default PageResult<ProcessContentDO> selectPage(ProcessContentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProcessContentDO>()
                .eqIfPresent(ProcessContentDO::getProcessId, reqVO.getProcessId())
                .eqIfPresent(ProcessContentDO::getOrderNum, reqVO.getOrderNum())
                .eqIfPresent(ProcessContentDO::getContentText, reqVO.getContentText())
                .eqIfPresent(ProcessContentDO::getDevice, reqVO.getDevice())
                .eqIfPresent(ProcessContentDO::getMaterial, reqVO.getMaterial())
                .eqIfPresent(ProcessContentDO::getDocUrl, reqVO.getDocUrl())
                .eqIfPresent(ProcessContentDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ProcessContentDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ProcessContentDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ProcessContentDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ProcessContentDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(ProcessContentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProcessContentDO::getId));
    }

    default List<ProcessContentDO> selectList(ProcessContentExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProcessContentDO>()
                .eqIfPresent(ProcessContentDO::getProcessId, reqVO.getProcessId())
                .eqIfPresent(ProcessContentDO::getOrderNum, reqVO.getOrderNum())
                .eqIfPresent(ProcessContentDO::getContentText, reqVO.getContentText())
                .eqIfPresent(ProcessContentDO::getDevice, reqVO.getDevice())
                .eqIfPresent(ProcessContentDO::getMaterial, reqVO.getMaterial())
                .eqIfPresent(ProcessContentDO::getDocUrl, reqVO.getDocUrl())
                .eqIfPresent(ProcessContentDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ProcessContentDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ProcessContentDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ProcessContentDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ProcessContentDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(ProcessContentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProcessContentDO::getId));
    }

    default List<ProcessContentDO> selectList(ProcessContentListVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProcessContentDO>()
                .eqIfPresent(ProcessContentDO::getProcessId, reqVO.getProcessId())
                .eqIfPresent(ProcessContentDO::getOrderNum, reqVO.getOrderNum())
                .eqIfPresent(ProcessContentDO::getContentText, reqVO.getContentText())
                .eqIfPresent(ProcessContentDO::getDevice, reqVO.getDevice())
                .eqIfPresent(ProcessContentDO::getMaterial, reqVO.getMaterial())
                .eqIfPresent(ProcessContentDO::getDocUrl, reqVO.getDocUrl())
                .eqIfPresent(ProcessContentDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ProcessContentDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ProcessContentDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ProcessContentDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ProcessContentDO::getAttr4, reqVO.getAttr4())
                .orderByDesc(ProcessContentDO::getId));
    }

}
