package com.dofast.module.pro.service.processcontent;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.processcontent.vo.*;
import com.dofast.module.pro.dal.dataobject.processcontent.ProcessContentDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 生产工序内容 Service 接口
 *
 * @author 芋道源码
 */
public interface ProcessContentService {

    List<ProcessContentDO> selectList(ProcessContentListVO listVO);
    /**
     * 创建生产工序内容
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createcessContent(@Valid ProcessContentCreateReqVO createReqVO);

    /**
     * 更新生产工序内容
     *
     * @param updateReqVO 更新信息
     */
    void updatecessContent(@Valid ProcessContentUpdateReqVO updateReqVO);

    /**
     * 删除生产工序内容
     *
     * @param id 编号
     */
    void deletecessContent(Long id);

    /**
     * 获得生产工序内容
     *
     * @param id 编号
     * @return 生产工序内容
     */
    ProcessContentDO getcessContent(Long id);

    /**
     * 获得生产工序内容列表
     *
     * @param ids 编号
     * @return 生产工序内容列表
     */
    List<ProcessContentDO> getcessContentList(Collection<Long> ids);

    /**
     * 获得生产工序内容分页
     *
     * @param pageReqVO 分页查询
     * @return 生产工序内容分页
     */
    PageResult<ProcessContentDO> getcessContentPage(ProcessContentPageReqVO pageReqVO);

    /**
     * 获得生产工序内容列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 生产工序内容列表
     */
    List<ProcessContentDO> getcessContentList(ProcessContentExportReqVO exportReqVO);

}
