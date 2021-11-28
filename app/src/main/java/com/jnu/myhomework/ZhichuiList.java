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

    private static final int REQUEST_CODE_ADD_SUILI = 100;
    private static final int REQUEST_CODE_UPDATE_SUILI = 101;

    private ZhichuDataBank dataBank;
    private SuiLiAdapter adapter;

    private class SuiLiAdapter extends ArrayAdapter<ZhichuData> {
        private int resourceId;

        public SuiLiAdapter(@NonNull Context context, int resource, @NonNull List<ZhichuData> objects) {
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
        ZhichuList = (ListView)findViewById(R.id.ZhichuList);//获取控件“ShouLiList”

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

            case REQUEST_CODE_ADD_SUILI:
                if (resultCode == RESULT_OK) {
                    String suili_time = data.getStringExtra("suili_time");
                    String suili_name = data.getStringExtra("suili_name");
                    String suili_money = data.getStringExtra("suili_money");
                    String suili_reason = data.getStringExtra("suili_reason");
                    int position = data.getIntExtra("suili_position", 1);
                    dataBank.getSuiLiData().add(position, new ZhichuData(R.drawable.zhichu, suili_time, suili_name, suili_money,suili_reason));
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
                startActivityForResult(intent, REQUEST_CODE_ADD_SUILI);

                break;

            case CONTEXT_MENU_ITEM_DELETE:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("询问");
                builder.setMessage("确定删除\""+dataBank.getSuiLiData().get(position).getName() + "\"这条记录吗？");
                builder.setCancelable(true);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBank.getSuiLiData().remove(position);
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
        if(0==dataBank.getSuiLiData().size())
        {
            dataBank.getSuiLiData().add(new ZhichuData(R.drawable.zhichu,"Time","Name","Money","Reason"));
            dataBank.getSuiLiData().add(new ZhichuData(R.drawable.zhichu,"1111","zzz","100","wu"));
        }
    }

    private void initView() {
        adapter = new SuiLiAdapter(this, R.layout.item_zhichu,  dataBank.getSuiLiData());

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
