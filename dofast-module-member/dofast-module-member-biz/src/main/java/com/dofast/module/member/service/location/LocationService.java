package com.dofast.module.member.service.location;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.member.controller.admin.loction.vo.*;
import com.dofast.module.member.dal.dataobject.location.LocationDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 三级位置信息 Service 接口
 *
 * @author 惠智造
 */
public interface LocationService {

    /**
     * 创建三级位置信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer create(@Valid LocationCreateReqVO createReqVO);

    /**
     * 更新三级位置信息
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid LocationUpdateReqVO updateReqVO);

    /**
     * 删除三级位置信息
     *
     * @param id 编号
     */
    void delete(Integer id);

    /**
     * 获得三级位置信息
     *
     * @param id 编号
     * @return 三级位置信息
     */
    LocationDO get(Integer id);

    /**
     * 获得三级位置信息列表
     *
     * @param ids 编号
     * @return 三级位置信息列表
     */
    List<LocationDO> getList(Collection<Integer> ids);

    /**
     * 获得三级位置信息分页
     *
     * @param pageReqVO 分页查询
     * @return 三级位置信息分页
     */
    PageResult<LocationDO> getPage(LocationPageReqVO pageReqVO);

    /**
     * 获得三级位置信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 三级位置信息列表
     */
    List<LocationDO> getList(LocationExportReqVO exportReqVO);

    /**
     * 根据ID获得三级位置信息列表
     *
     * @return 三级位置信息列表
     */
    List<LocationQueryId> getLocationList(Integer id);

}
