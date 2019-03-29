package com.project.projectquiz.model;

/**
 * Created by Varun on 18-07-2017.
 */

public class Category
{
    private int id;
    private String name;
    private String url;
    private String desc;

    public Category()
    {

    }

    public Category(int id, String name, String url, String desc)
    {
        this.id = id;
        this.name = name;
        this.url = url;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
