package com.dofast.module.wms.service.storagecore;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.pojo.UserConstants;
import com.dofast.framework.common.util.bean.BeanUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.controller.admin.transaction.vo.TransactionUpdateReqVO;
import com.dofast.module.wms.convert.transaction.TransactionConvert;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueTxBean;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeTxBean;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptTxBean;
import com.dofast.module.wms.dal.dataobject.productproduce.ProductProductTxBean;
import com.dofast.module.wms.dal.dataobject.productrecpt.ProductRecptTxBean;
import com.dofast.module.wms.dal.dataobject.productsalse.ProductSalseTxBean;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueTxBean;
import com.dofast.module.wms.dal.dataobject.rtsalse.RtSalseTxBean;
import com.dofast.module.wms.dal.dataobject.rtvendor.RtVendorTxBean;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.transaction.TransactionDO;
import com.dofast.module.wms.dal.dataobject.transfer.TransferTxBean;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.transaction.TransactionService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

@Component
public class StorageCoreServiceImpl implements StorageCoreService{
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;
    @Override
    public void processItemRecpt(List<ItemRecptTxBean> lines) {
        String transactionType = Constant.TRANSACTION_TYPE_ITEM_RECPT;
        if(CollUtil.isEmpty(lines)){
            throw exception(ITEM_RECPT_NOT_EXISTS);
        }

        for (int i =0;i<lines.size();i++){
            ItemRecptTxBean line = lines.get(i);
            TransactionDO transaction = new TransactionDO();
            transaction.setTransactionType(transactionType);
            BeanUtils.copyBeanProp(transaction,line);
            transaction.setItemId(line.getId());
            transaction.setId(null);
            transaction.setTransactionFlag(1); //库存增加
            transaction.setTransactionDate(LocalDateTime.now());
            TransactionUpdateReqVO updateReqVO = TransactionConvert.INSTANCE.convert01(transaction);
            transactionService.processTransaction(updateReqVO);
        }
    }

    @Override
    public void processRtVendor(List<RtVendorTxBean> lines) {
        String transactionType = Constant.TRANSACTION_TYPE_ITEM_RTV;
        if(CollUtil.isEmpty(lines)){
            throw exception(ErrorCodeConstants.RT_VENDOR_LINE_NOT_EXISTS);
        }

        for(int i=0;i<lines.size();i++){
            RtVendorTxBean line = lines.get(i);
            TransactionDO transaction = new TransactionDO();
            transaction.setTransactionType(transactionType);
            BeanUtils.copyBeanProp(transaction,line);
            transaction.setTransactionFlag(-1); //库存减少
            transaction.setTransactionDate(LocalDateTime.now());
            TransactionUpdateReqVO updateReqVO = TransactionConvert.INSTANCE.convert01(transaction);
            transactionService.processTransaction(updateReqVO);
        }
    }

    @Override
    public void processIssue(List<IssueTxBean> lines) {
        if(CollUtil.isEmpty(lines)){
            throw exception(ErrorCodeConstants.ISSUE_HEADER_NEED_PROCESS_LINE);
        }

        String transactionType_out = Constant.TRANSACTION_TYPE_ITEM_ISSUE_OUT;
        String transactionType_in = Constant.TRANSACTION_TYPE_ITEM_ISSUE_IN;
        for(int i=0;i<lines.size();i++){
            IssueTxBean line = lines.get(i);
            //这里先构造一条原库存减少的事务
            TransactionUpdateReqVO transaction_out = new TransactionUpdateReqVO();
            BeanUtils.copyBeanProp(transaction_out, line);
            transaction_out.setTransactionType(transactionType_out);
            transaction_out.setTransactionFlag(-1);//库存减少
            transaction_out.setTransactionDate(LocalDateTime.now());
            transactionService.processTransaction(transaction_out);

            //再构造一条目的库存增加的事务
            TransactionUpdateReqVO transaction_in = new TransactionUpdateReqVO();
            BeanUtils.copyBeanProp(transaction_in, line);
            transaction_in.setTransactionType(transactionType_in);
            transaction_in.setTransactionFlag(1);//库存增加

            //由于是新增的库存记录所以需要将查询出来的库存记录ID置为空
            transaction_in.setMaterialStockId(null);

            //使用出库事务的供应商初始化入库事务的供应商
            transaction_in.setVendorId(transaction_out.getVendorId());
            transaction_in.setVendorCode(transaction_out.getVendorCode());
            transaction_in.setVendorName(transaction_out.getVendorName());
            transaction_in.setVendorNick(transaction_out.getVendorNick());

            //这里使用系统默认生成的线边库初始化对应的入库仓库、库区、库位
            WarehouseDO warehouse = warehouseService.selectWmWarehouseByWarehouseCode(Constant.VIRTUAL_WH);
            transaction_in.setWarehouseId(warehouse.getId());
            transaction_in.setWarehouseCode(warehouse.getWarehouseCode());
            transaction_in.setWarehouseName(warehouse.getWarehouseName());
            StorageLocationDO location = storageLocationService.selectWmStorageLocationByLocationCode(Constant.VIRTUAL_WS);
            transaction_in.setLocationId(location.getId());
            transaction_in.setLocationCode(location.getLocationCode());
            transaction_in.setLocationName(location.getLocationName());
            StorageAreaDO area = storageAreaService.selectWmStorageAreaByAreaCode(Constant.VIRTUAL_WA);
            transaction_in.setAreaId(area.getId());
            transaction_in.setAreaCode(area.getAreaCode());
            transaction_in.setAreaName(area.getAreaName());
            //设置入库相关联的出库事务ID
            transaction_in.setRelatedTransactionId(transaction_out.getId());
            transactionService.processTransaction(transaction_in);
        }
    }

    @Override
    public void processRtIssue(List<RtIssueTxBean> lines) {
        if(CollUtil.isEmpty(lines)){
            throw exception(ErrorCodeConstants.RT_ISSUE_NO_LINE_PROCESS);
        }

        String transactionType_out = Constant.TRANSACTION_TYPE_ITEM_RT_ISSUE_OUT;
        String transactionType_in = Constant.TRANSACTION_TYPE_ITEM_RT_ISSUE_IN;
        for(int i=0;i<lines.size();i++){
            RtIssueTxBean line = lines.get(i);

            //构造一条目的库存减少的事务
            TransactionUpdateReqVO transaction_out = new TransactionUpdateReqVO();
            transaction_out.setTransactionType(transactionType_out);
            BeanUtils.copyBeanProp(transaction_out,line);

            //这里的出库事务默认从线边库出库到实际仓库
            WarehouseDO warehouse = warehouseService.selectWmWarehouseByWarehouseCode(Constant.VIRTUAL_WH);
            transaction_out.setWarehouseId(warehouse.getId());
            transaction_out.setWarehouseCode(warehouse.getWarehouseCode());
            transaction_out.setWarehouseName(warehouse.getWarehouseName());
           StorageLocationDO location = storageLocationService.selectWmStorageLocationByLocationCode(Constant.VIRTUAL_WS);
            transaction_out.setLocationId(location.getId());
            transaction_out.setLocationCode(location.getLocationCode());
            transaction_out.setLocationName(location.getLocationName());
            StorageAreaDO area = storageAreaService.selectWmStorageAreaByAreaCode(Constant.VIRTUAL_WA);
            transaction_out.setAreaId(area.getId());
            transaction_out.setAreaCode(area.getAreaCode());
            transaction_out.setAreaName(area.getAreaName());

            transaction_out.setTransactionFlag(-1);//库存减少
            transactionService.processTransaction(transaction_out);

            //构造一条目的库存增加的事务
            TransactionUpdateReqVO transaction_in = new TransactionUpdateReqVO();
            transaction_in.setTransactionType(transactionType_in);
            BeanUtils.copyBeanProp(transaction_in,line);
            transaction_in.setTransactionFlag(1);//库存增加
            transaction_in.setTransactionDate(LocalDateTime.now());
            //由于是新增的库存记录所以需要将查询出来的库存记录ID置为空
            transaction_in.setMaterialStockId(null);
            //设置入库相关联的出库事务ID
            transaction_in.setRelatedTransactionId(transaction_out.getId());

            transactionService.processTransaction(transaction_in);
        }
    }

    @Override
    public void processProductRecpt(List<ProductRecptTxBean> lines) {
        if(CollUtil.isEmpty(lines)){
            throw exception(ErrorCodeConstants.PRODUCT_RECPT_LINE_NO_PRODUCT);
        }

        String transactionType_out = Constant.TRANSACTION_TYPE_PRODUCT_RECPT_OUT;
        String transactionType_in = Constant.TRANSACTION_TYPE_PRODUCT_RECPT_IN;

        for (int i=0;i<lines.size();i++){
            ProductRecptTxBean line = lines.get(i);

            //构造一条目的库存减少的事务
            TransactionDO transaction_out = new TransactionDO();
            transaction_out.setTransactionType(transactionType_out);
            BeanUtils.copyBeanProp(transaction_out,line);

            //这里的产品入库是从线边库入到实际的仓库，出库事务对应的仓库是线边库
            WarehouseDO warehouse = warehouseService.selectWmWarehouseByWarehouseCode(Constant.VIRTUAL_WH);
            transaction_out.setWarehouseId(warehouse.getId());
            transaction_out.setWarehouseCode(warehouse.getWarehouseCode());
            transaction_out.setWarehouseName(warehouse.getWarehouseName());
            StorageLocationDO location = storageLocationService.selectWmStorageLocationByLocationCode(Constant.VIRTUAL_WS);
            transaction_out.setLocationId(location.getId());
            transaction_out.setLocationCode(location.getLocationCode());
            transaction_out.setLocationName(location.getLocationName());
            StorageAreaDO area = storageAreaService.selectWmStorageAreaByAreaCode(Constant.VIRTUAL_WA);
            transaction_out.setAreaId(area.getId());
            transaction_out.setAreaCode(area.getAreaCode());
            transaction_out.setAreaName(area.getAreaName());

            transaction_out.setTransactionFlag(-1);//库存减少
            transaction_out.setStorageCheckFlag(false); //针对未及时报工的情况，允许线边库的库存临时为负

            TransactionUpdateReqVO updateReqVO = TransactionConvert.INSTANCE.convert01(transaction_out);
            transactionService.processTransaction(updateReqVO);

            //构造一条目的库存增加的事务
            TransactionDO transaction_in = new TransactionDO();
            transaction_in.setTransactionType(transactionType_in);
            BeanUtils.copyBeanProp(transaction_in,line);
            transaction_in.setTransactionFlag(1);//库存增加
            transaction_in.setTransactionDate(LocalDateTime.now());
            //由于是新增的库存记录所以需要将查询出来的库存记录ID置为空
            transaction_in.setMaterialStockId(null);
            //设置入库相关联的出库事务ID
            transaction_in.setRelatedTransactionId(transaction_out.getId());

            TransactionUpdateReqVO updateReqVO1 = TransactionConvert.INSTANCE.convert01(transaction_in);
            transactionService.processTransaction(updateReqVO1);
        }

    }

    @Override
    public void processProductSalse(List<ProductSalseTxBean> lines) {
        if(CollUtil.isEmpty(lines)){
            throw exception(ErrorCodeConstants.NO_PROCESS_PRODUCT_SALSE);
        }

        String transactionType = Constant.TRANSACTION_TYPE_PRODUCT_ISSUE;
        for(int i=0;i<lines.size();i++){
            ProductSalseTxBean bean = lines.get(i);
            TransactionUpdateReqVO transaction = new TransactionUpdateReqVO();
            transaction.setTransactionType(transactionType);
            BeanUtils.copyBeanProp(transaction,bean);
            transaction.setTransactionFlag(-1); //库存减少
            transaction.setTransactionDate(LocalDateTime.now());
            transactionService.processTransaction(transaction);
        }
    }

    @Override
    public void processRtSalse(List<RtSalseTxBean> lines) {
        if(CollUtil.isEmpty(lines)){
            throw exception(ErrorCodeConstants.RT_SALSE_LINE_NO_PROCESS);
        }
        String transactionType = Constant.TRANSACTION_TYPE_PRODUCT_RS;
        for(int i=0;i<lines.size();i++){
            RtSalseTxBean bean = lines.get(i);
            TransactionUpdateReqVO transaction = new TransactionUpdateReqVO();
            transaction.setTransactionType(transactionType);
            BeanUtils.copyBeanProp(transaction,bean);
            transaction.setTransactionFlag(1); //库存增加
            transaction.setTransactionDate(LocalDateTime.now());
            transactionService.processTransaction(transaction);
        }
    }

    @Override
    public void processTransfer(List<TransferTxBean> lines) {
        if(CollUtil.isEmpty(lines)){
            throw exception(ErrorCodeConstants.TRANSFER_NO_PROCESS_LINE);
        }
        String transactionType_out = Constant.TRANSACTION_TYPE_WAREHOUSE_TRANS_OUT;
        String transactionType_in = Constant.TRANSACTION_TYPE_WAREHOUSE_TRANS_IN;

        for(int i=0;i<lines.size();i++){
            TransferTxBean line = lines.get(i);
            //先执行出库
            TransactionUpdateReqVO transaction_out = new TransactionUpdateReqVO();
            transaction_out.setTransactionType(transactionType_out);
            BeanUtils.copyBeanProp(transaction_out,line);
            transaction_out.setWarehouseId(line.getFromWarehouseId());
            transaction_out.setWarehouseCode(line.getFromWarehouseCode());
            transaction_out.setWarehouseName(line.getFromWarehouseName());
            transaction_out.setLocationId(line.getFromLocationId());
            transaction_out.setLocationCode(line.getFromLocationCode());
            transaction_out.setLocationName(line.getFromLocationName());
            transaction_out.setAreaId(line.getFromAreaId());
            transaction_out.setAreaCode(line.getFromAreaCode());
            transaction_out.setAreaName(line.getFromAreaName());
            transaction_out.setTransactionFlag(-1);//库存减少
            transactionService.processTransaction(transaction_out);
            //再执行入库
            TransactionUpdateReqVO transaction_in = new TransactionUpdateReqVO();
            transaction_in.setTransactionType(transactionType_in);
            BeanUtils.copyBeanProp(transaction_in,line);
            transaction_in.setWarehouseId(line.getToWarehouseId());
            transaction_in.setWarehouseCode(line.getToWarehouseCode());
            transaction_in.setWarehouseName(line.getToWarehouseName());
            transaction_in.setLocationId(line.getToLocationId());
            transaction_in.setLocationCode(line.getToLocationCode());
            transaction_in.setLocationName(line.getToLocationName());
            transaction_in.setAreaId(line.getToAreaId());
            transaction_in.setAreaCode(line.getToAreaCode());
            transaction_in.setAreaName(line.getToAreaName());

            transaction_in.setTransactionFlag(1);//库存增加
            transaction_in.setTransactionDate(LocalDateTime.now());
            //由于是新增的库存记录所以需要将查询出来的库存记录ID置为空
            transaction_in.setMaterialStockId(null);
            //设置入库相关联的出库事务ID
            transaction_in.setRelatedTransactionId(transaction_out.getId());
            transactionService.processTransaction(transaction_in);
        }
    }

    /**
     * 库存消耗
     *
     */
    public void processItemConsume(List<ItemConsumeTxBean> lines){
        if(CollUtil.isEmpty(lines)){
            throw exception(TRANSFER_NO_PROCESS_LINE);
        }
        String transactionType = UserConstants.TRANSACTION_TYPE_ITEM_CONSUME;
        for(int i=0;i<lines.size();i++){
            ItemConsumeTxBean line = lines.get(i);
            TransactionDO transaction = new TransactionDO();
            transaction.setTransactionType(transactionType);
            BeanUtils.copyBeanProp(transaction,line);
            transaction.setTransactionFlag(-1); //库存减少
            transaction.setStorageCheckFlag(false);//库存可以为负
            transaction.setTransactionDate(LocalDateTime.now());

            WarehouseDO warehouse = warehouseService.selectWmWarehouseByWarehouseCode(UserConstants.VIRTUAL_WH);
            transaction.setWarehouseId(warehouse.getId());
            transaction.setWarehouseCode(warehouse.getWarehouseCode());
            transaction.setWarehouseName(warehouse.getWarehouseName());
            StorageLocationDO location = storageLocationService.selectWmStorageLocationByLocationCode(UserConstants.VIRTUAL_WS);
            transaction.setLocationId(location.getId());
            transaction.setLocationCode(location.getLocationCode());
            transaction.setLocationName(location.getLocationName());
            StorageAreaDO area = storageAreaService.selectWmStorageAreaByAreaCode(UserConstants.VIRTUAL_WA);
            transaction.setAreaId(area.getId());
            transaction.setAreaCode(area.getAreaCode());
            transaction.setAreaName(area.getAreaName());
            TransactionUpdateReqVO transactionUpdateReqVO = BeanUtil.toBean(transaction, TransactionUpdateReqVO.class);
            transactionService.processTransaction(transactionUpdateReqVO);
        }
    }

    /**
     * 产品产出
     *
     */
    public void processProductProduce(List<ProductProductTxBean> lines){
        if(CollUtil.isEmpty(lines)){
            throw exception(TRANSFER_NO_PROCTS_LINE);
        }
        String transactionType = UserConstants.TRANSACTION_TYPE_PRODUCT_PRODUCE;
        for(int i=0;i<lines.size();i++){
            ProductProductTxBean line = lines.get(i);
            TransactionDO transaction = new TransactionDO();
            transaction.setTransactionType(transactionType);
            BeanUtils.copyBeanProp(transaction,line);
            transaction.setTransactionFlag(1); //库存增加
            transaction.setTransactionDate(LocalDateTime.now());
            String locationCode = line.getWarehouseCode();//WH161
//            WarehouseDO warehouse = warehouseService.selectWmWarehouseByWarehouseCode(UserConstants.VIRTUAL_WH);
            WarehouseDO warehouse = warehouseService.selectWmWarehouseByWarehouseCode(locationCode);
            if (warehouse==null){
                throw exception(STORAGECORE_IS_NOT_EXITS);
            }
            transaction.setWarehouseId(warehouse.getId());
            transaction.setWarehouseCode(warehouse.getWarehouseCode());
            transaction.setWarehouseName(warehouse.getWarehouseName());
            StorageLocationDO location = storageLocationService.selectWmStorageLocationByLocationCode(line.getLocationCode());
//            StorageLocationDO location = storageLocationService.selectWmStorageLocationByLocationCode(UserConstants.VIRTUAL_WS);
            transaction.setLocationId(location.getId());
            transaction.setLocationCode(location.getLocationCode());
            transaction.setLocationName(location.getLocationName());
            StorageAreaDO area = storageAreaService.selectWmStorageAreaByAreaCode(line.getAreaCode());
            transaction.setAreaId(area.getId());
            transaction.setAreaCode(area.getAreaCode());
            transaction.setAreaName(area.getAreaName());

            TransactionUpdateReqVO transactionUpdateReqVO = BeanUtil.toBean(transaction, TransactionUpdateReqVO.class);
            transactionService.processTransaction(transactionUpdateReqVO);
        }
    }
}
