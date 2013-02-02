package com.princeton.prayforme.asynctask;

import android.os.AsyncTask;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.providers.RestTemplateProvider;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.EventListener;

public class AsyncDelete extends AsyncTask<Void, Void, Void> {
    private String url;

    public AsyncDelete(String url) {
        super();
        this.url = url;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        GlobalConstants.log("AsyncDelete doinbackground", url);
        RestTemplate restTemplate = RestTemplateProvider.getRestTemplate();
        try {
            restTemplate.delete(url);
            GlobalConstants.log("AsyncDelete do", url);
        } catch (Exception e) {
            GlobalConstants.log("AsyncDelete do failure", e.toString());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        GlobalConstants.log("AsyncDelete preexecute", "");
    }

    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);
        GlobalConstants.log("AsyncDelete success", s);
    }
}
