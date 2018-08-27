package servicetest.example.com.dbhomework.Datas;

import android.util.JsonReader;

import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by mick2017 on 2018/6/11.
 */

public abstract class JsonCallback<T> extends AbsCallback<T>{
    private Type type;
    private Class<T> clazz;

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 拿到响应后，将数据转换成需要的格式，子线程中执行，可以是耗时操作
     *
     * @param response 需要转换的对象
     * @return 转换后的结果
     * @throws Exception 转换过程发生的异常
     */
    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body=response.body();
        if(body==null) return null;
        T data=null;
        Gson gson=new Gson();
        com.google.gson.stream.JsonReader jsonReader=new com.google.gson.stream.JsonReader(body.charStream());
        if(type!=null) data=gson.fromJson(jsonReader,type);
        if(clazz!=null) data=gson.fromJson(jsonReader,type);

        return data;
    }
}
