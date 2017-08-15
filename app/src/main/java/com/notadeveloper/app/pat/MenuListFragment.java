package com.notadeveloper.app.pat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by mxn on 2016/12/13.
 * MenuListFragment
 */

public class MenuListFragment extends Fragment {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_menu, container,
        false);

    NavigationView vNavigation = view.findViewById(R.id.vNavigation);
    vNavigation.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(MenuItem menuItem) {
            int id = menuItem.getItemId();
            if (id == R.id.logout) {

              final AlertDialog.Builder builder =
                  new AlertDialog.Builder(getActivity(), R.style.pop);
              builder.setMessage("Are You Sure?");
              builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                  AuthUI.getInstance()
                      .signOut(getActivity())
                      .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                          // user is now signed out
                          Intent i = new Intent(getActivity(), Login
                              .class);
                          i.setFlags(
                              Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                          startActivity(i);
                          //finish();
                        }
                      });
                  Toast.makeText(getActivity(), "Logout",
                      Toast.LENGTH_LONG).show();
                }
              });
              builder.setNegativeButton("No", null);
              builder.show();
            } else if (id == R.id.tac) {
              AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.pop);
              builder.setTitle("Terms and conditions");
              builder.setMessage("T & C");
              builder.setPositiveButton("OK", null);
              // builder.setNegativeButton("Cancel", null);
              builder.show();
            } else if (id == R.id.about) {
              Intent i = new Intent(getActivity(), AboutActivity.class);
              startActivity(i);

            } else if (id == R.id.action_sheesha) {
              Intent i = new Intent(getActivity(), SheeshaActivity.class);
              startActivity(i);
            } else if (id == R.id.order_sheesha) {

            }

            return true;
          }
        });
    // setupHeader();
    return view;
  }

  //    private void setupHeader() {
  //        int avatarSize = getResources().getDimensionPixelSize(R.dimen.global_menu_avatar_size);
  //        String profilePhoto = getResources().getString(R.string.user_profile_photo);
  //        Picasso.with(getActivity())
  //                .load(profilePhoto)
  //                .placeholder(R.drawable.img_circle_placeholder)
  //                .resize(avatarSize, avatarSize)
  //                .centerCrop()
  //                .transform(new CircleTransformation())
  //                .into(ivMenuUserProfilePhoto);
  //    }
}