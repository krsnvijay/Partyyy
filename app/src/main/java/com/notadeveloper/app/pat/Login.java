package com.notadeveloper.app.pat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Arrays;

public class Login extends AppCompatActivity {
  private static final int RC_SIGN_IN = 9001;
  private static boolean calledAlready = false;
  private final String TAG = "Login";
  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    if (!calledAlready) {
      FirebaseDatabase.getInstance().setPersistenceEnabled(true);
      calledAlready = true;
    }
    mAuth = FirebaseAuth.getInstance();
    mAuthListener = new FirebaseAuth.AuthStateListener() {
      @Override
      public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user == null) {

        } else {

        }

        // ...
      }
    };

    startActivityForResult(
        AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setTheme(R.style.AppTheme_NoActionBar)
            .setLogo(R.mipmap.ic_launcher)
            .setIsSmartLockEnabled(true)
            .setAvailableProviders(
                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                    new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()))
            .build(),
        RC_SIGN_IN);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RC_SIGN_IN) {
      IdpResponse response = IdpResponse.fromResultIntent(data);

      // Successfully signed in
      if (resultCode == RESULT_OK) {
        checkuserexits();
      } else {
        // Sign in failed
        if (response == null) {
          Snackbar.make(findViewById(android.R.id.content), R.string.sign_in_cancelled,
              Snackbar.LENGTH_SHORT).show();
          startActivity(new Intent(Login.this, Login.class));
          finish();
          return;
        }

        if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
          Snackbar snackbar = Snackbar
              .make(findViewById(android.R.id.content), R.string.no_internet_connection,
                  Snackbar.LENGTH_INDEFINITE)
              .setAction("RETRY", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  startActivity(new Intent(Login.this, Login.class));
                  finish();
                }
              });

          snackbar.show();
          return;
        }

        if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
          Snackbar.make(findViewById(android.R.id.content), R.string.unknown_error,
              Snackbar.LENGTH_SHORT).show();
          return;
        }
      }

      //Snackbar.make(findViewById(android.R.id.content), R.string.unknown_error, Snackbar.LENGTH_SHORT).show();
    }

    //Toast.makeText(Login.this, R.string.unknown_response,
    //Toast.LENGTH_LONG).show();
  }

  private void checkuserexits() {
    final String userid = mAuth.getCurrentUser().getUid();

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    mDatabase.keepSynced(true);

    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot datasnapshot) {
        if (datasnapshot.child("users").hasChild(userid)) {
          startActivity(new Intent(Login.this, MainActivity.class));
          finish();
        } else {
          startActivity(new Intent(Login.this, Register.class));
          finish();
        }
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }
}


