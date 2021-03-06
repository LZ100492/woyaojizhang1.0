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

import com.jnu.myhomework.classpack.ZhichuData;
import com.jnu.myhomework.classpack.ZhichuDataBank;

import java.util.List;

public class ZhichuiList extends Activity {
    ListView ZhichuList;//控件声明
    Button btn2;

    private static final int CONTEXT_MENU_ITEM_NEW = 1;
    private static final int CONTEXT_MENU_ITEM_DELETE = CONTEXT_MENU_ITEM_NEW+1;

    private static final int REQUEST_CODE_ADD_ZHICHU = 100;
    private static final int REQUEST_CODE_UPDATE_ZHICHU = 101;

    private ZhichuDataBank dataBank;
    private ZhichuAdapter adapter;

    private class ZhichuAdapter extends ArrayAdapter<ZhichuData> {
        private int resourceId;

        public ZhichuAdapter(@NonNull Context context, int resource, @NonNull List<ZhichuData> objects) {
            super(context, resource, objects);
            this.resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ZhichuData zhichuData = getItem(position);//获取当前项的实例
            View view;
            if(null==convertView)
                view = LayoutInflater.from(getContext()).inflate(this.resourceId, parent, false);
            else
                view=convertView;
            ((ImageView) view.findViewById(R.id.imageView2)).setImageResource(zhichuData.getZhichuImageId());
            ((TextView) view.findViewById(R.id.textview_zhichu_time)).setText(zhichuData.getTime());
            ((TextView) view.findViewById(R.id.textview_zhichu_name)).setText(zhichuData.getName());
            ((TextView) view.findViewById(R.id.textview_zhichu_money)).setText(zhichuData.getMoney());
            ((TextView) view.findViewById(R.id.textview_zhichu_reason)).setText(zhichuData.getReason());
            return view;
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhichulist);

        /*返回日历界面*/
        btn2 = (Button)findViewById(R.id.zhuye2);
        btn2.setOnClickListener(new btnclock2());

        /*Listview*/
        ZhichuList = (ListView)findViewById(R.id.ZhichuList);

        initData();
        initView();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v==findViewById(R.id.ZhichuList)) {
            menu.setHeaderTitle("操作");
            menu.add(1, CONTEXT_MENU_ITEM_NEW, 1, "新增");
            menu.add(1, CONTEXT_MENU_ITEM_DELETE, 1, "删除");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {

            case REQUEST_CODE_ADD_ZHICHU:
                if (resultCode == RESULT_OK) {
                    String zhichu_time = data.getStringExtra("zhichu_time");
                    String zhichu_name = data.getStringExtra("zhichu_name");
                    String zhichu_money = data.getStringExtra("zhichu_money");
                    String zhichu_reason = data.getStringExtra("zhichu_reason");
                    int position = data.getIntExtra("zhichu_position", 1);
                    dataBank.getZhichuData().add(position, new ZhichuData(R.drawable.zhichu, zhichu_time, zhichu_name, zhichu_money,zhichu_reason));
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
                intent = new Intent(ZhichuiList.this, AddZhichuData.class);
                intent.putExtra("position",position);
                startActivityForResult(intent, REQUEST_CODE_ADD_ZHICHU);

                break;

            case CONTEXT_MENU_ITEM_DELETE:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("询问");
                builder.setMessage("确定删除\""+dataBank.getZhichuData().get(position).getName() + "\"这条记录吗？");
                builder.setCancelable(true);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBank.getZhichuData().remove(position);
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
        dataBank=new ZhichuDataBank(this);
        dataBank.Load();
        if(0==dataBank.getZhichuData().size())
        {
            dataBank.getZhichuData().add(new ZhichuData(R.drawable.zhichu,"Time","Name","Money","Reason"));
        }
    }

    private void initView() {
        adapter = new ZhichuAdapter(this, R.layout.item_zhichu,  dataBank.getZhichuData());

        ListView listViewSuili=findViewById(R.id.ZhichuList);
        listViewSuili.setAdapter(adapter);

        this.registerForContextMenu(ZhichuList);
    }


    class btnclock2 implements OnClickListener
    {
        public void onClick(View v)
        {
            Intent intent2 = new Intent();
            intent2.setClass(ZhichuiList.this,MainActivity.class);
            startActivity(intent2);
        }
    }
}
