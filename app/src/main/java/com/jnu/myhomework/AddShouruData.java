package com.jnu.myhomework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddShouruData extends Activity {
    private int position;
    private String shouruName,shouruTime,shouruMoney,shouruReason;
    Button btn,btn2;
    EditText editTime,editName,editMoney,editReason;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshourudata);

        btn = (Button)findViewById(R.id.queding);//按钮“确定”，id为queding
        btn.setOnClickListener(new btnclock());
        btn2 = (Button)findViewById(R.id.quxiao);//按钮“取消”
        btn2.setOnClickListener(new btnclock2());

        editTime=(EditText)findViewById(R.id.editText1);
        editName=(EditText)findViewById(R.id.editText2);
        editMoney=(EditText)findViewById(R.id.editText3);
        editReason=(EditText)findViewById(R.id.editText4);

        position=getIntent().getIntExtra("position",0);
        shouruName=getIntent().getStringExtra("shouru_name");
        shouruTime=getIntent().getStringExtra("shouru_time");
        shouruMoney=getIntent().getStringExtra("shouru_money");
        shouruReason=getIntent().getStringExtra("shouru_reason");

    }

    /*传输数据A端设置*/
    class btnclock implements View.OnClickListener//监听接口，实现数据添加界面跳转到收入信息界面
    {
        public void onClick(View v)
        {

            Intent intent = new Intent();
            intent.putExtra("shouru_time",editTime.getText().toString());
            intent.putExtra("shouru_name",editName.getText().toString());
            intent.putExtra("shouru_money",editMoney.getText().toString());
            intent.putExtra("shouru_reason",editReason.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
            intent.setClass(AddShouruData.this, ShouruList.class);
            startActivity(intent);
        }
    }

    class btnclock2 implements View.OnClickListener//监听接口，实现数据添加界面跳转到主界面
    {
        public void onClick(View v)
        {

            Intent intent = new Intent();
            intent.setClass(AddShouruData.this, ShouruList.class);
            startActivity(intent);
        }
    }
}
