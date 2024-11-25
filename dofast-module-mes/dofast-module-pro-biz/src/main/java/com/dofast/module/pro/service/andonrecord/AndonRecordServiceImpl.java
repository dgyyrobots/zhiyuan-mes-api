package com.dofast.module.pro.service.andonrecord;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.pro.controller.admin.andonrecord.vo.*;
import com.dofast.module.pro.dal.dataobject.andonrecord.AndonRecordDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.andonrecord.AndonRecordConvert;
import com.dofast.module.pro.dal.mysql.andonrecord.AndonRecordMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 安灯呼叫记录 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class AndonRecordServiceImpl implements AndonRecordService {

    @Resource
    private AndonRecordMapper andonRecordMapper;

    @Override
    public Long createAndonRecord(AndonRecordCreateReqVO createReqVO) {
        // 插入
        AndonRecordDO andonRecord = AndonRecordConvert.INSTANCE.convert(createReqVO);
        andonRecordMapper.insert(andonRecord);
        // 返回
        return andonRecord.getRecordId();
    }

    @Override
    public void updateAndonRecord(AndonRecordUpdateReqVO updateReqVO) {
        // 校验存在
        validateAndonRecordExists(updateReqVO.getRecordId());
        // 更新
        AndonRecordDO updateObj = AndonRecordConvert.INSTANCE.convert(updateReqVO);
        andonRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteAndonRecord(Long id) {
        // 校验存在
        validateAndonRecordExists(id);
        // 删除
        andonRecordMapper.deleteById(id);
    }

    private void validateAndonRecordExists(Long id) {
        if (andonRecordMapper.selectById(id) == null) {
            throw exception(ANDON_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public AndonRecordDO getAndonRecord(Long id) {
        return andonRecordMapper.selectById(id);
    }

    @Override
    public List<AndonRecordDO> getAndonRecordList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return andonRecordMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AndonRecordDO> getAndonRecordPage(AndonRecordPageReqVO pageReqVO) {
        return andonRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AndonRecordDO> getAndonRecordList(AndonRecordExportReqVO exportReqVO) {
        return andonRecordMapper.selectList(exportReqVO);
    }

}
