package com.dofast.module.mes.service.mdproductsop;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.mdproductsop.vo.*;
import com.dofast.module.mes.dal.dataobject.mdproductsop.MdProductSopDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.mdproductsop.MdProductSopConvert;
import com.dofast.module.mes.dal.mysql.mdproductsop.MdProductSopMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 产品SOP Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MdProductSopServiceImpl implements MdProductSopService {

    @Resource
    private MdProductSopMapper mdProductSopMapper;

    @Override
    public Long createMdProductSop(MdProductSopCreateReqVO createReqVO) {
        // 插入
        MdProductSopDO mdProductSop = MdProductSopConvert.INSTANCE.convert(createReqVO);
        mdProductSopMapper.insert(mdProductSop);
        // 返回
        return mdProductSop.getId();
    }

    @Override
    public void updateMdProductSop(MdProductSopUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdProductSopExists(updateReqVO.getId());
        // 更新
        MdProductSopDO updateObj = MdProductSopConvert.INSTANCE.convert(updateReqVO);
        mdProductSopMapper.updateById(updateObj);
    }

    @Override
    public void deleteMdProductSop(Long id) {
        // 校验存在
        validateMdProductSopExists(id);
        // 删除
        mdProductSopMapper.deleteById(id);
    }

    private void validateMdProductSopExists(Long id) {
        if (mdProductSopMapper.selectById(id) == null) {
            throw exception(MD_PRODUCT_SOP_NOT_EXISTS);
        }
    }

    @Override
    public MdProductSopDO getMdProductSop(Long id) {
        return mdProductSopMapper.selectById(id);
    }

    @Override
    public List<MdProductSopDO> getMdProductSopList(Collection<Long> ids) {
        return mdProductSopMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MdProductSopDO> getMdProductSopPage(MdProductSopPageReqVO pageReqVO) {
        return mdProductSopMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdProductSopDO> getMdProductSopList(MdProductSopExportReqVO exportReqVO) {
        return mdProductSopMapper.selectList(exportReqVO);
    }

}
