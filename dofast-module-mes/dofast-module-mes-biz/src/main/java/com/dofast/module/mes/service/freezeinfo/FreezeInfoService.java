package com.dofast.module.mes.service.freezeinfo;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.freezeinfo.vo.*;
import com.dofast.module.mes.dal.dataobject.freezeinfo.FreezeInfoDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 产品冻结信息 Service 接口
 *
 * @author 惠智造
 */
public interface FreezeInfoService {

    /**
     * 创建产品冻结信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFreezeInfo(@Valid FreezeInfoCreateReqVO createReqVO);

    /**
     * 更新产品冻结信息
     *
     * @param updateReqVO 更新信息
     */
    void updateFreezeInfo(@Valid FreezeInfoUpdateReqVO updateReqVO);

    /**
     * 删除产品冻结信息
     *
     * @param id 编号
     */
    void deleteFreezeInfo(Long id);

    /**
     * 获得产品冻结信息
     *
     * @param id 编号
     * @return 产品冻结信息
     */
    FreezeInfoDO getFreezeInfo(Long id);

    /**
     * 获得产品冻结信息列表
     *
     * @param ids 编号
     * @return 产品冻结信息列表
     */
    List<FreezeInfoDO> getFreezeInfoList(Collection<Long> ids);

    /**
     * 获得产品冻结信息分页
     *
     * @param pageReqVO 分页查询
     * @return 产品冻结信息分页
     */
    PageResult<FreezeInfoDO> getFreezeInfoPage(FreezeInfoPageReqVO pageReqVO);

    /**
     * 获得产品冻结信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品冻结信息列表
     */
    List<FreezeInfoDO> getFreezeInfoList(FreezeInfoExportReqVO exportReqVO);

}
