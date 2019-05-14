package com.example.g2mdx.data.network;

public interface ResponseHandler<T> {

    void onSuccess(T result);

    void onFailure(String errorMessage);

}
