package xyz.ibat.sloth.domain.main.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.base.BaseActivity;
import xyz.ibat.sloth.base.TabsFragment;
import xyz.ibat.sloth.utils.SlothUtil;

/**
 * create by DongJr 2016/03/31
 * 图片素材，参考 https://design.google.com/icons/
 * http://www.jianshu.com/p/7cb2cc9b726a?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.main_toolbar)
    Toolbar mainToolbar;
    @Bind(R.id.navigation_main)
    NavigationView mNavigationView;
    @Bind(R.id.drawLayout)
    DrawerLayout mDrawerLayout;

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initDrawLayout();
        initFragment();
    }

    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_root,new TabsFragment());
        transaction.commitAllowingStateLoss();
    }


    private void initToolbar() {
        setSupportActionBar(mainToolbar);
        mainToolbar.setNavigationIcon(R.mipmap.ic_toolbar_drawer);
//      mainToolbar.setLogo();
//      mainToolbar.setSubtitle();
        mainToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = "";
                switch (item.getItemId()){
                    case R.id.action_search:
                        msg = "search";
                        break;
                    case R.id.action_about:
                        msg = "about";
                        break;
                    case R.id.action_settings:
                        msg = "setting";
                        break;
                }
                SlothUtil.showToast(msg);
                return false;
            }
        });
    }

    private void initDrawLayout() {
        //DrawerLayout和Toolbar关联
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mainToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                MeiziActivity.startActivity(this);
                break;
            case R.id.nav_messages:
                SlothUtil.showToast("MESSAGE");
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(this,MessageActivity.class));
                break;
            case R.id.nav_friends:
                SlothUtil.showToast("FRIENDS");
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(this,FriendActivity.class));
                break;
            case R.id.nav_discussion:
                SlothUtil.showToast("DISCUSSION");
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(this,DiscussionActivity.class));
                break;
            case R.id.sub_item1:
                SlothUtil.showToast("SUB_ITEM1");
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.sub_item2:
                SlothUtil.showToast("SUB_ITEM2");
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
        return false;
    }

    /**
     * Activity彻底运行起来之后的回调
     * Called when activity start-up is complete
     * (after onStart() and onRestoreInstanceState(Bundle) have been called).
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //箭头旋转动画的关键
        mDrawerToggle.syncState();
    }


}
