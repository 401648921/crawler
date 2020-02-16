package tool;
import tool.*;
import novels.*;
import novels.Chapter;
import novels.Novel;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import tool.tool;


import java.io.*;
import java.util.List;
import java.util.UUID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//下载图片和获取页面信息的工具类
public class toolIn implements tool {
    public PoolingHttpClientConnectionManager clientConnectionManager;   //连接池

    public toolIn() {
        //如果图片存放地址不存在，则创建文件夹
        File file = new File("C:\\Users\\40164\\Desktop\\images");
        if(!file.exists()){
            file.mkdir();
        }
        this.clientConnectionManager = new PoolingHttpClientConnectionManager();
        this.clientConnectionManager.setMaxTotal(60);
        this.clientConnectionManager.setDefaultMaxPerRoute(10);
    }





    public String get_text(String url) {
        CloseableHttpResponse response = null;
        //获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.clientConnectionManager).build();
        //创建httpGet请求对象，设置url地址
        HttpGet httpGet = new HttpGet(url);
        //设置请求信息
        httpGet.setConfig(this.getConfig());
        try {
            //使用httpClient发起请求，获得响应
            response = httpClient.execute(httpGet);
            //解析相应获得响应
            if(response.getStatusLine().getStatusCode() == 200){
                //判断响应体Entity是否不为空
                if(response.getEntity() != null){
                    String content = EntityUtils.toString(response.getEntity(),"gb2312");
                    return content;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response != null)
            {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "";   //失败返回空串
    }

    public RequestConfig getConfig(){
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000)   //创建连接最长时间
                .setConnectionRequestTimeout(500)    //获取连接最长时间
                .setSocketTimeout(10000)            //数据传输最长时长
                .build();
        return config;
    }

    public String get_picture(String url) {
        CloseableHttpResponse response = null;
        //获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.clientConnectionManager).build();
        //创建httpGet请求对象，设置url地址
        HttpGet httpGet = new HttpGet(url);
        //设置请求信息
        httpGet.setConfig(this.getConfig());
        try {
            //使用httpClient发起请求，获得响应
            response = httpClient.execute(httpGet);
            //解析相应获得响应
            if(response.getStatusLine().getStatusCode() == 200){
                //判断响应体Entity是否不为空
                if(response.getEntity() != null){
                    //下载图片
                    //获得图片的后缀
                    String lastName = url.substring(url.lastIndexOf("."));
                    //创建图片名，重命名图片
                    String picName = UUID.randomUUID().toString()+lastName;
                    //下载图片
                    OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\40164\\Desktop\\images\\"+picName));
                    response.getEntity().writeTo(outputStream);
                    //返回图片名称

                    return picName;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response != null)
            {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "";   //失败返回空串
    }
}
