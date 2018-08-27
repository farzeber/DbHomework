package servicetest.example.com.dbhomework;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import servicetest.example.com.dbhomework.LoginAndRegister.LoginActivity;

public class WelcomeAc extends AppCompatActivity {
    private TextView mCountDownTextView;
    private MyCount mCountDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        mCountDownTextView = (TextView) findViewById(R.id.start_skip_count_down);
        mCountDownTextView.setText("4s 跳过");
        //创建倒计时类
        mCountDownTimer = new MyCount(4000, 1000);
        mCountDownTimer.setTview(mCountDownTextView);
        mCountDownTimer.start();
        final Handler tmpHandler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(WelcomeAc.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
        tmpHandler.postDelayed(runnable, 3000);

        mCountDownTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tmpHandler.removeCallbacks(runnable);
                Intent intent=new Intent(WelcomeAc.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        super.onDestroy();

    }
}
