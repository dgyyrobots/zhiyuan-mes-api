package com.dofast.module.pro.api.RouteProductBomApi;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.pro.api.RouteProductBomApi.dto.RouteProductBomDTO;
import com.dofast.module.pro.controller.admin.routeproductbom.vo.RouteProductBomExportReqVO;
import com.dofast.module.pro.dal.dataobject.routeproductbom.RouteProductBomDO;
import com.dofast.module.pro.dal.mysql.routeproductbom.RouteProductBomMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteProductBomApiImpl implements RouteProductBomApi{
    @Resource
    RouteProductBomMapper routeProductBomMapper;

    @Override
    public List<RouteProductBomDTO> getList(RouteProductBomDTO routeProductBomDTO) {
        RouteProductBomExportReqVO routeProductBomExportReqVO = BeanUtil.toBean(routeProductBomDTO, RouteProductBomExportReqVO.class);
        List<RouteProductBomDO> list = routeProductBomMapper.selectList(routeProductBomExportReqVO);
        List<RouteProductBomDTO> list1 = new ArrayList<>();
        if (list.isEmpty() || list == null) return list1;
        for (RouteProductBomDO routeProductBomDO : list){
            RouteProductBomDTO routeProductBomDTO1 = BeanUtil.toBean(routeProductBomDO, RouteProductBomDTO.class);
            list1.add(routeProductBomDTO1);
        }
        return list1;
    }
}
