package com.dofast.module.mes.service.mditemtype;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.dofast.module.mes.controller.admin.mditemtype.vo.*;
import com.dofast.module.mes.dal.dataobject.mditemtype.MdItemTypeDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.mditemtype.MdItemTypeConvert;
import com.dofast.module.mes.dal.mysql.mditemtype.MdItemTypeMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 物料产品分类 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MdItemTypeServiceImpl implements MdItemTypeService {

    @Resource
    private MdItemTypeMapper mdItemTypeMapper;

    @Override
    public List<MdItemTreeTypeListVO> buildTreeSelect(List<MdItemTypeDO> list) {
        List<MdItemTypeDO> itemtypes =buildTree(list);
        List<MdItemTreeTypeListVO> listVOS = itemtypes.stream().map(MdItemTreeTypeListVO::new).collect(Collectors.toList());
        removeEmptyChildren(listVOS);
        return listVOS;
    }

    private List<MdItemTypeDO> buildTree(List<MdItemTypeDO> itemTypes){
        List<MdItemTypeDO> returnList = new ArrayList<MdItemTypeDO>();
        List<Long> tempList = new ArrayList<Long>();
        for(MdItemTypeDO it : itemTypes){
            tempList.add(it.getId());
        }

        for(MdItemTypeDO it : itemTypes){
            if(!tempList.contains(it.getParentTypeId())){
                recursionFn(itemTypes,it);
                returnList.add(it);
            }
        }
        if(returnList.isEmpty()){
            returnList = itemTypes;
        }
        return returnList;
    }

    private static void removeEmptyChildren(List<MdItemTreeTypeListVO> nodes) {
        for (MdItemTreeTypeListVO node : nodes) {
            if (node.getChildren() != null && !node.getChildren().isEmpty()) {
                removeEmptyChildren(node.getChildren());
            } else {
                node.setChildren(null);
            }
        }
    }
    /**
     * 递归列表
     */
    private void recursionFn(List<MdItemTypeDO> list, MdItemTypeDO t)
    {
        // 得到子节点列表
        List<MdItemTypeDO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (MdItemTypeDO tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<MdItemTypeDO> getChildList(List<MdItemTypeDO> list, MdItemTypeDO t)
    {
        List<MdItemTypeDO> tlist = new ArrayList<MdItemTypeDO>();
        Iterator<MdItemTypeDO> it = list.iterator();
        while (it.hasNext())
        {
            MdItemTypeDO n = (MdItemTypeDO) it.next();
            if (StrUtils.isNotNull(n.getParentTypeId()) && n.getParentTypeId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<MdItemTypeDO> list, MdItemTypeDO t)
    {
        return getChildList(list, t).size() > 0;
    }


    @Override
    public String checkItemTypeCodeUnique(MdItemTypeBaseVO itemType) {
        MdItemTypeDO itemType1 =mdItemTypeMapper.checkItemTypeCodeUnique(itemType.getItemTypeCode(),itemType.getParentTypeId());
        Long itemTypeId = itemType.getId() ==null? -1L:itemType.getId();
        if(StrUtils.isNotNull(itemType1)&& itemTypeId.longValue() != itemType1.getId().longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public String checkItemTypeNameUnique(MdItemTypeBaseVO itemType) {
        MdItemTypeDO itemType1 =mdItemTypeMapper.checkItemTypeNameUnique(itemType.getItemTypeName(),itemType.getParentTypeId());
        Long itemTypeId = itemType.getId() ==null? -1L:itemType.getId();
        if(StrUtils.isNotNull(itemType1)&& itemTypeId.longValue() != itemType1.getId().longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public boolean checkHasChild(Long itemTypeId) {
        return mdItemTypeMapper.hasChildByItemTypeId(itemTypeId) > 0;
    }

    @Override
    public boolean checkHasItem(Long itemTypeId) {
        return mdItemTypeMapper.hasItemByItemTypeId(itemTypeId)>0;
    }

    @Override
    public List<MdItemTypeDO> selectALl() {
        return mdItemTypeMapper.selectAll();
    }

    @Override
    public Long createMdItemType(MdItemTypeCreateReqVO createReqVO) {
        if(createReqVO.getParentTypeId()!= null){
            MdItemTypeDO parent = getMdItemType(createReqVO.getParentTypeId());
            if(StrUtils.isNotNull(parent)){
                createReqVO.setAncestors(parent.getAncestors()+","+parent.getId());
            }
        }
        // 插入
        MdItemTypeDO mdItemType = MdItemTypeConvert.INSTANCE.convert(createReqVO);
        mdItemTypeMapper.insert(mdItemType);
        // 返回
        return mdItemType.getId();
    }

    @Override
    public void updateMdItemType(MdItemTypeUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdItemTypeExists(updateReqVO.getId());
        // 更新
        MdItemTypeDO updateObj = MdItemTypeConvert.INSTANCE.convert(updateReqVO);
        mdItemTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteMdItemType(Long id) {
        // 校验存在
        validateMdItemTypeExists(id);
        // 删除
        mdItemTypeMapper.deleteById(id);
    }

    private void validateMdItemTypeExists(Long id) {
        if (mdItemTypeMapper.selectById(id) == null) {
            throw exception(MD_ITEM_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public MdItemTypeDO getMdItemType(Long id) {
        return mdItemTypeMapper.selectById(id);
    }

    @Override
    public List<MdItemTypeDO> getMdItemTypeList(Collection<Long> ids) {
        return mdItemTypeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MdItemTypeDO> getMdItemTypePage(MdItemTypePageReqVO pageReqVO) {
        return mdItemTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdItemTypeDO> getMdItemTypeList(MdItemTypeExportReqVO exportReqVO) {
        return mdItemTypeMapper.selectList(exportReqVO);
    }

    @Override
    public List<MdItemTypeDO> getMdItemTypeList(MdItemTypeListVO exportReqVO) {
        return mdItemTypeMapper.selectList(exportReqVO);
    }

}
