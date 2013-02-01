package com.princeton.prayforme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Security {
    /* returns a 32-char hash of s */
    public static String getMD5(String s) {
        GlobalConstants.log("MD5", s);
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            GlobalConstants.log("MD5", Arrays.toString(messageDigest));

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String hex = Integer.toHexString(0xFF & aMessageDigest);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            GlobalConstants.log("MD5", hexString.length() + " - " + hexString.toString());
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static View getColorSignature(LayoutInflater inflater, ViewGroup root, String s) {
        GlobalConstants.log("MD5", "Color sig - " + s);
        int alpha = 0xFF000000;
        int i = 0;
        View view = inflater.inflate(R.layout.color_signature, root, false);
        view.findViewById(R.id.signature_1).setBackgroundColor(alpha + Integer.parseInt(s.substring(i, i=i+6), 16));
        view.findViewById(R.id.signature_2).setBackgroundColor(alpha + Integer.parseInt(s.substring(i, i=i+6), 16));
        view.findViewById(R.id.signature_3).setBackgroundColor(alpha + Integer.parseInt(s.substring(i, i=i+6), 16));
        view.findViewById(R.id.signature_4).setBackgroundColor(alpha + Integer.parseInt(s.substring(i, i=i+6), 16));
        view.findViewById(R.id.signature_5).setBackgroundColor(alpha + Integer.parseInt(s.substring(i, i=i+6), 16));
        view.findViewById(R.id.signature_6).setBackgroundColor(alpha + Integer.parseInt(s.substring(i), 16));

        return view;
    }
}
