package com.example.myassignment_gzh;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class BannerActivity extends AppCompatActivity {

    private Banner mbanner;
    private MyImageLoaser myImageLoaser;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;
    private TextView listtitle,listinfo,textView;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        //查询
        Bmob.initialize(this, "77e60f5f6892f690500f840fb4d58cbf");
        BmobQuery<AddArticleData> bmobQuery = new BmobQuery<AddArticleData>();
        bmobQuery.getObject("c3e815d9a9", new QueryListener<AddArticleData>() {
            @Override
            public void done(AddArticleData data, BmobException e) {
                if(e==null){
                    Toast.makeText(BannerActivity.this, "欢迎来到热门发贴专区,"+data.getTextcontent(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(BannerActivity.this, "网络连接失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        textView = findViewById(R.id.textView);
        listview = findViewById(R.id.listview);
        listtitle = findViewById(R.id.listtitle);
        listinfo = findViewById(R.id.listinfo);
        SimpleAdapter adapter = new SimpleAdapter(this,
                getData(),
                R.layout.activity_banner,
                new String[]{"title","info"},
                new int[]{R.id.listtitle,R.id.listinfo});
        listview.setAdapter(adapter);




        setTitle("热门帖子");
        initData();
        initView();
    }

    private void initData(){
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.mipmap.banner1);
        imagePath.add(R.mipmap.banner2);
        imagePath.add(R.mipmap.banner3);
        imageTitle.add("耐克上新");
        imageTitle.add("生活");
        imageTitle.add("阿迪达斯上新");
    }

    private void initView(){
        myImageLoaser = new MyImageLoaser();
        mbanner = findViewById(R.id.banner);
        //设置banner样式
        mbanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mbanner.setImageLoader(myImageLoaser);
        //设置轮播动画效果
        mbanner.setBannerAnimation(com.youth.banner.Transformer.ZoomOutSlide);
        //设置图片文字
        mbanner.setBannerTitles(imageTitle);
        //设置轮播时间间隔
        mbanner.setDelayTime(3500);
        //设置是否为自动轮播
        mbanner.isAutoPlay(true);
        //设置指示器的位置，小点，居中
        mbanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        mbanner.setImages(imagePath)
                //轮播监听
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        switch(position){
                            case 0:
                                textView.setText("NIKE:我们或许身份各异，或许热爱着不同的运动，但我们有一个共同的身份，执着、心怀新年的运动儿。我们曾共同经历寒冬，现在也要一起奔赴春天");
                                break;
                            case 1:
                                textView.setText("一桌菜，无论多么丰盛，往往有一碗冒着热气的白米饭。而米饭要想做的软硬适度，香气扑鼻，其实是有些小技巧的！一起来讨论吧！");
                                break;
                            case 2:
                                textView.setText("#运动朝我看##运动新风尚#快来讨论！和adidas一起让运动更风尚");
                                break;
                        }
                    }
                }).start();
    }

    public class MyImageLoaser extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

    private List<Map<String,Object>> getData(){
        final List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        final Map<String,Object> map = new HashMap<String, Object>();
        map.put("title","京东");
        map.put("info","恭喜你，赶上了！这些你喜欢的爆款好物竟然降价了");
        list.add(map);
//
//        map.put("title","急急急ultra boost 42.5码出");
//        map.put("info","感兴趣的话和我私聊吧~");
//        list.add(map);
//
//        map.put("title","促销降价了！《环球国家地理绘本》全11册");
//        map.put("info","诚信经营，以书会友，新旧程度：11册全新包邮偏远地区补运费");
//        list.add(map);
        return list;
    }
}
