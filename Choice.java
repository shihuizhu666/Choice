package net.angrycode.app.NetWork;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Choice {

    //当前接口版本
    private double Version = 1.0;
    
    //接口用户代理
    private Map<String, String> Headers = new HashMap<String, String>();
    private Map<String, String> UserAgent = new HashMap<String, String>();
    
    //接口网关地址
    private String Gateway ="http://gateway.shihuizhu.net/open";

    public Choice(String AppID, String AppKey){
        this.header( AppID, AppKey );
    }

    private void header(String AppID, String AppKey){
        this.UserAgent.put("VERSION", String.valueOf(this.Version));
        this.UserAgent.put("APPID", AppID);
        this.UserAgent.put("APPKEY", AppKey);


        Set<String> setAgent = this.UserAgent.keySet();
        for(String key : setAgent){
            this.Headers.put(key.toUpperCase(), this.UserAgent.get(key));
        }
    }

    private String parseParam (Map<String, String> param) {
        String paramStr = "";
        Set<String> setParam = param.keySet();
        for(String key : setParam){
            paramStr += "&" + key + "=" + param.get(key);
        }
        return paramStr.substring(1);
    }

    public String JDKFetch(String request, String charset) throws Exception {
    
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);

        Set<String> setHeader = this.Headers.keySet();
        for(String key : setHeader){
            conn.setRequestProperty(key, this.Headers.get(key));
        }

        //if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream input = conn.getInputStream();
            StringBuffer sb = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, charset));
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(s + "\n");
            }
            input.close();
            conn.disconnect();
            return sb.toString();
        //}
        //return "";
    }

    //调用公有方法
    public String cates() throws Exception {
        String url = this.Gateway + "/" + "cates";
        return JDKFetch(url, "utf-8");
    }

    public String detail(String gid) throws Exception {
        String url = this.Gateway + "/" + "detail" + "/" + gid;
        return JDKFetch(url, "utf-8");
    }

    public String detail(String gid, String detail) throws Exception {
        String url = this.Gateway + "/" + "detail" + "/" + gid + "/" + detail;
        return JDKFetch(url, "utf-8");
    }

    public String goods(String cate_id, String page) throws Exception {
        String url = this.Gateway + "/" + "goods" + "/" + cate_id + "/" + page;
        return JDKFetch(url, "utf-8");
    }

    public String goods(String cate_id, String page, Map<String, String> attr) throws Exception {
        String url = this.Gateway + "/" + "goods" + "/" + cate_id + "/" + page + "/" + parseParam(attr);
        return JDKFetch(url, "utf-8");
    }

    public String video(String cate_id, String page) throws Exception {
        String url = this.Gateway + "/" + "video" + "/" + cate_id + "/" + page;
        return JDKFetch(url, "utf-8");
    }

    public String brand(String cate_id, String page) throws Exception {
        String url = this.Gateway + "/" + "brand" + "/" + cate_id + "/" + page;
        return JDKFetch(url, "utf-8");
    }

    public String today(String cate_id, String page) throws Exception {
        String url = this.Gateway + "/" + "today" + "/" + cate_id + "/" + page;
        return JDKFetch(url, "utf-8");
    }

    public String rankings(String rankings) throws Exception {
        String url = this.Gateway + "/" + "rankings" + "/" + rankings;
        return JDKFetch(url, "utf-8");
    }
}
