/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author heha
 */
public class Calendar {
    private int id,user_id;
    private Date start;
    private  String title,description;

    public Calendar() {
    }

    public Calendar(int user_id, Date start, String title, String description) {
        this.user_id = user_id;
        this.start = start;
        this.title = title;
        this.description = description;
    }

    public Calendar(int id, int user_id, Date start, String title, String description) {
        this.id = id;
        this.user_id = user_id;
        this.start = start;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Calendar{" + "id=" + id + ", user_id=" + user_id + ", start=" + start + ", title=" + title + ", description=" + description + '}';
    }
    
    
    
}
