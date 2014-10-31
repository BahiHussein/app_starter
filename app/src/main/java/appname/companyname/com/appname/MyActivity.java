package appname.companyname.com.appname;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import appname.companyname.com.appname.Rateme.AppRater;

public class MyActivity extends Activity {

    // UI Elements
    public Boolean fullscreen_webview = AppContent.fullscreen_webview;

    // Logs
    String tag = "state";

    // Functions
    Functions mFunctions;
    ConnectionDetector mConnectionDetector;

    // Google Play Ads
    private AdView adView;
    private InterstitialAd interstitial;
    String admobBannerID = AppContent.google_play_ads_banner_id;
    String admobInterstitialID = AppContent.google_play_ads_interstitial_id;
    Boolean admobBannerActive = AppContent.google_play_ads_banner_active;
    Boolean admobInterstitialActive = AppContent.google_play_ads_interstitial_active;

    // Rate me
    Boolean rateActive = AppContent.rate_dialog_active;

    // Connection
    String connection_error_message = AppContent.connection_error_message;
    public Boolean isConnected = false;

    // share
    String shareMessage = AppContent.share_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //===>> Logging
        Log.d(tag,"In OnCreate Event()");

        // Hiding the Action Bar for different android versions
        hideActionBar();

        // Set content view
        setContentView(R.layout.activity_my);

        // UI
        UI();

        // Declaring mConnection Detector
        mConnectionDetector = new ConnectionDetector(getApplicationContext());

        // Declaring mFunction
        mFunctions = new Functions(getApplicationContext());

        // Check Internet, User and Form State
        isConnected = mConnectionDetector.isConnectingToInternet();

        if(isConnected == false){
            // To do
        }else {
            // To do
        }

        // RateApp
        if (rateActive == true) {
            AppRater.app_rate(this);
        }

        // GooglePlayAds
        if (admobBannerActive == true) {
            admob_banner_block();
            admob_interstitial_block();
        }
    }

    public void onStart(){
        super.onStart();
        Log.d(tag, "In the onStart() event");
    }

    public void onRestart(){
        super.onRestart();
        Log.d(tag, "In the onRestart() event");
    }

    public void onResume(){
        super.onResume();
        Log.d(tag, "In the onResume() event");

        // Resume Google Play Ads
        if (adView != null) {
            adView.resume();
        }
    }

    public void onPause(){
        super.onPause();
        Log.d(tag, "In the onPause() event");
    }

    public void onStop(){
        super.onStop();
        Log.d(tag, "In the onStop() event");

        // Pause Google Play Ads
        if (adView != null) {
            adView.pause();
        }
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(tag, "In the onDestroy() event");

        // Destroy Google Play Ads
        if (adView != null) {
            adView.destroy();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // UI function
    public void UI(){
       //UI elements decliration
    }

    // Hide action bar
    @SuppressLint("NewApi")
    public void hideActionBar(){
        if (Build.VERSION.SDK_INT < 16 && fullscreen_webview == true) {
            // Hide the Action Bar on Android 4.0 and Lower
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else if (fullscreen_webview == true) {
            // Hide the Action Bar on Android 4.1 and Higher
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            android.app.ActionBar actionBar = getActionBar();
            actionBar.hide();
        }
    }

    // KeyDown_Back
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Log.d(tag, "Pressed Back");
            exitDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Exit Dialog
    public void exitDialog() {
        Log.d(tag, "In the exitDialog()");
        AlertDialog.Builder exitAlertDialog = new AlertDialog.Builder(MyActivity.this);

        exitAlertDialog.setTitle("Confirm Exit")
                .setMessage("Do you want to quit?")
                .setPositiveButton("Okay", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //===>> Logging
                        Log.d(tag, "Exit Dialog = true");
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //===>> Logging
                        Log.d(tag, "Exit Dialog = false");
                    }
                }).create();

        exitAlertDialog.show();
    }

    // Share Page
    private void sharepost() {
        Intent shareintent = new Intent(Intent.ACTION_SEND);
        shareintent.setType("text/plain");
        String xshare = "link";

            xshare = shareMessage; //add other condition

        shareintent.putExtra(Intent.EXTRA_TEXT, xshare);
        startActivity(Intent.createChooser(shareintent, "How do you want to share?"));
    }

    // Google Play Ads (banner)
    public void admob_banner_block() {
        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(admobBannerID);
        LinearLayout layout = (LinearLayout) findViewById(R.id.banner_ads_layout);
        layout.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    // Google Play Ads (interstitial)
    public void admob_interstitial_block() {
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(admobInterstitialID);
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial.loadAd(adRequest);
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayInterstitial();
            }
            public void onAdClosed() {
                // On Ads close
            }
        });
    }

    // Google Play Ads - interstitial (Display).
    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

}
