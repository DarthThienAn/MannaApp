package com.manna.MannaApp.asynctask;

import android.os.AsyncTask;
import com.manna.MannaApp.GlobalConstants;
import com.manna.MannaApp.providers.RestTemplateProvider;
import org.springframework.web.client.RestTemplate;

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
        RestTemplate restTemplate = RestTemplateProvider.getRestTemplate();
        T object = restTemplate.getForObject(url, clazz);
        GlobalConstants.log("AsyncGet do", object);
        return object;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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
    }


}
