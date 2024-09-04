package com.dofast.module.member.service.location;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.member.controller.admin.loction.vo.*;
import com.dofast.module.member.convert.location.LocationConvert;
import com.dofast.module.member.dal.dataobject.location.LocationDO;
import com.dofast.module.member.dal.mysql.location.LocationMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 三级位置信息 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class LocationServiceImpl implements LocationService {

    @Resource
    private LocationMapper Mapper;

    @Override
    public Integer create(LocationCreateReqVO createReqVO) {
        // 插入
        LocationDO locationDO = LocationConvert.INSTANCE.convert(createReqVO);
        Mapper.insert(locationDO);
        // 返回
        return locationDO.getId();
    }

    @Override
    public void update(LocationUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        LocationDO updateObj = LocationConvert.INSTANCE.convert(updateReqVO);
        Mapper.updateById(updateObj);
    }

    @Override
    public void delete(Integer id) {
        // 校验存在
        validateExists(id);
        // 删除
        Mapper.deleteById(id);
    }

    private void validateExists(Integer id) {
        if (Mapper.selectById(id) == null) {
            throw exception(1004005000, "用户收件地址不存在");
        }
    }

    @Override
    public LocationDO get(Integer id) {
        return Mapper.selectById(id);
    }

    @Override
    public List<LocationDO> getList(Collection<Integer> ids) {
        return Mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<LocationDO> getPage(LocationPageReqVO pageReqVO) {
        return Mapper.selectPage(pageReqVO);
    }

    @Override
    public List<LocationDO> getList(LocationExportReqVO exportReqVO) {
        return Mapper.selectList(exportReqVO);
    }

    @Override
    public List<LocationQueryId> getLocationList(Integer id) {
        List<LocationQueryId> locationQueryIds = new ArrayList<>();
        id = id == null ? 100000 : id;
        LocationDO locationDO = Mapper.selectById(id);
        LocationQueryId locationQueryId = new LocationQueryId();
        BeanUtils.copyProperties(locationDO, locationQueryId);
        locationQueryIds.add(locationQueryId);
        List<LocationDO> list = Mapper.getLocationList(id);
        if (!CollectionUtils.isEmpty(list)) {
            for(int i = 0; i<list.size(); i++){
                locationDO = list.get(i);
                locationQueryId = new LocationQueryId();
                BeanUtils.copyProperties(locationDO, locationQueryId);
                locationQueryIds.add(locationQueryId);
            }
        }
        return locationQueryIds;
    }
}
