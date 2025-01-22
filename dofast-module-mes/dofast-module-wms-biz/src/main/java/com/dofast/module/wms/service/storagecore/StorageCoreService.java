package com.dofast.module.wms.service.storagecore;

import com.dofast.module.wms.dal.dataobject.allocatedheader.AllocatedTxBean;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueTxBean;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeTxBean;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptTxBean;
import com.dofast.module.wms.dal.dataobject.productproduce.ProductProductTxBean;
import com.dofast.module.wms.dal.dataobject.productrecpt.ProductRecptTxBean;
import com.dofast.module.wms.dal.dataobject.productsalse.ProductSalseTxBean;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueDO;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueTxBean;
import com.dofast.module.wms.dal.dataobject.rtsalse.RtSalseTxBean;
import com.dofast.module.wms.dal.dataobject.rtvendor.RtVendorTxBean;
import com.dofast.module.wms.dal.dataobject.transfer.TransferTxBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StorageCoreService {
    /**
     * 处理物料入库单
     * @param lines
     */
    public void processItemRecpt(List<ItemRecptTxBean> lines);


    /**
     * 减少物料入库单
     * 用于入库单拆分后, 减少原入库单数量
     * @param lines
     */
    public void  processReduceItemRecpt(List<ItemRecptTxBean> lines);


    /**
     * 处理供应商退货单
     * @param lines
     */
    public void processRtVendor(List<RtVendorTxBean> lines);

    /**
     * 处理生产领料
     * @param lines
     */
    public void processIssue(List<IssueTxBean> lines);

    /**
     * 处理调拨单
     * @param lines
     */
    public void processAllocated(List<AllocatedTxBean> lines);


    /**
     * 处理生产退料
     * @param lines
     */
    public void processRtIssue(List<RtIssueTxBean> lines, RtIssueDO rtIssueDO);

    /**
     * 处理生产消耗
     * @param lines
     */
    public void processItemConsume(List<ItemConsumeTxBean> lines);

    /**
     * 处理产品产出
     * @param lines
     */
    public void processProductProduce(List<ProductProductTxBean> lines);

    /**
     * 处理产品入库
     * @param lines
     */
    public void processProductRecpt(List<ProductRecptTxBean> lines);

    /**
     * 处理产品销售出库
     * @param lines
     */
    public void processProductSalse(List<ProductSalseTxBean> lines);

    /**
     * 处理销售退货
     * @param lines
     */
    public void processRtSalse(List<RtSalseTxBean> lines);

    /**
     * 处理转移单
     * @param lines
     */
    public void processTransfer(List<TransferTxBean> lines);
}
