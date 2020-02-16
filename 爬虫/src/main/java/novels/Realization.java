package novels;
import tool.*;
import novels.Realization;
import novels.*;
import novels.Chapter;
import novels.Novel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class Realization implements Runnable{
    public tool tool = new toolIn();
    public int runNumber;
    public final int chapterMax = 50;  //设置最多读取多少章节


    public Realization(int runNumber) {
        this.runNumber = runNumber;
    }

    //需要解析的初始地址
    public String url = "http://www.biquge.tv/0_";                //网站章节提取地址
    public String url_to = "http://www.biquge.tv";                //网站源地址
    public String picIn = "C:\\Users\\40164\\Desktop\\images\\";  //图片存放地址
    public String chapterIn = "/1.html";                          //章节信息存放地址

    public void oneTask(int number) {
        String com = url + number + "/";
        String text1 = tool.get_text(com);
        //解析并且保存至数据库
        this.analysis(text1);
    }




    //解析并且保存至数据库
    public void analysis(String text1) {
        int chapterNumber = 0;
        List<Chapter> chapter = new ArrayList<Chapter>();
        int chapterNUM = 9;
        //解析文件信息
        Document doc = Jsoup.parse(text1);
        Elements getText = doc.select("div#info > h1");
        Elements getText1 = doc.select("div#info > p");
        Elements getIntroduction = doc.select("div#intro > p");
        Elements getpic = doc.select("div#fmimg > img");
        Elements chapterIn = doc.select("div#list > dl > dd");
        Elements chapterIn1 = doc.select("div#list > dl > dd >a");
        //获取小说标题
        String title = getText.iterator().next().text();
        //获取小说作者
        String author1 = getText1.iterator().next().text();
        String author = author1.substring(author1.indexOf("：")+1);
        //获取小说简介
        String introduction = getIntroduction.iterator().next().text();
        //获取小说封面
        String picUrl1 = getpic.iterator().next().attr("src");
        String picUrl = url_to+picUrl1;
        String picname = this.tool.get_picture(picUrl);
        //建立小说对象
        Novel novel_task = new Novel(title, author, introduction,picIn+picname);
        //获取章节信息
        for(Element element : chapterIn){
            if(chapterNUM > 0){
                chapterNUM--;
                continue;
            }
            Chapter chapterTask = new Chapter();
            chapterTask.title = element.text();
            chapter.add(chapterTask);
            if(chapterNumber == chapterMax){
                break;
            }
            chapterNumber++;
        }
        chapterNUM = 9;
        int number = 0;
        chapterNumber = 0;
        for(Element element : chapterIn1){
            if(chapterNumber == (chapterMax -1)  ){
                break;
            }
            chapterNumber++;
            if(chapterNUM > 0){
                chapterNUM--;
                continue;
            }
            Chapter chapterTask = chapter.get(number);
            String get1 = element.attr("href");
            String textTask = tool.get_text(url_to+get1);
            Document doc1 = Jsoup.parse(textTask);
            Elements getToText = doc1.select("div#content");
            String textGET = getToText.text();
            chapterTask.text = textGET;
            number++;
        }
        novel_task.chapter = chapter;
        SalSave.save(novel_task);
    }

    @Override
    //多线程
    public void run() {
        oneTask(runNumber);
    }
}
//修改获得作者名字的方法