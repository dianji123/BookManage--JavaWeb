package cn.itcast.bookmanager.utils;

import cn.itcast.bookmanager.model.User;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

public class toolUtil{
    public static boolean isEmpty(String str){
        if(str != null && !"".equals(str.trim())){
            return false;
        }
        return true;
    }
    public static Long getTime(){
        long time = System.currentTimeMillis();
        return time;
    }
    public static String getDateByTime(Long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string = format.format(new Date(time));
        return string;
    }
    public static User getUser(HttpSession session){
        User user = (User)session.getAttribute("user");
        return user;
    }
    public static void setUser(HttpSession session,User user){
        session.setAttribute("user", user);
    }
}

