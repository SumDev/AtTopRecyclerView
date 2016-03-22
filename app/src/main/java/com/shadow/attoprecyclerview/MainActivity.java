package com.shadow.attoprecyclerview;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static String TOP_STATES = "TOP";

    private RecyclerView mRecyclerView;

    private RecyclerViewAdapter mRecyclerViewAdapter;

    private List<Session> sessionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        registerListener();
    }


    private void init() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        TypedArray typedArray = this.getResources().obtainTypedArray(R.array.icon_array);
        sessionList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Session session = new Session();
            session.setAvatar(typedArray.getResourceId(i, R.mipmap.ic_launcher));
            sessionList.add(session);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewAdapter = new RecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
//        ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                int fromPosition = viewHolder.getAdapterPosition();
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//
//            }
//        };
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
//        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        refreshView();
        typedArray.recycle();
    }


    private void registerListener() {

        mRecyclerViewAdapter.setItemListener(new RecyclerViewAdapter.ItemOnLongClickListener() {
            @Override
            public void itemLongClick(final Session session) {
                Bundle bundle = new Bundle();
                bundle.putInt(TOP_STATES, session.getTop());
                PopupDialogFragment popupDialog = new PopupDialogFragment();
                popupDialog.setArguments(bundle);
                popupDialog.setItemOnClickListener(new PopupDialogFragment.DialogItemOnClickListener() {
                    @Override
                    public void onTop() {
                        //置顶
                        session.setTop(1);
                        session.setTime(System.currentTimeMillis());
                        refreshView();
                    }

                    @Override
                    public void onCancel() {
                        //取消
                        session.setTop(0);
                        session.setTime(System.currentTimeMillis());
                        refreshView();
                    }
                });
                popupDialog.show(getFragmentManager(), "popup");
            }
        });

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "hello", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    private void refreshView() {
        //如果不调用sort方法，是不会进行排序的，也就不会调用compareTo
        Collections.sort(sessionList);
        mRecyclerViewAdapter.updateData(sessionList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
