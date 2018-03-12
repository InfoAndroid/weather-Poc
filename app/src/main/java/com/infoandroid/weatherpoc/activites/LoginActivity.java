package com.infoandroid.weatherpoc.activites;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.infoandroid.weatherpoc.R;

import com.infoandroid.weatherpoc.utils.Shared_Preferance_utility;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;


public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private TextView textView;
    private ImageView mgProfile;

    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            displayMessage(profile);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        FacebookSdk.sdkInitialize(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        callbackManager = CallbackManager.Factory.create();
        mgProfile = (ImageView)findViewById(R.id.mgProfile);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        textView = (TextView) findViewById(R.id.textView);
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);

        accessTokenTracker= new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                if (newProfile==null){
                    Shared_Preferance_utility.ClearPreferences(LoginActivity.this);
                }else {
                    Shared_Preferance_utility.SetPreferences(LoginActivity.this,"userName",newProfile.getName());
                    Shared_Preferance_utility.SetPreferences(LoginActivity.this,"userimageUrl","https://graph.facebook.com/"+ newProfile.getId() + "/picture?type=large" );

                }


                displayMessage(newProfile);
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void displayMessage(Profile profile){
        if(profile != null){
            textView.setText(profile.getName());
            Picasso.with(this).load("https://graph.facebook.com/" + profile.getId() + "/picture?type=large").fit().centerCrop()
                    .placeholder(R.drawable.com_facebook_profile_picture_blank_portrait)
                    .error(R.drawable.com_facebook_profile_picture_blank_portrait)
                    .into(mgProfile);

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayMessage(profile);
    }


}
