package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.Tasklists;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class TaskAdapter extends BaseAdapter {

    private Context context;

    private List<Tasklists> taskBeanList;

    private LayoutInflater inflater;

    private final int TYPE_COUNT = 2;

    private final int TYPE_ONE = 0;

    private final int TYPE_TWO = 1;


    private int currentType;

    //    private BitmapUtils bitmapUtils;

    public TaskAdapter(Context context, List<Tasklists> dataList) {
        this.context = context;
        this.taskBeanList = dataList;
        inflater = LayoutInflater.from(context);
//        bitmapUtils = new BitmapUtils(context);
    }

    @Override
    public int getCount() {
        return taskBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return taskBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        Tasklists tasklists = taskBeanList.get(position);
//        List<String> imageUrls = result.getImageUrl();
        currentType = getItemViewType(position);
        //根据currentType来加载不同的布局,并复用convertview
        if (currentType == TYPE_ONE) {    //加载第一种布局
            ViewHolderCompleted viewHolderCompleted;
            //首先判断convertview==null
            if (convertView == null) {
                viewHolderCompleted = new ViewHolderCompleted();
                convertView = inflater.inflate(R.layout.task_list_completed_item, null);
                viewHolderCompleted.TaskID = (TextView) convertView.findViewById(R.id.task_completed_id);
                viewHolderCompleted.TaskName = (TextView) convertView.findViewById(R.id.task_completed_dome_name);
                viewHolderCompleted.TaskCompleted = (TextView) convertView.findViewById(R.id.task_completed_txt);
                viewHolderCompleted.TaskPublisher = (TextView) convertView.findViewById(R.id.task_completed_publisher);
                viewHolderCompleted.TaskPublisherName = (TextView) convertView.findViewById(R.id.task_completed_publisher_name);
                viewHolderCompleted.StarTimeText = (TextView) convertView.findViewById(R.id.task_completed_star);
                viewHolderCompleted.StarTime = (TextView) convertView.findViewById(R.id.task_completed_star_time);
                viewHolderCompleted.EntTimeText = (TextView) convertView.findViewById(R.id.task_completed_ent);
                viewHolderCompleted.EntTime = (TextView) convertView.findViewById(R.id.task_completed_ent_time);
                convertView.setTag(viewHolderCompleted);
            } else {
                viewHolderCompleted = (ViewHolderCompleted) convertView.getTag();
            }

            int takid = position+1;
            viewHolderCompleted.TaskID.setText(takid+"");
            viewHolderCompleted.TaskName.setText(tasklists.getTaskName());
            viewHolderCompleted.TaskPublisherName.setText(tasklists.getRecipienter());

            Date dateTimes = new Date(tasklists.getTaskStartDate().getTime());
            SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
            String StarTime = dateFormats.format(dateTimes);
            viewHolderCompleted.StarTime.setText(StarTime);

            Date dateTimeo = new Date(tasklists.getTaskOverDate().getTime());
            SimpleDateFormat dateFormato = new SimpleDateFormat("yyyy-MM-dd");
            String OverTime = dateFormato.format(dateTimeo);
            viewHolderCompleted.EntTime.setText(OverTime);

        } else if (currentType == TYPE_TWO) {  //加载第二种布局
            ViewHolderHang viewHolderHang;
            if (convertView == null) {
                viewHolderHang = new ViewHolderHang();
                convertView = inflater.inflate(R.layout.task_list_hang_item, null);
                viewHolderHang.TaskID = (TextView) convertView.findViewById(R.id.task_hang_id);
                viewHolderHang.TaskName = (TextView) convertView.findViewById(R.id.task_hang_dome_name);
                viewHolderHang.TaskHang = (TextView) convertView.findViewById(R.id.task_hang_txt);
                viewHolderHang.TaskPublisher = (TextView) convertView.findViewById(R.id.task_hang_publisher);
                viewHolderHang.TaskPublisherName = (TextView) convertView.findViewById(R.id.task_hang_publisher_name);
                viewHolderHang.StarTimeText = (TextView) convertView.findViewById(R.id.task_hang_star);
                viewHolderHang.StarTime = (TextView) convertView.findViewById(R.id.task_hang_star_time);
                viewHolderHang.EntTimeText = (TextView) convertView.findViewById(R.id.task_hang_ent);
                viewHolderHang.EntTime = (TextView) convertView.findViewById(R.id.task_hang_ent_time);
                convertView.setTag(viewHolderHang);
            } else {
                viewHolderHang = (ViewHolderHang) convertView.getTag();
            }
            int takid = position+1;
            viewHolderHang.TaskID.setText(takid+"");
            viewHolderHang.TaskName.setText(tasklists.getTaskName());
            viewHolderHang.TaskPublisherName.setText(tasklists.getRecipienter());

            Date dateTimes = new Date(tasklists.getTaskStartDate().getTime());
            SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
            String StarTime = dateFormats.format(dateTimes);
            viewHolderHang.StarTime.setText(StarTime);

            Date dateTimeo = new Date(tasklists.getTaskOverDate().getTime());
            SimpleDateFormat dateFormato = new SimpleDateFormat("yyyy-MM-dd");
            String OverTime = dateFormato.format(dateTimeo);
            viewHolderHang.EntTime.setText(OverTime);
        }
        return convertView;
    }

    public static abstract class MyClickListener implements View.OnClickListener {
        /**
         * 基类的onClick方法
         */
        @Override
        public void onClick(View v) {
            myOnClick((Integer) v.getTag(), v);
        }

        public abstract void myOnClick(int position, View v);
    }

    public class ViewHolderCompleted {

        TextView TaskID;
        TextView TaskName;
        TextView TaskCompleted;
        TextView TaskPublisher;
        TextView TaskPublisherName;
        TextView StarTimeText;
        TextView StarTime;
        TextView EntTimeText;
        TextView EntTime;
    }

    public class ViewHolderHang {

        TextView TaskID;
        TextView TaskName;
        TextView TaskHang;
        TextView TaskPublisher;
        TextView TaskPublisherName;
        TextView StarTimeText;
        TextView StarTime;
        TextView EntTimeText;
        TextView EntTime;
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("debug", "position=" + position);
        Tasklists tasklists = taskBeanList.get(position);
        Log.i("ddd", "result=" + tasklists);
        int type = tasklists.getEnabledFlag();
        switch (type) {
            case TYPE_ONE:
                return TYPE_ONE;

            case TYPE_TWO:
                return TYPE_TWO;

            default:
                return -1;
        }
    }


}
