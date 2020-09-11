package com.bohniman.incomingportal.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class FireSms {

    public static boolean doFireSMS(String mobile, String otp) {
        String msg = "Dear Applicant your OTP is " + otp + ". Use this to login at https://visitassam.org/";
        return sendMessageBySMS(mobile, msg);
        // return true;
        // "Dear " + V1
    }

    public static boolean sendMessageBySMS(String mobileNo, String message) {
        try {
            // https://alerts.solutionsinfini.com/api/v4/?api_key=A3711226269281ab372a99869b5cdaf4a&method=sms&message=Dear
            // Applicant your OTP is 12345&to=9954005079&sender=BONIMN
            String data = "api_key=A3711226269281ab372a99869b5cdaf4a&method=sms&message=" + message + "&to=" + mobileNo
                    + "&sender=BONIMN";
            String urlstr = "https://alerts.solutionsinfini.com/api/v4/";
            URI uri = new URI(urlstr);
            URL url = null;
            try {
                url = uri.toURL();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println(url + "?" + data);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setUseCaches(false);
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");

            DataOutputStream wr = new DataOutputStream(httpConn.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();
            httpConn.disconnect();
            System.out.println(httpConn);
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // reads server's response
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                String responsex = reader.readLine();
                System.out.println("SMS SEND SERVER RESPONSE\n" + responsex);
                if (responsex.contains("False")) {
                    System.out.println("Failed");
                    return false;
                } else {
                    System.out.println("Success");
                    return true;
                }
            } else {
                System.out.println("Server ERROR: " + responseCode);
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {

        }
    }

}