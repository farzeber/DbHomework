package servicetest.example.com.dbhomework;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;


import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import servicetest.example.com.dbhomework.Datas.LoginInfo;
import servicetest.example.com.dbhomework.Datas.resData;


/**
 * Created by mick2017 on 2018/6/11.
 */

public class http {
    private static final String myIp="http://10.63.112.166:8080/";
    public static final int type_register=1;
    public static final int type_login=2;
    public static final int type_stu=3;
    public static final int type_man=4;
    public static final int type_upd=5;

    public static void myhp(int type, AbsCallback mycallback, JSONObject jsonObject){
        String url;
       switch (type){

           case type_login:
               url=myIp+"login";
        //       Type listype=new TypeToken<resData<LoginInfo>>(){}.getType();
                     OkGo.post(url)
                       .upJson(jsonObject)
                       .execute(mycallback);
               break;
           case type_register:
               url=myIp+"register_stu";
               OkGo.post(url)
                       .upJson(jsonObject)
                       .execute(mycallback);
               break;
           case type_stu:
               url=myIp+"search";
               String ad="";
               try{
                   ad=jsonObject.getString("admissionNum");
               }catch (JSONException e){
                   e.printStackTrace();
               }
               OkGo.post(url)

                       .params("admissionNum",ad)
                       .execute(mycallback);
               break;
           case type_upd:
               url=myIp+"update";
               OkGo.post(url)
                        .upJson(jsonObject)
                       .execute(mycallback);
               break;
       }
    }

}
