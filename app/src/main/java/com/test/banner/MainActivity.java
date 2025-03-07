package com.test.banner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import com.test.banner.adapter.ImageAdapter;
import com.test.banner.adapter.ImageTitleAdapter;
import com.test.banner.adapter.ImageTitleNumAdapter;
import com.test.banner.adapter.MultipleTypesAdapter;
import com.test.banner.bean.DataBean;
import com.test.banner.ui.ConstraintLayoutBannerActivity;
import com.test.banner.ui.GalleryActivity;
import com.test.banner.ui.RecyclerViewBannerActivity;
import com.test.banner.ui.TVActivity;
import com.test.banner.ui.TouTiaoActivity;
import com.test.banner.ui.VideoActivity;
import com.test.banner.ui.Vp2FragmentRecyclerviewActivity;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;
import com.youth.banner.util.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.indicator)
    RoundLinesIndicator indicator;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<DataBean> datas = DataBean.getTestData2();

        //自定义的图片适配器，也可以使用默认的BannerImageAdapter
        ImageAdapter adapter = new ImageAdapter(datas);

        banner.setAdapter(adapter)
//              .setCurrentItem(0,false)
                //添加生命周期观察者
                .addBannerLifecycleObserver(this)
                //设置指示器
                .setIndicator(new CircleIndicator(this))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(@NonNull MotionEvent e, Object data, int position) {
                        Snackbar.make(banner, "点击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    }

                    @Override
                    public void OnBannerDoubleClick(@NonNull MotionEvent e, Object data, int position) {
                        Snackbar.make(banner, "双击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    }

                    @Override
                    public void OnBannerLongClick(@NonNull MotionEvent e, Object data, int position) {
                        Snackbar.make(banner, "长按: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    }
                });

        //添加item之间切换时的间距(如果使用了画廊效果就不要添加间距了，因为内部已经添加过了)
//        banner.addPageTransformer(new MarginPageTransformer( BannerUtils.dp2px(10)));

        //和下拉刷新配套使用
        refresh.setOnRefreshListener(() -> {
            //模拟网络请求需要3秒，请求完成，设置setRefreshing 为false
            new Handler().postDelayed(() -> {
                refresh.setRefreshing(false);

                //给banner重新设置数据（完全覆盖）
                banner.setDatas(DataBean.getTestData());

                //模拟请求成功（原数据减少） 刷新banner
//                datas.remove(0);
//                adapter.notifyDataSetChanged();

                //对setDatas()方法不满意？你可以自己在adapter控制数据，参考setDatas()的实现修改
//                adapter.updateData(DataBean.getTestData());
//                banner.setCurrentItem(banner.getStartPosition(), false);
//                banner.setIndicatorPageChange();

            }, 2000);
        });

    }


    @OnClick({R.id.style_image, R.id.style_image_title, R.id.style_image_title_num, R.id.style_multiple,
            R.id.style_net_image, R.id.change_indicator, R.id.rv_banner, R.id.cl_banner, R.id.vp_banner,
            R.id.banner_video, R.id.banner_tv, R.id.gallery, R.id.topLine})
    public void click(View view) {
        indicator.setVisibility(View.GONE);
        switch (view.getId()) {
            case R.id.style_image:
                refresh.setEnabled(true);
                banner.setAdapter(new ImageAdapter(DataBean.getTestData()))
                        .setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "点击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }

                            @Override
                            public void OnBannerDoubleClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "双击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }

                            @Override
                            public void OnBannerLongClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "长按: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }
                        });
                banner.setIndicator(new CircleIndicator(this));
                banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
                break;
            case R.id.style_image_title:
                refresh.setEnabled(true);
                banner.setAdapter(new ImageTitleAdapter(DataBean.getTestData()))
                        .setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "点击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }

                            @Override
                            public void OnBannerDoubleClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "双击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }

                            @Override
                            public void OnBannerLongClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "长按: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }
                        });
                banner.setIndicator(new CircleIndicator(this));
                banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT);
                banner.setIndicatorMargins(new IndicatorConfig.Margins(0, 0,
                        BannerConfig.INDICATOR_MARGIN, BannerUtils.dp2px(12)));
                break;
            case R.id.style_image_title_num:
                refresh.setEnabled(true);
                //这里是将数字指示器和title都放在adapter中的，如果不想这样你也可以直接设置自定义的数字指示器
                banner.setAdapter(new ImageTitleNumAdapter(DataBean.getTestData()))
                        .setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "点击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }

                            @Override
                            public void OnBannerDoubleClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "双击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }

                            @Override
                            public void OnBannerLongClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "长按: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }
                        });
                banner.removeIndicator();
                break;
            case R.id.style_multiple:
                refresh.setEnabled(true);
                banner.setIndicator(new CircleIndicator(this));
                banner.setAdapter(new MultipleTypesAdapter(this, DataBean.getTestData()))
                        .setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "点击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }

                            @Override
                            public void OnBannerDoubleClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "双击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }

                            @Override
                            public void OnBannerLongClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "长按: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }
                        });
                break;
            case R.id.style_net_image:
                refresh.setEnabled(false);
                //方法一：使用自定义图片适配器
//                banner.setAdapter(new ImageNetAdapter(DataBean.getTestData3()));

                //方法二：使用自带的图片适配器
                banner.setAdapter(new BannerImageAdapter<DataBean>(DataBean.getTestData3()) {
                            @Override
                            public void onBindView(BannerImageHolder holder, DataBean data, int position, int size) {
                                //图片加载自己实现
                                Glide.with(holder.itemView)
                                        .load(data.imageUrl)
                                        .thumbnail(Glide.with(holder.itemView).load(R.drawable.loading))
                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                        .into(holder.imageView);
                            }
                        })
                        .setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "点击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }

                            @Override
                            public void OnBannerDoubleClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "双击: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }

                            @Override
                            public void OnBannerLongClick(@NonNull MotionEvent e, Object data, int position) {
                                Snackbar.make(banner, "长按: " + ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                LogUtils.d("position：" + position);
                            }
                        });
                banner.setIndicator(new RoundLinesIndicator(this));
                banner.setIndicatorSelectedWidth(BannerUtils.dp2px(15));
                break;
            case R.id.change_indicator:
                indicator.setVisibility(View.VISIBLE);
                //在布局文件中使用指示器，这样更灵活
                banner.setIndicator(indicator, false);
                banner.setIndicatorSelectedWidth(BannerUtils.dp2px(15));
                break;
            case R.id.gallery:
                startActivity(new Intent(this, GalleryActivity.class));
                break;
            case R.id.rv_banner:
                startActivity(new Intent(this, RecyclerViewBannerActivity.class));
                break;
            case R.id.cl_banner:
                startActivity(new Intent(this, ConstraintLayoutBannerActivity.class));
                break;
            case R.id.vp_banner:
                startActivity(new Intent(this, Vp2FragmentRecyclerviewActivity.class));
                break;
            case R.id.banner_video:
                startActivity(new Intent(this, VideoActivity.class));
                break;
            case R.id.banner_tv:
                startActivity(new Intent(this, TVActivity.class));
                break;
            case R.id.topLine:
                startActivity(new Intent(this, TouTiaoActivity.class));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}
