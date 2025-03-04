package com.dofast.module.qms.service.index;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.qms.controller.admin.index.vo.*;
import com.dofast.module.qms.dal.dataobject.index.IndexDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.qms.convert.index.IndexConvert;
import com.dofast.module.qms.dal.mysql.index.IndexMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.qms.enums.ErrorCodeConstants.*;

/**
 * 检测项 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class IndexServiceImpl implements IndexService {

    @Resource
    private IndexMapper indexMapper;

    @Override
    public String checkIndexCodeUnique(IndexBaseVO qcIndex) {
        IndexDO index = indexMapper.checkIndexCodeUnique(qcIndex);
        Long indexId = qcIndex.getId()==null?-1L:qcIndex.getId();
        if(StrUtils.isNotNull(index) &&index.getId().longValue() != indexId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public String checkIndexNameUnique(IndexBaseVO qcIndex) {
        IndexDO index = indexMapper.checkIndexNameUnique(qcIndex);
        Long indexId = qcIndex.getId()==null?-1L:qcIndex.getId();
        if(StrUtils.isNotNull(index) &&index.getId().longValue() != indexId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createIndex(IndexCreateReqVO createReqVO) {
        // 插入
        IndexDO index = IndexConvert.INSTANCE.convert(createReqVO);
        indexMapper.insert(index);
        // 返回
        return index.getId();
    }

    @Override
    public void updateIndex(IndexUpdateReqVO updateReqVO) {
        // 校验存在
        validateIndexExists(updateReqVO.getId());
        // 更新
        IndexDO updateObj = IndexConvert.INSTANCE.convert(updateReqVO);
        indexMapper.updateById(updateObj);
    }

    @Override
    public void deleteIndex(Long id) {
        // 校验存在
        validateIndexExists(id);
        // 删除
        indexMapper.deleteById(id);
    }

    private void validateIndexExists(Long id) {
        if (indexMapper.selectById(id) == null) {
            throw exception(INDEX_NOT_EXISTS);
        }
    }

    @Override
    public IndexDO getIndex(Long id) {
        return indexMapper.selectById(id);
    }

    /**
     * 获得检测项列表
     * @param processCode
     * @return
     */
    @Override
    public List<IndexDO> getIndexByProcessCode(String processCode){
        return indexMapper.selectList(IndexDO::getProcessCode,processCode);
    }



    @Override
    public List<IndexDO> getIndexList(Collection<Long> ids) {
        return indexMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<IndexDO> getIndexPage(IndexPageReqVO pageReqVO) {
        return indexMapper.selectPage(pageReqVO);
    }

    @Override
    public List<IndexDO> getIndexList(IndexExportReqVO exportReqVO) {
        return indexMapper.selectList(exportReqVO);
    }

}
