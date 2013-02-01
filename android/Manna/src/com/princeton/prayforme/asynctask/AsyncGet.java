package com.princeton.prayforme.asynctask;

import android.os.AsyncTask;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.providers.RestTemplateProvider;
import org.springframework.web.client.RestTemplate;

import java.util.EventListener;

public class AsyncGet<T> extends AsyncTask<String, String, T> {
    private String url;
    private Class<T> clazz;
    private T result;

    public AsyncGet(String url, Class<T> clazz) {
        super();
        this.url = url;
        this.clazz = clazz;
    }

    @Override
    protected T doInBackground(String... strings) {
        RestTemplate restTemplate = RestTemplateProvider.getRestTemplate();
        T object = null;
        try {
            object = restTemplate.getForObject(url, clazz);
            GlobalConstants.log("AsyncGet do", object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        GlobalConstants.log("AsyncGet preexecute", "");
    }

    @Override
    protected void onPostExecute(T s) {
        super.onPostExecute(s);
        GlobalConstants.log("AsyncGet success", s);
        result = s;

    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(T s) {
        super.onCancelled(s);
        GlobalConstants.log("AsyncGet canceled", s);
        result = s;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        GlobalConstants.log("AsyncGet canceled", "no param");
    }

    public T getResult() {
        return result;
    }
}
