package com.xrjframework.demo.easypermission;

import android.Manifest;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.xrj.framework.nice.activity.BaseActivity;
import com.xrjframework.demo.R;

import butterknife.BindView;

/**
 * Created by a on 2017/6/26.
 *
 * @auther XRJ
 */
public class EasypermissionActivity extends BaseActivity {
    @BindView(R.id.permission_img)
    ImageView img;

    @Override
    protected int initLayoutId() {
        return R.layout.easypermission_layout;
    }

    @Override
    protected void initView() {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission(new String[]{Manifest.permission.CALL_PHONE}, 0x0001);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void permissionsuccess(int requestCode) {
        switch (requestCode) {
            case 0x0001:
                Toast.makeText(EasypermissionActivity.this, "获取打电话权限成功", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:13468857714"));

                break;
            case 0x0002:
                Toast.makeText(EasypermissionActivity.this,"获取写入sd卡权限成功",Toast.LENGTH_SHORT).show();
                break;

            case 0x0003:
                Toast.makeText(EasypermissionActivity.this,"获取拍照权限成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}