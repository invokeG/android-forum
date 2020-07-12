package com.example.myassignment_gzh;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView mylist;
    int[] array ={0,0,0,0,0};
    List<ItemBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        setTitle("品牌讨论");
        //List<ItemBean> list = new ArrayList<>();
        list.add(new ItemBean(R.mipmap.nike,"Nike","NIKE公司总部位于美国俄勒冈州波特兰市。公司生产的体育用品包罗万象，例如服装，鞋类，运动器材等。",R.color.white));
        list.add(new ItemBean(R.mipmap.adidas,"Adidas","adidas（阿迪达斯）创办于1949年，是德国运动用品制造商阿迪达斯AG成员公司。",R.color.white));
        list.add(new ItemBean(R.mipmap.vers,"Converse","Converse诞生于1908年。创办以来Converse坚持品牌的独立性设计，不追随。",R.color.white));
        list.add(new ItemBean(R.mipmap.nb,"New Balance","New Balance，1906年William J. Riley先生在美国马拉松之城波士顿成立的品牌，在美国及许多国家被誉为“总统慢跑鞋”，“慢跑鞋之王”。",R.color.white));
        list.add(new ItemBean(R.mipmap.anta,"Anta","安踏体育用品有限公司 (港交所：2020)，简称安踏体育、安踏，是中国领先的体育用品企业，主要从事设计、开发、制造和行销安踏品牌的体育用品，包括运动鞋、服装及配饰。",R.color.white));
        MyListAdapter myListAdapter = new MyListAdapter(this,list);
        mylist = (ListView)findViewById(R.id.listview);
        mylist.setAdapter(myListAdapter);
    }

}

