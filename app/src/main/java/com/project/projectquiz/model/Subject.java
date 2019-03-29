package com.project.projectquiz.model;

/**
 * Created by Varun on 19-07-2017.
 */

public class Subject
{
    private int sid;
    private int cid;
    private String name;
    private String url;
    private String description;

    public Subject() {
    }

    public Subject(int sid, int cid, String name, String url, String description) {
        this.sid = sid;
        this.cid = cid;
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
