package com.princeton.prayforme.asynctask;

import android.os.AsyncTask;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.providers.RestTemplateProvider;
import org.springframework.web.client.RestTemplate;

import java.util.EventListener;

public class AsyncGet<T> extends AsyncTask<String, String, T> {
    private String url;
    private Class<T> clazz;

    public AsyncGet(String url, Class<T> clazz) {
        super();
        this.url = url;
        this.clazz = clazz;
    }

    @Override
    protected T doInBackground(String... strings) {
        GlobalConstants.log("AsyncGet doinbackground", url);
        RestTemplate restTemplate = RestTemplateProvider.getRestTemplate();
        T object = null;
        try {
            object = restTemplate.getForObject(url, clazz);
            GlobalConstants.log("AsyncGet do", object);
        } catch (Exception e) {
            GlobalConstants.log("AsyncGet do failed", e);
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
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(T s) {
        super.onCancelled(s);
        GlobalConstants.log("AsyncGet canceled", s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        GlobalConstants.log("AsyncGet canceled", "no param");
    }
}
