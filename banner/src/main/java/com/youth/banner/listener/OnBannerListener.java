package com.youth.banner.listener;

import android.view.MotionEvent;

import androidx.annotation.NonNull;

public interface OnBannerListener<T> {

    /**
     * 点击事件
     *
     * @param data     数据实体
     * @param position 当前位置
     */
    void OnBannerClick(@NonNull MotionEvent e, T data, int position);

    /**
     * 双击事件
     *
     * @param data     数据实体
     * @param position 当前位置
     */
    void OnBannerDoubleClick(@NonNull MotionEvent e, T data, int position);

    /**
     * 长按事件
     *
     * @param data     数据实体
     * @param position 当前位置
     */
    void OnBannerLongClick(@NonNull MotionEvent e, T data, int position);
}