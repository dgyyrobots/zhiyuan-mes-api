package com.dofast.module.mes.service.mdproductbom;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.mdproductbom.vo.*;
import com.dofast.module.mes.dal.dataobject.mdproductbom.MdProductBomDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 产品BOM关系 Service 接口
 *
 * @author 芋道源码
 */
public interface MdProductBomService {

    List<MdProductBomDO> selectList(MdProductBomListVO listVO);

    /**
     * 创建产品BOM关系
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMdProductBom(@Valid MdProductBomCreateReqVO createReqVO);

    /**
     * 更新产品BOM关系
     *
     * @param updateReqVO 更新信息
     */
    void updateMdProductBom(@Valid MdProductBomUpdateReqVO updateReqVO);

    /**
     * 删除产品BOM关系
     *
     * @param id 编号
     */
    void deleteMdProductBom(Long id);

    /**
     * 获得产品BOM关系
     *
     * @param id 编号
     * @return 产品BOM关系
     */
    MdProductBomDO getMdProductBom(Long id);

    /**
     * 获得产品BOM关系列表
     *
     * @param ids 编号
     * @return 产品BOM关系列表
     */
    List<MdProductBomDO> getMdProductBomList(Collection<Long> ids);

    /**
     * 获得产品BOM关系分页
     *
     * @param pageReqVO 分页查询
     * @return 产品BOM关系分页
     */
    PageResult<MdProductBomDO> getMdProductBomPage(MdProductBomPageReqVO pageReqVO);

    /**
     * 获得产品BOM关系列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品BOM关系列表
     */
    List<MdProductBomDO> getMdProductBomList(MdProductBomExportReqVO exportReqVO);

}
