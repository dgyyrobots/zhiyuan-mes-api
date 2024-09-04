package com.dofast.module.wms.service.package1;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.package1.vo.*;
import com.dofast.module.wms.dal.dataobject.package1.PackageDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.package1.PackageConvert;
import com.dofast.module.wms.dal.mysql.package1.PackageMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 装箱单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PackageServiceImpl implements PackageService {

    @Resource
    private PackageMapper packageMapper;

    @Override
    public PackageDO selectWmPackageByPackageId(Long packageId) {
        return packageMapper.selectWmPackageByPackageId(packageId);
    }

    @Override
    public String checkPackgeCodeUnique(PackageBaseVO wmPackage) {
        PackageDO pack = packageMapper.checkPackgeCodeUnique(wmPackage);
        Long packgeId = wmPackage.getId() ==null?-1L:wmPackage.getId();
        if(StrUtils.isNotNull(pack) && packgeId.longValue()!=pack.getId()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createPackage(PackageCreateReqVO createReqVO) {
        // 插入
        PackageDO packageDO = PackageConvert.INSTANCE.convert(createReqVO);
        packageMapper.insert(packageDO);
        // 返回
        return packageDO.getId();
    }

    @Override
    public void updatePackage(PackageUpdateReqVO updateReqVO) {
        // 校验存在
        validatePackageExists(updateReqVO.getId());
        // 更新
        PackageDO updateObj = PackageConvert.INSTANCE.convert(updateReqVO);
        packageMapper.updateById(updateObj);
    }

    @Override
    public void deletePackage(Long id) {
        // 校验存在
        validatePackageExists(id);
        // 删除
        packageMapper.deleteById(id);
    }

    private void validatePackageExists(Long id) {
        if (packageMapper.selectById(id) == null) {
            throw exception(PACKAGE_NOT_EXISTS);
        }
    }

    @Override
    public PackageDO getPackage(Long id) {
        return packageMapper.selectById(id);
    }

    @Override
    public List<PackageDO> getPackageList(Collection<Long> ids) {
        return packageMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PackageDO> getPackagePage(PackagePageReqVO pageReqVO) {
        return packageMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PackageDO> getPackageList(PackageExportReqVO exportReqVO) {
        return packageMapper.selectList(exportReqVO);
    }

}
