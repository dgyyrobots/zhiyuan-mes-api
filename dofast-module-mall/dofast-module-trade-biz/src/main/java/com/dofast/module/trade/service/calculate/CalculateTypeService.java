package com.dofast.module.trade.service.calculate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypeCreateReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypeExportReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypePageReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypeUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.calculate.CalculateTypeDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 计价类型 Service 接口
 *
 * @author 惠智造
 */
public interface CalculateTypeService {

    /**
     * 创建计价类型
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCalculateType(@Valid CalculateTypeCreateReqVO createReqVO);

    /**
     * 更新计价类型
     *
     * @param updateReqVO 更新信息
     */
    void updateCalculateType(@Valid CalculateTypeUpdateReqVO updateReqVO);

    /**
     * 删除计价类型
     *
     * @param id 编号
     */
    void deleteCalculateType(Integer id);

    /**
     * 获得计价类型
     *
     * @param id 编号
     * @return 计价类型
     */
    CalculateTypeDO getCalculateType(Integer id);

    /**
     * 获得计价类型列表
     *
     * @param ids 编号
     * @return 计价类型列表
     */
    List<CalculateTypeDO> getCalculateTypeList(Collection<Integer> ids);

    /**
     * 获得计价类型分页
     *
     * @param pageReqVO 分页查询
     * @return 计价类型分页
     */
    PageResult<CalculateTypeDO> getCalculateTypePage(CalculateTypePageReqVO pageReqVO);

    /**
     * 获得计价类型列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 计价类型列表
     */
    List<CalculateTypeDO> getCalculateTypeList(CalculateTypeExportReqVO exportReqVO);

}
