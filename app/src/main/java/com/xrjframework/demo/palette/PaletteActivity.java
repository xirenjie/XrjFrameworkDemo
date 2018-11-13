package com.xrjframework.demo.palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.xrj.framework.nice.activity.BaseActivity;
import com.xrjframework.demo.R;

import butterknife.BindView;

/**
 * Created by xrj on 2018/5/16.
 * 引用
 * compile 'com.android.support:palette-v7:24.2.1'
 */
public class PaletteActivity extends BaseActivity {
    @BindView(R.id.palette_text)
    TextView palette_text;
    @BindView(R.id.palette_img)
    ImageView palette_img;

    @Override
    protected int initLayoutId() {
        return R.layout.palette_layout;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void permissionsuccess(int requestCode) {

    }

    /**
     * 根据id获取当前展示的bitmap
     * @param view
     * @return
     */
    public Bitmap convertViewToBitmap(View view){
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    public void init() {
        // 用来提取颜色的Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.index_bg);
// Palette的部分
        Palette.Builder builder = Palette.from(convertViewToBitmap(palette_img));
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //获取到充满活力的这种色调
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                //根据调色板Palette获取到图片中的颜色设置到toolbar和tab中背景，标题等，使整个UI界面颜色统一
                palette_text.setTextColor(vibrant.getRgb());

                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getWindow();
                    window.setStatusBarColor(vibrant.getRgb());
                    window.setNavigationBarColor(vibrant.getRgb());
                }
            }
        });
    }

}
