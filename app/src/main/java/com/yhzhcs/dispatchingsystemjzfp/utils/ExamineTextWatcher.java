package com.yhzhcs.dispatchingsystemjzfp.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

/**
 * 用户输入验证器
 *
 * Created by Luhuai Wu on 2018-3-14.
 */
public class ExamineTextWatcher implements TextWatcher {
    private static final String TAG = "ExamineTextWatcher";

    /**
     * 帐号
     */
    public static final int TYPE_ACCOUNT = 1;

    /**
     * 密码
     */
    public static final int TYPE_PASSWORD = 2;

    /**
     * 输入框
     */
    private EditText mEditText;

    /**
     * 验证类型
     */
    private int examineType;

    /**
     * 输入前的文本内容
     */
    private String beforeText;

    private Context context;

    /**
     * 构造器
     *
     * @param type     验证类型
     * @param editText 输入框
     * @param context
     */
    public ExamineTextWatcher(Context context, int type, EditText editText) {
        this.context = context;
        this.examineType = type;
        this.mEditText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // 输入前的文本
        beforeText = s.toString();
        Log.v(TAG, "beforeText =>>>" + beforeText);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // 输入后的文本
        String afterText = s.toString();
        Log.v(TAG, "afterText =>>>" + afterText);

        boolean isValid = true;
        if (!TextUtils.isEmpty(afterText)) {
            switch (examineType) {
                case TYPE_ACCOUNT:
                    isValid = ValidateUtil.isAccount(afterText);
                    if (beforeText.length() > 20){
                        ToastUtil.showInfo(context,"账号为20位账号！");
                    }
                    else if (!isValid){
                        ToastUtil.showInfo(context,"只能输入英文字母和数字！");
                    }
                    break;
                case TYPE_PASSWORD:
                    isValid = ValidateUtil.isPassWord(afterText);
                    if (beforeText.length() > 18){
                        ToastUtil.showInfo(context,"密码为18位密码！");
                    }
                    else if (!isValid){
                        ToastUtil.showInfo(context,"只能输入英文字母和数字！");
                    }
                    break;
            }
            if (!isValid) {
                // 用户现在输入的字符数减去之前输入的字符数，等于新增的字符数
                int differ = afterText.length() - beforeText.length();
                // 如果用户的输入不符合规范，则显示之前输入的文本
                mEditText.setText(beforeText);
                // 光标移动到文本末尾
                mEditText.setSelection(afterText.length() - differ);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {}
}
