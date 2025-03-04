package com.test.banner.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.test.banner.R;
import com.test.banner.adapter.ImageTitleAdapter;
import com.test.banner.bean.DataBean;
import com.youth.banner.Banner;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;
import com.youth.banner.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConstraintLayoutBannerActivity extends AppCompatActivity {
    private static final String TAG = "banner_log";
    @BindView(R.id.banner)
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_banner);
        ButterKnife.bind(this);
        banner.setAdapter(new ImageTitleAdapter(DataBean.getTestData()))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        Snackbar.make(banner, "点击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    }

                    @Override
                    public void OnBannerDoubleClick(Object data, int position) {
                        Snackbar.make(banner, "双击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    }

                    @Override
                    public void OnBannerLongClick(Object data, int position) {
                        Snackbar.make(banner, "长按: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    }
                });
        banner.setIndicator(new CircleIndicator(this));
        banner.setIndicatorSelectedColorRes(R.color.main_color);
        banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT);
        banner.setIndicatorMargins(new IndicatorConfig.Margins(0, 0,
                BannerConfig.INDICATOR_MARGIN, (int) BannerUtils.dp2px(12)));
        banner.addBannerLifecycleObserver(this);
    }

}
