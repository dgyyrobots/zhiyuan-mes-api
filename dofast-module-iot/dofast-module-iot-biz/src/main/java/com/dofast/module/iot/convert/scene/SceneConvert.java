package com.dofast.module.iot.convert.scene;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.scene.vo.*;
import com.dofast.module.iot.dal.dataobject.scene.SceneDO;

/**
 * 场景联动 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface SceneConvert {

    SceneConvert INSTANCE = Mappers.getMapper(SceneConvert.class);

    SceneDO convert(SceneCreateReqVO bean);

    SceneDO convert(SceneUpdateReqVO bean);

    SceneRespVO convert(SceneDO bean);

    List<SceneRespVO> convertList(List<SceneDO> list);

    PageResult<SceneRespVO> convertPage(PageResult<SceneDO> page);

    List<SceneExcelVO> convertList02(List<SceneDO> list);

}
