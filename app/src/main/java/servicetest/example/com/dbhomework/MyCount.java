package servicetest.example.com.dbhomework;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by mick2017 on 2018/6/10.
 */

public class MyCount extends CountDownTimer {
    TextView mytext;
    public MyCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public void setTview(TextView tx){
        mytext=tx;
    }

    @Override
    public void onTick(long l) {
        mytext.setText( l/ 1000 + "s 跳过");
    }

    @Override
    public void onFinish() {
        mytext.setText("0s 跳过");
    }
}
