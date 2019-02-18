package com.example.flipassignment.holder;

import com.example.flipassignment.database.entity.EventEntity;

import java.util.ArrayList;
import java.util.List;

public class EventHolder {

    private List<EventEntity> data = new ArrayList<>();
    private Integer count;
    private Integer page;

    public List<EventEntity> getData() {
        return data;
    }

    public void setData(List<EventEntity> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
