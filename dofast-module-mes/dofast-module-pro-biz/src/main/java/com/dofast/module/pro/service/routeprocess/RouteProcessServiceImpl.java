package com.dofast.module.pro.service.routeprocess;

import cn.hutool.core.collection.CollectionUtil;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.api.WorkStationAPi.WorkStationApi;
import com.dofast.module.mes.api.WorkStationAPi.dto.WorkStationDTO;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.controller.admin.routeproduct.vo.RouteProductListVO;
import com.dofast.module.pro.dal.dataobject.feedback.FeedbackDO;
import com.dofast.module.pro.dal.dataobject.routeproduct.RouteProductDO;
import com.dofast.module.pro.dal.mysql.route.RouteMapper;
import com.dofast.module.pro.dal.mysql.routeproduct.RouteProductMapper;
import com.dofast.module.pro.service.route.RouteService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import com.dofast.module.pro.controller.admin.routeprocess.vo.*;
import com.dofast.module.pro.dal.dataobject.routeprocess.RouteProcessDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.routeprocess.RouteProcessConvert;
import com.dofast.module.pro.dal.mysql.routeprocess.RouteProcessMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 工艺组成 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RouteProcessServiceImpl implements RouteProcessService {

    @Resource
    private RouteProcessMapper routeProcessMapper;
    @Resource
    private RouteProductMapper routeProductMapper;
    @Resource
    private WorkStationApi workStationApi;
    @Resource
    private RouteService routeService;

    @Override
    public int deleteByRouteId(Long routeId) {
        return 0;
    }

    @Override
    public RouteProcessDO findNextProcess(RouteProcessBaseVO baseVO) {
        return routeProcessMapper.findNextProcess(baseVO);
    }

    @Override
    public RouteProcessDO findPreProcess(RouteProcessBaseVO baseVO) {
        return routeProcessMapper.findPreProcess(baseVO);
    }

    @Override
    public boolean checkKeyProcess(FeedbackDO feedback) {
        //根据当前生产的产品获取对应的工艺路线
        Long routeId =-1L,processId = -1L;
        RouteProductListVO param = new RouteProductListVO();
        param.setItemId(feedback.getItemId());
        List<RouteProductDO> products = routeProductMapper.selectList(param);
        if(CollectionUtil.isNotEmpty(products)){
            products = products.stream().filter(item -> routeService.getRoute(item.getRouteId()).getEnableFlag().equals(Constant.YES)).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(products)){
                routeId = products.get(0).getRouteId();
            }
        }

        //根据工作站获取工序
        WorkStationDTO workstation = workStationApi.getWorkstation(feedback.getWorkstationId());
        processId = workstation.getProcessId();

        //再判断当前的工序在此工艺路线中是否是关键工序
        RouteProcessListVO param2 = new RouteProcessListVO();
        param2.setRouteId(routeId);
        param2.setProcessId(processId);
        param2.setKeyFlag(Constant.YES);
        List<RouteProcessDO> processes = routeProcessMapper.selectList(param2);
        if(CollectionUtil.isNotEmpty(processes)){
            return true;
        }
        return false;
    }

    @Override
    public String checkOrderNumExists(RouteProcessBaseVO proRouteProcess) {
        RouteProcessDO process = routeProcessMapper.checkOrderNumExists(proRouteProcess);
        Long recordId = proRouteProcess.getId()==null?-1L:proRouteProcess.getId();
        if(StrUtils.isNotNull(process) && process.getId().longValue() != recordId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public String checkProcessExists(RouteProcessBaseVO proRouteProcess) {
        RouteProcessDO process = routeProcessMapper.checkProcessExists(proRouteProcess);
        Long recordId = proRouteProcess.getId()==null?-1L:proRouteProcess.getId();
        if(StrUtils.isNotNull(process) && process.getId().longValue() != recordId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public String checkUpdateFlagUnique(RouteProcessBaseVO proRouteProcess) {
        RouteProcessDO process = routeProcessMapper.checkUpdateFlagUnique(proRouteProcess);
        Long recordId = proRouteProcess.getId()==null?-1L:proRouteProcess.getId();
        if(StrUtils.isNotNull(process) && process.getId().longValue() != recordId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public List<RouteProcessDO> selectList(RouteProcessListVO listVO) {
        return routeProcessMapper.selectList(listVO);
    }

    @Override
    public Long createRouteProcess(RouteProcessCreateReqVO createReqVO) {
        // 插入
        RouteProcessDO routeProcess = RouteProcessConvert.INSTANCE.convert(createReqVO);
        routeProcessMapper.insert(routeProcess);
        // 返回
        return routeProcess.getId();
    }

    @Override
    public void updateRouteProcess(RouteProcessUpdateReqVO updateReqVO) {
        // 校验存在
        validateRouteProcessExists(updateReqVO.getId());
        // 更新
        RouteProcessDO updateObj = RouteProcessConvert.INSTANCE.convert(updateReqVO);
        routeProcessMapper.updateById(updateObj);
    }

    @Override
    public void deleteRouteProcess(Long id) {
        // 校验存在
        validateRouteProcessExists(id);
        // 删除
        routeProcessMapper.deleteById(id);
    }

    private void validateRouteProcessExists(Long id) {
        if (routeProcessMapper.selectById(id) == null) {
            throw exception(ROUTE_PROCESS_NOT_EXISTS);
        }
    }

    @Override
    public RouteProcessDO getRouteProcess(Long id) {
        return routeProcessMapper.selectById(id);
    }

    @Override
    public List<RouteProcessDO> getRouteProcessList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return routeProcessMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RouteProcessDO> getRouteProcessPage(RouteProcessPageReqVO pageReqVO) {
        return routeProcessMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RouteProcessDO> getRouteProcessList(RouteProcessExportReqVO exportReqVO) {
        return routeProcessMapper.selectList(exportReqVO);
    }

}
