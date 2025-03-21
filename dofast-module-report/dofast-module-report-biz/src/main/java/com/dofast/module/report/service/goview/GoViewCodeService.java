package com.dofast.module.report.service.goview;

import java.util.*;
import javax.validation.*;

import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeCreateReqVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeExportReqVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodePageReqVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeUpdateReqVO;
import com.dofast.module.report.dal.dataobject.goview.GoViewCodeDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * GoView登录 Service 接口
 *
 * @author 惠智造
 */
public interface GoViewCodeService {

    /**
     * 创建GoView登录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGoViewCode(@Valid GoViewCodeCreateReqVO createReqVO);

    /**
     * 更新GoView登录
     *
     * @param updateReqVO 更新信息
     */
    void updateGoViewCode(@Valid GoViewCodeUpdateReqVO updateReqVO);

    /**
     * 删除GoView登录
     *
     * @param id 编号
     */
    void deleteGoViewCode(Long id);

    /**
     * 获得GoView登录
     *
     * @param id 编号
     * @return GoView登录
     */
    GoViewCodeDO getGoViewCode(Long id);

    /**
     * 获得GoView登录
     *
     * @param code 编号
     * @return GoView登录
     */
    GoViewCodeDO getGoViewCode(String code);


    /**
     * 获得GoView登录列表
     *
     * @param ids 编号
     * @return GoView登录列表
     */
    List<GoViewCodeDO> getGoViewCodeList(Collection<Long> ids);

    /**
     * 获得GoView登录分页
     *
     * @param pageReqVO 分页查询
     * @return GoView登录分页
     */
    PageResult<GoViewCodeDO> getGoViewCodePage(GoViewCodePageReqVO pageReqVO);

    /**
     * 获得GoView登录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return GoView登录列表
     */
    List<GoViewCodeDO> getGoViewCodeList(GoViewCodeExportReqVO exportReqVO);

}
