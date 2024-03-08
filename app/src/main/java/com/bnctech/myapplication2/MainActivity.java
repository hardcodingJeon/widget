package com.bnctech.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //3
    private ProgressDialog pd; // 프로그레스바 선언


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

//        showProgress("");
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);


        Log.e("tv1 글자 크기",tv1.getPaint().measureText("Hello World!")+"");
        Log.e("tv2 글자 크기",tv2.getPaint().measureText("Hello World!")+"");

    }

    // 프로그레스 다이얼로그 보이기
    public void showProgress(String msg) {
        if (pd == null) {
            // 객체를 1회만 생성한다.
            pd = new ProgressDialog(this); // 생성한다.
            pd.setCancelable(false);
            // 백키로 닫는 기능을 제거한다.
        }
        pd.setMessage(msg); // 원하는 메시지를 세팅한다.
        pd.show(); // 화면에 띠워라
    }

    // 프로그레스 다이얼로그 숨기기
    public void hideProgress() {
        if (pd != null && pd.isShowing()) { // 닫는다 : 객체가 존재하고, 보일때만
            pd.dismiss();
        }
    }

}