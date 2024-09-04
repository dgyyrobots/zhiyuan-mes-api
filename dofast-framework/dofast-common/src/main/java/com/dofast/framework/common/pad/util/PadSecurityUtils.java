package com.dofast.framework.common.pad.util;

import com.dofast.framework.common.core.LoginUser;
import com.dofast.framework.common.exception.ServiceException;
import com.dofast.framework.common.pad.constant.PadHttpStatus;
import com.dofast.framework.common.pad.pojo.PadLoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Field;

/**
 * 安全服务工具类
 * 
 * @author ruoyi
 */
public class PadSecurityUtils
{
    /**
     * 用户ID
     **/
    public static Long getUserId()
    {
        try
        {
            Object principal = getLoginUser();
            Field id = principal.getClass().getDeclaredField("id");
            id.setAccessible(true);
            Object o = id.get(principal);
            System.out.println(o);
            return (Long) o;
        }
        catch (Exception e)
        {
            throw new ServiceException(PadHttpStatus.UNAUTHORIZED, "获取用户ID异常");
        }
    }

    /**
     * 获取部门ID
     **/
    /*public static Long getDeptId()
    {
        try
        {
            return getLoginUser().getDeptId();
        }
        catch (Exception e)
        {
            throw new ServiceException(PadHttpStatus.UNAUTHORIZED, "获取部门ID异常");
        }
    }*/

    /**
     * 获取用户账户
     **/
    /*public static String getUsername()
    {
        try
        {
            return getLoginUser().getUsername();
        }
        catch (Exception e)
        {
            throw new ServiceException(PadHttpStatus.UNAUTHORIZED, "获取用户账户异常");
        }
    }*/



    /**
     * 获取用户
     **/
    public static Object getLoginUser()
    {
        try
        {
            /*Object principal = getAuthentication().getPrincipal();
            return (PadLoginUser) getAuthentication().getPrincipal();*/
            return  getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            throw new ServiceException(PadHttpStatus.UNAUTHORIZED, "获取用户信息异常");
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }
}
