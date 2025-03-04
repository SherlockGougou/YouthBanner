package com.test.banner.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.test.banner.R;
import com.test.banner.adapter.ImageAdapter;
import com.test.banner.bean.DataBean;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.DrawableIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity {

    @BindView(R.id.banner1)
    Banner mBanner1;
    @BindView(R.id.banner2)
    Banner mBanner2;
    @BindView(R.id.indicator)
    DrawableIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        /**
         * 画廊效果
         */
        mBanner1.setAdapter(new ImageAdapter(DataBean.getTestData2()))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        Snackbar.make(mBanner1, "点击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    }

                    @Override
                    public void OnBannerDoubleClick(Object data, int position) {
                        Snackbar.make(mBanner1, "双击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    }

                    @Override
                    public void OnBannerLongClick(Object data, int position) {
                        Snackbar.make(mBanner1, "长按: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    }
                });
        mBanner1.setIndicator(new CircleIndicator(this));
        //添加画廊效果
        mBanner1.setBannerGalleryEffect(50, 10);
        //(可以和其他PageTransformer组合使用，比如AlphaPageTransformer，注意但和其他带有缩放的PageTransformer会显示冲突)
        //添加透明效果(画廊配合透明效果更棒)
        //mBanner1.addPageTransformer(new AlphaPageTransformer());


        /**
         * 魅族效果
         */
        mBanner2.setAdapter(new ImageAdapter(DataBean.getTestData()))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        Snackbar.make(mBanner2, "点击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    }

                    @Override
                    public void OnBannerDoubleClick(Object data, int position) {
                        Snackbar.make(mBanner2, "双击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    }

                    @Override
                    public void OnBannerLongClick(Object data, int position) {
                        Snackbar.make(mBanner2, "长按: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    }
                });
        mBanner2.setIndicator(indicator, false);
        //添加魅族效果
        mBanner2.setBannerGalleryMZ(20);


    }


}