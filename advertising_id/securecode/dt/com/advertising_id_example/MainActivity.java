package advertising_id.securecode.dt.com.advertising_id_example;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    private String advertisingId;
    private boolean optOutEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * code to get advertising id. Should not
         * be called from main thread.
         */
        new Thread(new Runnable() {
            public void run() {
                try {
                    AdvertisingIdClient.AdInfo adInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
                    advertisingId = adInfo.getId();
                    optOutEnabled = adInfo.isLimitAdTrackingEnabled();

                    if(!optOutEnabled) {
                        Log.i(TAG, "advertising id is " + advertisingId);
                    } else {
                        Log.i(TAG, "User has opted not to use the advertising Id");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
