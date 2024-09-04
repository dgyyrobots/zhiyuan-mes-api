package com.dofast.module.pro.service.transconsume;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.transconsume.vo.*;
import com.dofast.module.pro.dal.dataobject.transconsume.TransConsumeDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 物料消耗记录 Service 接口
 *
 * @author 芋道源码
 */
public interface TransConsumeService {

    /**
     * 创建物料消耗记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTransConsume(@Valid TransConsumeCreateReqVO createReqVO);

    /**
     * 更新物料消耗记录
     *
     * @param updateReqVO 更新信息
     */
    void updateTransConsume(@Valid TransConsumeUpdateReqVO updateReqVO);

    /**
     * 删除物料消耗记录
     *
     * @param id 编号
     */
    void deleteTransConsume(Long id);

    /**
     * 获得物料消耗记录
     *
     * @param id 编号
     * @return 物料消耗记录
     */
    TransConsumeDO getTransConsume(Long id);

    /**
     * 获得物料消耗记录列表
     *
     * @param ids 编号
     * @return 物料消耗记录列表
     */
    List<TransConsumeDO> getTransConsumeList(Collection<Long> ids);

    /**
     * 获得物料消耗记录分页
     *
     * @param pageReqVO 分页查询
     * @return 物料消耗记录分页
     */
    PageResult<TransConsumeDO> getTransConsumePage(TransConsumePageReqVO pageReqVO);

    /**
     * 获得物料消耗记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 物料消耗记录列表
     */
    List<TransConsumeDO> getTransConsumeList(TransConsumeExportReqVO exportReqVO);
    List<TransConsumeDO> getTransConsumeList(TransConsumeListVO listVO);

}
