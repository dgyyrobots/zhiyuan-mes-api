package com.dofast.module.cmms.service.dvmachinerytype;

import java.util.*;
import javax.validation.*;
import com.dofast.module.cmms.controller.admin.dvmachinerytype.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvmachinerytype.DvMachineryTypeDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 设备类型 Service 接口
 *
 * @author 芋道源码
 */
public interface DvMachineryTypeService {

    /**
     * 查询设备类型
     *
     * @param machineryTypeId 设备类型主键
     * @return 设备类型
     */
    public DvMachineryTypeDO selectDvMachineryTypeByMachineryTypeId(Long machineryTypeId);
    /**
     * 创建设备类型
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDvMachineryType(@Valid DvMachineryTypeCreateReqVO createReqVO);

    /**
     * 更新设备类型
     *
     * @param updateReqVO 更新信息
     */
    void updateDvMachineryType(@Valid DvMachineryTypeUpdateReqVO updateReqVO);

    /**
     * 删除设备类型
     *
     * @param id 编号
     */
    void deleteDvMachineryType(Long id);

    /**
     * 获得设备类型
     *
     * @param id 编号
     * @return 设备类型
     */
    DvMachineryTypeDO getDvMachineryType(Long id);

    /**
     * 获得设备类型列表
     *
     * @param ids 编号
     * @return 设备类型列表
     */
    List<DvMachineryTypeDO> getDvMachineryTypeList(Collection<Long> ids);

    /**
     * 获得设备类型分页
     *
     * @param pageReqVO 分页查询
     * @return 设备类型分页
     */
    PageResult<DvMachineryTypeDO> getDvMachineryTypePage(DvMachineryTypePageReqVO pageReqVO);

    /**
     * 获得设备类型列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 设备类型列表
     */
    List<DvMachineryTypeDO> getDvMachineryTypeList(DvMachineryTypeExportReqVO exportReqVO);

    /**
     * 获得设备类型简单列表
     *
     * @return 设备类型简单列表
     */
    List<DvMachineryTypeSimpleVO> getDvMachineryTypeSimpleList();
}
