package dduwcom.mobile.report.afinal.final_report_01_20151019;

import java.io.Serializable;

public class Daily implements Serializable {
    long _id;
    int month;
    int day;
    String title;
    String content;
    String place;
    int happy;

    public Daily(long _id, int month, int day, String place, String title, String content,  int happy) {
        this._id = _id;
        this.month = month;
        this.day = day;
        this.place = place;
        this.title = title;
        this.content = content;
        this.happy = happy;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }
}
