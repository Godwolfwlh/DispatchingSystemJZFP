package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.UserInfo;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

/**
 * Created by Administrator on 2018/3/1.
 */

public class BasicDocumentActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView Ltitle;
    private TextView Title,Rtitle,Name,Phone,Company;
    private UserInfo userInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_document);
        userInfo = (UserInfo) getIntent().getSerializableExtra("USER_INFO");
        LogUtil.v("BDUSERINFO",userInfo.toString());
        intView();
    }

    private void intView(){
        Ltitle = (ImageView) findViewById(R.id.bd_title_left);
        Title = (TextView) findViewById(R.id.bd_title_name);
        Rtitle = (TextView) findViewById(R.id.bd_title_right);
        Name = (TextView) findViewById(R.id.bd_name);
        Phone = (TextView) findViewById(R.id.bd_phone);
        Company = (TextView) findViewById(R.id.bd_company);

        Name.setText(userInfo.getMissionName());
//        Phone.setText(userInfo.getPhoto());
        Company.setText(userInfo.getMissionCompany());

        Ltitle.setOnClickListener(this);
        Rtitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bd_title_left:
                finish();
                break;
            case R.id.bd_title_right:
                Intent intent = new Intent(this,EditProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("USER_INFO", userInfo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }

    }
}
