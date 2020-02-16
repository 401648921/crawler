package novels;
import tool.*;

import java.io.*;
//该爬虫的编码是GB2312，启用前请先更改编译器的编码
//该爬虫的编码是GB2312，启用前请先更改编译器的编码
//该爬虫的编码是GB2312，启用前请先更改编译器的编码

//本人爬取网站时，可能由于电脑问题，使用UTF-8编码会导致读取的文件产生乱码，望审批人海涵。
public class CrawlerNovel {


    //该主方法运用多线程爬取笔趣网小说
    //通过将进程写入文件，下次运行时读取文件获取上次进程，实现暂停机制
    public static void main(String[] args) {
        int stare = 1;
        String filePath = "D:\\file\\novelNum.txt";
        final int novelNumber = 5;       //设置一次爬取几部小说
        //如果文件不存在，创建文件
        File file = new File(filePath);
        try {
            if(!file.exists())
            {
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(filePath));
                //默认从 1 开始
                dos.writeInt(1);
                dos.flush();
                dos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取文件，获取进程
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(filePath));
            stare = dis.readInt();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //多线程
        for(int i = stare; i< stare+novelNumber+1; i++){
            Thread run = new Thread(new Realization(i));
            run.start();
        }
        //写入当前进程
        try{
            file.delete();
            DataOutputStream dosIn = new DataOutputStream(new FileOutputStream(filePath));
            dosIn.writeInt(stare+novelNumber);
            dosIn.flush();
            dosIn.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
