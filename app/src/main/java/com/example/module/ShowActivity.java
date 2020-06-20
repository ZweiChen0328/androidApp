package com.example.module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ShowActivity extends AppCompatActivity {
    TextView idNum;
    TextView nameStr;
    TextView timeStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intent = getIntent();
        idNum = (TextView)findViewById(R.id.idNum);
        nameStr = (TextView)findViewById(R.id.name);
        timeStr = (TextView)findViewById(R.id.tTime);
        String cTime = intent.getStringExtra("cTime");
        String id = intent.getStringExtra("id");
        idNum.setText(id);
        String name = intent.getStringExtra("name");
        nameStr.setText(name);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        Calendar nowTime = Calendar.getInstance();
        try {
            c.setTime(df.parse(cTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int[] deadline = new int[6],now =new int[6];

        deadline[0] = c.get(Calendar.YEAR);
        deadline[1] = c.get(Calendar.MONTH)+1;
        deadline[2] = c.get(Calendar.DAY_OF_MONTH);
        deadline[3] = c.get(Calendar.HOUR_OF_DAY);
        deadline[4] = c.get(Calendar.MINUTE);
        deadline[5] = c.get(Calendar.SECOND);

        now[0] = nowTime.get(Calendar.YEAR);
        now[1] = nowTime.get(Calendar.MONTH)+1;
        now[2] = nowTime.get(Calendar.DAY_OF_MONTH);
        now[3] = nowTime.get(Calendar.HOUR_OF_DAY);
        now[4] = nowTime.get(Calendar.MINUTE);
        now[5] = nowTime.get(Calendar.SECOND);

        String state = "";

        for(int i=0;i<6;i++){
            if(now[i]>deadline[i]){
                state="已過期";
                break;
            }else if (now[i]<deadline[i]){
                int temp = deadline[i] - now[i];
                state="剩下" + temp + returnTimeStr(i);
                break;
            }
        }
        timeStr.setText(state);
    }

    protected String returnTimeStr(int i) {
        String str = "";
        switch (i) {
            case 0:
                str = "年";
                break;
            case 1:
                str = "月";
                break;
            case 2:
                str ="日";
                break;
            case 3:
                str ="小時";
                break;
            case 4:
                str ="分鍾";
                break;
            case 5:
                str ="秒(快點領啊!!!跑起來!!)";
                break;
            default:
                str="錯誤";
                break;
        }return str;
    }
}
