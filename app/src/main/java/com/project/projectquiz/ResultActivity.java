package com.project.projectquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.project.projectquiz.adapter.ResultAdapter;
import com.project.projectquiz.model.Result;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity
{

    ArrayList<Result> results;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        results = new ArrayList<>();

        String[] usrans = getIntent().getStringArrayExtra("selectedans");
        String[] crans = getIntent().getStringArrayExtra("correctans");
        int[] qno = getIntent().getIntArrayExtra("qno");
        int length = getIntent().getIntExtra("lngth",0);
        for(int i=0;i<length;i++)
        {
            Result result = new Result();
            result.setSelans(usrans);
            result.setCrans(crans);
            result.setQno(qno[i]);
           // Log.i("App",usrans[i]);
            if(usrans[i].equals("-"))
            {
                result.setRslt("Not Attempted");
            }
            else {
                if (usrans[i].equals(crans[i])) {
                    result.setRslt("Correct Answer");
                } else {
                    result.setRslt("Wrong Answer");
                }
            }
            results.add(result);
        }

        ListView rslt_lst = (ListView) findViewById(R.id.lstvw_rslt);
        ResultAdapter adapter = new ResultAdapter(ResultActivity.this,results);
        rslt_lst.setAdapter(adapter);

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ResultActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
