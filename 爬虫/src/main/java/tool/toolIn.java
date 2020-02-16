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


//����ͼƬ�ͻ�ȡҳ����Ϣ�Ĺ�����
public class toolIn implements tool {
    public PoolingHttpClientConnectionManager clientConnectionManager;   //���ӳ�

    public toolIn() {
        //���ͼƬ��ŵ�ַ�����ڣ��򴴽��ļ���
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
        //��ȡhttpClient����
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.clientConnectionManager).build();
        //����httpGet�����������url��ַ
        HttpGet httpGet = new HttpGet(url);
        //����������Ϣ
        httpGet.setConfig(this.getConfig());
        try {
            //ʹ��httpClient�������󣬻����Ӧ
            response = httpClient.execute(httpGet);
            //������Ӧ�����Ӧ
            if(response.getStatusLine().getStatusCode() == 200){
                //�ж���Ӧ��Entity�Ƿ�Ϊ��
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

        return "";   //ʧ�ܷ��ؿմ�
    }

    public RequestConfig getConfig(){
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000)   //���������ʱ��
                .setConnectionRequestTimeout(500)    //��ȡ�����ʱ��
                .setSocketTimeout(10000)            //���ݴ����ʱ��
                .build();
        return config;
    }

    public String get_picture(String url) {
        CloseableHttpResponse response = null;
        //��ȡhttpClient����
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.clientConnectionManager).build();
        //����httpGet�����������url��ַ
        HttpGet httpGet = new HttpGet(url);
        //����������Ϣ
        httpGet.setConfig(this.getConfig());
        try {
            //ʹ��httpClient�������󣬻����Ӧ
            response = httpClient.execute(httpGet);
            //������Ӧ�����Ӧ
            if(response.getStatusLine().getStatusCode() == 200){
                //�ж���Ӧ��Entity�Ƿ�Ϊ��
                if(response.getEntity() != null){
                    //����ͼƬ
                    //���ͼƬ�ĺ�׺
                    String lastName = url.substring(url.lastIndexOf("."));
                    //����ͼƬ����������ͼƬ
                    String picName = UUID.randomUUID().toString()+lastName;
                    //����ͼƬ
                    OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\40164\\Desktop\\images\\"+picName));
                    response.getEntity().writeTo(outputStream);
                    //����ͼƬ����

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

        return "";   //ʧ�ܷ��ؿմ�
    }
}
