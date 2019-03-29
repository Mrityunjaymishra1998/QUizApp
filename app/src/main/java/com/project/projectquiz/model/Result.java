package com.project.projectquiz.model;

/**
 * Created by Varun on 06-08-2017.
 */

public class Result
{
    private String[] selans;
    private String[] crans;
    private int qno;
    private String rslt;

    public String[] getCrans() {
        return crans;
    }

    public void setCrans(String[] crans) {
        this.crans = crans;
    }

    public int getQno() {
        return qno;
    }

    public void setQno(int qno) {
        this.qno = qno;
    }

    public String[] getSelans() {
        return selans;

    }

    public void setSelans(String[] selans) {
        this.selans = selans;
    }

    public String getRslt() {
        return rslt;
    }

    public void setRslt(String rslt) {
        this.rslt = rslt;
    }

    public Result() {

    }

    public Result(String[] selans, String[] crans, int qno, String rslt) {

        this.selans = selans;
        this.crans = crans;
        this.qno = qno;
        this.rslt = rslt;
    }
}
