package com.dofast.module.wms.service.transfer;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.dal.dataobject.transfer.TransferTxBean;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.transfer.vo.*;
import com.dofast.module.wms.dal.dataobject.transfer.TransferDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.transfer.TransferConvert;
import com.dofast.module.wms.dal.mysql.transfer.TransferMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 转移单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TransferServiceImpl implements TransferService {

    @Resource
    private TransferMapper transferMapper;

    @Override
    public List<TransferTxBean> getTxBeans(Long transferid) {
        return transferMapper.getTxBeans(transferid);
    }

    @Override
    public String checkUnique(TransferBaseVO wmTransfer) {
        TransferDO transfer = transferMapper.checkUnique(wmTransfer);
        Long transferId = wmTransfer.getId() == null?-1L:wmTransfer.getId();
        if(StrUtils.isNotNull(transfer) && transferId.longValue() != transfer.getId().longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createTransfer(TransferCreateReqVO createReqVO) {
        // 插入
        TransferDO transfer = TransferConvert.INSTANCE.convert(createReqVO);
        transferMapper.insert(transfer);
        // 返回
        return transfer.getId();
    }

    @Override
    public void updateTransfer(TransferUpdateReqVO updateReqVO) {
        // 校验存在
        validateTransferExists(updateReqVO.getId());
        // 更新
        TransferDO updateObj = TransferConvert.INSTANCE.convert(updateReqVO);
        transferMapper.updateById(updateObj);
    }

    @Override
    public void deleteTransfer(Long id) {
        // 校验存在
        validateTransferExists(id);
        // 删除
        transferMapper.deleteById(id);
    }

    private void validateTransferExists(Long id) {
        if (transferMapper.selectById(id) == null) {
            throw exception(TRANSFER_NOT_EXISTS);
        }
    }

    @Override
    public TransferDO getTransfer(Long id) {
        return transferMapper.selectById(id);
    }

    @Override
    public List<TransferDO> getTransferList(Collection<Long> ids) {
        return transferMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TransferDO> getTransferPage(TransferPageReqVO pageReqVO) {
        return transferMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TransferDO> getTransferList(TransferExportReqVO exportReqVO) {
        return transferMapper.selectList(exportReqVO);
    }

}
