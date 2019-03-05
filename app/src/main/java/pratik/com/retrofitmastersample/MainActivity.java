package pratik.com.retrofitmastersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.pratik.surela.retrofitmaster.ServiceCall.OnServiceCallResponse;
import com.pratik.surela.retrofitmaster.ServiceCall.ServiceCallUtils;
import com.pratik.surela.retrofitmaster.Utils.Utils;

import java.util.HashMap;

import pratik.com.retrofitmastersample.Model.IndiaResponse;

public class MainActivity extends AppCompatActivity implements OnServiceCallResponse {

    private String TAG = "MainActivity";
    private static String API_NAME = "IN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //No params required
        new ServiceCallUtils(this, API_NAME, new HashMap<String, String>(), IndiaResponse.class, ServiceCallUtils.GET_NO_HEADER, MainActivity.this);
    }

    @Override
    public void onResponseSuccess(Object response) {
        if (response instanceof IndiaResponse) {
            IndiaResponse countryResponse = (IndiaResponse) response;
            Utils.showToast(this, "Country Name : " + countryResponse.getRestResponse().getResult().getName());
            Utils.showToast(this, "Alpha Code : " + countryResponse.getRestResponse().getResult().getAlpha3Code());
        }
    }

    @Override
    public void onResponseFail(String strErrorMessage) {
        Log.d(TAG, "onResponseFail: strErrorMessage : " + strErrorMessage);
    }
}