package com.example.flipassignment.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "event_table")
public class EventEntity {

    @PrimaryKey(autoGenerate = true)
    int e_id;

    private String type;
    private String title;
    private String description;
    private String organizer;
    private String start_date;
    private String end_date;
    private String website;
    private String email;
    private String venue;
    private String address;
    private String city;
    private String country;
    private String screenshot;

    public EventEntity(String type, String title, String description, String organizer, String start_date, String end_date,
                       String website, String email, String venue, String address, String city, String country, String screenshot) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.organizer = organizer;
        this.start_date = start_date;
        this.end_date = end_date;
        this.website = website;
        this.email = email;
        this.venue = venue;
        this.address = address;
        this.city = city;
        this.country = country;
        this.screenshot = screenshot;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }
}
