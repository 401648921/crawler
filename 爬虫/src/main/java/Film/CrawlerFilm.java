package Film;

import novels.Realization;

import java.io.*;
import java.lang.reflect.Field;

import static java.lang.Integer.*;
//������ı�����GB2312������ǰ���ȸ��ı������ı���
//������ı�����GB2312������ǰ���ȸ��ı������ı���
//������ı�����GB2312������ǰ���ȸ��ı������ı���

//������ȡ��վʱ���������ڵ������⣬ʹ��UTF-8����ᵼ�¶�ȡ���ļ��������룬�������˺�����
public class CrawlerFilm {
    public static void main(String[] args) {
        int stare = 60; //���ôӵڼ�����Ӱ��ʼ
        final int getNumber = 64;   //������ȡ������Ӱ

        //���߳���ȡ
        for( int i = stare; i < getNumber+1 ; i++){
            Thread run = new Thread(new RealizationTo(i));
            run.start();
        }


    }

}
