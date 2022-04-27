package com.fynnjason.mvvmarchitecture.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Simple7Entity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String english;
    private String chinese;

    public Simple7Entity(String english, String chinese) {
        this.english = english;
        this.chinese = chinese;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}
