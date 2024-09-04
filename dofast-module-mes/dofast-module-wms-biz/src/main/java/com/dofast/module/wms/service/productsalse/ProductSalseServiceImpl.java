package com.dofast.module.wms.service.productsalse;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.api.WorkStationAPi.WorkStationApi;
import com.dofast.module.mes.api.WorkStationAPi.dto.WorkStationDTO;
import com.dofast.module.mes.api.autocode.AutoCodeApi;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.dal.dataobject.mdclient.MdClientDO;
import com.dofast.module.mes.service.mdclient.MdClientService;
import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;
import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;
import com.dofast.module.qms.api.oqcApi.dto.OqcDTO;
import com.dofast.module.trade.api.mixinorder.MixinOrderApi;
import com.dofast.module.trade.api.mixinorder.dto.MixinOrderDTO;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockExportReqVO;
import com.dofast.module.wms.dal.dataobject.materialstock.MaterialStockDO;
import com.dofast.module.wms.dal.dataobject.productsalse.ProductSalseTxBean;
import com.dofast.module.wms.dal.dataobject.productsalseline.ProductSalseLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.dal.mysql.productproduceline.ProductProduceLineMapper;
import com.dofast.module.wms.dal.mysql.productsalseline.ProductSalseLineMapper;
import com.dofast.module.wms.service.materialstock.MaterialStockService;
import com.dofast.module.wms.service.productproduceline.ProductProduceLineService;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.productsalse.vo.*;
import com.dofast.module.wms.dal.dataobject.productsalse.ProductSalseDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.productsalse.ProductSalseConvert;
import com.dofast.module.wms.dal.mysql.productsalse.ProductSalseMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.ADD_ROW;
import static com.dofast.module.pro.enums.ErrorCodeConstants.COME_WORDORER;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 销售出库单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductSalseServiceImpl implements ProductSalseService {

    @Resource
    private ProductSalseMapper productSalseMapper;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @Resource
    private WorkStationApi workStationApi;

    @Resource
    private MdClientService mdClientService;

    @Resource
    private AutoCodeApi autoCodeApi;

    @Resource
    private MixinOrderApi mixinOrderApi;

    @Resource
    private ProductSalseLineMapper productSalseLineMapper;

    @Resource
    private MaterialStockService materialStockService;

    @Override
    public List<ProductSalseTxBean> getTxBeans(Long salseId) {
        return productSalseMapper.getTxBeans(salseId);
    }

    @Override
    public String checkUnique(ProductSalseBaseVO wmProductSalse) {
        ProductSalseDO salse = productSalseMapper.checkUnique(wmProductSalse);
        Long salseId = wmProductSalse.getId() ==null? -1L:wmProductSalse.getId();
        if(StrUtils.isNotNull(salse) && salseId.longValue() != salse.getId().longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createProductSalse(ProductSalseCreateReqVO createReqVO) {
        // 插入
        ProductSalseDO productSalse = ProductSalseConvert.INSTANCE.convert(createReqVO);
        productSalseMapper.insert(productSalse);
        // 返回
        return productSalse.getId();
    }

    @Override
    public void updateProductSalse(ProductSalseUpdateReqVO updateReqVO) {
        // 校验存在
        validateProductSalseExists(updateReqVO.getId());
        // 更新
        ProductSalseDO updateObj = ProductSalseConvert.INSTANCE.convert(updateReqVO);
        productSalseMapper.updateById(updateObj);
    }

    @Override
    public void deleteProductSalse(Long id) {
        // 校验存在
        validateProductSalseExists(id);
        // 删除
        productSalseMapper.deleteById(id);
    }

    private void validateProductSalseExists(Long id) {
        if (productSalseMapper.selectById(id) == null) {
            throw exception(PRODUCT_SALSE_NOT_EXISTS);
        }
    }

    @Override
    public ProductSalseDO getProductSalse(Long id) {
        return productSalseMapper.selectById(id);
    }

    @Override
    public List<ProductSalseDO> getProductSalseList(Collection<Long> ids) {
        return productSalseMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductSalseDO> getProductSalsePage(ProductSalsePageReqVO pageReqVO) {
        return productSalseMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductSalseDO> getProductSalseList(ProductSalseExportReqVO exportReqVO) {
        return productSalseMapper.selectList(exportReqVO);
    }

    @Override
    public void generateProductSale(FeedbackDTO feedback, WorkorderDTO workorder, OqcDTO oqc){
        ProductSalseDO productSalseDO = new ProductSalseDO();
        productSalseDO.setSalseCode(autoCodeApi.genSerialCode("PRODUCTSALSE_CODE", null));
        productSalseDO.setSalseName(workorder.getWorkorderName());
        //更新库区，库位，仓库的信息
        WorkStationDTO workstation = workStationApi.getWorkstation(feedback.getWorkstationId());
        WarehouseDO warehouse = warehouseService.getWarehouse(workstation.getWarehouseId());
        StorageLocationDO location = storageLocationService.getStorageLocation(workstation.getLocationId());
        StorageAreaDO area = storageAreaService.getStorageArea(workstation.getAreaId());
        productSalseDO.setWarehouseId(warehouse.getId());
        productSalseDO.setWarehouseCode(warehouse.getWarehouseCode());
        productSalseDO.setWarehouseName(warehouse.getWarehouseName());
        productSalseDO.setLocationId(location.getId());
        productSalseDO.setLocationCode(location.getLocationCode());
        productSalseDO.setLocationName(location.getLocationName());
        productSalseDO.setAreaId(area.getId());
        productSalseDO.setAreaCode(area.getAreaCode());
        productSalseDO.setAreaName(area.getAreaName());

        //客户信息
        MdClientDO mdClientDO = mdClientService.getMdClient(workorder.getClientId());
        productSalseDO.setClientCode(mdClientDO.getClientCode());
        productSalseDO.setClientId(mdClientDO.getId());
        productSalseDO.setClientName(mdClientDO.getClientName());
        productSalseDO.setClientNick(mdClientDO.getClientNick());

        productSalseDO.setOqcId(oqc.getId());
        productSalseDO.setOqcCode(oqc.getOqcCode());

        MixinOrderDTO mixinOrderDTO = mixinOrderApi.getMixinOrder(workorder.getMixinOrderId());
        productSalseDO.setSoCode(mixinOrderDTO.getSaleNo());
        productSalseDO.setRemark(workorder.getRemark());
        productSalseDO.setStatus(workorder.getStatus());
        Integer id = productSalseMapper.insert(productSalseDO);
        if (id<=0){
            throw exception(COME_WORDORER);
        }

        //关联行数据
        ProductSalseLineDO line = BeanUtil.toBean(productSalseDO, ProductSalseLineDO.class);
        line.setItemId(feedback.getItemId());
        line.setItemCode(feedback.getItemCode());
        line.setItemName(feedback.getItemName());
        line.setSalseId(Long.valueOf(id.toString()));
        line.setUnitOfMeasure(oqc.getUnitOfMeasure());
        line.setSpecification(oqc.getSpecification());
        line.setBatchCode(oqc.getBatchCode());
        line.setQuantitySalse(oqc.getQuantityOut());
        MaterialStockExportReqVO materialStockExportReqVO = new MaterialStockExportReqVO();
        materialStockExportReqVO.setItemId(feedback.getItemId());
        MaterialStockDO materialStockDO = materialStockService.getMaterialStockList(materialStockExportReqVO).get(0);
        line.setMaterialStockId(materialStockDO.getId());
        line.setId(null);
        int insert = productSalseLineMapper.insert(line);
        if (insert<=0){
            throw exception(ADD_ROW);
        }
    }

}
