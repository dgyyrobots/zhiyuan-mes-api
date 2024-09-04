package com.dofast.module.iot.service.scene;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.scene.vo.*;
import com.dofast.module.iot.dal.dataobject.scene.SceneDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 场景联动 Service 接口
 *
 * @author 惠智造
 */
public interface SceneService {

    /**
     * 创建场景联动
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createScene(@Valid SceneCreateReqVO createReqVO);

    /**
     * 更新场景联动
     *
     * @param updateReqVO 更新信息
     */
    void updateScene(@Valid SceneUpdateReqVO updateReqVO);

    /**
     * 删除场景联动
     *
     * @param id 编号
     */
    void deleteScene(Long id);

    /**
     * 获得场景联动
     *
     * @param id 编号
     * @return 场景联动
     */
    SceneDO getScene(Long id);

    /**
     * 获得场景联动列表
     *
     * @param ids 编号
     * @return 场景联动列表
     */
    List<SceneDO> getSceneList(Collection<Long> ids);

    /**
     * 获得场景联动分页
     *
     * @param pageReqVO 分页查询
     * @return 场景联动分页
     */
    PageResult<SceneDO> getScenePage(ScenePageReqVO pageReqVO);

    /**
     * 获得场景联动列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 场景联动列表
     */
    List<SceneDO> getSceneList(SceneExportReqVO exportReqVO);

}
