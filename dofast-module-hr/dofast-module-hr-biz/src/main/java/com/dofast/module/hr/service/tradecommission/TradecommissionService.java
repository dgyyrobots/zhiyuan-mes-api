package com.dofast.module.hr.service.tradecommission;

import java.util.*;
import javax.validation.*;
import com.dofast.module.hr.controller.admin.tradecommission.vo.*;
import com.dofast.module.hr.dal.dataobject.tradecommission.TradecommissionDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 工资提成 Service 接口
 *
 * @author 惠智造
 */
public interface TradecommissionService {

    /**
     * 创建工资提成
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createTradecommission(@Valid TradecommissionCreateReqVO createReqVO);

    /**
     * 更新工资提成
     *
     * @param updateReqVO 更新信息
     */
    void updateTradecommission(@Valid TradecommissionUpdateReqVO updateReqVO);

    /**
     * 删除工资提成
     *
     * @param id 编号
     */
    void deleteTradecommission(Integer id);

    /**
     * 获得工资提成
     *
     * @param id 编号
     * @return 工资提成
     */
    TradecommissionDO getTradecommission(Integer id);

    /**
     * 获得工资提成列表
     *
     * @param ids 编号
     * @return 工资提成列表
     */
    List<TradecommissionDO> getTradecommissionList(Collection<Integer> ids);

    /**
     * 获得工资提成分页
     *
     * @param pageReqVO 分页查询
     * @return 工资提成分页
     */
    PageResult<TradecommissionDO> getTradecommissionPage(TradecommissionPageReqVO pageReqVO);

    /**
     * 获得工资提成列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 工资提成列表
     */
    List<TradecommissionDO> getTradecommissionList(TradecommissionExportReqVO exportReqVO);

}
