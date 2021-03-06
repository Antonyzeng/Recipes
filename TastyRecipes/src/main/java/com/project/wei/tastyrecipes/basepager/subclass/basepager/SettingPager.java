package com.project.wei.tastyrecipes.basepager.subclass.basepager;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.project.wei.tastyrecipes.R;
import com.project.wei.tastyrecipes.basepager.BasePager;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public class SettingPager extends BasePager{

    private static final String TAG = "SettingPager";
    private View view;
    private ListView lv_pager_item;
    private ImageButton ib_mypage_daynight;
    private ImageButton ib_mypage_headportrait;
    private ImageButton ib_mypage_opinion;
    private ImageButton ib_mypage_setting;
    boolean isDaynight = false;//是否是夜间模式，默认为否
    private TextView tv_mypager_daynight;

    public SettingPager(Activity activity) {
        super(activity);
    }
    public View initView(){

        view = View.inflate(mActivity, R.layout.pager_mypager_detail, null);
        return view;
    }

    public void initData() {
        /*TextView textView = new TextView(mActivity);
        textView.setText("设置");
        textView.setTextSize(50);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        fl_content.addView(textView);

        tv_title.setText("设置");//修改页面标题
        ibtn_menu.setVisibility(View.INVISIBLE);*/// 隐藏菜单按钮
        lv_pager_item = (ListView) view.findViewById(R.id.lv_pager_item);
        //第一步 搞定数据集
        //第二步 搞定适配器
        ListAdapter adapter = new MyAdapter();
        //第三步 listView 绑定适配器
        lv_pager_item.setAdapter(adapter);
        ib_mypage_daynight = (ImageButton) view.findViewById(R.id.ib_mypage_daynight);
        ib_mypage_headportrait = (ImageButton) view.findViewById(R.id.ib_mypage_headportrait);
        ib_mypage_opinion = (ImageButton) view.findViewById(R.id.ib_mypage_opinion);
        ib_mypage_setting = (ImageButton) view.findViewById(R.id.ib_mypage_setting);
        ib_mypage_daynight.setOnClickListener(new MyListener());
        ib_mypage_headportrait.setOnClickListener(new MyListener());
        ib_mypage_opinion.setOnClickListener(new MyListener());
        ib_mypage_setting.setOnClickListener(new MyListener());

        tv_mypager_daynight = (TextView) view.findViewById(R.id.tv_mypager_daynight);

    }

    class  MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i(TAG, "onClick方法执行到了");
            switch (v.getId()) {
                case R.id.ib_mypage_headportrait://跳到第三方登录
                    break;
                case R.id.ib_mypage_daynight://切换日夜模式
                    Log.i(TAG, "日夜模式开始切换");
                    if (isDaynight == false) {
                        ib_mypage_daynight.setImageResource(R.drawable.mypage_icon_daynight);
                        tv_mypager_daynight.setText("日间模式");
                        isDaynight = true;
                    } else {
                        ib_mypage_daynight.setImageResource(R.drawable.mypager_icon_sun);
                        tv_mypager_daynight.setText("夜间模式");
                        isDaynight = false;
                    }
                    break;
                case R.id.ib_mypage_opinion://跳转到意见反馈
                    break;
                case R.id.ib_mypage_setting://跳转到设置
                    break;
                default:
                    break;
            }
        }

    }


    class MyAdapter extends BaseAdapter {
        int[] imagesId={R.drawable.mypager_icon_mycollection,R.drawable.mypager_icon_mycollection,R.drawable.mypager_icon_mycollection};
        String[] itemName = {"我的收藏","新闻推送","最新活动"};
        @Override
        public int getCount() {
            return 3;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LinearLayout view ;
            view = (LinearLayout) View.inflate(mActivity,R.layout.mypager_list_verticleitem,null);
            ImageView iv_listitemnews_icon = (ImageView) view.findViewById(R.id.iv_listitemnews_icon);
            TextView tv_listitemnews_title = (TextView) view.findViewById(R.id.tv_listitemnews_title);

            iv_listitemnews_icon.setImageResource(imagesId[position]);
            tv_listitemnews_title.setText(itemName[position]);

            //给ListView设置一个背景选择器
            if(view ==null)
            {
                view = (LinearLayout) LayoutInflater.from(mActivity).inflate(R.layout.mypager_list_verticleitem, null);
            }
            view.setBackgroundResource(R.drawable.mypager_item_selector);
            return view;
        }
    }
    /**
     * 条目点击事件
     */
    private void listviewItemClick() {
        lv_pager_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //view : 条目的view对象
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                switch(position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }

        });
    }
}
