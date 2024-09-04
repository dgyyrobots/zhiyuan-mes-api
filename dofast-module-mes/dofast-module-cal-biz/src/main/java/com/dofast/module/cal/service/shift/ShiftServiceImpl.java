package com.dofast.module.cal.service.shift;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.cal.controller.admin.shift.vo.*;
import com.dofast.module.cal.dal.dataobject.shift.ShiftDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.cal.convert.shift.ShiftConvert;
import com.dofast.module.cal.dal.mysql.shift.ShiftMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.cal.enums.ErrorCodeConstants.*;

/**
 * 计划班次 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ShiftServiceImpl implements ShiftService {

    @Resource
    private ShiftMapper shiftMapper;

    @Override
    public Long createShift(ShiftCreateReqVO createReqVO) {
        // 插入
        ShiftDO shift = ShiftConvert.INSTANCE.convert(createReqVO);
        shiftMapper.insert(shift);
        // 返回
        return shift.getId();
    }

    @Override
    public void updateShift(ShiftUpdateReqVO updateReqVO) {
        // 校验存在
        validateShiftExists(updateReqVO.getId());
        // 更新
        ShiftDO updateObj = ShiftConvert.INSTANCE.convert(updateReqVO);
        shiftMapper.updateById(updateObj);
    }

    @Override
    public void deleteShift(Long id) {
        // 校验存在
        validateShiftExists(id);
        // 删除
        shiftMapper.deleteById(id);
    }

    private void validateShiftExists(Long id) {
        if (shiftMapper.selectById(id) == null) {
            throw exception(SHIFT_NOT_EXISTS);
        }
    }

    @Override
    public ShiftDO getShift(Long id) {
        return shiftMapper.selectById(id);
    }

    @Override
    public List<ShiftDO> getShiftList(Collection<Long> ids) {
        return shiftMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ShiftDO> getShiftPage(ShiftPageReqVO pageReqVO) {
        return shiftMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ShiftDO> getShiftList(ShiftExportReqVO exportReqVO) {
        return shiftMapper.selectList(exportReqVO);
    }

}
