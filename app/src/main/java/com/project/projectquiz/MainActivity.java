package com.project.projectquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.project.projectquiz.adapter.CategoryAdapter;
import com.project.projectquiz.helper.Helper;
import com.project.projectquiz.helper.JSONDownload;
import com.project.projectquiz.helper.JSONListener;
import com.project.projectquiz.model.Category;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements JSONListener
{

    ArrayList<Category> categories;
    GridView gridView;

    @Override
    public void onRemoteCallComplete(String json)
    {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Category category = new Category();
                category.setId(jsonObject.getInt("categoryid"));
                category.setName(jsonObject.getString("name"));
                category.setUrl(jsonObject.getString("url"));
                category.setDesc(jsonObject.getString("descr"));
                categories.add(category);
            }
            CategoryAdapter adapter = new CategoryAdapter(MainActivity.this,categories);
            gridView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Log.i("App","Error in Main" +  e.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categories = new ArrayList<>();
        gridView = (GridView) findViewById(R.id.gridview_main);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,SubjectActivity.class);
                intent.putExtra("catId",categories.get(i).getId());
                startActivity(intent);
            }
        });

        JSONDownload jsonDownload = new JSONDownload(this);
        try {
            jsonDownload.execute(new URL(Helper.getBaseUrl() + "getcategories.php"));
        }
        catch (Exception e)
        {
            Log.i("App",e.getMessage());
        }
    }
}
