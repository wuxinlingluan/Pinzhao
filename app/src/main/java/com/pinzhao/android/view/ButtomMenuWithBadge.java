package com.pinzhao.android.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pinzhao.R;
import com.pinzhao.common.util.UIUtils;

/**
 * Created by ${sheldon} on 2016/12/31.
 */
public class ButtomMenuWithBadge extends LinearLayout {

    private TextView tv_text;
    private Drawable srcDrawable;
    private String mText;
    private ImageView iv_pic;
    private TextView tv_unread_count;

    @SuppressLint("NewApi")
    public ButtomMenuWithBadge(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();

        initAttrs(context, attrs);
    }

    /**
     * 初始化属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ButtomMenuWithBadge);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ButtomMenuWithBadge_src:
                    srcDrawable = a.getDrawable(attr);
                    break;

                case R.styleable.ButtomMenuWithBadge_text:
                    mText = a.getString(attr);
                    break;

            }
        }
        setTextColor(Color.GRAY);
        setText(mText);
        setGravity(Gravity.TOP | Gravity.RIGHT);
        setTextTopIamge(srcDrawable);
        a.recycle();
    }

    public ButtomMenuWithBadge(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtomMenuWithBadge(Context context) {
        this(context, null, 0);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        LayoutInflater inflater = (LayoutInflater) UIUtils.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_bottom_tab, this);
        tv_text = (TextView) findViewById(R.id.tv_text);
        tv_unread_count = (TextView) findViewById(R.id.tv_unread_count);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);

    }

    /**
     * 设置未读消息数
     *
     * @param count
     */
    public void setUnreadMsgCount(int count) {
        if (count > 0) {
            // 显示未读消息数
            tv_unread_count.setVisibility(View.VISIBLE);
            // 若是大于99条
            if (count > 99) {
                tv_unread_count.setText("99+");
            } else {
                tv_unread_count.setText(count + "");
            }

        } else {
            // 隐藏未读消息
            tv_unread_count.setVisibility(View.GONE);
        }
    }

    /**
     * 设置显示的文字
     */
    public void setText(String text) {
        tv_text.setText(text);
    }

    /**
     * 设置显示的文本的颜色
     */
    public void setTextColor(int color) {
        tv_text.setTextColor(color);

    }

    /**
     * 设置显示的文本的大小
     */
    public void setTextSize(float size) {
        tv_text.setTextSize(size);
    }

    /**
     * 设置文字上方的显示图片
     */
    public void setTextTopIamge(Drawable top) {
        iv_pic.setImageDrawable(top);
    }

}

