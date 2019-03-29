package com.project.projectquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.project.projectquiz.adapter.CategoryAdapter;
import com.project.projectquiz.helper.Helper;
import com.project.projectquiz.helper.JSONDownload;
import com.project.projectquiz.helper.JSONListener;
import com.project.projectquiz.model.Category;
import com.project.projectquiz.model.Subject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity implements JSONListener
{

    ArrayList<Subject> subjects;
    ArrayList<String> sbname;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        subjects = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lstvw_sub);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent = new Intent(SubjectActivity.this,ExamActivity.class);
                intent.putExtra("sbid",subjects.get(i).getSid());
                intent.putExtra("sbnme",subjects.get(i).getName());
                startActivity(intent);
            }
        });
        int catid = getIntent().getIntExtra("catId",0);
        JSONDownload jsonDownload = new JSONDownload(this);
        try {
            jsonDownload.execute(new URL(Helper.getBaseUrl() + "getsubjects.php?cid=" + catid));
        }
        catch (Exception e)
        {
            Log.i("App",e.getMessage());
        }
    }

    @Override
    public void onRemoteCallComplete(String json)
    {
        try {

            sbname = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Subject subject = new Subject();
                subject.setCid(jsonObject.getInt("categoryid"));
               subject.setName(jsonObject.getString("name"));
                subject.setUrl(jsonObject.getString("url"));
                subject.setSid(jsonObject.getInt("subjectid"));
                subject.setDescription(jsonObject.getString("descr"));
                subjects.add(subject);
                sbname.add(subject.getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(SubjectActivity.this,android.R.layout.simple_list_item_1,sbname);
            listView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Log.i("App","Error in Subject" +  e.getMessage());
        }
    }
}
