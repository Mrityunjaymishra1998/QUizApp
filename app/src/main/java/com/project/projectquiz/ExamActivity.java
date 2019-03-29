package com.project.projectquiz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project.projectquiz.helper.Helper;
import com.project.projectquiz.helper.JSONDownload;
import com.project.projectquiz.helper.JSONListener;
import com.project.projectquiz.model.Exam;
import com.project.projectquiz.model.Subject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class ExamActivity extends AppCompatActivity implements JSONListener
{
    String[] answers;
    int[] crctans = new int[20];
    int[] userans = new int[20];
    String[] selanswr = new String[50];
    String[] crctanswr = new String[50];
    String crntanswr;
    String[] prevanswr = new String[4];
    int[] quesno = new int[20];
    int optnno,j;
    boolean res,ent;
    int nosel=1;
    ArrayList<Exam> exams;
    TextView exmnme, timer, ques;
    CheckBox op1, op2, op3, op4;
    Button next;
    int i;
    JSONArray jsonArray;
    CountDownTimer ctimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        exams = new ArrayList<>();

        int subid = getIntent().getIntExtra("sbid",0);
        String sbnme = getIntent().getStringExtra("sbnme");
        exmnme = (TextView)findViewById(R.id.txt_exmnme);
        timer = (TextView)findViewById(R.id.txt_timer);
        ques = (TextView) findViewById(R.id.txt_ques);
        op1 = (CheckBox) findViewById(R.id.chk_optn1);
        op2 = (CheckBox) findViewById(R.id.chck_optn2);
        op3 = (CheckBox) findViewById(R.id.chk_optn3);
        op4 = (CheckBox) findViewById(R.id.chk_optn4);
        next = (Button) findViewById(R.id.btn_nxt);

        exmnme.setText(sbnme);
        JSONDownload jsonDownload = new JSONDownload(this);
        try {
            jsonDownload.execute(new URL(Helper.getBaseUrl() + "getexams.php?sid=" + subid));
        }
        catch (Exception e)
        {
            Log.i("App",e.getMessage());
        }



       op1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
       {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b && exams.get(i).getType().equals("Multiple"))
                {
                    if (nosel == 1)
                    {
                        userans[i] = 0;
                        selanswr[i] = exams.get(i).getAnswrs()[userans[i]];
                        prevanswr[userans[i]] = selanswr[i];
                        nosel = 2;
                        Log.i("App",selanswr[i]);
                    }
                    else
                    {
                            userans[i]=0;
                            crntanswr = exams.get(i).getAnswrs()[userans[i]];
                            prevanswr[userans[i]] = crntanswr;
                            selanswr[i] = prevanswr[0];
                            for(int j=1;j<4;j++)
                            {
                                if(prevanswr[j]!=null)
                                selanswr[i] += ", " + prevanswr[j];
                            }
                            nosel++;
                        Log.i("App",selanswr[i]);
                    }
                }
                else
                {
                    if (!b && exams.get(i).getType().equals("Multiple")&&!ent)
                    {
                        optnno = 0;
                        if (nosel == 2)
                        {
                            nosel = 1;
                            prevanswr[0]=null;
                            Log.i("App",selanswr[i]);
                        }
                        else
                        {
                            nosel--;
                            prevanswr[optnno]=null;
                            for(j=0;j<4;j++) {
                                if(prevanswr[j]!=null) {
                                    selanswr[i] = prevanswr[j];
                                    j++;
                                    break;
                                }
                            }
                            for(;j<4;j++)
                            {
                                if(prevanswr[j]!=null)
                                    selanswr[i] += ", " + prevanswr[j];
                            }
                            Log.i("App",selanswr[i]);
                        }
                    }
                    else
                    {
                        if (b && exams.get(i).getType().equals("Single") && !res)
                        {
                            userans[i] = 0;
                            selanswr[i] = exams.get(i).getAnswrs()[userans[i]];
                            Log.i("App", selanswr[i]);
                            res = true;
                        }
                        else
                        {
                            if (!b)
                            {
                                res = false;
                                nosel = 1;
                            }
                            else
                            {
                                op1.setChecked(false);
                                Toast.makeText(ExamActivity.this, "Single Answer type Question", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });



        op2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b && exams.get(i).getType().equals("Multiple"))
                {

                    if (nosel == 1)
                    {
                        userans[i] = 1;
                        selanswr[i] = exams.get(i).getAnswrs()[userans[i]];
                        prevanswr[userans[i]] = selanswr[i];
                        nosel = 2;
                        Log.i("App",selanswr[i]);
                    }
                    else
                    {
                            userans[i]=1;
                            crntanswr = exams.get(i).getAnswrs()[userans[i]];
                            prevanswr[userans[i]] = crntanswr;
                            for(j=0;j<4;j++) {
                                if(prevanswr[j]!=null) {
                                    selanswr[i] = prevanswr[j];
                                    j++;
                                    break;
                                }
                            }
                            for(;j<4;j++)
                            {
                                if(prevanswr[j]!=null)
                                selanswr[i] += ", " + prevanswr[j];
                            }
                            nosel++;
                        Log.i("App",selanswr[i]);
                    }
                }
                else
                {
                    if (!b && exams.get(i).getType().equals("Multiple")&&!ent)
                    {
                        optnno = 1;
                        if (nosel == 2)
                        {
                            prevanswr[optnno]=null;
                            prevanswr[0]=null;
                            nosel = 1;
                            Log.i("App", selanswr[i]);
                        }
                        else
                        {
                            nosel--;
                            prevanswr[optnno]=null;
                            for(j=0;j<4;j++) {
                                if(prevanswr[j]!=null) {
                                    selanswr[i] = prevanswr[j];
                                    j++;
                                    break;
                                }
                            }
                            for(;j<4;j++)
                            {
                                if(prevanswr[j]!=null)
                                    selanswr[i] += ", " + prevanswr[j];
                            }
                            Log.i("App", selanswr[i]);
                        }
                    }
                    else
                    {
                        if (b && exams.get(i).getType().equals("Single") && !res) {
                            userans[i] = 1;
                            selanswr[i] = exams.get(i).getAnswrs()[userans[i]];
                            res = true;
                            Log.i("App", selanswr[i]);
                        } else {
                            if (!b) {
                                res = false;
                                nosel = 1;
                            } else {
                                op2.setChecked(false);
                                Toast.makeText(ExamActivity.this, "Single Answer type Question", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });

        op3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b && exams.get(i).getType().equals("Multiple"))
                {
                    if (nosel == 1)
                    {
                        userans[i] = 2;
                        selanswr[i] = exams.get(i).getAnswrs()[userans[i]];
                        prevanswr[userans[i]] = selanswr[i];
                        nosel = 2 ;
                        Log.i("App",selanswr[i]);
                    }
                    else
                    {
                            userans[i]=2;
                            crntanswr = exams.get(i).getAnswrs()[userans[i]];
                            prevanswr[userans[i]] = crntanswr;
                            for(j=0;j<4;j++) {
                                if(prevanswr[j]!=null) {
                                    selanswr[i] = prevanswr[j];
                                    j++;
                                    break;
                                }
                            }
                            for(;j<4;j++)
                            {
                                if(prevanswr[j]!=null)
                                selanswr[i] += ", " + prevanswr[j];
                            }
                            nosel++;
                        Log.i("App",selanswr[i]);
                    }
                }
                else
                {
                    if (!b && exams.get(i).getType().equals("Multiple") && !ent) {
                        optnno = 2;
                        if (nosel == 2) {
                            nosel = 1;
                            prevanswr[optnno]=null;
                            prevanswr[0]=null;
                            Log.i("App", selanswr[i]);
                        } else
                        {
                            nosel--;
                            prevanswr[optnno]=null;
                            for(j=0;j<4;j++) {
                                if(prevanswr[j]!=null) {
                                    selanswr[i] = prevanswr[j];
                                    j++;
                                    break;
                                }
                            }
                            for(;j<4;j++)
                            {
                                if(prevanswr[j]!=null)
                                    selanswr[i] += ", " + prevanswr[j];
                            }

                            Log.i("App", selanswr[i]);
                        }
                    } else {
                        if (b && exams.get(i).getType().equals("Single") && !res) {
                            userans[i] = 2;
                            selanswr[i] = exams.get(i).getAnswrs()[userans[i]];
                            res = true;
                            Log.i("App", selanswr[i]);
                        } else {
                            if (!b) {
                                res = false;
                                nosel = 1;
                            } else {
                                op3.setChecked(false);
                                Toast.makeText(ExamActivity.this, "Single Answer type Question", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });

        op4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b && exams.get(i).getType().equals("Multiple"))
                {
                    userans[i] = 3;
                    if (nosel == 1)
                    {
                        selanswr[i] = exams.get(i).getAnswrs()[userans[i]];
                        prevanswr[userans[i]] = selanswr[i];
                        nosel = 2;
                        Log.i("App",selanswr[i]);
                    }
                    else
                    {
                            crntanswr = exams.get(i).getAnswrs()[userans[i]];
                            prevanswr[userans[i]] = crntanswr;
                            selanswr[i] += ", " + crntanswr;
                            nosel++;
                            Log.i("App",selanswr[i]);
                    }
                }
                else {
                    if (!b && exams.get(i).getType().equals("Multiple") &&!ent) {
                        optnno = 3;
                        if (nosel == 2) {
                            nosel = 1;
                            prevanswr[optnno]=null;
                            prevanswr[0]=null;
                            Log.i("App", selanswr[i]);
                        } else
                        {
                            nosel--;
                            prevanswr[optnno]=null;
                            for(j=0;j<4;j++) {
                                if(prevanswr[j]!=null) {
                                    selanswr[i] = prevanswr[j];
                                    j++;
                                    break;
                                }
                            }
                            for(;j<4;j++)
                            {
                                if(prevanswr[j]!=null)
                                    selanswr[i] += ", " + prevanswr[j];
                            }
                            Log.i("App", selanswr[i]);
                        }
                    } else {
                        if (b && exams.get(i).getType().equals("Single") && !res) {
                            userans[i] = 3;
                            selanswr[i] = exams.get(i).getAnswrs()[userans[i]];
                            res = true;
                            Log.i("App", selanswr[i]);
                        } else {
                            if (!b) {
                                res = false;
                                nosel = 1;
                            } else {
                                op4.setChecked(false);
                                Toast.makeText(ExamActivity.this, "Single Answer type Question", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }

        });
    }

    @Override
    public void onRemoteCallComplete(String json)
    {
        try
        {
            jsonArray = new JSONArray(json);
            for(i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Exam exam = new Exam();
                exam.setType(jsonObject.getString("type"));
                exam.setQuestions(jsonObject.getString("question"));
                JSONArray jsonArray1 = jsonObject.getJSONArray("answers");
                answers = new String[]{jsonArray1.get(0).toString(),jsonArray1.get(1).toString(),jsonArray1.get(2).toString(),jsonArray1.get(3).toString()};
                exam.setAnswrs(answers);
                exam.setType(jsonObject.getString("type"));
                JSONArray jsonArray2 = jsonObject.getJSONArray("canswers");
                for(int j=0;j<jsonArray2.length();j++)
                {
                    crctans[i] = Integer.parseInt(jsonArray2.getString(j));
                    if(j==0)
                    crctanswr[i] = answers[crctans[i]];
                    if (jsonArray2.length() > 1 && j>0)
                    {
                        crctanswr[i] += ", ";
                        crctanswr[i] += answers[crctans[i]];
                    }
                }
                    exam.setCrctanswr(crctanswr[i]);
                    Log.i("App",exam.getCrctanswr());
                exam.setSbjctid(jsonObject.getInt("qid"));
                Log.i("App",exam.getType());
                quesno[i] = exam.getSbjctid();
                exams.add(exam);
            }
            i=0;
            ques.setText(exams.get(i).getQuestions());
            op1.setText(exams.get(i).getAnswrs()[0]);
            op2.setText(exams.get(i).getAnswrs()[1]);
            op3.setText(exams.get(i).getAnswrs()[2]);
            op4.setText(exams.get(i).getAnswrs()[3]);

            starttimer();

        }
        catch (Exception e)
        {
            Log.i("App","Error in Question No." +  e.getMessage());
        }
    }

    public void Prev(View view)
    {
        if(i!=0)
        {
            i--;
            next.setText("next");
            ques.setText(exams.get(i).getQuestions());
            op1.setText(exams.get(i).getAnswrs()[0]);
            op2.setText(exams.get(i).getAnswrs()[1]);
            op3.setText(exams.get(i).getAnswrs()[2]);
            op4.setText(exams.get(i).getAnswrs()[3]);
            op1.setChecked(false);
            op2.setChecked(false);
            op3.setChecked(false);
            op4.setChecked(false);
            cancelTimer();
            starttimer();

        }
        else
        {
            Toast.makeText(ExamActivity.this,"Already the First Question",Toast.LENGTH_SHORT).show();
        }
    }

    public void nxt(View view)
    {
        if(!op1.isChecked() && !op2.isChecked() && !op3.isChecked() && !op4.isChecked())
        {
            selanswr[i] = "-";
        }
        ent = true;
        op1.setChecked(false);
        op2.setChecked(false);
        op3.setChecked(false);
        op4.setChecked(false);
        if(i<jsonArray.length()-1)
        {
            i++;
            ques.setText(exams.get(i).getQuestions());
            op1.setText(exams.get(i).getAnswrs()[0]);
            op2.setText(exams.get(i).getAnswrs()[1]);
            op3.setText(exams.get(i).getAnswrs()[2]);
            op4.setText(exams.get(i).getAnswrs()[3]);

            cancelTimer();
            starttimer();

            if(i==jsonArray.length()-1)
            {
                next.setText("FINISH");
                return;
            }
        }
        if(i==jsonArray.length()-1)
        {
            Intent intent = new Intent(ExamActivity.this,ResultActivity.class);
            intent.putExtra("correctans",crctanswr);
            intent.putExtra("selectedans",selanswr);
            intent.putExtra("qno",quesno);
            intent.putExtra("lngth",jsonArray.length());
            cancelTimer();
            startActivity(intent);
        }

    }

    void starttimer()
    {
        ctimer = new CountDownTimer(31000,1000)
        {
            @Override
            public void onTick(long l) {
                timer.setText("Time Remaining: " + l/1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(ExamActivity.this,"Time's Up!",Toast.LENGTH_SHORT).show();
                nxt(findViewById(R.id.btn_nxt));
            }
        }.start();
    }

    void cancelTimer()
    {
        if(ctimer!=null)
        {
            ctimer.cancel();
        }
    }
}
