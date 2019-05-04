package com.example.dell.litepaltest;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;


public class User_page_classify_display extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private String[] groupString = {"经济", "政治／军事", "文化", "计算机／网络"};
    private String[][] childString = {
            {"经济数学", "区域经济", "财政税收", "贸易政策"},
            {"政治理论", "中国政治", "世界政治 ", "军事"},
            {"文化理论", "世界文化", "中国文化", "文化评述 "},
            {"计算机理论", "操作系统", "数据库", "程序设计"}
    };
    private String fcname;
    private String scname;
    private String uname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page_classify_display);

        //接收用户名
        Intent intent = getIntent();
        uname = intent.getStringExtra("uname");
        //二级菜单
        expandableListView = findViewById(R.id.expend_list);
        expandableListView.setAdapter(new MyExtendableListViewAdapter());
        //设置分组的监听
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        //控制他只能打开一个组
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int count = new MyExtendableListViewAdapter().getGroupCount();
                for(int i = 0;i < count;i++){
                    if (i!=groupPosition){
                        expandableListView.collapseGroup(i);
                    }
                }
            }
        });

        //设置子项布局监听
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                fcname = groupString[groupPosition];
                scname = childString[groupPosition][childPosition];
                Intent intent = new Intent(User_page_classify_display.this,User_page_bookdisplay.class);
                intent.putExtra("sbname","null_list");
                intent.putExtra("fcname",fcname);
                intent.putExtra("scname",scname);
                intent.putExtra("uname",uname);
                startActivityForResult(intent,1);
                return true;

            }
        });




    }
}
