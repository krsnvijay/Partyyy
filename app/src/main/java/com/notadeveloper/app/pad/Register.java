package com.notadeveloper.app.pad;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

public class Register extends AppCompatActivity {
  AutoCompleteTextView name;
  AutoCompleteTextView email;
  AutoCompleteTextView number;
  CheckBox check;
  Boolean s;
  DatabaseReference mDatabase;
  FirebaseAuth mAuth;
  String uid;

  private static String UppercaseFirstLetters(String str) {
    if (str == null) {
      str = "";
    }
    boolean prevWasWhiteSp = true;
    char[] chars = str.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (Character.isLetter(chars[i])) {
        if (prevWasWhiteSp) {
          chars[i] = Character.toUpperCase(chars[i]);
        }
        prevWasWhiteSp = false;
      } else {
        prevWasWhiteSp = Character.isWhitespace(chars[i]);
      }
    }
    return new String(chars);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.register);

    name = (AutoCompleteTextView) findViewById(R.id.name);
    number = (AutoCompleteTextView) findViewById(R.id.number);
    email = (AutoCompleteTextView) findViewById(R.id.email);
    check = (CheckBox) findViewById(R.id.check);

    mAuth = FirebaseAuth.getInstance();
    mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
    FirebaseUser mUser = mAuth.getCurrentUser();

    if (mUser != null) {
      // Name, email address, and profile photo Url
      uid = mUser.getUid();
      String profilename = mUser.getDisplayName();
      name.setText(UppercaseFirstLetters(profilename));

      String profileemail = mUser.getEmail();
      email.setText(profileemail);
    }
    s = false;
    TextView text = (TextView) findViewById(R.id.terms);
    text.setPaintFlags(text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    text.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this, R.style.pop);
        builder.setTitle("Terms and conditions");
        builder.setMessage("T & C");
        builder.setPositiveButton("OK", null);
        // builder.setNegativeButton("Cancel", null);
        builder.show();
      }
    });

    Button register = (Button) findViewById(R.id.register);
    register.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        boolean cancel = false;

        View focusView = null;

        final String a = name.getText().toString();
        final String b = number.getText().toString();
        final String f = email.getText().toString();

        TextInputLayout name1 = (TextInputLayout) findViewById(R.id.name1);
        TextInputLayout number1 = (TextInputLayout) findViewById(R.id.number1);

        TextInputLayout email = (TextInputLayout) findViewById(R.id.emaila);
        check = (CheckBox) findViewById(R.id.check);

        if (!check.isChecked()) {
          cancel = true;
          Snackbar.make(findViewById(android.R.id.content), "Accept T&C", Snackbar.LENGTH_SHORT)
              .setActionTextColor(Color.WHITE)
              .show();
          focusView = check;
        }

        if (isEditTextEmpty(a)) {
          name1.setError("Field cannot be empty!");
          focusView = name1;
          cancel = true;
        } else {
          name1.setError(null);
        }
        if (isEditTextEmpty(f)) {
          email.setError("Field cannot be empty!");
          focusView = email;
          cancel = true;
        } else {
          email.setError(null);
        }
        if (isEditTextEmpty(b)) {
          number1.setError("Field cannot be empty!");
          focusView = number1;
          cancel = true;
        } else {
          number1.setError(null);
        }

        if (!isValidEmail(f)) {
          email.setError("Invalid e-mail address");
          focusView = email;
          cancel = true;
        } else {
          email.setError(null);
        }

        if (!isValidPhone(b)) {
          number1.setError("Invalid Number");
          focusView = number1;
          cancel = true;
        } else {
          number1.setError(null);
        }

        if (check.isChecked() == false) {
          cancel = true;
          focusView = null;
          Toast.makeText(Register.this, "Accept the terms and conditions first!",
              Toast.LENGTH_LONG).show();
        }

        if (cancel) {
          // There was an error; don't attempt login and focus the first
          // form field with an error.
          focusView.requestFocus();
        } else {
          users user = new users(uid, a, b, f);

          mDatabase.child(uid).setValue(user);
          Intent i = new Intent(Register.this, MainActivity.class);
          startActivity(i);
          finish();
        }
      }
    });
  }

  private boolean isEditTextEmpty(String mInput) {
    return mInput.length() == 0;
  }

  private boolean isValidPhone(CharSequence target) {
    return !isEmpty(target)
        && android.util.Patterns.PHONE.matcher(target).matches()
        && target.length() == 10;
  }

  private boolean isValidEmail(String email) {

    Pattern pattern = Patterns.EMAIL_ADDRESS;
    return !isEmpty(email) && pattern.matcher(email).matches();
  }
}
