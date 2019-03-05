package com.pratik.surela.retrofitmaster.ServiceCall;

public interface OnServiceCallResponse {
    void onResponseSuccess(Object response);
    void onResponseFail(String strErrorMessage);
}
