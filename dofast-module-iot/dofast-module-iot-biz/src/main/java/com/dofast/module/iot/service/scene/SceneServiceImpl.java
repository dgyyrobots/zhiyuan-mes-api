package com.dofast.module.iot.service.scene;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.scene.vo.*;
import com.dofast.module.iot.dal.dataobject.scene.SceneDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.scene.SceneConvert;
import com.dofast.module.iot.dal.mysql.scene.SceneMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;

/**
 * 场景联动 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class SceneServiceImpl implements SceneService {

    @Resource
    private SceneMapper sceneMapper;

    @Override
    public Long createScene(SceneCreateReqVO createReqVO) {
        // 插入
        SceneDO scene = SceneConvert.INSTANCE.convert(createReqVO);
        sceneMapper.insert(scene);
        // 返回
        return scene.getId();
    }

    @Override
    public void updateScene(SceneUpdateReqVO updateReqVO) {
        // 校验存在
        validateSceneExists(updateReqVO.getId());
        // 更新
        SceneDO updateObj = SceneConvert.INSTANCE.convert(updateReqVO);
        sceneMapper.updateById(updateObj);
    }

    @Override
    public void deleteScene(Long id) {
        // 校验存在
        validateSceneExists(id);
        // 删除
        sceneMapper.deleteById(id);
    }

    private void validateSceneExists(Long id) {
        if (sceneMapper.selectById(id) == null) {
            throw exception(SCENE_NOT_EXISTS);
        }
    }

    @Override
    public SceneDO getScene(Long id) {
        return sceneMapper.selectById(id);
    }

    @Override
    public List<SceneDO> getSceneList(Collection<Long> ids) {
        return sceneMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SceneDO> getScenePage(ScenePageReqVO pageReqVO) {
        return sceneMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SceneDO> getSceneList(SceneExportReqVO exportReqVO) {
        return sceneMapper.selectList(exportReqVO);
    }

}
