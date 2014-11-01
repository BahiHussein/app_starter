package appname.companyname.com.appname;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.os.Handler;
import android.content.Intent;
import android.util.Log;


public class SplashScreen extends Activity {

    ConnectionDetector mConnectionDetector;
    Functions mFunctions;
    public Boolean isConnected = false;
    private static int SPLASH_TIME_OUT = 4000;
    public int user_reg = 0;
    private ImageView ic_logo;
    private Animation fade_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Set Content View
        setContentView(R.layout.activity_splash_screen);

        // Defining Logo
        ic_logo = (ImageView)findViewById(R.id.splash_ic_logo);
        fade_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        ic_logo.startAnimation(fade_in);

        // Declaring mConnection Detector
        mConnectionDetector = new ConnectionDetector(getApplicationContext());

        // Declaring mFunction
        mFunctions = new Functions(getApplicationContext());

        // Post SplashScreen Time Out
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                // Check Internet, User and Form State
                isConnected = mConnectionDetector.isConnectingToInternet();
                if(isConnected == false){

                    //===>
                    //Log.d("Internet", "no connection");
                   
                }
                else {
                    Intent i = new Intent(SplashScreen.this, MyActivity.class);
                    startActivity(i);
                    finish();
                }
                 Intent i = new Intent(SplashScreen.this, MyActivity.class);
                    startActivity(i);
                    finish();
                
            }
        }, SPLASH_TIME_OUT);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_screen, menu);
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
}
