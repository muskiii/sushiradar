package org.fabiano.sushiradar.api.utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHelper {

    public static JSONObject get(String url){

        URL obj = null;
        HttpURLConnection con = null;
        JSONObject response = null;

        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            in.close();
              response = new JSONObject(stringBuffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
