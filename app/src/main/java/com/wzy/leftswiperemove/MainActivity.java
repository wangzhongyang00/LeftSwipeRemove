package com.wzy.leftswiperemove;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王中阳 on 2016/11/28.
 * 邮箱 : wangzhongyang@maiyatian.com
 * 微信(QQ)：425772719
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    SwipeMenuListView listView;
    MainActivity context;
    List<MyBean> data = new ArrayList<>();
    CollectorListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        listView = (SwipeMenuListView) findViewById(R.id.collector_listview);
        initData();
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            data.add(new MyBean(i, " name" + i));
        }
        adapter = new CollectorListAdapter(context, data);
        listView.setAdapter(adapter);
        listView.setEmptyView(context.findViewById(R.id.collector_listview_empty));


        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(context.getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(255, 74, 109)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                // set item title font color
                deleteItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);

        // step 2. listener item click event
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                MyBean device = data.get(position);

                switch (index) {
                    case 0:
                        // delete
                        Log.e(TAG, "onMenuItemClick: 点击删除" + position);
                        delete(device);
                        data.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

        // set SwipeListener
        listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                MyBean device = data.get(position);
                open(device);
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    /**
     * 打开
     *
     * @param item
     */
    private void open(MyBean item) {

    }

    /**
     * 删除
     *
     * @param item
     */
    private void delete(MyBean item) {

    }
}

