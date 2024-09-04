package com.dofast.module.sales.service.bpm;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.bpm.api.task.BpmProcessInstanceApi;
import com.dofast.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmCreateReqVO;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmPageReqVO;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmUpdateReqVO;
import com.dofast.module.sales.controller.admin.orderafter.vo.OrderAfterUpdateReqVO;
import com.dofast.module.sales.convert.orderafter.OrderAfterConvert;
import com.dofast.module.sales.dal.dataobject.orderafter.OrderAfterDO;
import com.dofast.module.sales.dal.mysql.orderafter.OrderAfterMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pad.util.PadSecurityUtils.getUserId;
import static com.dofast.module.sales.enums.ErrorCodeConstants.ORDER_AFTER_NOT_EXISTS;

@Service
public class BpmSalesServiceImpl implements BpmSalesService{
    /**
     * 售后对应的流程定义 KEY
     */
    public static final String SALES_KEY = "after_sales";

    @Resource
    private OrderAfterMapper orderAfterMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;
    @Override
    @Transactional
    public Long createSales(Long userId, OrderAfterBpmCreateReqVO createReqVO) {
        // 插入 售后信息
        OrderAfterDO convert = OrderAfterConvert.INSTANCE.convert(createReqVO);
        convert.setCreator(String.valueOf(getUserId()));
        orderAfterMapper.insert(convert);

        OrderAfterDO orderAfterDO = orderAfterMapper.getSelectOne(OrderAfterConvert.INSTANCE.convert(createReqVO));

        // 发起 BPM 流程
        Map<String, Object> processInstanceVariables = new HashMap<>();
        processInstanceVariables.put("after-order",createReqVO);
        String processInstanceId = processInstanceApi.createProcessInstance(userId,
                new BpmProcessInstanceCreateReqDTO().setProcessDefinitionKey(SALES_KEY)
                        .setVariables(processInstanceVariables).setBusinessKey(String.valueOf(orderAfterDO.getId())));

        // 将工作流的编号，更新到售后单中
        orderAfterMapper.updateById(OrderAfterConvert.INSTANCE.convert(createReqVO).setProcessInstanceId(processInstanceId));
        return orderAfterDO.getId();
    }

    @Override
    public void updateSaleResult(Long id, Integer result) {
        validateLeaveExists(id);
        orderAfterMapper.updateById(new OrderAfterDO().setId(id).setStatus(String.valueOf(result)));
    }



    private void validateLeaveExists(Long id) {
        if (orderAfterMapper.selectById(id) == null) {
            throw exception(ORDER_AFTER_NOT_EXISTS);
        }
    }

    @Override
    public OrderAfterDO getSales(Long id) {
        return orderAfterMapper.selectById(id);
    }

    @Override
    public PageResult<OrderAfterDO> getSalesPage(Long userId, OrderAfterBpmPageReqVO pageReqVO) {
        return orderAfterMapper.selectPageBpm(pageReqVO.setUserId(userId));
    }
}
