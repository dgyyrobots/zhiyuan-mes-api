package com.dofast.module.system.service.user;

import java.util.*;
import javax.validation.*;
import com.dofast.module.system.controller.admin.user.vo.usersFace.*;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.dal.dataobject.user.UsersFaceDO;

/**
 * 用户人脸数据 Service 接口
 *
 * @author 惠智造
 */
public interface UsersFaceService {

    /**
     * 创建用户人脸数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUsersFace(@Valid UsersFaceCreateReqVO createReqVO);

    /**
     * 更新用户人脸数据
     *
     * @param updateReqVO 更新信息
     */
    void updateUsersFace(@Valid UsersFaceUpdateReqVO updateReqVO);

    /**
     * 删除用户人脸数据
     *
     * @param id 编号
     */
    void deleteUsersFace(Long id);

    /**
     * 获得用户人脸数据
     *
     * @param id 编号
     * @return 用户人脸数据
     */
    UsersFaceDO getUsersFace(Long id);

    /**
     * 获得用户人脸数据列表
     *
     * @param ids 编号
     * @return 用户人脸数据列表
     */
    List<UsersFaceDO> getUsersFaceList(Collection<Long> ids);

    /**
     * 获得用户人脸数据分页
     *
     * @param pageReqVO 分页查询
     * @return 用户人脸数据分页
     */
    PageResult<UsersFaceDO> getUsersFacePage(UsersFacePageReqVO pageReqVO);

    /**
     * 获得用户人脸数据列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 用户人脸数据列表
     */
    List<UsersFaceDO> getUsersFaceList(UsersFaceExportReqVO exportReqVO);

}
