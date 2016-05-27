package com.example.rgdixit.haptikchat;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.rgdixit.haptikchat.chat.ChatAdapter;
import com.example.rgdixit.haptikchat.chat.ChatModelWrapper;
import com.example.rgdixit.haptikchat.network.NetworkAsyncCallBack;
import com.example.rgdixit.haptikchat.network.NetworkUtil;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mChatList;
    private View mLoadingView;
    private View mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChatList = (RecyclerView) findViewById(R.id.chat_list);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        llm.setStackFromEnd(true);
        mContainer = findViewById(R.id.container);
        mChatList.setLayoutManager(llm);
        mLoadingView = findViewById(R.id.loading_view);
        fetchData();
    }

    private void fetchData() {
        NetworkUtil.doAsyncGetCall(this, getString(R.string.data_url), new DataFetchCallBack(this));
    }

    private class DataFetchCallBack extends NetworkAsyncCallBack<ChatModelWrapper> {
        public DataFetchCallBack(Context context) {
            super(ChatModelWrapper.class, context);
        }


        @Override
        protected void onSuccessResponse(ChatModelWrapper response) {
            if (response != null && !isFinishing() && response.getMessages() != null) {
                // post changes to ui
                Log.d("Haptic", response.toString());
                mChatList.setAdapter(new ChatAdapter(MainActivity.this, response.getMessages()));
                mChatList.setVisibility(View.VISIBLE);
                mLoadingView.setVisibility(View.GONE);
            }
        }

        @Override
        protected void onFailure(String message) {
            if (!isFinishing()) {
                mLoadingView.setVisibility(View.GONE);
                Snackbar.make(mContainer, message, Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mLoadingView.setVisibility(View.VISIBLE);
                        fetchData();
                    }
                }).show();
            }
        }
    }
}
