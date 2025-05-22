package com.dofast.module.system.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.*;

@Component
public class MD5Util {

    /**
     * 使用MD5算法进行加密
     *
     * @param target  要加密的字符串
     * @param charset 编码（请设置胃utf-8）
     * @return 加密后的字符串
     */
    public String encryptWithMD5(String target, String charset) {
        String md5Str = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            byte[] bytes = md5.digest(charset == null ? target.getBytes() : target.getBytes(charset));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if (bt < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            md5Str = stringBuffer.toString();
        } catch (Exception ex) {
            //logger.error("encrypt error,target:" + target, ex);
        }
        return md5Str;
    }

    public String sign(String appId, String account, String timedate) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "get_token");
        params.put("app_id", appId);
        params.put("account", account);
        params.put("timestamp", timedate);

        String sign = null;
        StringBuffer sb = new StringBuffer();
        //排序
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(params.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });

        //对参数数组进行按key升序排序，然后拼接，最h后调用MD5签名方法
        int size = infoIds.size();
        for (int i = 0; i < size; i++) {
            sb.append(infoIds.get(i).getKey() + "=" + infoIds.get(i).getValue() + "&");
        }
        String newStrTemp = sb.toString();
        String newStr = newStrTemp.substring(0, newStrTemp.length() - 1);
        System.out.println(newStr);
        //获取 sign_method
        sign = encryptWithMD5(newStr, "UTF-8");
        System.out.println(sign);
        return sign;
    }

}
