package com.dofast.module.trade.service.calculate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordCreateReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordExportReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordPageReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.calculate.CalculateRecordDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 计价记录 Service 接口
 *
 * @author 惠智造
 */
public interface CalculateRecordService {

    /**
     * 创建计价记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCalculateRecord(@Valid CalculateRecordCreateReqVO createReqVO);

    /**
     * 更新计价记录
     *
     * @param updateReqVO 更新信息
     */
    void updateCalculateRecord(@Valid CalculateRecordUpdateReqVO updateReqVO);

    /**
     * 删除计价记录
     *
     * @param id 编号
     */
    void deleteCalculateRecord(Long id);

    /**
     * 获得计价记录
     *
     * @param id 编号
     * @return 计价记录
     */
    CalculateRecordDO getCalculateRecord(Long id);

    /**
     * 获得计价记录列表
     *
     * @param ids 编号
     * @return 计价记录列表
     */
    List<CalculateRecordDO> getCalculateRecordList(Collection<Long> ids);

    /**
     * 获得计价记录分页
     *
     * @param pageReqVO 分页查询
     * @return 计价记录分页
     */
    PageResult<CalculateRecordDO> getCalculateRecordPage(CalculateRecordPageReqVO pageReqVO);

    /**
     * 获得计价记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 计价记录列表
     */
    List<CalculateRecordDO> getCalculateRecordList(CalculateRecordExportReqVO exportReqVO);

}
