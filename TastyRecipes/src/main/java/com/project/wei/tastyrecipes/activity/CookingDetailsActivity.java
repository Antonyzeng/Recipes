package com.project.wei.tastyrecipes.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.project.wei.tastyrecipes.R;
import com.project.wei.tastyrecipes.domain.ClassifyDetail;

import java.util.List;

public class CookingDetailsActivity extends Activity {

   /* @ViewInject(R.id.ibtn_menu)
    private ImageButton ibtn_menu;
    @ViewInject(R.id.ibtn_back)
    private ImageButton ibtn_back;
    @ViewInject(R.id.ibtn_textsize)
    private ImageButton ibtn_textsize;
    @ViewInject(R.id.ibtn_share)
    private ImageButton ibtn_share;
    @ViewInject(R.id.ll_tools)
    private LinearLayout ll_tools;
    @ViewInject(R.id.wv_news_details)
    private WebView mWebView;
    @ViewInject(R.id.pb_loading)
    private ProgressBar pb_loading;

    private String mUrl;
    private WebSettings settings;
*/


    @ViewInject(R.id.lv_cooking)
    private ListView lv_cooking;

    private List<ClassifyDetail.StepsBean> steps;
    private BitmapUtils bitmapUtils;
    @ViewInject(R.id.iv_show)
    private ImageView iv_show;
    @ViewInject(R.id.tv_show)
    private TextView tv_show;
    @ViewInject(R.id.tv_imtro)
    private TextView tv_imtro;
    @ViewInject(R.id.tv_burden)
    private TextView tv_burden;
    @ViewInject(R.id.tv_ingerdients)
    private TextView tv_ingerdients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_details);
        ViewUtils.inject(this);

        ClassifyDetail.DataBean dataBean = getIntent().getParcelableExtra("step");//获取数据
        steps = dataBean.getSteps();

        View headView = View.inflate(getApplicationContext(), R.layout.item_header, null);
        ViewUtils.inject(this,headView);
       /* iv_show = (ImageView) headView.findViewById(R.id.iv_show);
        tv_show = (TextView) headView.findViewById(R.id.tv_show);*/

        lv_cooking.addHeaderView(headView);// 添加头布局

        bitmapUtils = new BitmapUtils(getApplicationContext());

        bitmapUtils.display(iv_show,dataBean.albums.get(0));
        tv_show.setText(dataBean.title);
        tv_imtro.setText(dataBean.imtro);
        tv_burden.setText(dataBean.burden);
        tv_ingerdients.setText(dataBean.ingredients);

        lv_cooking.setAdapter(new CookingDetailAdapter());// 设置数据
    }

    class CookingDetailAdapter extends BaseAdapter {
        BitmapUtils bitmapUtils;
        public CookingDetailAdapter() {
             bitmapUtils = new BitmapUtils(getApplicationContext());
        }

        @Override
        public int getCount() {
            return steps.size();
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
            View view = View.inflate(getApplicationContext(), R.layout.item_cookingdetail, null);
            ImageView iv_steps = (ImageView) view.findViewById(R.id.iv_steps);
            TextView tv_step = (TextView) view.findViewById(R.id.tv_step);
            bitmapUtils.display(iv_steps,steps.get(position).img);
            tv_step.setText(steps.get(position).step);
            return view;
        }
    }








      /*  ClassifyDetailParcel step = getIntent().getParcelableExtra("step");
        String img = step.getImg();
        Log.i("fffffffffffff",img);*/


       /* ibtn_menu.setVisibility(View.INVISIBLE);
        ibtn_back.setVisibility(View.VISIBLE);
        ll_tools.setVisibility(View.VISIBLE);

        ibtn_back.setOnClickListener(this);
        ibtn_textsize.setOnClickListener(this);
        ibtn_share.setOnClickListener(this);

       // mWebView.loadUrl("www.baidu.com");
            mWebView.loadUrl(mUrl);



        settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);// 支持js功能，必写，几乎每个网页都会有js
        settings.setBuiltInZoomControls(true);// 显示缩放按钮（wap网页不支持，因为wap已经适配好了手机）
        settings.setUseWideViewPort(true);// 支持双击缩放（wap 网页不支持）

//      每次进入点击新闻后，先查看以前存过的TextSize ,进行回显
        chooseWhich = SharedPrefUtil.getInt(getApplicationContext(), "TextSize", 2);
        settings.setTextZoom(textSize[chooseWhich]);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override  // 开始加载网页
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pb_loading.setVisibility(View.VISIBLE);

                // 起这个子线程没什么软用，就是为了有时候网页加载完了，进度条还不隐藏的情况
                // 这里强制五秒钟后，不管加不加载完，都要隐藏掉，要不看着不爽
                new Thread(){
                    @Override
                    public void run() {
                        SystemClock.sleep(5000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pb_loading.setVisibility(View.INVISIBLE);
                            }
                        });
                    }

                }.start();
            }

            @Override  // 网页加载结束
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i("tag","网页加载完了");
                pb_loading.setVisibility(View.INVISIBLE);
            }

            @Override  // 点击网页里所有链接跳转都会走此方法
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);// 在跳转链接时强制在当前webview中加载
                return true;// true表示在此界面的view中跳转
            }
        });
        //        mWebView.goBack(); //跳到上个页面
        //        mWebView.goForward();//跳到下个页面

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override  //  进度发生变化  newProgress
            public void onProgressChanged(WebView view, int newProgress) {
               pb_loading.setProgress(newProgress);
            }

            @Override  //  网页标题  title
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_back:
                finish();
                break;
            case R.id.ibtn_textsize:
                changeTextSize();
                break;
            case R.id.ibtn_share:
                showShare();
                break;
        }
    }

     int chooseWhich = 2; // 点击确定以后，保存的字体大小
     int [] textSize = new int[]{200,150,100,75,50};
    private void changeTextSize() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置字体大小");
        String[] items = new String[]{"超大号字体","大号字体","正常字体","小号字体","超小号字体"};
        builder.setSingleChoiceItems(items, chooseWhich, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chooseWhich = which;  // 使用这里的 which
            }
        });
        builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {  //这里的 which 是默认值，不会改变，所以不用它
                settings.setTextZoom(textSize[chooseWhich]);
                SharedPrefUtil.setInt(getApplicationContext(),"TextSize",chooseWhich);
            }
        });
        builder.setNegativeButton("cancel",null);
        builder.show();
    }


    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        oks.setTheme(OnekeyShareTheme.CLASSIC);

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("zhbj");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }
*/

}
