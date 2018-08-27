package servicetest.example.com.dbhomework.Search;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import servicetest.example.com.dbhomework.Datas.LoginInfo;
import servicetest.example.com.dbhomework.Datas.adPlan;
import servicetest.example.com.dbhomework.R;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = this.getIntent();
        //传递LoginInfo信息
        LoginInfo info=(LoginInfo) intent.getSerializableExtra("data");
        if(info!=null){
        List<adPlan> plans=info.getAdmitPlans();
        List<String> uv=new ArrayList<>();
        List<Integer> ad=new ArrayList<>();
        for(int i=0;i<plans.size();i++) {
            uv.add(plans.get(i).university);
            ad.add(plans.get(i).admitId);
            System.out.println(plans.get(i).university);
        }
        viewPager = (ViewPager) findViewById(R.id.myviewpager);
        tabLayout = (TabLayout) findViewById(R.id.mytab);

       list = new ArrayList<>();
        list.add(new tab1_fragment(plans));
        list.add(new stu_frament(uv,ad));
       MyFragmentAdapter adapter=new MyFragmentAdapter(getSupportFragmentManager(),list);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
}}}
