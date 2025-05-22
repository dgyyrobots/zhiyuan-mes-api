package com.dofast.module.system.oneLogin.service.Impl;

import com.dofast.framework.common.pad.util.PadSecurityUtils;
import com.dofast.framework.tenant.core.context.TenantContextHolder;
import com.dofast.module.system.oneLogin.domain.InitLoginVo;
import com.dofast.module.system.oneLogin.mapper.OneLoginMapper;
import com.dofast.module.system.oneLogin.service.OneLoginService;
import com.dofast.module.system.util.MD5Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

@Service
public class OneLoginServiceImpl implements OneLoginService {

    @Resource
    private OneLoginMapper oneLoginMapper;

    private final static String ZY_APP_ID = "KiXGnlgz6o%2BLiAzcSRGVnA%3D%3D";
    private final static String DH_APP_ID = "PLblv2zVd773pt5vgYlGtg%3D%3D";


    @Override
    public List<InitLoginVo> initOneLoginInfo() {
        //获取当前租户ID
        String tenantId = TenantContextHolder.getTenantId().toString();
        String userId = PadSecurityUtils.getUserId().toString();
        List<InitLoginVo> list = oneLoginMapper.initOneLogin(tenantId, userId);
        return list;
    }

    @Override
    public void register(Map<String, String> map) {
        String userName = map.get("userName");
        String password = map.get("password");
        String sysId = map.get("sysId");
        String tenantId = TenantContextHolder.getTenantId().toString();
        String userId = PadSecurityUtils.getUserId().toString();

        oneLoginMapper.register(userId, userName, password, sysId, tenantId);
    }

    @Override
    public String getAmAccessToken(String username, String password, String net) {

        String tenantId = TenantContextHolder.getTenantId().toString();
        //通过当前用户的租户ID，获取租户名称
        String tenantName = oneLoginMapper.getTenantNameByTenantId(tenantId);

        //获取访问地址
        String requstUrl = oneLoginMapper.getLoginAddress(tenantId, net, "澳美MES");
        //获取新的租户ID
        String newTenantId = getTenantId(tenantName, requstUrl);

        // 请求URL
        String url = requstUrl + "/prod-api/admin-api/system/auth/login";

        // 创建HttpClient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建HttpPost请求
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("tenant-id", newTenantId);  // 添加tenant-id请求头

            // 设置请求体
            String jsonPayload = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
            httpPost.setEntity(new StringEntity(jsonPayload));

            // 执行请求并获取响应
            HttpResponse response = httpClient.execute(httpPost);

            // 获取响应内容
            String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);
            String accessToken = rootNode.path("data").path("accessToken").asText();

            // 打印响应信息
            System.out.println("Status Code: " + response.getStatusLine().getStatusCode());
            System.out.println("Response Body: " + responseBody);
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String SECRET_KEY = "5212345678900000";
    private static final String INIT_VECTOR = SECRET_KEY;

    @Override
    public String zyHrAutoLogin(String userName, String password, String net) throws JsonProcessingException {

        String tenantId = TenantContextHolder.getTenantId().toString();
        //获取访问地址
        String requstUrl = oneLoginMapper.getLoginAddress(tenantId, net, "智源HR");

        // 使用 LinkedHashMap 保证字段顺序
        Map<String, String> saveData = new LinkedHashMap<>();
        String url = requstUrl + "/Authentication/Validate";

        // 账号信息
        String login1 = userName;
        // 密码信息
        String password1 = password;
        // 验证码:HR验证码在前端做验证,后台不校验
        String validateCode1 = "";
        // 记住用户,默认不记住
        String rememberMe1 = "0";

        saveData.put("ValidateCodeID", "ValidateCode1");
        saveData.put("Mlog", encrypt(login1));
        saveData.put("Mpwd", encrypt(password1));
        saveData.put("ValidateCode", encrypt(validateCode1));
        saveData.put("RememberMe", encrypt(rememberMe1));

        saveData.put("ReturnUrl", "/");

        System.out.println("请求URL: " + url);
        System.out.println("请求数据: " + saveData);

        // 创建 ObjectMapper 实例
        ObjectMapper objectMapper = new ObjectMapper();
        // 新增：将 saveData 转为 JSON 并整体加密
        String jsonData = objectMapper.writeValueAsString(saveData);
        String encJsonData = encrypt(jsonData);
        System.out.println("请求数据: " + encJsonData);

        return encJsonData;
    }

    @Resource
    private MD5Util md5Util;

    @Override
    public String getDhAccessToken(String appId, String account, String net) throws JsonProcessingException {
        String timedate = Instant.now().getEpochSecond() + "";
        String tenantId = TenantContextHolder.getTenantId().toString();
        //获取访问地址
        String requstUrl = oneLoginMapper.getLoginAddress(tenantId, net, "东合MES");
        String sign = md5Util.sign(DH_APP_ID, account, timedate);
        WebClient webClient = WebClient.create(requstUrl);
        Map<String, String> map = new HashMap<>();
        map.put("action", "get_token");
        map.put("app_id", DH_APP_ID);
        map.put("account", account);
        map.put("timestamp", timedate);
        map.put("sign", sign);
        // 发送 POST 请求（同步阻塞方式）
        String response = webClient.post()
                .uri("/outside/oauth_login")  // 接口路径
                .bodyValue(map)  // 请求体（JSON）
                .header("Content-Type", "application/json")     // 设置请求头
                .retrieve()                                   // 发起请求
                .bodyToMono(String.class)                     // 响应转为 String
                .block();                                      // 阻塞等待结果
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response);
        String accessToken = rootNode.path("data").path("access_token").asText();
        return accessToken;
    }

    @Override
    public String zyMesAutoLogin(String appId, String account, String net) throws JsonProcessingException {
        String timedate = Instant.now().getEpochSecond() + "";
        String tenantId = TenantContextHolder.getTenantId().toString();
        //获取访问地址
        String requstUrl = oneLoginMapper.getLoginAddress(tenantId, net, "智源MES");
        String sign = md5Util.sign(ZY_APP_ID, account, timedate);
        WebClient webClient = WebClient.create(requstUrl);
        Map<String, String> map = new HashMap<>();
        map.put("action", "get_token");
        map.put("app_id", ZY_APP_ID);
        map.put("account", account);
        map.put("timestamp", timedate);
        map.put("sign", sign);
        // 发送 POST 请求（同步阻塞方式）
        String response = webClient.post()
                .uri("/outside/oauth_login")  // 接口路径
                .bodyValue(map)  // 请求体（JSON）
                .header("Content-Type", "application/json")     // 设置请求头
                .retrieve()                                   // 发起请求
                .bodyToMono(String.class)                     // 响应转为 String
                .block();                                      // 阻塞等待结果
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response);
        String accessToken = rootNode.path("data").path("access_token").asText();
        return accessToken;
    }


    @Override
    public Map<String, String> zyToken(String account, String net) throws JsonProcessingException {
        String timedate = Instant.now().getEpochSecond() + "";
        String tenantId = TenantContextHolder.getTenantId().toString();
        //获取访问地址
        String requstUrl = oneLoginMapper.getLoginAddress(tenantId, net, "智源MES");
        String sign = md5Util.sign(ZY_APP_ID, account, timedate);
        WebClient webClient = WebClient.create(requstUrl);
        Map<String, String> map = new HashMap<>();
        map.put("action", "get_token");
        map.put("app_id", ZY_APP_ID);
        map.put("account", account);
        map.put("timestamp", timedate);
        map.put("sign", sign);
        // 发送 POST 请求（同步阻塞方式）
        String response = webClient.post()
                .uri("/outside/oauth_login")  // 接口路径
                .bodyValue(map)  // 请求体（JSON）
                .header("Content-Type", "application/json")     // 设置请求头
                .retrieve()                                   // 发起请求
                .bodyToMono(String.class)                     // 响应转为 String
                .block();                                      // 阻塞等待结果
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response);
        String accessToken = rootNode.path("data").path("access_token").asText();
        String userId = rootNode.path("data").path("userId").asText();
        String expires_in = rootNode.path("data").path("expires_in").asText();
        String expires_date = rootNode.path("data").path("expires_date").asText();
        String time = rootNode.path("time").asText();
        Map<String, String> result = new HashMap<>();
        result.put("userId", userId);
        result.put("access_token", accessToken);
        result.put("expires_in", expires_in);
        result.put("expires_date", expires_date);
        result.put("time", time);
        return result;
    }

    @Override
    public Map<String, String> zyNewToken(String accessToken, String userId, String net) throws JsonProcessingException {
        String timedate = Instant.now().getEpochSecond() + "";
        String tenantId = TenantContextHolder.getTenantId().toString();
        //获取访问地址
        String requstUrl = oneLoginMapper.getLoginAddress(tenantId, net, "智源MES");
        WebClient webClient = WebClient.create(requstUrl);
        // 发送 GET 请求（同步阻塞方式）
        String response = webClient.get()
                .uri("/outside/oauth_login" + "/action:login/access_token:" + accessToken)  // 接口路径
                .header("Content-Type", "application/json")     // 设置请求头
                .retrieve()                                   // 发起请求
                .bodyToMono(String.class)                     // 响应转为 String
                .block();                                      // 阻塞等待结果
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response);
        String newAccessToken = rootNode.path("access_token").asText();
        Map<String, String> result = new HashMap<>();
        result.put("userId", userId);
        result.put("access_token", newAccessToken);
        return result;
    }

    private String getTenantId(String tenantName, String requstUrl) {
        // 请求URL（注意URL中的中文参数已编码为UTF-8）
        //获取 租户
        String url = requstUrl + "/prod-api/admin-api/system/tenant/get-id-by-name?name=" + tenantName;
        // 创建HttpClient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建HttpGet请求
            HttpGet httpGet = new HttpGet(url);
            // 执行请求并获取响应
            HttpResponse response = httpClient.execute(httpGet);
            // 获取响应内容
            String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);
            String data = rootNode.path("data").asText();
            // 打印响应信息
            System.out.println("Status Code: " + response.getStatusLine().getStatusCode());
            System.out.println("Response Body: " + responseBody);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
