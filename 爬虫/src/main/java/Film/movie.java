package Film;

import tool.Preserve;

import java.sql.*;
import java.util.List;

public class movie implements Preserve {
    public String picNAME = null;             //��Ӱ���汣��·��
    public String filmName = null;           //��Ӱ����
    public int length = 0;                    //��Ӱʱ��
    public double score = 0;                 //��Ӱ��������
    public int personNumber = 0;             //��Ӱ��������
    public List<direct> directs = null;      //��Ӱ�Ķ������
    public List<comment> comments = null;    //��Ӱ�Ķ�������
    public List<writer> writers = null;      //��Ӱ�Ķ�λ���
    public Grade grade = null;               //��Ӱ����ռ��
    public List<actor> actors = null;         //��Ӱ�Ķ�λ����

    public String getPicNAME() {
        return picNAME;
    }

    public void setPicNAME(String picNAME) {
        this.picNAME = picNAME;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public List<direct> getDirects() {
        return directs;
    }

    public void setDirects(List<direct> directs) {
        this.directs = directs;
    }

    public List<writer> getWriters() {
        return writers;
    }

    public void setWriters(List<writer> writers) {
        this.writers = writers;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public List<actor> getActors() {
        return actors;
    }

    public void setActors(List<actor> actors) {
        this.actors = actors;
    }

    public movie(String picNAME, String filmName, int time, double score, int personNumber, List<direct> directs, List<comment> comments, List<writer> writers, Grade grade, List<actor> actors) {
        this.picNAME = picNAME;
        this.filmName = filmName;
        this.length = time;
        this.score = score;
        this.personNumber = personNumber;
        this.directs = directs;
        this.comments = comments;
        this.writers = writers;
        this.grade = grade;
        this.actors = actors;
    }

    @Override
    public void save() {
        ResultSet rs = null;
        Statement stmt = null;
        String url = "jdbc:mysql://localhost:3306/films?useUnicode=true&characterEncoding=GB2312";
        String user = "root";
        String pass = "zhen415322";
        Connection conn = null;
        try {
            int movieId = 0;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,pass);
            stmt = conn.createStatement();
            //ȷ���ò���Ӱ���ݿ�����û��,��������Ƴ����淽��
            String sql0 = "select id from film where film_name = '"+this.filmName+"'";
            rs = stmt.executeQuery(sql0);
            if(rs.next()){
                System.out.println("The movie is already in the database!");
                return;
            }
            //����������film��
            String sql1 = "insert into film (picname,film_name,film_length,score) values('"+this.picNAME+"','"+this.filmName+"',"+this.length+","+this.score+")";
            String sql2 = "select id from film where film_name = '"+this.filmName+"'";
            stmt.executeUpdate(sql1);
            //��ȡ����ĵ�Ӱ����id
            rs = stmt.executeQuery(sql2);
            if(rs.next()){
                movieId = rs.getInt(1);
            }
            //����������direct���film_direct_relation��
            for(direct direct : this.directs){
                int directId = 0 ;
                //���жϸõ����Ƿ������ݿ���
                String sqlFirst = "select id from direct where direct_name = '"+direct.name+"'";
                rs = stmt.executeQuery(sqlFirst);
                if(rs.next()){
                    directId = rs.getInt(1);
                }else{
                    String sql3 = "insert into direct (direct_name) values('"+direct.name+"')";
                    String sql4 = "select id from direct where direct_name = '"+direct.name+"'";
                    //����������direct��
                    stmt.executeUpdate(sql3);
                    //Ѱ��������direct��id
                    rs = stmt.executeQuery(sql4);
                    if(rs.next()){
                        directId = rs.getInt(1);
                    }
                }
                String sql5 = "insert into film_direct_relation (film_id,direct_id) values("+movieId+","+directId+")";
                stmt.executeUpdate(sql5);
            }
            //����������actor��film_actor_relation��
            for(actor actor : this.actors){
                int actorId = 0 ;
                //���жϸ������Ƿ������ݿ���
                String sqlFirst = "select id from actor where actor_name = '"+actor.name+"'";
                rs = stmt.executeQuery(sqlFirst);
                if(rs.next()){
                    actorId = rs.getInt(1);
                }else{
                    String sql3 = "insert into actor (actor_name) values('"+actor.name+"')";
                    String sql4 = "select id from actor where actor_name = '"+actor.name+"'";
                    //����������actor��
                    stmt.executeUpdate(sql3);
                    //Ѱ��������actor��id
                    rs = stmt.executeQuery(sql4);
                    if(rs.next()){
                        actorId = rs.getInt(1);
                    }
                }
                //�����������film_actor_relation��
                String sql5 = "insert into film_actor_relation (film_id,actor_id) values("+movieId+","+actorId+")";
                stmt.executeUpdate(sql5);
            }
            //����������actor��film_actor_relation��
            for(writer writer : this.writers){
                int writerId = 0 ;
                //���жϸñ���Ƿ������ݿ���
                String sqlFirst = "select id from writer where writer_name = '"+writer.name+"'";
                rs = stmt.executeQuery(sqlFirst);
                if(rs.next()){
                    writerId = rs.getInt(1);
                }else{
                    String sql3 = "insert into writer (writer_name) values('"+writer.name+"')";
                    String sql4 = "select id from writer where writer_name = '"+writer.name+"'";
                    //����������writer��
                    stmt.executeUpdate(sql3);
                    //Ѱ��������writer��id
                    rs = stmt.executeQuery(sql4);
                    if(rs.next()){
                        writerId = rs.getInt(1);
                    }
                }
                //��������ӵ�film_writer_relation��
                String sql5 = "insert into film_writer_relation (film_id,writer_id) values("+movieId+","+writerId+")";
                stmt.executeUpdate(sql5);
            }
            //�������ռ������
            String gradeSql = "insert into grade (star_one,star_two,star_three,star_four,star_five,film_id) values("+this.grade.star1+","+this.grade.star2+","+this.grade.star3+","+this.grade.star4+","+this.grade.star5+","+movieId+")";
            stmt.executeUpdate(gradeSql);
            //���������Ϣ
            for(comment comment : comments){
                String commentSql = "insert into comment (comment_text,film_id) values('"+comment.comment+"',"+movieId+")";
                stmt.executeUpdate(commentSql);
            }
            System.out.println("Save successfully");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Save failed");
            e.printStackTrace();
        }
    }
}
