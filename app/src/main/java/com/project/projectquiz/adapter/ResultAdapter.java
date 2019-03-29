package com.project.projectquiz.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project.projectquiz.R;
import com.project.projectquiz.model.Result;

import java.util.ArrayList;

/**
 * Created by Varun on 06-08-2017.
 */

public class ResultAdapter extends ArrayAdapter<Result>
{
    TextView tvqno, tvcans, tvselans,tvrslt;

    public ResultAdapter(Context context, ArrayList<Result> resultArrayList)
    {
        super(context,0,resultArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view = convertView;
        if(view==null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.rslt_custom,parent,false);
        }

        Result result = getItem(position);

        tvqno = view.findViewById(R.id.txtvw_quesno);
        tvcans = view.findViewById(R.id.txtvw_crctans);
        tvselans = view.findViewById(R.id.txtvw_userans);
        tvrslt = view.findViewById(R.id.txtve_rslt);

        //Log.i("App",String.valueOf(result.getQno()) + result.getCrans()[position] + result.getSelans()[position] + result.getRslt());
        tvqno.setText("Q. No. " + String.valueOf(result.getQno()));
        tvcans.setText("Correct Answer: " + result.getCrans()[position]);
        tvselans.setText("Your Response: " + result.getSelans()[position]);
        tvrslt.setText(result.getRslt() + "!");
        return view;
    }
}
