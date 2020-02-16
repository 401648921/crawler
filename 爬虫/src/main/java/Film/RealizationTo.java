package Film;

import novels.Chapter;
import novels.Novel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tool.*;
import novels.*;

import java.util.ArrayList;
import java.util.List;

public class RealizationTo implements Runnable{
    public tool tool = new toolIn();
    public int runNumber;            //电影的名次
    List<String> urlFirst = new ArrayList<String>();
    //需要解析的初始地址
    public String url = "https://movie.douban.com/top250?start=0&filter=";                //豆瓣电影Top250提取地址
    public String picIn = "C:\\Users\\40164\\Desktop\\douban\\";                          //图片存放地址



    public RealizationTo(int runNumber) {
        String urlTo = url;
        int goPage = 0;
        if(runNumber>250){
            System.out.println("You can't get a movie with more than 250 numbers");
            return;
        }
        if(runNumber > 25){
            goPage = (int)Math.ceil(runNumber/25);
            goPage = goPage*25;
            urlTo = url.replaceAll("start=0","start="+goPage);
            runNumber = runNumber - goPage;
        }
        String textFirst = tool.get_text(urlTo);
        Document doc = Jsoup.parse(textFirst);
        Elements getTextFirst = doc.select("div.hd > a");
        for(Element element : getTextFirst){
            String url2 = element.attr("href");
            urlFirst.add(url2);
        }
        this.runNumber = runNumber;
    }

    public void oneTask() {
        String com = urlFirst.get(runNumber - 1);
        String text1 = tool.get_text(com);
        //解析并且保存至数据库
        this.analysis(text1);
    }




    //解析并且保存至数据库
    public void analysis(String text1) {
        List<direct> directs = new ArrayList<>();
        List<writer> writers = new ArrayList<>();
        List<actor> actors = new ArrayList<>();
        List<comment> comments = new ArrayList<>();
        //解析文件信息
        Document doc = Jsoup.parse(text1);
        Elements getName = doc.select("div#content > h1 > span[property]");
        Elements getDirect = doc.select("div#info > span > span.attrs > a[rel=\"v:directedBy\"]");
        Elements getActor = doc.select("div#info>span>span>a[rel=\"v:starring\"]");
        Elements getScore = doc.select("div.rating_self>strong");
        Elements getperson = doc.select("div.rating_sum>a>span");
        Elements getTime = doc.select("div#info>span[property=\"v:runtime\"]");
        Elements getWriter = doc.select("div#info>span>span.attrs>a");
        Elements getGrade = doc.select("div.item>span.rating_per");
        Elements getpic = doc.select("div#mainpic>a>img");
        Elements getComment = doc.select("div.comment");
        //获取电影名字
        String name = getName.iterator().next().text();
        //获取电影导演
        for(Element element : getDirect){
            String  directName = element.text();
            direct direct = new direct(directName);
            directs.add(direct);
        }
        //获取电影主演
        for(Element element : getActor){
            String  ActorName = element.text();
            actor actor = new actor(ActorName);
            actors.add(actor);
        }
        //获取电影评分
        String score1 = getScore.first().text();
        Double score = Double.parseDouble(score1);
        //获取评分人数
        String person1 = getperson.first().text();
        int person = Integer.parseInt(person1);
        //获取电影时长
        String time1 = getTime.first().text();
        String time2;
        if(time1.contains(" ")){
            time2 = time1.substring(0, time1.indexOf(" "));
        }else {
            time2 = time1.substring(0, time1.indexOf("分"));
        }
        int time = Integer.parseInt(time2);
        //获取电影封面
        String picUrl = getpic.iterator().next().attr("src");
        String picname = this.tool.get_picture(picUrl);
        //获取编剧
        int sum = directs.size();
        for(Element element : getWriter){
            sum --;
            if(sum> -1){
                continue;
            }
            String  writerName = element.text();
            if(writerName.equals(actors.get(0).name)){
                break;
            }
            writer writer = new writer(writerName);
            writers.add(writer);
        }
        //获取电影评星百分比
        //五星百分比
        String grade5 = getGrade.get(0).text();
        //String str1=str.substring(0, str.indexOf("?"));
        String grade05 = grade5.substring(0,grade5.indexOf("%"));
        double gradefirst5 = Double.parseDouble(grade05);
        //四星百分比
        String grade4 = getGrade.get(1).text();
        String grade04 = grade4.substring(0,2);
        double gradefirst4 = Double.parseDouble(grade04);
        //三星百分比
        String grade3 = getGrade.get(2).text();
        String grade03 = grade3.substring(0,2);
        double gradefirst3 = Double.parseDouble(grade03);
        //二星百分比
        String grade2 = getGrade.get(3).text();
        String grade02 = grade2.substring(0,2);
        double gradefirst2 = Double.parseDouble(grade02);
        //一星百分比
        String grade1 = getGrade.get(4).text();
        String grade01 = grade1.substring(0,2);
        double gradefirst1 = Double.parseDouble(grade01);
        Grade grade = new Grade(gradefirst1,gradefirst2,gradefirst3,gradefirst4,gradefirst5);
        //获取评论
        for(Element element : getComment){
            String  comment1 = element.text();
            comment comment = new comment(comment1);
            comments.add(comment);
        }
        //建立电影对象
        movie movieTask = new movie(picname,name,time,score,person,directs,comments,writers,grade,actors);

        movieTask.save();
    }

    @Override
    //多线程
    public void run() {
        oneTask();
    }


}
