package com.notadeveloper.app.pat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

  int mNumOfTabs;

  public PagerAdapter(FragmentManager fm, int NumOfTabs) {
    super(fm);
    this.mNumOfTabs = NumOfTabs;
  }

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        PartyFragment tab1 = new PartyFragment();
        return tab1;
      case 1:
        ClubFragment tab2 = new ClubFragment();
        return tab2;
      default:
        return null;
    }
  }

  @Override
  public int getCount() {
    return mNumOfTabs;
  }
}
