package novels;

import novels.Chapter;
import tool.*;

import java.sql.*;
import java.util.List;

public class Novel implements Preserve{
    public String title = null;   //����
    public String author = null;   //����
    public String introduction = null;  //���
    public String picture = null;   //�����ַ
    public List<Chapter> chapter = null;   //С˵�Ķ���½�

    public List<Chapter> getChapter() {
        return chapter;
    }

    public void setChapter(List<Chapter> chapter) {
        this.chapter = chapter;
    }

    public Novel(String title, String author, String introduction, String picture) {
        this.title = title;
        this.author = author;
        this.introduction = introduction;
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override

   public void save() {
        ResultSet rs = null;
        Statement stmt = null;
        String url = "jdbc:mysql://localhost:3306/novels?useUnicode=true&characterEncoding=GB2312";
        String user = "root";
        String pass = "zhen415322";
        Connection conn = null;

        try {
            List<Chapter> chapter = this.chapter;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,pass);
            stmt = conn.createStatement();
            //�鿴���ݿ����Ƿ����ⲿС˵,����в��ڴ������ݿ⣬�Ƴ��÷���
            String sqlFirst = "select id from novel where novel_title = '"+this.title+"'";
            rs = stmt.executeQuery(sqlFirst);
            if(rs.next())
            {
                System.out.println("The novel is already in the database!");
                return;
            }
            //�鿴author�����Ƿ��и�����
            String sql1 ="select id,author_name from author where author_name = '"+this.author+"'";
            rs = stmt.executeQuery(sql1);
            boolean flag = rs.next();
            int getId = 0;
            if(flag&&rs.getString(2).equals(this.author)){
                //�����������ұ������ݺ������������Ӧ��������������ݣ�ֱ�ӻ�ȡ������id
                getId = rs.getInt(1);
            }else{
                //������ƥ�����ݣ�����Ӹ�������Ϣ��Ȼ����ӻ�ȡ��id
                String sql0 = "insert into author (author_name) values('"+this.author+"')";
                stmt.executeUpdate(sql0);
                String sql = "select id from author where author_name = '"+this.author+"';";
                rs = stmt.executeQuery(sql);
                rs.next();
                getId = rs.getInt(1);
            }

            String sql2 = "insert into novel (novel_title,author_id,introduction,picture) values('"+this.title+"',"+getId+",'"+this.introduction+"','"+this.picture+"')";
            String sql3 = "select id from novel where novel_title = '"+this.title+"'";
            //���С˵��Ϣ���һ�ȡС˵��id
            stmt.executeUpdate(sql2);
            rs = stmt.executeQuery(sql3);
            boolean flag1 = rs.next();
            int id = -1;
            if(flag1){
                id = rs.getInt(1);
            }
            //����½���Ϣ�����ݿ�
            for(Chapter chapter_one : chapter)
            {
                String sql4 = "insert into chapter (chapter_title,chapter_text,in_novel_id) values('"+chapter_one.title+"','"+chapter_one.text+"',"+id+")";
                stmt.executeUpdate(sql4);
            }
            System.out.println("Save successfully");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Save failed");
        }finally {
            if(rs != null)
            {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
