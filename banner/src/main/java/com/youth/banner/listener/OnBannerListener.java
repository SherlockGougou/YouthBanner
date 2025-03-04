package com.youth.banner.listener;

public interface OnBannerListener<T> {

    /**
     * 点击事件
     *
     * @param data     数据实体
     * @param position 当前位置
     */
    void OnBannerClick(T data, int position);

    /**
     * 双击事件
     *
     * @param data     数据实体
     * @param position 当前位置
     */
    void OnBannerDoubleClick(T data, int position);

    /**
     * 长按事件
     *
     * @param data     数据实体
     * @param position 当前位置
     */
    void OnBannerLongClick(T data, int position);
}