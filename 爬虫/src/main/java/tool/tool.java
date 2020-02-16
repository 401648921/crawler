package tool;

import novels.Novel;
import novels.*;
import org.apache.http.client.config.RequestConfig;

public interface tool{
    public String get_text(String url); //获取页面数据
    public String get_picture(String url);  //下载图片
    public RequestConfig getConfig();    //设置请求信息


}
