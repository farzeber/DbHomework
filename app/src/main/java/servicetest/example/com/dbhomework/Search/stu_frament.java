package servicetest.example.com.dbhomework.Search;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.table.MapTableData;
import com.bin.david.form.data.table.TableData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import servicetest.example.com.dbhomework.Datas.JsonCallback;
import servicetest.example.com.dbhomework.Datas.LoginInfo;
import servicetest.example.com.dbhomework.Datas.StuSerch;
import servicetest.example.com.dbhomework.Datas.adPlan;
import servicetest.example.com.dbhomework.Datas.resData;
import servicetest.example.com.dbhomework.LoginAndRegister.LoginActivity;
import servicetest.example.com.dbhomework.R;
import servicetest.example.com.dbhomework.http;

/**
 * Created by mick2017 on 2018/6/11.
 */

public class stu_frament extends Fragment {
    private LinearLayout mylayout;
  private   SmartTable table1;
    private SmartTable table2;
    private SmartTable table3;
    private EditText stu_number;
    private Button stu_search;
    private Spinner select_uv;
    private Button up_info;
    private StuSerch info;
    private List<String> uv;
    private List<Integer> ad;
    private ProgressDialog progressDialog;
    private ProgressDialog progressDialog1;
    private int wt_ad;
    private String wt_uv;
    private void initDialog(){
        progressDialog1=new ProgressDialog(getActivity());
        progressDialog1.setTitle("正在查询");
        progressDialog1.setMessage("Loading...");
        progressDialog1.setCancelable(false);
    }
    public stu_frament() {
    }

    @SuppressLint("ValidFragment")
    public stu_frament(List<String> uv,List<Integer> ad) {
        this.uv = uv;
        this.ad=ad;
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
        View view=inflater.inflate(R.layout.fragment2,container,false);
         table1=(SmartTable) view.findViewById(R.id.peo_info);
        table2=(SmartTable) view.findViewById(R.id.stu_info);
        table3=(SmartTable)view.findViewById(R.id.admit_info);
        stu_number=(EditText) view.findViewById(R.id.stu_number);
        select_uv=(Spinner) view.findViewById(R.id.select_university);
        up_info=(Button) view.findViewById(R.id.up_info);
        mylayout=(LinearLayout) view.findViewById(R.id.mylayout);
        stu_search=(Button) view.findViewById(R.id.stu_search);
        stu_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attempTosearch();
            }
        });
initDialog();
        return view;
    }

    private void attempTosearch(){
        table1.setVisibility(View.INVISIBLE);
        table2.setVisibility(View.INVISIBLE);
        table3.setVisibility(View.INVISIBLE);
        //关闭投递
        mylayout.setVisibility(View.INVISIBLE);
        //关闭软盘
        InputMethodManager imm = ( InputMethodManager ) getActivity().getSystemService( Context.INPUT_METHOD_SERVICE );
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus()
                        .getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        stu_number.clearFocus();
        String adId=stu_number.getText().toString();
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("admissionNum",adId);
        }catch (JSONException e){
            e.printStackTrace();
        }
        Type listype=new TypeToken<resData<StuSerch>>(){}.getType();
        JsonCallback<resData<StuSerch>> jsonCallback=new JsonCallback<resData<StuSerch>>(listype) {
            @Override
            public void onStart(Request request) {
                super.onStart(request);

             progressDialog1.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                progressDialog1.dismiss();
                stu_number.setText("");
                stu_number.setError("准考证不存在");
                stu_number.requestFocus();
                Toast.makeText(getActivity(),"查找失败",Toast.LENGTH_SHORT).show();

            }

            /**
             * 对返回数据进行操作的回调， UI线程
             *
             * @param response
             */
            @Override
            public void onSuccess(Response<resData<StuSerch>> response) {
                progressDialog1.dismiss();
                DisplayMetrics dm =getActivity().getResources().getDisplayMetrics();
                int screenWith=dm.widthPixels;
                resData myres=(resData<StuSerch>) response.body();
                info=(StuSerch) myres.data;
                if(info==null)
                    Toast.makeText(getActivity(),"没有信息",Toast.LENGTH_SHORT).show();
                if(info!=null){
                    final StuSerch.Stu stu=( StuSerch.Stu)info.student;
                    System.out.println(stu.admissionNum);
                    StuSerch.mark mark=info.scores;
                    if(mark==null){
                        mark=(StuSerch.mark)new StuSerch.mark();
                    mark.admissionNum=stu.admissionNum;}
                    adPlan school=info.admitPlan;

                    if(stu!=null){
                        Toast.makeText(getActivity(),"查找成功",Toast.LENGTH_SHORT).show();
                        //个人信息
                        List<StuSerch.Stu> l1=new ArrayList<>();
                         l1.add(stu);
                        final Column<String> Column1 = new Column<>("姓名", "name");
                        final Column<String> Column2 = new Column<>("性别", "sex");
                        final Column<Integer> Column3 = new Column<>("出生日期", "burnDate");
                        table1.setVisibility(View.VISIBLE);
                        table1.setTableData(new TableData<StuSerch.Stu>("个人信息",l1,Column1,Column2,Column3));
                        table1.getConfig().setShowXSequence(false);
                        table1.getConfig().setFixedXSequence(true);

                        table1.getConfig().setMinTableWidth(screenWith);

                        //考生信息
                        List<StuSerch.mark> l2=new ArrayList<>();
                        if(school==null)
                            mark.info="未投递";
                        else if(school!=null)
                            mark.info="已投递";
                        l2.add(mark);
                        final Column<String> peo_Column1 = new Column<>("准考证", "admissionNum");
                        final Column<Integer> peo_Column2 = new Column<>("分数", "score");
                        final Column<String> peo_Column3 = new Column<>("投递信息", "info");
                        table2.setVisibility(View.VISIBLE);
                        table2.setTableData(new TableData<StuSerch.mark>("考生信息",l2,peo_Column1,peo_Column2,peo_Column3));

                        table2.getConfig().setShowXSequence(false);
                        table2.getConfig().setFixedXSequence(true);
                        table2.getConfig().setMinTableWidth(screenWith);

                        //被录取显示学校信息
                        if(school!=null&&school.admitId!=0){
                            table3.setVisibility(View.VISIBLE);
                            List<adPlan> l3=new ArrayList<>();
                            l3.add(school);
                            final Column<Integer> sch_Column1 = new Column<>("学校编号", "admitId");
                            final Column<String> sch_Column2 = new Column<>("学校", "university");
                            final Column<String> sch_Column3 = new Column<>("省份", "province");
                            table3.setTableData(new TableData<adPlan>("投递学校",l3,sch_Column1,sch_Column2,sch_Column3));
                            table3.getConfig().setShowXSequence(false);
                            table3.getConfig().setFixedXSequence(true);
                            table3.getConfig().setMinTableWidth(screenWith);
                        }
                        else {
                            mylayout.setVisibility(View.VISIBLE);

                        //进行投档
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_item, (String [])uv.toArray(new String[uv.size()]));
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        select_uv.setAdapter(adapter);
                        select_uv.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    wt_ad=ad.get(i);
                                    wt_uv=uv.get(i);

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> arg0) {
                                // TODO Auto-generated method stub
                            }
                        });
                        //提交信息
                        up_info.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                System.out.println(wt_ad);
                                System.out.println(wt_uv);
                                Type listype=new TypeToken<resData>(){}.getType();
                                JsonCallback jsonCallback1=new JsonCallback<resData>(listype) {
                                   @Override
                                   public void onStart(Request<resData, ? extends Request> request) {
                                       super.onStart(request);
                                           progressDialog=new ProgressDialog(getActivity());
                                           progressDialog.setTitle("正在投递学校");
                                           progressDialog.setMessage("准考证:"+stu.admissionNum+"\n目标大学："+wt_uv);
                                           progressDialog.setCancelable(false);
                                           progressDialog.show();
                                   }

                                   @Override
                                   public void onError(Response<resData> response) {
                                       super.onError(response);
                                       progressDialog.dismiss();
                                       Toast.makeText(getActivity(),"投递失败",Toast.LENGTH_SHORT).show();
                                   }

                                   @Override
                                   public void onSuccess(Response<resData> response) {
                                      progressDialog.dismiss();
                                       Toast.makeText(getActivity(),"投递成功",Toast.LENGTH_SHORT).show();
                                       stu_number.setText(String.valueOf(stu.admissionNum));

                                       attempTosearch();
                                   }
                               };
                                JSONObject object=new JSONObject();
                                try {

                                    object.put("admitId",wt_ad);
                                    object.put("admissionNum",stu.admissionNum);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                                http.myhp(http.type_upd,jsonCallback1,object);
                            }
                        });}
                    }
                }

            }
        };

        http.myhp(http.type_stu,jsonCallback,jsonObject);
    }
}
