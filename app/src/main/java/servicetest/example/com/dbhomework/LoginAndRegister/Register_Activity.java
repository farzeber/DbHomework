package servicetest.example.com.dbhomework.LoginAndRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import servicetest.example.com.dbhomework.Datas.JsonCallback;
import servicetest.example.com.dbhomework.Datas.LoginInfo;
import servicetest.example.com.dbhomework.Datas.ResInfo;
import servicetest.example.com.dbhomework.Datas.resData;
import servicetest.example.com.dbhomework.R;
import servicetest.example.com.dbhomework.http;

public class Register_Activity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private void initDialog(){
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("正在注册");
        progressDialog.setMessage("请稍等");
        progressDialog.setCancelable(false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button register=(Button) findViewById(R.id.email_register_in_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attempToRegister();
            }
        });
        initDialog();
    }

    private void attempToRegister(){
        EditText edit1=(EditText) findViewById(R.id.register_user);
        EditText edit2=(EditText) findViewById(R.id.register_name);
        EditText edit3=(EditText) findViewById(R.id.register_pass);


        final String user=edit1.getText().toString();
      final  String name=edit2.getText().toString();
     final   String pass=edit3.getText().toString();

        edit1.setError(null);
        edit2.setError(null);
        edit3.setError(null);
        if(TextUtils.isEmpty(user)){
            edit1.setError("用户名不可为空");
            edit1.requestFocus();
        }

        if(!TextUtils.isEmpty(user)&&TextUtils.isEmpty(name)){
            edit2.setError("姓名不可为空");
            edit2.requestFocus();
        }

        if(!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(name)&&TextUtils.isEmpty(pass)){
            edit3.setError("密码不可为空");
            edit3.requestFocus();
        }
        Type listype=new TypeToken<resData<ResInfo>>(){}.getType();
        JsonCallback jsonCallback=new JsonCallback<resData<ResInfo>>(listype) {
            @Override
            public void onStart(Request<resData<ResInfo>, ? extends Request> request) {
                super.onStart(request);
                progressDialog.show();
            }

            @Override
            public void onError(Response<resData<ResInfo>> response) {
                super.onError(response);
                progressDialog.dismiss();
                Toast.makeText(Register_Activity.this,"注册失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(Response<resData<ResInfo>> response) {
                progressDialog.dismiss();
                Toast.makeText(Register_Activity.this,"注册成功",Toast.LENGTH_SHORT).show();
                resData<ResInfo> res=response.body();
                ResInfo info=res.data;
                Intent intent=new Intent();
                intent.putExtra("user",user);
                intent.putExtra("pass",pass);
                setResult(RESULT_OK,intent);
                finish();
            }
        };
        JSONObject object=new JSONObject();
        try {
            object.put("user",user);
            object.put("pass",pass);
            object.put("name",name);
        }catch (JSONException e){
            e.printStackTrace();
        }

        http.myhp(http.type_register,jsonCallback,object);
    }
}
