package com.dofast.module.pro.api.RouteProductBomApi;

import com.dofast.module.pro.api.RouteProductBomApi.dto.RouteProductBomDTO;

import java.util.List;

public interface RouteProductBomApi {
    List<RouteProductBomDTO> getList(RouteProductBomDTO routeProductBomDTO);
}
