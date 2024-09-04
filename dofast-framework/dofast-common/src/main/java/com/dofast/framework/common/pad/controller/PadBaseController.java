package com.dofast.framework.common.pad.controller;

import com.dofast.framework.common.pad.constant.PadHttpStatus;
import com.dofast.framework.common.pad.page.PadPageDomain;
import com.dofast.framework.common.pad.page.PadTableDataInfo;
import com.dofast.framework.common.pad.page.PadTableSupport;
import com.dofast.framework.common.pad.pojo.PadLoginUser;
import com.dofast.framework.common.pad.util.PadDateUtils;
import com.dofast.framework.common.pad.util.PadPageUtils;
import com.dofast.framework.common.pad.util.PadSecurityUtils;
import com.dofast.framework.common.pad.util.PadStringUtils;
import com.dofast.framework.common.pojo.AjaxResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 * 
 * @author ruoyi
 */
public class PadBaseController
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(PadDateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PadPageUtils.startPage();
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy()
    {
        PadPageDomain padPageDomain = PadTableSupport.buildPageRequest();
        if (PadStringUtils.isNotEmpty(padPageDomain.getOrderBy()))
        {
            String orderBy = padPageDomain.getOrderBy();
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 清理分页的线程变量
     */
    protected void clearPage()
    {
        PadPageUtils.clearPage();
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected PadTableDataInfo getDataTable(List<?> list)
    {
        PadTableDataInfo rspData = new PadTableDataInfo();
        rspData.setCode(PadHttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    /**
     * 响应返回结果
     * 
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 响应返回结果
     * 
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return PadStringUtils.format("redirect:{}", url);
    }

    /**
     * 获取用户缓存信息
     */
    /*public PadLoginUser getLoginUser()
    {
        return PadSecurityUtils.getLoginUser();
    }*/

    /**
     * 获取登录用户id
     */
    /*public Long getUserId()
    {
        return getLoginUser().getUserId();
    }*/

    /**
     * 获取登录部门id
     */
    /*public Long getDeptId()
    {
        return getLoginUser().getDeptId();
    }*/

    /**
     * 获取登录用户名
     */
    /*public String getUsername()
    {
        return getLoginUser().getUsername();
    }*/
}
