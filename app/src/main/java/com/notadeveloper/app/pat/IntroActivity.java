package com.notadeveloper.app.pat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by krsnv on 9/23/2017.
 */

public class IntroActivity extends AppIntro {
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Note here that we DO NOT use setContentView();

    // Instead of fragments, you can also use our default slide
    // Just set a title, description, background and image. AppIntro will do the rest.
    addSlide(AppIntroFragment.newInstance("P.A.T", "Book Instant Tickets for Parties in Your City",
        R.drawable.ic_internet, ContextCompat.getColor(this, R.color.bgcolor1)));
    addSlide(AppIntroFragment.newInstance("Clubs",
        "View the various clubs in your area and find the best one suiting your needs",
        R.drawable.ic_party_dj, ContextCompat.getColor(this, R.color.bgcolor2)));
    addSlide(AppIntroFragment.newInstance("Premium Service",
        "Enjoy our other party related services only through P.A.T", R.drawable.ic_medal,
        ContextCompat.getColor(this, R.color.bgcolor3)));
    setFlowAnimation();
  }

  @Override public void onSkipPressed(Fragment currentFragment) {
    super.onSkipPressed(currentFragment);
    finish();
    // Do something when users tap on Skip button.
  }

  @Override public void onDonePressed(Fragment currentFragment) {
    super.onDonePressed(currentFragment);
    finish();
    // Do something when users tap on Done button.
  }

  @Override
  public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
    super.onSlideChanged(oldFragment, newFragment);
    // Do something when the slide changes.
  }
}