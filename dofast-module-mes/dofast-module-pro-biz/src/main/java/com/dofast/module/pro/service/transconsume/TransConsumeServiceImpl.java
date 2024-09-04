package com.dofast.module.pro.service.transconsume;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.pro.controller.admin.transconsume.vo.*;
import com.dofast.module.pro.dal.dataobject.transconsume.TransConsumeDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.transconsume.TransConsumeConvert;
import com.dofast.module.pro.dal.mysql.transconsume.TransConsumeMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;

/**
 * 物料消耗记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TransConsumeServiceImpl implements TransConsumeService {

    @Resource
    private TransConsumeMapper transConsumeMapper;

    @Override
    public Long createTransConsume(TransConsumeCreateReqVO createReqVO) {
        // 插入
        TransConsumeDO transConsume = TransConsumeConvert.INSTANCE.convert(createReqVO);
        transConsumeMapper.insert(transConsume);
        // 返回
        return transConsume.getId();
    }

    @Override
    public void updateTransConsume(TransConsumeUpdateReqVO updateReqVO) {
        // 校验存在
        validateTransConsumeExists(updateReqVO.getId());
        // 更新
        TransConsumeDO updateObj = TransConsumeConvert.INSTANCE.convert(updateReqVO);
        transConsumeMapper.updateById(updateObj);
    }

    @Override
    public void deleteTransConsume(Long id) {
        // 校验存在
        validateTransConsumeExists(id);
        // 删除
        transConsumeMapper.deleteById(id);
    }

    private void validateTransConsumeExists(Long id) {
        if (transConsumeMapper.selectById(id) == null) {
            throw exception(TRANS_CONSUME_NOT_EXISTS);
        }
    }

    @Override
    public TransConsumeDO getTransConsume(Long id) {
        return transConsumeMapper.selectById(id);
    }

    @Override
    public List<TransConsumeDO> getTransConsumeList(Collection<Long> ids) {
        return transConsumeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TransConsumeDO> getTransConsumePage(TransConsumePageReqVO pageReqVO) {
        return transConsumeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TransConsumeDO> getTransConsumeList(TransConsumeExportReqVO exportReqVO) {
        return transConsumeMapper.selectList(exportReqVO);
    }

    @Override
    public List<TransConsumeDO> getTransConsumeList(TransConsumeListVO listVO) {
        return transConsumeMapper.selectList(listVO);
    }

}
