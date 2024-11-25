package com.dofast.module.mes.service.mdproductbom;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.mdproductbom.vo.*;
import com.dofast.module.mes.dal.dataobject.mdproductbom.MdProductBomDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.mdproductbom.MdProductBomConvert;
import com.dofast.module.mes.dal.mysql.mdproductbom.MdProductBomMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 产品BOM关系 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MdProductBomServiceImpl implements MdProductBomService {

    @Resource
    private MdProductBomMapper mdProductBomMapper;

        @Override
        public List<MdProductBomDO> selectList(MdProductBomListVO listVO) {
            return mdProductBomMapper.selectList(listVO);
        }

    @Override
    public Long createMdProductBom(MdProductBomCreateReqVO createReqVO) {
        // 插入
        MdProductBomDO mdProductBom = MdProductBomConvert.INSTANCE.convert(createReqVO);
        mdProductBomMapper.insert(mdProductBom);
        // 返回
        return mdProductBom.getId();
    }

    @Override
    public void updateMdProductBom(MdProductBomUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdProductBomExists(updateReqVO.getId());
        // 更新
        MdProductBomDO updateObj = MdProductBomConvert.INSTANCE.convert(updateReqVO);
        mdProductBomMapper.updateById(updateObj);
    }

    @Override
    public void deleteMdProductBom(Long id) {
        // 校验存在
        validateMdProductBomExists(id);
        // 删除
        mdProductBomMapper.deleteById(id);
    }

    private void validateMdProductBomExists(Long id) {
        if (mdProductBomMapper.selectById(id) == null) {
            throw exception(MD_PRODUCT_BOM_NOT_EXISTS);
        }
    }

    @Override
    public MdProductBomDO getMdProductBom(Long id) {
        return mdProductBomMapper.selectById(id);
    }

    @Override
    public List<MdProductBomDO> getMdProductBomList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return mdProductBomMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MdProductBomDO> getMdProductBomPage(MdProductBomPageReqVO pageReqVO) {
        return mdProductBomMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdProductBomDO> getMdProductBomList(MdProductBomExportReqVO exportReqVO) {
        return mdProductBomMapper.selectList(exportReqVO);
    }

}
