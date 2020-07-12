package com.example.myassignment_gzh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class DrawerLayoutUsing extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    ArrayList<String> menuList;
    private ArrayAdapter<String> adapter;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mToggle;
    private FragmentManager fragmentManager;
    private ListView mylist;
    private LinearLayout ll_drawer;
    private int currentFragmentId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("讨论专区");
//        设置菜单内容
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.lv_drawer);
        menuList = new ArrayList<>();
        menuList.add("热门帖子");
        menuList.add("品牌讨论");
        menuList.add("我要发贴");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuList);
        mDrawerList.setAdapter(adapter);
        ll_drawer = (LinearLayout) findViewById(R.id.ll_drawer);
//        设置点击事件
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switchFragement(position);
            }
        });
//        设置toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
//        设置左上标
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToggle.syncState();
        mDrawerLayout.addDrawerListener(mToggle);
//        设置默认fragment
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.ll_content,new Fragment1()).commit();
    }

//switchFragment方法切换界面
    private void switchFragement(int fragmentId) {
        mDrawerLayout.closeDrawer(ll_drawer);
//        if(currentFragmentId==fragmentId&&fragmentId!=2){
//            return;
//        }
//        currentFragmentId=fragmentId;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (fragmentId){
            case 0:
                Intent intentFragment1 = new Intent(this,BannerActivity.class);
                startActivity(intentFragment1);
                fragmentTransaction.replace(R.id.ll_content,new Fragment1());
                toolbar.setTitle("讨论专区");
                break;
            case 1:
                Intent intentFragment2 = new Intent(this,ListViewActivity.class);
                startActivity(intentFragment2);
                fragmentTransaction.replace(R.id.ll_content, new Fragment1());
                toolbar.setTitle("讨论专区");
                break;
            case 2:

                Intent intentFragment3 = new Intent(this,AddArticles.class);
                startActivity(intentFragment3);
                fragmentTransaction.replace(R.id.ll_content, new Fragment1());
                toolbar.setTitle("讨论专区");
                break;
        }
        fragmentTransaction.commit();

    }




//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        if(i==0){
//            Intent intentAddArticle = new Intent(this,AddArticles.class);
//            startActivity(intentAddArticle);
//        }

//    }
}
