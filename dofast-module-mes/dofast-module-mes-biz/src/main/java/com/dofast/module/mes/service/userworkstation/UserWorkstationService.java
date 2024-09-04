package com.dofast.module.mes.service.userworkstation;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.userworkstation.vo.*;
import com.dofast.module.mes.dal.dataobject.userworkstation.UserWorkstationDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 用户工作站绑定关系 Service 接口
 *
 * @author 惠智造
 */
public interface UserWorkstationService {

    /**
     * 创建用户工作站绑定关系
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserWorkstation(@Valid UserWorkstationCreateReqVO createReqVO);

    /**
     * 更新用户工作站绑定关系
     *
     * @param updateReqVO 更新信息
     */
    void updateUserWorkstation(@Valid UserWorkstationUpdateReqVO updateReqVO);

    /**
     * 删除用户工作站绑定关系
     *
     * @param id 编号
     */
    void deleteUserWorkstation(Long id);

    /**
     * 删除用户工作站绑定关系
     *
     * @param username 用户名
     */
    void deleteUserWorkstation(String username);
    /**
     * 获得用户工作站绑定关系
     *
     * @param id 编号
     * @return 用户工作站绑定关系
     */
    UserWorkstationDO getUserWorkstation(Long id);

    /**
     * 获得用户工作站绑定关系列表
     *
     * @param ids 编号
     * @return 用户工作站绑定关系列表
     */
    List<UserWorkstationDO> getUserWorkstationList(Collection<Long> ids);

    /**
     * 获得用户工作站绑定关系分页
     *
     * @param pageReqVO 分页查询
     * @return 用户工作站绑定关系分页
     */
    PageResult<UserWorkstationDO> getUserWorkstationPage(UserWorkstationPageReqVO pageReqVO);

    /**
     * 获得用户工作站绑定关系列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 用户工作站绑定关系列表
     */
    List<UserWorkstationDO> getUserWorkstationList(UserWorkstationExportReqVO exportReqVO);

}
