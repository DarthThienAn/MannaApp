package com.princeton.prayforme.asynctask;

import android.os.AsyncTask;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.providers.RestTemplateProvider;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class AsyncPut extends AsyncTask<Object, Void, Void> {
    private String url;
    private String params;

    public AsyncPut(String url, String params) {
        super();
        this.url = url;
        this.params = params;
    }

    @Override
    protected Void doInBackground(Object... strings) {
        GlobalConstants.log("AsyncPut do", url + "..." + params);
        RestTemplate restTemplate = RestTemplateProvider.getRestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> requestEntity = new HttpEntity<String>(params, requestHeaders);
        try {
            restTemplate.put(url, requestEntity, Void.class);
            GlobalConstants.log("AsyncPut do", url + "..." + params);
        } catch (Exception e) {
            GlobalConstants.log("AsyncPut do failure", e.toString());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        GlobalConstants.log("AsyncPut preexecute", "");
    }

    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);
        GlobalConstants.log("AsyncPut success", s);
    }

    @Override
    protected void onCancelled(Void s) {
        super.onCancelled(s);
        GlobalConstants.log("AsyncPut canceled", s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}