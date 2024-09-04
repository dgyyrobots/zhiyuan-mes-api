package com.dofast.module.wms.service.packageline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.packageline.vo.*;
import com.dofast.module.wms.dal.dataobject.packageline.PackageLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.packageline.PackageLineConvert;
import com.dofast.module.wms.dal.mysql.packageline.PackageLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 装箱明细 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PackageLineServiceImpl implements PackageLineService {

    @Resource
    private PackageLineMapper packageLineMapper;

    @Override
    public Long createPackageLine(PackageLineCreateReqVO createReqVO) {
        // 插入
        PackageLineDO packageLine = PackageLineConvert.INSTANCE.convert(createReqVO);
        packageLineMapper.insert(packageLine);
        // 返回
        return packageLine.getId();
    }

    @Override
    public void updatePackageLine(PackageLineUpdateReqVO updateReqVO) {
        // 校验存在
        validatePackageLineExists(updateReqVO.getId());
        // 更新
        PackageLineDO updateObj = PackageLineConvert.INSTANCE.convert(updateReqVO);
        packageLineMapper.updateById(updateObj);
    }

    @Override
    public void deletePackageLine(Long id) {
        // 校验存在
        validatePackageLineExists(id);
        // 删除
        packageLineMapper.deleteById(id);
    }

    private void validatePackageLineExists(Long id) {
        if (packageLineMapper.selectById(id) == null) {
            throw exception(PACKAGE_LINE_NOT_EXISTS);
        }
    }

    @Override
    public PackageLineDO getPackageLine(Long id) {
        return packageLineMapper.selectById(id);
    }

    @Override
    public List<PackageLineDO> getPackageLineList(Collection<Long> ids) {
        return packageLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PackageLineDO> getPackageLinePage(PackageLinePageReqVO pageReqVO) {
        return packageLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PackageLineDO> getPackageLineList(PackageLineExportReqVO exportReqVO) {
        return packageLineMapper.selectList(exportReqVO);
    }

}
