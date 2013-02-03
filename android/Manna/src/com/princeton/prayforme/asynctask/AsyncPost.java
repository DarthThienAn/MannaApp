package com.princeton.prayforme.asynctask;

import android.os.AsyncTask;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.providers.RestTemplateProvider;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class AsyncPost<T> extends AsyncTask<Object, Void, T> {
    private String url;
    private String params;
    private Class<T> clazz;

    public AsyncPost(String url, String params, Class<T> clazz) {
        super();
        this.url = url;
        this.clazz = clazz;
        this.params = params;
    }

    @Override
    protected T doInBackground(Object... strings) {
        GlobalConstants.log("AsyncPost url/params:", url + " " + params);
        RestTemplate restTemplate = RestTemplateProvider.getRestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> requestEntity = new HttpEntity<String>(params, requestHeaders);
        T object = null;
        try {
            object = restTemplate.postForObject(url, requestEntity, clazz);
            GlobalConstants.log("AsyncPost do", object);
        } catch (Exception e) {
            GlobalConstants.log("AsyncPost do failure", e.toString());
            e.printStackTrace();
        }
        return object;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(T s) {
        super.onPostExecute(s);
        GlobalConstants.log("AsyncPost success", s);

    }

    @Override
    protected void onCancelled(T s) {
        super.onCancelled(s);
        GlobalConstants.log("AsyncPost canceled", s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}