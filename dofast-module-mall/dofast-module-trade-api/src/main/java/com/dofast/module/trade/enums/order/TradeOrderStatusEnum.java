package com.dofast.module.trade.enums.order;

import cn.hutool.core.util.ObjectUtil;
import com.dofast.framework.common.core.IntArrayValuable;
import com.dofast.framework.common.util.object.ObjectUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.dofast.module.trade.enums.aftersale.TradeAfterSaleStatusEnum.COMPLETE;

/**
 * 交易订单 - 状态
 *
 * @author Sin
 */
@RequiredArgsConstructor
@Getter
public enum TradeOrderStatusEnum implements IntArrayValuable {

    UNPAID(0, "待支付"),
    UNDELIVERED(10, "待发货"),
    DELIVERED(20, "已发货"),
    COMPLETED(30, "已完成"),
    CANCELED(40, "已取消"),
    CLOSE(1, "CLOSE"),
    CANCEL(40, "CANCEL"),
    DELETED(3, "DELETED"),
    UNKNOWN(4, "UNKNOWN"),
    PART_PAID(5, "PART_PAID"),
    NOT_SHIPPED(10, "NOT_SHIPPED"),
    PART_SHIPPED(7, "PART_SHIPPED"),
    SHIPPED(20, "SHIPPED"),
    REJECTED(9, "REJECTED"),
    BILL_SHIPPED(10, "BILL_SHIPPED"),
    BILL_COMPLETE(11, "BILL_COMPLETE"),
    PAUSE(12, "PAUSE"),
    LOCKED(13, "LOCKED"),
    COMPLETE(30, "COMPLETE"),
    WAIT_SELLER_AGREE(14, "WAIT_SELLER_AGREE"),
    WAIT_BUYER_RETURN_GOODS(15, "WAIT_BUYER_RETURN_GOODS"),
    WAIT_SELLER_CONFIRM_GOODS(16, "WAIT_SELLER_CONFIRM_GOODS"),
    SELLER_REFUSE_BUYER(17, "SELLER_REFUSE_BUYER"),
    CLOSED(18, "CLOSED"),
    SUCCESS(19, "SUCCESS"),
    FAILED(21, "FAILED"),
    REFUNDING(22, "REFUNDING"),
    EXCHANGE_APPLY(23, "EXCHANGE_APPLY"),
    EXCHANGE_WAIT_OUT_GOODS(24, "EXCHANGE_WAIT_OUT_GOODS"),
    EXCHANGE_WAIT_CONFIRM_OUT_GOODS(25, "EXCHANGE_WAIT_CONFIRM_OUT_GOODS"),
    EXCHANGE_CLOSE(26, "EXCHANGE_CLOSE"),
    EXCHANGE_SUCCESS(27, "EXCHANGE_SUCCESS"),
    EXCHANGE_WAIT_MODIFY(28, "EXCHANGE_WAIT_MODIFY"),
    EXCHANGE_WAIT_SEND_GOODS(29, "EXCHANGE_WAIT_SEND_GOODS"),
    EXCHANGE_WAIT_IN_GOODS(31, "EXCHANGE_WAIT_IN_GOODS"),
    EXCHANGE_WAIT_REFUND(32, "EXCHANGE_WAIT_REFUND");


    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(TradeOrderStatusEnum::getStatus).toArray();

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

    public static Integer getStatusByName(String name) {
        for (TradeOrderStatusEnum status : TradeOrderStatusEnum.values()) {
            if (status.getName().equals(name)) {
                return status.getStatus();
            }
        }
        return null; // 如果找不到匹配的枚举值，则返回null或其他适当的默认值
    }

    // === 问：为什么写了很多 isXXX 和 haveXXX 的判断逻辑呢？ ===
    // === 答：方便找到某一类判断，哪些业务正在使用 ===

    /**
     * 判断指定状态，是否正处于【未付款】状态
     *
     * @param status 指定状态
     * @return 是否
     */
    public static boolean isUnpaid(Integer status) {
        return ObjectUtil.equal(UNPAID.getStatus(), status);
    }

    /**
     * 判断指定状态，是否正处于【待发货】状态
     *
     * @param status 指定状态
     * @return 是否
     */
    public static boolean isUndelivered(Integer status) {
        return ObjectUtil.equal(UNDELIVERED.getStatus(), status);
    }

    /**
     * 判断指定状态，是否正处于【已发货】状态
     *
     * @param status 指定状态
     * @return 是否
     */
    public static boolean isDelivered(Integer status) {
        return ObjectUtil.equals(status, DELIVERED.getStatus());
    }

    /**
     * 判断指定状态，是否正处于【已取消】状态
     *
     * @param status 指定状态
     * @return 是否
     */
    public static boolean isCanceled(Integer status) {
        return ObjectUtil.equals(status, CANCELED.getStatus());
    }

    /**
     * 判断指定状态，是否正处于【已完成】状态
     *
     * @param status 指定状态
     * @return 是否
     */
    public static boolean isCompleted(Integer status) {
        return ObjectUtil.equals(status, COMPLETE.getStatus());
    }

    /**
     * 判断指定状态，是否有过【已付款】状态
     *
     * @param status 指定状态
     * @return 是否
     */
    public static boolean havePaid(Integer status) {
        return ObjectUtils.equalsAny(status, NOT_SHIPPED.getStatus(),
                SHIPPED.getStatus(), COMPLETE.getStatus());
    }

    /**
     * 判断指定状态，是否有过【已发货】状态
     *
     * @param status 指定状态
     * @return 是否
     */
    public static boolean haveDelivered(Integer status) {
        return ObjectUtils.equalsAny(status, DELIVERED.getStatus(), COMPLETED.getStatus());
    }



}
