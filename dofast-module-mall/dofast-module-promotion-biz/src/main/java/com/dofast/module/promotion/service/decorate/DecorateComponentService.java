package com.dofast.module.promotion.service.decorate;

import com.dofast.module.promotion.controller.admin.decorate.vo.DecorateComponentSaveReqVO;
import com.dofast.module.promotion.dal.dataobject.decorate.DecorateComponentDO;
import com.dofast.module.promotion.enums.decorate.DecoratePageEnum;

import java.util.List;

/**
 * 装修组件 Service 接口
 *
 * @author jason
 */
public interface DecorateComponentService {

    /**
     * 保存页面的组件信息
     *
     * @param reqVO 请求 VO
     */
    void saveDecorateComponent(DecorateComponentSaveReqVO reqVO);

    /**
     * 根据页面 id，获取页面的组件信息
     *
     * @param page 页面编号 {@link DecoratePageEnum#getPage()}
     * @param status 状态
     */
    List<DecorateComponentDO> getDecorateComponentListByPage(Integer page, Integer status);

}
