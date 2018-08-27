package servicetest.example.com.dbhomework.Datas;

import java.io.Serializable;

public class resData<T> implements Serializable {
        public int code;
        public String msg;
        public T data;
    }