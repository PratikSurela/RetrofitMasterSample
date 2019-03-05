package com.pratik.surela.retrofitmaster.ServiceCall;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pratik.surela.retrofitmaster.R;
import com.pratik.surela.retrofitmaster.Utils.ConnectionCheck;
import com.pratik.surela.retrofitmaster.Utils.Utils;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ServiceCallHandler {

    private String TAG = "ServiceCallHandler";
    private Context context;
    private String strURL = "", strHeader = "", strParams = "";
    private GetDataService service;
    private RequestBody requestBody;
    private Dialog dialog;
    public Object model;
    private ConnectionCheck connectionCheck;
    private boolean isLoadingShow = true;

    public ServiceCallHandler(Context context, String strURL, HashMap<String, String> paramsMap, Object model, String requestType) {
        this.context = context;
        this.strURL = strURL;
        this.model = model;

        //Check internet
        connectionCheck = new ConnectionCheck();

        strParams = new Gson().toJson(paramsMap);
        requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), strParams);
        if (connectionCheck.isNetworkConnected(context)) {
            if (requestType.equalsIgnoreCase(ServiceCallUtils.POST_NO_HEADER)) {
                service = RetrofitClientApiInstance.getRetrofitInstance().create(GetDataService.class);
                callSimpleServiceCall();
            } else if (requestType.equalsIgnoreCase(ServiceCallUtils.POST_WITH_HEADER)) {
                service = RetrofitClientApiInstance.getRetrofitInstance().create(GetDataService.class);
                callHeaderServiceCall();
            } else if (requestType.equalsIgnoreCase(ServiceCallUtils.GET_NO_HEADER)) {
                service = RetrofitClientApiInstance.getRetrofitInstance().create(GetDataService.class);
                callSimpleGETServiceCall();
            } else if (requestType.equalsIgnoreCase(ServiceCallUtils.GET_WITH_HEADER)) {
                service = RetrofitClientApiInstance.getRetrofitInstance().create(GetDataService.class);
            } else if (requestType.equalsIgnoreCase(ServiceCallUtils.POST_TOKEN_URL_ENCODED)) {
                service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                callPostTokenServiceCall(paramsMap);
            }
        } else {
            connectionCheck.showconnectiondialog(context);
        }
    }

    public ServiceCallHandler(Context context, String strURL, HashMap<String, String> paramsMap, Object model, String requestType, boolean isLoadingShow) {
        this.context = context;
        this.strURL = strURL;
        this.model = model;
        this.isLoadingShow = isLoadingShow;

        //Check internet
        connectionCheck = new ConnectionCheck();
        //Get header from pref

        strParams = new Gson().toJson(paramsMap);
        requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), strParams);
        if (connectionCheck.isNetworkConnected(context)) {
            if (requestType.equalsIgnoreCase(ServiceCallUtils.POST_NO_HEADER)) {
                service = RetrofitClientApiInstance.getRetrofitInstance().create(GetDataService.class);
                callSimpleServiceCall();
            } else if (requestType.equalsIgnoreCase(ServiceCallUtils.POST_WITH_HEADER)) {
                service = RetrofitClientApiInstance.getRetrofitInstance().create(GetDataService.class);
                callHeaderServiceCall();
            } else if (requestType.equalsIgnoreCase(ServiceCallUtils.GET_NO_HEADER)) {
                service = RetrofitClientApiInstance.getRetrofitInstance().create(GetDataService.class);
            } else if (requestType.equalsIgnoreCase(ServiceCallUtils.GET_WITH_HEADER)) {
                service = RetrofitClientApiInstance.getRetrofitInstance().create(GetDataService.class);
            } else if (requestType.equalsIgnoreCase(ServiceCallUtils.POST_TOKEN_URL_ENCODED)) {
                service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                callPostTokenServiceCall(paramsMap);
            }
        } else {
            connectionCheck.showconnectiondialog(context);
        }
    }

    private void callSimpleServiceCall() {
        if (isLoadingShow) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_loading);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            dialog.show();
        }

        service.CallSimplePostServiceCall(strURL, requestBody).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (response.body() != null) {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());
                    Log.d(TAG, "onResponse: prettyStaff1 : " + json);
                    onServiceCallSuccessResponse(gson.fromJson(json, (Class) model));
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                onServiceCallFailResponse(t.getMessage());
            }
        });
    }

    private void callSimpleGETServiceCall() {
        if (isLoadingShow) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_loading);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            dialog.show();
        }

        service.CallSimpleGetServiceCall(strURL).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (response.body() != null) {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());
                    Log.d(TAG, "onResponse: CallSimpleGetServiceCall : " + json);
                    onServiceCallSuccessResponse(gson.fromJson(json, (Class) model));
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                onServiceCallFailResponse(t.getMessage());
            }
        });
    }

    private void callHeaderServiceCall() {
        if (isLoadingShow) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_loading);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            dialog.show();
        }

        service.CallHeaderPostServiceCall(strURL, strHeader, requestBody).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (response.body() != null) {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());
                    Log.d(TAG, "onResponse: prettyStaff1 : " + json);
                    onServiceCallSuccessResponse(gson.fromJson(json, (Class) model));
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                onServiceCallFailResponse(t.getMessage());
            }
        });
    }

    private void callPostTokenServiceCall(HashMap<String, String> paramsMap) {
        if (isLoadingShow) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_loading);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            dialog.show();
        }
        service.CallPostTokenServiceCall(strURL, paramsMap).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (response.body() != null) {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());
                    Log.d(TAG, "onResponse: prettyStaff1 : " + json);
                    onServiceCallSuccessResponse(gson.fromJson(json, (Class) model));
                } else {
                    onServiceCallFailResponse(response.message());
                    Utils.showToast(context, response.message());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                onServiceCallFailResponse(t.getMessage());
            }
        });
    }

    protected abstract void onServiceCallSuccessResponse(Object response);

    protected abstract void onServiceCallFailResponse(String strErrorMessage);
}