package com.dofast.module.trade.enums;

import com.dofast.framework.common.core.IntArrayValuable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 交易订单 - 售后状态
 *
 * @author Sin
 */
@RequiredArgsConstructor
@Getter
public enum TradeOrderAfterSaleStatusEnum implements IntArrayValuable {

    NONE(0, "未退款"),
    PART(1, "部分退款"),
    ALL(2, "全部退款"),
    //未知
    NON(0, "NONE"),
    //无退款
    NO_REFUND(1, "NO_REFUND"),
    //申请退款
    REFUNDING(2, "REFUNDING"),
    //部分退款完成
    PART_REFUNDED(3, "PART_REFUNDED"),
    //退款完成
    REFUNDED(4, "REFUNDED"),
    //等待买家退货
    WAIT_BUYER_RETURN_GOODS(5, "WAIT_BUYER_RETURN_GOODS"),
    //拒绝退货
    REJECT_REFUNDED(6, "REJECT_REFUNDED"),
    ON_SALE(7, "ON_SALE"),
    AFTER_SALE(8, "AFTER_SALE"),
    WAIT_SELLER_AGREE(14, "WAIT_SELLER_AGREE"),
    WAIT_SELLER_CONFIRM_GOODS(16, "WAIT_SELLER_CONFIRM_GOODS"),
    SELLER_REFUSE_BUYER(17, "SELLER_REFUSE_BUYER"),
    CLOSED(18, "CLOSED"),
    SUCCESS(19, "SUCCESS"),
    FAILED(21, "FAILED"),
    EXCHANGE_APPLY(23, "EXCHANGE_APPLY"),
    EXCHANGE_WAIT_OUT_GOODS(24, "EXCHANGE_WAIT_OUT_GOODS"),
    EXCHANGE_WAIT_CONFIRM_OUT_GOODS(25, "EXCHANGE_WAIT_CONFIRM_OUT_GOODS"),
    EXCHANGE_CLOSE(26, "EXCHANGE_CLOSE"),
    EXCHANGE_SUCCESS(27, "EXCHANGE_SUCCESS"),
    EXCHANGE_WAIT_MODIFY(28, "EXCHANGE_WAIT_MODIFY"),
    EXCHANGE_WAIT_SEND_GOODS(29, "EXCHANGE_WAIT_SEND_GOODS"),
    EXCHANGE_WAIT_IN_GOODS(31, "EXCHANGE_WAIT_IN_GOODS"),
    EXCHANGE_WAIT_REFUND(32, "EXCHANGE_WAIT_REFUND");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(TradeOrderAfterSaleStatusEnum::getStatus).toArray();

    public static Integer getStatusByName(String name) {
        for (TradeOrderAfterSaleStatusEnum status : TradeOrderAfterSaleStatusEnum.values()) {
            if (status.getName().equals(name)) {
                return status.getStatus();
            }
        }
        return null; // 如果找不到匹配的枚举值，则返回null或其他适当的默认值
    }
    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
