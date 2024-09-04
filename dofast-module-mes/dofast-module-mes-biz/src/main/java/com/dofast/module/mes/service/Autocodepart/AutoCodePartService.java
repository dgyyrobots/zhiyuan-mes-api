package com.dofast.module.mes.service.Autocodepart;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.Autocodepart.vo.*;
import com.dofast.module.mes.controller.admin.Autocoderule.vo.AutoCodeRuleListVO;
import com.dofast.module.mes.dal.dataobject.Autocodepart.AutoCodePartDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 编码生成规则组成 Service 接口
 *
 * @author 芋道源码
 */
public interface AutoCodePartService {
    public String checkPartUnique(AutoCodePartBaseVO sysAutoCodePart);
    List<AutoCodePartDO> getAutoCodePartList(AutoCodePartListVO listVO);
    /**
     * 创建编码生成规则组成
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAutoCodePart(@Valid AutoCodePartCreateReqVO createReqVO);

    /**
     * 更新编码生成规则组成
     *
     * @param updateReqVO 更新信息
     */
    void updateAutoCodePart(@Valid AutoCodePartUpdateReqVO updateReqVO);

    /**
     * 删除编码生成规则组成
     *
     * @param id 编号
     */
    void deleteAutoCodePart(Long id);

    /**
     * 获得编码生成规则组成
     *
     * @param id 编号
     * @return 编码生成规则组成
     */
    AutoCodePartDO getAutoCodePart(Long id);

    /**
     * 获得编码生成规则组成列表
     *
     * @param ids 编号
     * @return 编码生成规则组成列表
     */
    List<AutoCodePartDO> getAutoCodePartList(Collection<Long> ids);

    /**
     * 获得编码生成规则组成分页
     *
     * @param pageReqVO 分页查询
     * @return 编码生成规则组成分页
     */
    PageResult<AutoCodePartDO> getAutoCodePartPage(AutoCodePartPageReqVO pageReqVO);

    /**
     * 获得编码生成规则组成列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 编码生成规则组成列表
     */
    List<AutoCodePartDO> getAutoCodePartList(AutoCodePartExportReqVO exportReqVO);

}
