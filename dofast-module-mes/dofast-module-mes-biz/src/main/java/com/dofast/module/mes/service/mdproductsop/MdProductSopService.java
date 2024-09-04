package com.dofast.module.mes.service.mdproductsop;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.mdproductsop.vo.*;
import com.dofast.module.mes.dal.dataobject.mdproductsop.MdProductSopDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 产品SOP Service 接口
 *
 * @author 芋道源码
 */
public interface MdProductSopService {

    /**
     * 创建产品SOP
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMdProductSop(@Valid MdProductSopCreateReqVO createReqVO);

    /**
     * 更新产品SOP
     *
     * @param updateReqVO 更新信息
     */
    void updateMdProductSop(@Valid MdProductSopUpdateReqVO updateReqVO);

    /**
     * 删除产品SOP
     *
     * @param id 编号
     */
    void deleteMdProductSop(Long id);

    /**
     * 获得产品SOP
     *
     * @param id 编号
     * @return 产品SOP
     */
    MdProductSopDO getMdProductSop(Long id);

    /**
     * 获得产品SOP列表
     *
     * @param ids 编号
     * @return 产品SOP列表
     */
    List<MdProductSopDO> getMdProductSopList(Collection<Long> ids);

    /**
     * 获得产品SOP分页
     *
     * @param pageReqVO 分页查询
     * @return 产品SOP分页
     */
    PageResult<MdProductSopDO> getMdProductSopPage(MdProductSopPageReqVO pageReqVO);

    /**
     * 获得产品SOP列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品SOP列表
     */
    List<MdProductSopDO> getMdProductSopList(MdProductSopExportReqVO exportReqVO);

}
