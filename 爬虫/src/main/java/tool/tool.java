package tool;

import novels.Novel;
import novels.*;
import org.apache.http.client.config.RequestConfig;

public interface tool{
    public String get_text(String url); //��ȡҳ������
    public String get_picture(String url);  //����ͼƬ
    public RequestConfig getConfig();    //����������Ϣ


}
