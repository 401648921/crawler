package novels;
import tool.*;

import java.io.*;
//������ı�����GB2312������ǰ���ȸ��ı������ı���
//������ı�����GB2312������ǰ���ȸ��ı������ı���
//������ı�����GB2312������ǰ���ȸ��ı������ı���

//������ȡ��վʱ���������ڵ������⣬ʹ��UTF-8����ᵼ�¶�ȡ���ļ��������룬�������˺�����
public class CrawlerNovel {


    //�����������ö��߳���ȡ��Ȥ��С˵
    //ͨ��������д���ļ����´�����ʱ��ȡ�ļ���ȡ�ϴν��̣�ʵ����ͣ����
    public static void main(String[] args) {
        int stare = 1;
        String filePath = "D:\\file\\novelNum.txt";
        final int novelNumber = 5;       //����һ����ȡ����С˵
        //����ļ������ڣ������ļ�
        File file = new File(filePath);
        try {
            if(!file.exists())
            {
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(filePath));
                //Ĭ�ϴ� 1 ��ʼ
                dos.writeInt(1);
                dos.flush();
                dos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //��ȡ�ļ�����ȡ����
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(filePath));
            stare = dis.readInt();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //���߳�
        for(int i = stare; i< stare+novelNumber+1; i++){
            Thread run = new Thread(new Realization(i));
            run.start();
        }
        //д�뵱ǰ����
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
