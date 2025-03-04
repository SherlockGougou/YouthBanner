package com.youth.banner.adapter;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.R;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.holder.IViewHolder;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;


public abstract class BannerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements IViewHolder<T, VH> {

    private static final String TAG = "BannerAdapter";

    protected List<T> mDatas = new ArrayList<>();
    private OnBannerListener<T> mOnBannerListener;
    private VH mViewHolder;
    private int mIncreaseCount = BannerConfig.INCREASE_COUNT;

    public BannerAdapter(List<T> datas) {
        setDatas(datas);
    }

    /**
     * 设置实体集合（可以在自己的adapter自定义，不一定非要使用）
     *
     * @param datas
     */
    public void setDatas(List<T> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 获取指定的实体（可以在自己的adapter自定义，不一定非要使用）
     *
     * @param position 真实的position
     * @return
     */
    public T getData(int position) {
        if (position > mDatas.size() - 1) {
            return null;
        }
        return mDatas.get(position);
    }

    /**
     * 获取指定的实体（可以在自己的adapter自定义，不一定非要使用）
     *
     * @param position 这里传的position不是真实的，获取时转换了一次
     * @return
     */
    public T getRealData(int position) {
        int realPosition = getRealPosition(position);
        if (realPosition > mDatas.size() - 1) {
            return null;
        }
        return mDatas.get(realPosition);
    }


    @Override
    public final void onBindViewHolder(@NonNull VH holder, int position) {
        mViewHolder = holder;
        int real = getRealPosition(position);
        T data = mDatas.get(real);
        holder.itemView.setTag(R.id.banner_data_key, data);
        holder.itemView.setTag(R.id.banner_pos_key, real);
        onBindView(holder, mDatas.get(real), real, getRealCount());
        if (mOnBannerListener != null) {
            GestureDetector detector = new GestureDetector(holder.itemView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
                    mOnBannerListener.OnBannerClick(data, real);
                    Log.d(TAG, "onSingleTap: 单击");
                    return false;
                }

                @Override public boolean onDoubleTap(@NonNull MotionEvent e) {
                    mOnBannerListener.OnBannerDoubleClick(data, real);
                    Log.d(TAG, "onDoubleTap: 双击");
                    return false;
                }

                @Override
                public void onLongPress(@NonNull MotionEvent e) {
                    mOnBannerListener.OnBannerLongClick(data, real);
                    Log.d(TAG, "onLongPress: 长按");
                }
            });
            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    detector.onTouchEvent(event);
                    return true;
                }
            });
        }
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VH vh = onCreateHolder(parent, viewType);
        return vh;
    }

    @Override
    public int getItemCount() {
        return getRealCount() > 1 ? getRealCount() + mIncreaseCount : getRealCount();
    }

    public int getRealCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public int getRealPosition(int position) {
        return BannerUtils.getRealPosition(mIncreaseCount == BannerConfig.INCREASE_COUNT, position, getRealCount());
    }

    public void setOnBannerListener(OnBannerListener<T> listener) {
        this.mOnBannerListener = listener;
    }

    public VH getViewHolder() {
        return mViewHolder;
    }

    public void setIncreaseCount(int increaseCount) {
        this.mIncreaseCount = increaseCount;
    }
}