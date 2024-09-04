package com.dofast.framework.common.pad.util;

import com.dofast.framework.common.pad.page.PadPageDomain;
import com.dofast.framework.common.pad.page.PadTableSupport;
import com.dofast.framework.common.util.string.StrUtils;
import com.github.pagehelper.PageHelper;

/**
 * 分页工具类
 * 
 * @author ruoyi
 */
public class PadPageUtils extends PageHelper
{
    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PadPageDomain padPageDomain = PadTableSupport.buildPageRequest();
        Integer pageNum = padPageDomain.getPageNum();
        Integer pageSize = padPageDomain.getPageSize();
        if (StrUtils.isNotNull(pageNum) && StrUtils.isNotNull(pageSize))
        {
            String orderBy = padPageDomain.getOrderBy();
            Boolean reasonable = padPageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage()
    {
        PageHelper.clearPage();
    }
}
