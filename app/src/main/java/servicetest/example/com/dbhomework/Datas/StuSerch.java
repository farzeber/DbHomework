package servicetest.example.com.dbhomework.Datas;

import java.io.Serializable;

/**
 * Created by mick2017 on 2018/6/11.
 */

public class StuSerch implements Serializable {
    public class Stu{
        public String admissionNum;
        public String name;
        public String sex;
        public int burnDate;
        public int admitId;
     }
    public static class mark{
        public String admissionNum;
        public int  score;
        public String info;
    }
     public Stu student;
    public adPlan admitPlan;
    public mark scores;

}
