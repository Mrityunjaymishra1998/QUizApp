package com.project.projectquiz.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Varun on 19-07-2017.
 */

public class DownloadImage extends AsyncTask<URL,Void,Bitmap>
{
    ImageView iv = null;

    public DownloadImage(ImageView imageView)
    {
        iv = imageView;
    }

    @Override
    protected Bitmap doInBackground(URL... urls)
    {
        Bitmap bitmap = null;
        try
        {
            URL url = urls[0];
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(20000);
            urlConnection.setReadTimeout(20000);

            InputStream inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        catch (Exception e)
        {
            Log.i("App","Exception in DownloadImage: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap)
    {
        iv.setImageBitmap(bitmap);
    }
}

