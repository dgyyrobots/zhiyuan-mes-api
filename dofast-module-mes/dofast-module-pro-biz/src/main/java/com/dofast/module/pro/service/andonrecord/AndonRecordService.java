package com.dofast.module.pro.service.andonrecord;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.andonrecord.vo.*;
import com.dofast.module.pro.dal.dataobject.andonrecord.AndonRecordDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 安灯呼叫记录 Service 接口
 *
 * @author 惠智造
 */
public interface AndonRecordService {

    /**
     * 创建安灯呼叫记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAndonRecord(@Valid AndonRecordCreateReqVO createReqVO);

    /**
     * 更新安灯呼叫记录
     *
     * @param updateReqVO 更新信息
     */
    void updateAndonRecord(@Valid AndonRecordUpdateReqVO updateReqVO);

    /**
     * 删除安灯呼叫记录
     *
     * @param id 编号
     */
    void deleteAndonRecord(Long id);

    /**
     * 获得安灯呼叫记录
     *
     * @param id 编号
     * @return 安灯呼叫记录
     */
    AndonRecordDO getAndonRecord(Long id);

    /**
     * 获得安灯呼叫记录列表
     *
     * @param ids 编号
     * @return 安灯呼叫记录列表
     */
    List<AndonRecordDO> getAndonRecordList(Collection<Long> ids);

    /**
     * 获得安灯呼叫记录分页
     *
     * @param pageReqVO 分页查询
     * @return 安灯呼叫记录分页
     */
    PageResult<AndonRecordDO> getAndonRecordPage(AndonRecordPageReqVO pageReqVO);

    /**
     * 获得安灯呼叫记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 安灯呼叫记录列表
     */
    List<AndonRecordDO> getAndonRecordList(AndonRecordExportReqVO exportReqVO);

}
