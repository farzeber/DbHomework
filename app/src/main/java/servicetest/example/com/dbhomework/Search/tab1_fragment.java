package servicetest.example.com.dbhomework.Search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.bg.BaseBackgroundFormat;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.bin.david.form.data.format.bg.ICellBackgroundFormat;
import com.bin.david.form.data.table.TableData;

import java.util.ArrayList;
import java.util.List;

import servicetest.example.com.dbhomework.Datas.adPlan;
import servicetest.example.com.dbhomework.R;

/**
 * Created by mick2017 on 2018/3/17.
 */

public class tab1_fragment extends Fragment {
    private List<adPlan> plans=new ArrayList<>();


    public class UserInfo {

        private int admitId;

        private String university;

        private String province;

        private int stuNums;

        public int getAdmitId() {
            return admitId;
        }

        public void setAdmitId(int admitId) {
            this.admitId = admitId;
        }

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getStuNums() {
            return stuNums;
        }

        public void setStuNums(int stuNums) {
            this.stuNums = stuNums;
        }
    }


    public tab1_fragment() {
    }

    @SuppressLint("ValidFragment")
    public tab1_fragment(List<adPlan> plans) {
        this.plans=plans;
    }


    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,container,false);
       com.bin.david.form.core.SmartTable<UserInfo> table=(com.bin.david.form.core.SmartTable<UserInfo>) view.findViewById(R.id.table);
        List<UserInfo> myplans=getString(plans);
        final Column<Integer> Column1 = new Column<>("学校编号", "admitId");
        final Column<String> Column2 = new Column<>("学校", "university");
        final Column<String> Column3 = new Column<>("省份", "province");
        final Column<Integer> Column4 = new Column<>("录取人数", "stuNums");

        table.setTableData(new TableData<UserInfo>("招生计划",myplans,Column1,Column2,Column3,Column4));

       table.setZoom(true,1.5f,1);
        table.getConfig().setFixedYSequence(true);
        table.getConfig().setShowXSequence(false);
        table.getConfig().setFixedXSequence(true);

        DisplayMetrics dm =getActivity().getResources().getDisplayMetrics();
        int screenWith=dm.widthPixels;
        table.getConfig().setMinTableWidth(screenWith);
        return view;
    }
    private List<UserInfo> getString(List<adPlan> plans){

     List<UserInfo> lists=new ArrayList<>();
     for(int i=0;i<plans.size();i++){
         UserInfo userInfo=new UserInfo();
         userInfo.setAdmitId(plans.get(i).admitId);
         userInfo.setProvince(plans.get(i).province);
         userInfo.setStuNums(plans.get(i).stuNums);
         userInfo.setUniversity(plans.get(i).university);
         lists.add(userInfo);
     }
      /*  UserInfo userInfo=new UserInfo();
        userInfo.setAdmitId(1321531);
        userInfo.setProvince("asdfasf");
        userInfo.setStuNums(20);
        userInfo.setUniversity("北京");
        lists.add(userInfo);*/
     return lists;
    }
}
