package com.pratik.surela.retrofitmaster.ServiceCall;

import android.content.Context;

import java.util.HashMap;

public class ServiceCallUtils extends ServiceCallHandler {

    private String TAG = "ServiceCallUtils";
    private OnServiceCallResponse onServiceCallResponse;
    public final static String POST_NO_HEADER = "POST_NO_HEADER", POST_WITH_HEADER = "POST_WITH_HEADER",
            GET_NO_HEADER = "GET_NO_HEADER", GET_WITH_HEADER = "GET_WITH_HEADER", POST_TOKEN_URL_ENCODED = "POST_TOKEN_URL_ENCODED";

    public ServiceCallUtils(Context context, String strURL, HashMap<String, String> paramsMap, Object model, String requestType, OnServiceCallResponse onServiceCallResponse) {
        super(context, strURL, paramsMap, model, requestType);
        this.onServiceCallResponse = onServiceCallResponse;
    }

    public ServiceCallUtils(Context context, String strURL, HashMap<String, String> paramsMap, Object model, String requestType, OnServiceCallResponse onServiceCallResponse, boolean isLoadingShow) {
        super(context, strURL, paramsMap, model, requestType, isLoadingShow);
        this.onServiceCallResponse = onServiceCallResponse;
    }


    @Override
    protected void onServiceCallSuccessResponse(Object response) {
        onServiceCallResponse.onResponseSuccess(response);
    }

    @Override
    protected void onServiceCallFailResponse(String strErrorMessage) {
        onServiceCallResponse.onResponseFail(strErrorMessage);
    }
}
