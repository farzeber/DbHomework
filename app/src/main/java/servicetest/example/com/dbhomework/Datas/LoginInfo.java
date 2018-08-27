package servicetest.example.com.dbhomework.Datas;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mick2017 on 2018/6/11.
 */

public class LoginInfo implements Serializable {
 public ResInfo manager;
  public List<adPlan> admitPlans;

  public ResInfo getManager() {
    return manager;
  }

  public void setManager(ResInfo manager) {
    this.manager = manager;
  }

  public List<adPlan> getAdmitPlans() {
    return admitPlans;
  }

  public void setAdmitPlans(List<adPlan> admitPlans) {
    this.admitPlans = admitPlans;
  }
}
