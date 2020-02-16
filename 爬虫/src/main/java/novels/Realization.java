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
    public final int chapterMax = 50;  //��������ȡ�����½�


    public Realization(int runNumber) {
        this.runNumber = runNumber;
    }

    //��Ҫ�����ĳ�ʼ��ַ
    public String url = "http://www.biquge.tv/0_";                //��վ�½���ȡ��ַ
    public String url_to = "http://www.biquge.tv";                //��վԴ��ַ
    public String picIn = "C:\\Users\\40164\\Desktop\\images\\";  //ͼƬ��ŵ�ַ
    public String chapterIn = "/1.html";                          //�½���Ϣ��ŵ�ַ

    public void oneTask(int number) {
        String com = url + number + "/";
        String text1 = tool.get_text(com);
        //�������ұ��������ݿ�
        this.analysis(text1);
    }




    //�������ұ��������ݿ�
    public void analysis(String text1) {
        int chapterNumber = 0;
        List<Chapter> chapter = new ArrayList<Chapter>();
        int chapterNUM = 9;
        //�����ļ���Ϣ
        Document doc = Jsoup.parse(text1);
        Elements getText = doc.select("div#info > h1");
        Elements getText1 = doc.select("div#info > p");
        Elements getIntroduction = doc.select("div#intro > p");
        Elements getpic = doc.select("div#fmimg > img");
        Elements chapterIn = doc.select("div#list > dl > dd");
        Elements chapterIn1 = doc.select("div#list > dl > dd >a");
        //��ȡС˵����
        String title = getText.iterator().next().text();
        //��ȡС˵����
        String author1 = getText1.iterator().next().text();
        String author = author1.substring(author1.indexOf("��")+1);
        //��ȡС˵���
        String introduction = getIntroduction.iterator().next().text();
        //��ȡС˵����
        String picUrl1 = getpic.iterator().next().attr("src");
        String picUrl = url_to+picUrl1;
        String picname = this.tool.get_picture(picUrl);
        //����С˵����
        Novel novel_task = new Novel(title, author, introduction,picIn+picname);
        //��ȡ�½���Ϣ
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
    //���߳�
    public void run() {
        oneTask(runNumber);
    }
}
//�޸Ļ���������ֵķ���