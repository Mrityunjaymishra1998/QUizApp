package com.project.projectquiz.model;

import java.util.ArrayList;

/**
 * Created by Varun on 19-07-2017.
 */

public class Exam
{
    private int Examid;
    private int sbjctid;
    private String questions;
    private String[] answrs;
    private String crctanswr;
    private String type;

    public Exam(int examid, int sbjctid, String questions, String[] answrs, String crctanswr, String type) {
        Examid = examid;
        this.sbjctid = sbjctid;
        this.questions = questions;
        this.answrs = answrs;
        this.crctanswr = crctanswr;
        this.type = type;
    }

    public Exam() {
    }

    public int getExamid() {
        return Examid;
    }

    public void setExamid(int examid) {
        Examid = examid;
    }

    public int getSbjctid() {
        return sbjctid;
    }

    public void setSbjctid(int sbjctid) {
        this.sbjctid = sbjctid;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String[] getAnswrs() {
        return answrs;
    }

    public void setAnswrs(String[] answrs) {
        this.answrs = answrs;
    }

    public String getCrctanswr() {
        return crctanswr;
    }

    public void setCrctanswr(String crctanswr) {
        this.crctanswr = crctanswr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
