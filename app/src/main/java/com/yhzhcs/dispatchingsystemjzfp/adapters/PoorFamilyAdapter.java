package com.yhzhcs.dispatchingsystemjzfp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhzhcs.dispatchingsystemjzfp.R;
import com.yhzhcs.dispatchingsystemjzfp.bean.PoorFamilyBean;
import com.yhzhcs.dispatchingsystemjzfp.bean.Poorlist;

import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */

public class PoorFamilyAdapter extends BaseAdapter {

    private Context context;
    private List<Poorlist> poorlists;
    LayoutInflater inflater;

    public PoorFamilyAdapter (Context context, List<Poorlist> poorlists){

        this.context = context;
        this.poorlists = poorlists;
        inflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return poorlists.size();
    }

    @Override
    public Object getItem(int position) {
        return poorlists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Poorlist poorlist = poorlists.get(position);
        ViewHolderFamily viewHolderFamily;
        if (convertView == null){
            viewHolderFamily = new ViewHolderFamily();
            convertView = inflater.inflate(R.layout.fragment_poor_family_item,null);
            viewHolderFamily.Num = (TextView) convertView.findViewById(R.id.family_item_num);
            viewHolderFamily.FamilyName = (TextView) convertView.findViewById(R.id.family_item_name);
            viewHolderFamily.FamilySex = (TextView) convertView.findViewById(R.id.family_item_sex);
            viewHolderFamily.FamilySexInput = (TextView) convertView.findViewById(R.id.family_item_sex_input);
            viewHolderFamily.FamilyAge = (TextView) convertView.findViewById(R.id.family_item_age);
            viewHolderFamily.FamilyAgeInput = (TextView) convertView.findViewById(R.id.family_item_age_input);
            viewHolderFamily.FamilyNexus = (TextView) convertView.findViewById(R.id.family_item_nexus);
            viewHolderFamily.FamilyNexusInput = (TextView) convertView.findViewById(R.id.family_item_nexus_input);
            viewHolderFamily.FamilyHealth = (TextView) convertView.findViewById(R.id.family_item_health);
            viewHolderFamily.FamilyHealthInput = (TextView) convertView.findViewById(R.id.family_item_health_input);
            viewHolderFamily.FamilyPaper = (TextView) convertView.findViewById(R.id.family_item_paper);
            viewHolderFamily.FamilyPaperInput = (TextView) convertView.findViewById(R.id.family_item_paper_input);
            convertView.setTag(viewHolderFamily);
        }else {
            viewHolderFamily = (ViewHolderFamily) convertView.getTag();
        }
        int num = position+1;
        viewHolderFamily.Num.setText(num+"");
        viewHolderFamily.FamilyName.setText(poorlist.getName());
        viewHolderFamily.FamilySex.setText("性别：");
        viewHolderFamily.FamilySexInput.setText(poorlist.getSex());
        viewHolderFamily.FamilyAge.setText("年龄：");
        viewHolderFamily.FamilyAgeInput.setText(poorlist.getAge()+"岁");
        viewHolderFamily.FamilyNexus.setText("与户主关系：");
        viewHolderFamily.FamilyNexusInput.setText(poorlist.getWithNelation());
        viewHolderFamily.FamilyHealth.setText("健康状况：");
        viewHolderFamily.FamilyHealthInput.setText(poorlist.getHealth());
        viewHolderFamily.FamilyPaper.setText("证件号：");
        viewHolderFamily.FamilyPaperInput.setText(poorlist.getCradNumber());
        return convertView;
    }

    public class ViewHolderFamily{
        TextView Num;
        TextView FamilyName;
        TextView FamilySex;
        TextView FamilySexInput;
        TextView FamilyAge;
        TextView FamilyAgeInput;
        TextView FamilyNexus;
        TextView FamilyNexusInput;
        TextView FamilyHealth;
        TextView FamilyHealthInput;
        TextView FamilyPaper;
        TextView FamilyPaperInput;
    }
}
