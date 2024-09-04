package com.dofast.module.qms.service.defectrecord;

import java.util.*;
import javax.validation.*;
import com.dofast.module.qms.controller.admin.defectrecord.vo.*;
import com.dofast.module.qms.dal.dataobject.defectrecord.DefectRecordDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 检验单缺陷记录 Service 接口
 *
 * @author 芋道源码
 */
public interface DefectRecordService {
    /**
     * 根据检测单ID和对应的类型删除
     * @param qcDefectRecord
     * @return
     */
    public int deleteByQcIdAndType(DefectRecordBaseVO qcDefectRecord);
    /**
     * 创建检验单缺陷记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDefectRecord(@Valid DefectRecordCreateReqVO createReqVO);

    /**
     * 更新检验单缺陷记录
     *
     * @param updateReqVO 更新信息
     */
    void updateDefectRecord(@Valid DefectRecordUpdateReqVO updateReqVO);

    /**
     * 删除检验单缺陷记录
     *
     * @param id 编号
     */
    void deleteDefectRecord(Long id);

    /**
     * 获得检验单缺陷记录
     *
     * @param id 编号
     * @return 检验单缺陷记录
     */
    DefectRecordDO getDefectRecord(Long id);

    /**
     * 获得检验单缺陷记录列表
     *
     * @param ids 编号
     * @return 检验单缺陷记录列表
     */
    List<DefectRecordDO> getDefectRecordList(Collection<Long> ids);

    /**
     * 获得检验单缺陷记录分页
     *
     * @param pageReqVO 分页查询
     * @return 检验单缺陷记录分页
     */
    PageResult<DefectRecordDO> getDefectRecordPage(DefectRecordPageReqVO pageReqVO);

    /**
     * 获得检验单缺陷记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 检验单缺陷记录列表
     */
    List<DefectRecordDO> getDefectRecordList(DefectRecordExportReqVO exportReqVO);

    void updateAllDefectRecord(List<DefectRecordUpdateReqVO> updateReqVO);

    void updateOrCreateDefectRecord(List<DefectRecordUpdateReqVO> updateReqVOS);
}
