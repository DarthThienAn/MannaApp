package com.princeton.prayforme.helper;

import android.view.View;
import com.princeton.prayforme.GlobalConstants;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;

public class URLHelper {

    // Basic URLs
    public static String rootURL() {
        return buildBasicURL("");
    }

    public static String prayerURL() {
        return buildBasicURL("prayer");
    }

    // POST builders
    public static String postPrayer(String signature, String person, String subject, String message) {
        List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add(new BasicNameValuePair("signature", signature));
        params.add(new BasicNameValuePair("person", person));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("message", message));
        params.add(new BasicNameValuePair("kind", "1"));

        return buildParams(params);
    }

    public static String postReply(String signature, String person, String subject, String message) {
        List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add(new BasicNameValuePair("signature", signature));
        params.add(new BasicNameValuePair("person", person));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("message", message));

        return buildParams(params);
    }

    // GET URLs

    public static String getRecentURL(int count) {
        List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add(new BasicNameValuePair("count", String.valueOf(count)));
        return buildGetURL("recent", params);
    }

    public static String getPrayerURL(int prayer_id) {
        List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add(new BasicNameValuePair("prayer_id", String.valueOf(prayer_id)));
        return buildGetURL("prayer", params);
    }

    public static String getReplyURL(int[] reply_ids) {
        List<NameValuePair> params = new LinkedList<NameValuePair>();
        for (int id : reply_ids) {
            params.add(new BasicNameValuePair("reply_id", String.valueOf(id)));
        }
        return buildGetURL("reply", params);
    }

    // helper methods
    private static String buildBasicURL(String endpoint) {
        return GlobalConstants.API + endpoint;
    }

    private static String buildGetURL(String endpoint) {
        return buildGetURL(endpoint, new LinkedList<NameValuePair>());
    }

    private static String buildGetURL(String endpoint, List<NameValuePair> params) {
        return buildBasicURL(endpoint) + "?" + buildParams(params);
    }

    public static String buildParams(List<NameValuePair> params) {
        return URLEncodedUtils.format(params, "utf-8");
    }
}
