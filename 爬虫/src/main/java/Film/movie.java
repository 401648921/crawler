package Film;

import tool.Preserve;

import java.sql.*;
import java.util.List;

public class movie implements Preserve {
    public String picNAME = null;             //电影封面保存路径
    public String filmName = null;           //电影名字
    public int length = 0;                    //电影时长
    public double score = 0;                 //电影豆瓣评分
    public int personNumber = 0;             //电影评分人数
    public List<direct> directs = null;      //电影的多个导演
    public List<comment> comments = null;    //电影的多条评论
    public List<writer> writers = null;      //电影的多位编剧
    public Grade grade = null;               //电影评星占比
    public List<actor> actors = null;         //电影的多位主演

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
            //确保该部电影数据库里面没有,如果有则推出保存方法
            String sql0 = "select id from film where film_name = '"+this.filmName+"'";
            rs = stmt.executeQuery(sql0);
            if(rs.next()){
                System.out.println("The movie is already in the database!");
                return;
            }
            //保存数据至film表
            String sql1 = "insert into film (picname,film_name,film_length,score) values('"+this.picNAME+"','"+this.filmName+"',"+this.length+","+this.score+")";
            String sql2 = "select id from film where film_name = '"+this.filmName+"'";
            stmt.executeUpdate(sql1);
            //获取保存的电影自增id
            rs = stmt.executeQuery(sql2);
            if(rs.next()){
                movieId = rs.getInt(1);
            }
            //保存数据至direct表和film_direct_relation表
            for(direct direct : this.directs){
                int directId = 0 ;
                //先判断该导演是否在数据库中
                String sqlFirst = "select id from direct where direct_name = '"+direct.name+"'";
                rs = stmt.executeQuery(sqlFirst);
                if(rs.next()){
                    directId = rs.getInt(1);
                }else{
                    String sql3 = "insert into direct (direct_name) values('"+direct.name+"')";
                    String sql4 = "select id from direct where direct_name = '"+direct.name+"'";
                    //保存数据至direct表
                    stmt.executeUpdate(sql3);
                    //寻找自增的direct的id
                    rs = stmt.executeQuery(sql4);
                    if(rs.next()){
                        directId = rs.getInt(1);
                    }
                }
                String sql5 = "insert into film_direct_relation (film_id,direct_id) values("+movieId+","+directId+")";
                stmt.executeUpdate(sql5);
            }
            //保存数据至actor和film_actor_relation表
            for(actor actor : this.actors){
                int actorId = 0 ;
                //先判断该主演是否在数据库中
                String sqlFirst = "select id from actor where actor_name = '"+actor.name+"'";
                rs = stmt.executeQuery(sqlFirst);
                if(rs.next()){
                    actorId = rs.getInt(1);
                }else{
                    String sql3 = "insert into actor (actor_name) values('"+actor.name+"')";
                    String sql4 = "select id from actor where actor_name = '"+actor.name+"'";
                    //保存数据至actor表
                    stmt.executeUpdate(sql3);
                    //寻找自增的actor的id
                    rs = stmt.executeQuery(sql4);
                    if(rs.next()){
                        actorId = rs.getInt(1);
                    }
                }
                //将数据添加至film_actor_relation表
                String sql5 = "insert into film_actor_relation (film_id,actor_id) values("+movieId+","+actorId+")";
                stmt.executeUpdate(sql5);
            }
            //保存数据至actor和film_actor_relation表
            for(writer writer : this.writers){
                int writerId = 0 ;
                //先判断该编剧是否在数据库中
                String sqlFirst = "select id from writer where writer_name = '"+writer.name+"'";
                rs = stmt.executeQuery(sqlFirst);
                if(rs.next()){
                    writerId = rs.getInt(1);
                }else{
                    String sql3 = "insert into writer (writer_name) values('"+writer.name+"')";
                    String sql4 = "select id from writer where writer_name = '"+writer.name+"'";
                    //保存数据至writer表
                    stmt.executeUpdate(sql3);
                    //寻找自增的writer的id
                    rs = stmt.executeQuery(sql4);
                    if(rs.next()){
                        writerId = rs.getInt(1);
                    }
                }
                //将数据添加倒film_writer_relation表
                String sql5 = "insert into film_writer_relation (film_id,writer_id) values("+movieId+","+writerId+")";
                stmt.executeUpdate(sql5);
            }
            //添加评星占比数据
            String gradeSql = "insert into grade (star_one,star_two,star_three,star_four,star_five,film_id) values("+this.grade.star1+","+this.grade.star2+","+this.grade.star3+","+this.grade.star4+","+this.grade.star5+","+movieId+")";
            stmt.executeUpdate(gradeSql);
            //添加评论信息
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
