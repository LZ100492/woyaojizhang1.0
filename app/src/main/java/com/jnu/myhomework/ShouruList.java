package com.jnu.myhomework;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jnu.myhomework.classpack.ShouruData;
import com.jnu.myhomework.classpack.ShouruDataBank;
import com.jnu.myhomework.classpack.ZhichuData;

import java.util.List;

public class ShouruList extends Activity {

    private static final int CONTEXT_MENU_ITEM_NEW = 1;
    private static final int CONTEXT_MENU_ITEM_DELETE = CONTEXT_MENU_ITEM_NEW + 1;

    private static final int REQUEST_CODE_ADD_SHOURU = 100;
    private static final int REQUEST_CODE_UPDATE_SHOURU = 101;

    private ShouruDataBank dataBank;
    private ShouruAdapter adapter;

    ListView ShouruList;//控件声明
    Button btn2;


    private class ShouruAdapter extends ArrayAdapter<ShouruData> {
        private int resourceId;

        public ShouruAdapter(@NonNull Context context, int resource, @NonNull List<ShouruData> objects) {
            super(context, resource, objects);
            this.resourceId = resource;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ShouruData shouruData = getItem(position);//获取当前项的实例
            View view;
            if(null==convertView)
                view = LayoutInflater.from(getContext()).inflate(this.resourceId, parent, false);
            else
                view=convertView;
            ((ImageView) view.findViewById(R.id.listview_shouru)).setImageResource(shouruData.getShouruImageId());
            ((TextView) view.findViewById(R.id.time)).setText(shouruData.getTime());
            ((TextView) view.findViewById(R.id.name)).setText(shouruData.getName());
            ((TextView) view.findViewById(R.id.money)).setText(shouruData.getMoney());
            ((TextView) view.findViewById(R.id.reason)).setText(shouruData.getReason());
            return view;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shourulist);


        /*-----------------返回日历界面----------------*/
        btn2 = (Button) findViewById(R.id.zhuye);

        class btnclock2 implements OnClickListener {
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(ShouruList.this, MainActivity.class);
                startActivity(intent2);
            }
        }
        btn2.setOnClickListener(new btnclock2());

        /*Listview*/
        ShouruList = (ListView) findViewById(R.id.listview_shouru);//获取控件“ShouruList”

        initData();
        initView();
        }//onCreate

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v==findViewById(R.id.listview_shouru)) {
            menu.setHeaderTitle("操作");
            menu.add(1, CONTEXT_MENU_ITEM_NEW, 1, "新增");
            menu.add(1, CONTEXT_MENU_ITEM_DELETE, 1, "删除");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {

            case REQUEST_CODE_ADD_SHOURU:
                if (resultCode == RESULT_OK) {
                    String shouru_name = data.getStringExtra("shouru_name");
                    String shouru_time = data.getStringExtra("shouru_time");
                    String shouru_money = data.getStringExtra("shouru_money");
                    String shouru_reason = data.getStringExtra("shouru_reason");
                    int position=data.getIntExtra("shouru_position",1);
                    dataBank.getShouruData().add(position,new ShouruData(R.drawable.zhichu,shouru_time,shouru_name,shouru_money,shouru_reason));
                    dataBank.Save();
                    adapter.notifyDataSetChanged();
                }
                break;

            default:

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Intent intent;
        final int position=menuInfo.position;
        switch(item.getItemId())
        {
            case CONTEXT_MENU_ITEM_NEW:
                intent = new Intent(ShouruList.this, AddShouruData.class);
                intent.putExtra("position",position);
                startActivityForResult(intent, REQUEST_CODE_ADD_SHOURU);

                break;

            case CONTEXT_MENU_ITEM_DELETE:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("询问");
                builder.setMessage("确定删除\""+dataBank.getShouruData().get(position).getName() + "\"这条记录吗？");
                builder.setCancelable(true);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBank.getShouruData().remove(position);
                        dataBank.Save();
                        adapter.notifyDataSetChanged();
                    }
                });  //正面的按钮（肯定）
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }); //反面的按钮（否定)
                builder.create().show();


                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

        private void initData()
        {
            dataBank=new ShouruDataBank(this);
            dataBank.Load();
            if(0==dataBank.getShouruData().size())
            {
                dataBank.getShouruData().add(new ShouruData(R.drawable.zhichu,"Time","Reason","Money","Detail"));
                dataBank.getShouruData().add(new ShouruData(R.drawable.zhichu,"1213","  Food","   105","    None"));
                dataBank.getShouruData().add(new ShouruData(R.drawable.zhichu,"1214","  Cash","  6000","    None"));
            }
        }

        private void initView() {
            adapter = new ShouruAdapter(this, R.layout.item_shouru,  dataBank.getShouruData());

            ListView listViewShouli=findViewById(R.id.listview_shouru);
            listViewShouli.setAdapter(adapter);

            this.registerForContextMenu(ShouruList);
        }
}

