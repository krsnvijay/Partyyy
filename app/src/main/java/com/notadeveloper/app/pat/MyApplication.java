package com.notadeveloper.app.pat;

import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by krsnv on 9/17/2017.
 */

public class MyApplication extends MultiDexApplication {
  static {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
  }

  @Override public void onCreate() {
    super.onCreate();
  }
}
