package com.thoughtworks.androidactivity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LifeCycleActivity extends AppCompatActivity {
    public final String TAG = LifeCycleActivity.this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "LifeCycleActivity is created!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "LifeCycleActivity is start!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "LifeCycleActivity is resume!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "LifeCycleActivity is pause!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "LifeCycleActivity is stop!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "LifeCycleActivity is restart!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "LifeCycleActivity will be destroy!");
    }
}
