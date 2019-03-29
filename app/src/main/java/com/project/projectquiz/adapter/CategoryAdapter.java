package com.project.projectquiz.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.projectquiz.R;
import com.project.projectquiz.helper.Helper;
import com.project.projectquiz.model.Category;
import com.project.projectquiz.helper.DownloadImage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Varun on 18-07-2017.
 */

public class CategoryAdapter extends ArrayAdapter<Category>
{
    ImageView imagecat;
    TextView txtcatname;

    public CategoryAdapter(Context context, ArrayList<Category> categories)
    {
        super(context,0,categories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view = convertView;
        if(view==null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.cat_custom,parent,false);
        }

        Category category = getItem(position);
        imagecat = (ImageView) view.findViewById(R.id.img_cat);
        txtcatname = (TextView) view.findViewById(R.id.txt_cat_name);

        txtcatname.setText(category.getName());

        try
        {
            DownloadImage task = new DownloadImage(imagecat);
            task.execute(new URL(Helper.getBaseUrl() + category.getUrl()));
        }
        catch (Exception e)
        {
            Log.i("App","Exception in CatAdapter: " + e.getMessage());
        }
        return view;
    }
}

