package com.project.projectquiz.helper;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Varun on 19-07-2017.
 */

public class JSONDownload extends AsyncTask<URL,Void,String>
{
    JSONListener jsonListener;

    public JSONDownload(JSONListener jsonListener)
    {
        this.jsonListener = jsonListener;
    }

    @Override
    protected String doInBackground(URL... urls) {
        URL url = urls[0];

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(20000);
            connection.setConnectTimeout(20000);

            InputStream inputStream = connection.getInputStream();
            InputStreamReader  reade = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            BufferedReader bufferedReader = new BufferedReader(reade);

            StringBuilder op = new StringBuilder();
            String line = null;

            line = bufferedReader.readLine();

            while(line!=null)
            {
                op.append(line);
                line = bufferedReader.readLine();
            }
            return op.toString();
        }
        catch (Exception e)
        {
            Log.i("App",e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s)
    {
        jsonListener.onRemoteCallComplete(s);
    }
}
