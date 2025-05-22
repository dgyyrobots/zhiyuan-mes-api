package com.dofast.module.system.service.dj002;

import java.util.*;
import javax.validation.*;
import com.dofast.module.system.controller.admin.dj002.vo.*;
import com.dofast.module.system.dal.dataobject.dj002.Dj002DO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 系统地址信息 Service 接口
 *
 * @author 惠智造
 */
public interface Dj002Service {

    /**
     * 创建系统地址信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createDj002(@Valid Dj002CreateReqVO createReqVO);

    /**
     * 更新系统地址信息
     *
     * @param updateReqVO 更新信息
     */
    void updateDj002(@Valid Dj002UpdateReqVO updateReqVO);

    /**
     * 删除系统地址信息
     *
     * @param id 编号
     */
    void deleteDj002(Integer id);

    /**
     * 获得系统地址信息
     *
     * @param id 编号
     * @return 系统地址信息
     */
    Dj002DO getDj002(Integer id);

    /**
     * 获得系统地址信息列表
     *
     * @param ids 编号
     * @return 系统地址信息列表
     */
    List<Dj002DO> getDj002List(Collection<Integer> ids);

    /**
     * 获得系统地址信息分页
     *
     * @param pageReqVO 分页查询
     * @return 系统地址信息分页
     */
    PageResult<Dj002DO> getDj002Page(Dj002PageReqVO pageReqVO);

    /**
     * 获得系统地址信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 系统地址信息列表
     */
    List<Dj002DO> getDj002List(Dj002ExportReqVO exportReqVO);

}
