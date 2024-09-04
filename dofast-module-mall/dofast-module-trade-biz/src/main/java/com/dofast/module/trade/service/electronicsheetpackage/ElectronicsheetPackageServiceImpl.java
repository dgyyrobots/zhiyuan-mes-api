package com.dofast.module.trade.service.electronicsheetpackage;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageCreateReqVO;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageExportReqVO;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackagePageReqVO;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageUpdateReqVO;
import com.dofast.module.trade.convert.electronicsheetpackage.ElectronicsheetPackageConvert;
import com.dofast.module.trade.dal.dataobject.electronicsheetpackage.ElectronicsheetPackageDO;
import com.dofast.module.trade.dal.mysql.electronicsheetpackage.ElectronicsheetPackageMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.trade.enums.ErrorCodeConstants.ELECTRONICSHEET_PACKAGE_NOT_EXISTS;

/**
 * 电子面单 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ElectronicsheetPackageServiceImpl implements ElectronicsheetPackageService {

    @Resource
    private ElectronicsheetPackageMapper electronicsheetPackageMapper;

    @Override
    public Integer createElectronicsheetPackage(ElectronicsheetPackageCreateReqVO createReqVO) {
        // 插入
        ElectronicsheetPackageDO electronicsheetPackage = ElectronicsheetPackageConvert.INSTANCE.convert(createReqVO);
        electronicsheetPackageMapper.insert(electronicsheetPackage);
        // 返回
        return electronicsheetPackage.getId();
    }

    @Override
    public void updateElectronicsheetPackage(ElectronicsheetPackageUpdateReqVO updateReqVO) {
        // 校验存在
        validateElectronicsheetPackageExists(updateReqVO.getId());
        // 更新
        ElectronicsheetPackageDO updateObj = ElectronicsheetPackageConvert.INSTANCE.convert(updateReqVO);
        electronicsheetPackageMapper.updateById(updateObj);
    }

    @Override
    public void deleteElectronicsheetPackage(Integer id) {
        // 校验存在
        validateElectronicsheetPackageExists(id);
        // 删除
        electronicsheetPackageMapper.deleteById(id);
    }

    private void validateElectronicsheetPackageExists(Integer id) {
        if (electronicsheetPackageMapper.selectById(id) == null) {
            throw exception(ELECTRONICSHEET_PACKAGE_NOT_EXISTS);
        }
    }

    @Override
    public ElectronicsheetPackageDO getElectronicsheetPackage(Integer id) {
        return electronicsheetPackageMapper.selectById(id);
    }

    @Override
    public List<ElectronicsheetPackageDO> getElectronicsheetPackageList(Collection<Integer> ids) {
        return electronicsheetPackageMapper.selectBatchIds(ids);
    }

    @Override
    public List<ElectronicsheetPackageDO> getElectronicsheetPackageListByOrderNo(String orderNo) {
        ElectronicsheetPackageExportReqVO exportReqVO = new ElectronicsheetPackageExportReqVO();
        exportReqVO.setOrderNo(orderNo);
        return electronicsheetPackageMapper.selectList(exportReqVO);
    }

    @Override
    public PageResult<ElectronicsheetPackageDO> getElectronicsheetPackagePage(ElectronicsheetPackagePageReqVO pageReqVO) {
        return electronicsheetPackageMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ElectronicsheetPackageDO> getElectronicsheetPackageList(ElectronicsheetPackageExportReqVO exportReqVO) {
        return electronicsheetPackageMapper.selectList(exportReqVO);
    }

    @Override
    public List<ElectronicsheetPackageDO> getElectronicsheetByOrderNo(String orderNo) {
        return electronicsheetPackageMapper.selectByOrderNo(orderNo);
    }

}
