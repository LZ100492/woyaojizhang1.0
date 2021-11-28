package com.jnu.myhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.jnu.myhomework.classpack.ZhichuDataBank;

public class MainActivity  extends AppCompatActivity {

    private Button btn,btn2,btn3,btn4;
    private ZhichuDataBank dataBankSuiLi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //添加按钮响应
        btn = (Button)findViewById(R.id.add);//按钮“添加事件”，id为add
        btn.setOnClickListener(new btnclock());
        btn2 = (Button)findViewById(R.id.shouru);//按钮“收礼界面”，id为shouli
        btn2.setOnClickListener(new btnclock2());
        btn3 = (Button)findViewById(R.id.zhichu);
        btn3.setOnClickListener(new btnclock3());
        btn4= (Button)findViewById(R.id.add2);
        btn4.setOnClickListener(new btnclock());

    }




    /*添加事件按钮*/
    class btnclock implements View.OnClickListener//监听接口1，实现主页跳转到数据添加界面
    {
        public void onClick(View v)
        {
            Intent intent_AddData = new Intent(MainActivity.this, AddZhichuData.class);
            startActivity(intent_AddData);
       }
    }

    class btnclock4 implements View.OnClickListener//监听接口1，实现主页跳转到数据添加界面
    {
        public void onClick(View v)
        {
            Intent intent_AddData = new Intent(MainActivity.this, AddShouruData.class);
            startActivity(intent_AddData);
        }
    }
/*跳转收礼界面*/
    class btnclock2 implements View.OnClickListener//监听接口2，实现主页跳转到收礼信息界面
    {
        public void onClick(View v)
        {
            Intent intent_ShouLiList = new Intent(MainActivity.this, ShouruList.class);
            startActivity(intent_ShouLiList);
        }
    }

    /*跳转随礼界面*/
    class btnclock3 implements View.OnClickListener//监听接口2，实现主页跳转到收礼信息界面
    {
        public void onClick(View v)
        {
            Intent intent_SuiLiList = new Intent(MainActivity.this, ZhichuiList.class);
            startActivity(intent_SuiLiList);
        }
    }
}

