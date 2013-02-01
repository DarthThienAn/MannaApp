package com.princeton.prayforme.helper;

import android.view.View;
import com.princeton.prayforme.GlobalConstants;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;

public class URLHelper {

    private String name;
    private String signature;

    public URLHelper(String name, String signature) {
        this.name = name;
        this.signature = signature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    // Basic URLs
    public String rootURL() {
        return buildBasicURL("");
    }

    public String prayerURL() {
        return buildBasicURL("prayers");
    }

    public String repliesURL() {
        return buildBasicURL("replies");
    }

    public String personURL() {
        return buildBasicURL("person");
    }

    // POST builders
    public String postPrayer(String name, String subject, String message) {
        List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("message", message));

        return buildParams(params);
    }

    public String postReply(String name, String subject, String message) {
        List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("message", message));

        return buildParams(params);
    }

    // GET URLs

    public String getPrayerURL(int prayer_id) {
        List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add(new BasicNameValuePair("prayer_id", String.valueOf(prayer_id)));
        return buildGetURL("prayers", params);
    }

    public String getReplyURL(int[] reply_ids) {
        List<NameValuePair> params = new LinkedList<NameValuePair>();
        for (int id : reply_ids) {
            params.add(new BasicNameValuePair("reply_id", String.valueOf(id)));
        }
        return buildGetURL("reply", params);
    }

    // helper methods
    private String buildBasicURL(String endpoint) {
        return GlobalConstants.API + endpoint;
    }

    private  String buildGetURL(String endpoint) {
        return buildGetURL(endpoint, new LinkedList<NameValuePair>());
    }

    private  String buildGetURL(String endpoint, List<NameValuePair> params) {
        return buildBasicURL(endpoint) + "?" + buildParams(params);
    }

    public String buildParams(List<NameValuePair> params) {
        params.add(new BasicNameValuePair("signature", signature));

        return URLEncodedUtils.format(params, "utf-8");
    }
}
