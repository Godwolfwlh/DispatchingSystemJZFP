package com.yhzhcs.dispatchingsystemjzfp.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.adapters.MomentAdapter;
import com.yhzhcs.dispatchingsystemjzfp.bean.ForumBean;
import com.yhzhcs.dispatchingsystemjzfp.model.Comment;
import com.yhzhcs.dispatchingsystemjzfp.model.Moment;
import com.yhzhcs.dispatchingsystemjzfp.model.User;
import com.yhzhcs.dispatchingsystemjzfp.utils.CommentFun;
import com.yhzhcs.dispatchingsystemjzfp.utils.Constant;
import com.yhzhcs.dispatchingsystemjzfp.utils.CustomTagHandler;
import com.yhzhcs.dispatchingsystemjzfp.utils.LogUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ForumActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView titleImgL;
    private TextView titleName;
    private ImageView titleImgR;

    public static User sUser = new User(1, "走向远方"); // 当前登录用户

    private ListView mListView;
    private MomentAdapter mAdapter;

    private List<ForumBean> listBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forum);
        mListView = (ListView) findViewById(R.id.list_moment);

        getData();
        intView();

        // 模拟数据
        ArrayList<Moment> moments = new ArrayList<Moment>();
        for (int i = 0; i < 20; i++) {
            ArrayList<Comment> comments = new ArrayList<Comment>();
            comments.add(new Comment(new User(i + 2, "用户" + i), "评论" + i, null));
            comments.add(new Comment(new User(i + 100, "用户" + (i + 100)), "评论" + i, new User(i + 200, "用户" + (i + 200))));
            comments.add(new Comment(new User(i + 200, "用户" + (i + 200)), "评论" + i, null));
            comments.add(new Comment(new User(i + 300, "用户" + (i + 300)), "评论" + i, null));
            moments.add(new Moment("动态 " + i, comments));
        }

        mAdapter = new MomentAdapter(this, moments, new CustomTagHandler(this, new CustomTagHandler.OnCommentClickListener() {
            @Override
            public void onCommentatorClick(View view, User commentator) {
                Toast.makeText(getApplicationContext(), commentator.mName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceiverClick(View view, User receiver) {
                Toast.makeText(getApplicationContext(), receiver.mName, Toast.LENGTH_SHORT).show();
            }

            // 点击评论内容，弹出输入框回复评论
            @Override
            public void onContentClick(View view, User commentator, User receiver) {
                if (commentator != null && commentator.mId == sUser.mId) { // 不能回复自己的评论
                    return;
                }
                inputComment(view, commentator);
            }
        }));

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Toast.makeText(getApplicationContext(), "click " + position, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getData() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("id", "227");
        params.addQueryStringParameter("pageIndex", "1");
        httpUtils.send(HttpMethod.GET, Constant.URL_FORUM, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.logJson("FORUMHTTP", "onSuccess===>>" + responseInfo.result.toString());
                String body = responseInfo.result;
                Gson gson = new Gson();
                listBean = new ArrayList<ForumBean>();
                Type type = new TypeToken<ArrayList<ForumBean>>() {}.getType();
                listBean = gson.fromJson(body,type);
                LogUtil.logJson("FORUMFORUMBEAN", "Bean===" + responseInfo.result.toString());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.logJson("FORUMHTTP", "onFailure===>>" + s);
            }
        });
    }

    private void intView() {
        titleImgL = (ImageView) findViewById(R.id.title_left);
        titleName = (TextView) findViewById(R.id.title_name);
        titleImgR = (ImageView) findViewById(R.id.title_right);

        titleImgL.setVisibility(View.VISIBLE);
        titleImgL.setImageResource(R.mipmap.icon_back);
        titleName.setText("讨论区");
        titleImgL.setOnClickListener(this);
        titleImgR.setOnClickListener(this);
    }

    public void inputComment(final View v) {
        inputComment(v, null);
    }

    /**
     * 弹出评论对话框
     *
     * @param v
     * @param receiver
     */
    public void inputComment(final View v, User receiver) {
        CommentFun.inputComment(ForumActivity.this, mListView, v, receiver, new CommentFun.InputCommentListener() {
            @Override
            public void onCommitComment() {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
        }
    }
}
