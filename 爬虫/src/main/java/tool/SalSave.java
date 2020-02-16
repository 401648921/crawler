package tool;

//为防止多个数据同时载入数据库产生数据异常，加入类锁
public class SalSave {
    public synchronized static void save(Preserve e){
        e.save();
    }
}
