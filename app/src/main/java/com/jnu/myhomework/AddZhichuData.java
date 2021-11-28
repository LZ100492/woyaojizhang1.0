package com.jnu.myhomework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddZhichuData extends Activity {
    private int position;
    private String zhichuName,zhichuTime,zhichuMoney,zhichuReason;
    Button btn,btn2;
    EditText editTime,editName,editMoney,editReason;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addzhichudata);

        btn = (Button)findViewById(R.id.queding2);//按钮“确定”，id为queding
        btn.setOnClickListener(new btnclock());
        btn2 = (Button)findViewById(R.id.quxiao2);//按钮“取消”
        btn2.setOnClickListener(new btnclock2());

        editTime=(EditText)findViewById(R.id.editText_zhichu_time);
        editName=(EditText)findViewById(R.id.editText_zhichu_name);
        editMoney=(EditText)findViewById(R.id.editText_zhichu_money);
        editReason=(EditText)findViewById(R.id.editText_zhichu_reason);

        position=getIntent().getIntExtra("position",1);
        zhichuName=getIntent().getStringExtra("zhichu_name");
        zhichuTime=getIntent().getStringExtra("zhichu_time");
        zhichuMoney=getIntent().getStringExtra("zhichu_money");
        zhichuReason=getIntent().getStringExtra("zhichu_reason");

    }

    /*传输数据A端设置*/
    class btnclock implements View.OnClickListener//监听接口，实现数据添加界面跳转到收礼信息界面
    {
        public void onClick(View v)
        {

            Intent intent = new Intent();
            intent.putExtra("zhichu_time",editTime.getText().toString());
            intent.putExtra("zhichu_name",editName.getText().toString());
            intent.putExtra("zhichu_money",editMoney.getText().toString());
            intent.putExtra("zhichu_reason",editReason.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
            intent.setClass(AddZhichuData.this, ShouruList.class);
            startActivity(intent);
        }
    }

    class btnclock2 implements View.OnClickListener//监听接口，实现数据添加界面跳转到主界面
    {
        public void onClick(View v)
        {

            Intent intent = new Intent();
            intent.setClass(AddZhichuData.this, ZhichuiList.class);
            startActivity(intent);
        }
    }
}