package com.dofast.module.trade.service.calculate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordCreateReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordExportReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordPageReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordUpdateReqVO;
import com.dofast.module.trade.convert.calculate.CalculateRecordConvert;
import com.dofast.module.trade.dal.dataobject.calculate.CalculateRecordDO;
import com.dofast.module.trade.dal.mysql.calculate.CalculateRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.trade.enums.ErrorCodeConstants.CALCULATE_RECORD_NOT_EXISTS;

/**
 * 计价记录 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CalculateRecordServiceImpl implements CalculateRecordService {

    @Resource
    private CalculateRecordMapper calculateRecordMapper;

    @Override
    public Long createCalculateRecord(CalculateRecordCreateReqVO createReqVO) {
        // 插入
        CalculateRecordDO calculateRecord = CalculateRecordConvert.INSTANCE.convert(createReqVO);
        calculateRecordMapper.insert(calculateRecord);
        // 返回
        return calculateRecord.getId();
    }

    @Override
    public void updateCalculateRecord(CalculateRecordUpdateReqVO updateReqVO) {
        // 校验存在
        validateCalculateRecordExists(updateReqVO.getId());
        // 更新
        CalculateRecordDO updateObj = CalculateRecordConvert.INSTANCE.convert(updateReqVO);
        calculateRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteCalculateRecord(Long id) {
        // 校验存在
        validateCalculateRecordExists(id);
        // 删除
        calculateRecordMapper.deleteById(id);
    }

    private void validateCalculateRecordExists(Long id) {
        if (calculateRecordMapper.selectById(id) == null) {
            throw exception(CALCULATE_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public CalculateRecordDO getCalculateRecord(Long id) {
        return calculateRecordMapper.selectById(id);
    }

    @Override
    public List<CalculateRecordDO> getCalculateRecordList(Collection<Long> ids) {
        return calculateRecordMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CalculateRecordDO> getCalculateRecordPage(CalculateRecordPageReqVO pageReqVO) {
        return calculateRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CalculateRecordDO> getCalculateRecordList(CalculateRecordExportReqVO exportReqVO) {
        return calculateRecordMapper.selectList(exportReqVO);
    }

}
