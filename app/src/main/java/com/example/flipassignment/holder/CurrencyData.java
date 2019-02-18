package com.example.flipassignment.holder;

public class CurrencyData {

    private Double usd;
    private Double inr;

    public CurrencyData(Double usd, Double inr) {
        this.usd = usd;
        this.inr = inr;
    }

    public Double getUsd() {
        return usd;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }

    public Double getInr() {
        return inr;
    }

    public void setInr(Double inr) {
        this.inr = inr;
    }
}
