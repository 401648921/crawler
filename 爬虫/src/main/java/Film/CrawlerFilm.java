package Film;

import novels.Realization;

import java.io.*;
import java.lang.reflect.Field;

import static java.lang.Integer.*;
//该爬虫的编码是GB2312，启用前请先更改编译器的编码
//该爬虫的编码是GB2312，启用前请先更改编译器的编码
//该爬虫的编码是GB2312，启用前请先更改编译器的编码

//本人爬取网站时，可能由于电脑问题，使用UTF-8编码会导致读取的文件产生乱码，望审批人海涵。
public class CrawlerFilm {
    public static void main(String[] args) {
        int stare = 60; //设置从第几部电影开始
        final int getNumber = 64;   //设置爬取几部电影

        //多线程爬取
        for( int i = stare; i < getNumber+1 ; i++){
            Thread run = new Thread(new RealizationTo(i));
            run.start();
        }


    }

}
