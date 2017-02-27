package com.jzbwlkj.banner.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jzbwlkj.banner.R;
import com.jzbwlkj.banner.config.Path;
import com.jzbwlkj.banner.custom.MyImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mBanners;
    private List<String> mBannerTitles;
    private Banner mBanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBanners = new ArrayList<>();
        mBannerTitles = new ArrayList<>();

        addData();

//        bannerSimple();
        bannerComplex();

        setListener();
    }

    /**
     * 设置Banner的点击事件，position从1开始
     */
    private void setListener() {
        mBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(MainActivity.this, "第" + position + "张图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 添加图片地址
     */
    private void addData() {
        mBanners.add(Path.img1);
        mBanners.add(Path.img2);
        mBanners.add(Path.img5);
        mBanners.add(Path.img4);
        mBanners.add(Path.img5);

        mBannerTitles.add("第一张图片");
        mBannerTitles.add("第二张图片");
        mBannerTitles.add("第三张图片");
        mBannerTitles.add("第四张图片");
        mBannerTitles.add("第五张图片");
    }

    /**
     * Banner的复杂使用
     */
    private void bannerComplex() {
        mBanner = (Banner) findViewById(R.id.banner);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        mBanner.setImageLoader(new MyImageLoader());
        //设置图片集合
        mBanner.setImages(mBanners);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(mBannerTitles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    /**
     * Banner的简单使用
     */
    private void bannerSimple() {
        mBanner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        mBanner.setImageLoader(new MyImageLoader());
        //设置图片集合
        mBanner.setImages(mBanners);
        //设置样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }


    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

}
