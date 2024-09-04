package com.dofast.framework.common.pad.page;


import com.dofast.framework.common.util.servlet.ServletUtils;

import java.util.Map;

/**
 * 表格数据处理
 * 
 * @author ruoyi
 */
public class PadTableSupport
{
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 分页参数合理化
     */
    public static final String REASONABLE = "reasonable";

    /**
     * 封装分页对象
     */
    public static PadPageDomain getPageDomain()
    {
        Map<String, String> map = ServletUtils.getParamMap(ServletUtils.getRequest());
        PadPageDomain padPageDomain = new PadPageDomain();
        padPageDomain.setPageNum(Integer.valueOf(map.get(PAGE_NUM)));
        padPageDomain.setPageSize(Integer.valueOf(map.get(PAGE_SIZE)));
        padPageDomain.setOrderByColumn(map.get(ORDER_BY_COLUMN));
        padPageDomain.setIsAsc(map.get(IS_ASC));
        padPageDomain.setReasonable(Boolean.valueOf(map.get(REASONABLE)));
        return padPageDomain;
    }

    public static PadPageDomain buildPageRequest()
    {
        return getPageDomain();
    }
}
