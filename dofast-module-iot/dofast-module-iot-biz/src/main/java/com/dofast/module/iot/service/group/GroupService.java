package com.dofast.module.iot.service.group;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.group.vo.*;
import com.dofast.module.iot.dal.dataobject.group.GroupDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 设备分组 Service 接口
 *
 * @author 惠智造
 */
public interface GroupService {

    /**
     * 创建设备分组
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGroup(@Valid GroupCreateReqVO createReqVO);

    /**
     * 更新设备分组
     *
     * @param updateReqVO 更新信息
     */
    void updateGroup(@Valid GroupUpdateReqVO updateReqVO);

    /**
     * 删除设备分组
     *
     * @param id 编号
     */
    void deleteGroup(Long id);

    /**
     * 获得设备分组
     *
     * @param id 编号
     * @return 设备分组
     */
    GroupDO getGroup(Long id);

    /**
     * 获得设备分组列表
     *
     * @param ids 编号
     * @return 设备分组列表
     */
    List<GroupDO> getGroupList(Collection<Long> ids);

    /**
     * 获得设备分组分页
     *
     * @param pageReqVO 分页查询
     * @return 设备分组分页
     */
    PageResult<GroupDO> getGroupPage(GroupPageReqVO pageReqVO);

    /**
     * 获得设备分组列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 设备分组列表
     */
    List<GroupDO> getGroupList(GroupExportReqVO exportReqVO);

}
