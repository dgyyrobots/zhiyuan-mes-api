package com.dofast.module.wms.service.transferline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.transferline.vo.*;
import com.dofast.module.wms.dal.dataobject.transferline.TransferLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.transferline.TransferLineConvert;
import com.dofast.module.wms.dal.mysql.transferline.TransferLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 转移单行 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TransferLineServiceImpl implements TransferLineService {

    @Resource
    private TransferLineMapper transferLineMapper;

    @Override
    public int deleteByTransferId(Long transferId) {
        return transferLineMapper.deleteByTransferId(transferId);
    }

    @Override
    public Long createTransferLine(TransferLineCreateReqVO createReqVO) {
        // 插入
        TransferLineDO transferLine = TransferLineConvert.INSTANCE.convert(createReqVO);
        transferLineMapper.insert(transferLine);
        // 返回
        return transferLine.getId();
    }

    @Override
    public void updateTransferLine(TransferLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateTransferLineExists(updateReqVO.getId());
        // 更新
        TransferLineDO updateObj = TransferLineConvert.INSTANCE.convert(updateReqVO);
        transferLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteTransferLine(Long id) {
        // 校验存在
        validateTransferLineExists(id);
        // 删除
        transferLineMapper.deleteById(id);
    }

    private void validateTransferLineExists(Long id) {
        if (transferLineMapper.selectById(id) == null) {
            throw exception(TRANSFER_LINE_NOT_EXISTS);
        }
    }

    @Override
    public TransferLineDO getTransferLine(Long id) {
        return transferLineMapper.selectById(id);
    }

    @Override
    public List<TransferLineDO> getTransferLineList(Collection<Long> ids) {
        return transferLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TransferLineDO> getTransferLinePage(TransferLinePageReqVO pageReqVO) {
        return transferLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TransferLineDO> getTransferLineList(TransferLineExportReqVO exportReqVO) {
        return transferLineMapper.selectList(exportReqVO);
    }

    @Override
    public List<TransferLineDO> getTransferLineList(TransferLineListVO exportReqVO) {
        return transferLineMapper.selectList(exportReqVO);
    }

}
