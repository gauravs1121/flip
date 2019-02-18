package com.example.flipassignment.holder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinDetailHolder {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private ImageHolder image;
    @SerializedName("coingecko_score")
    @Expose
    private Double coingeckoScore;
    @SerializedName("developer_score")
    @Expose
    private Double developerScore;
    @SerializedName("community_score")
    @Expose
    private Double communityScore;
    @SerializedName("public_interest_score")
    @Expose
    private Double publicInterestScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageHolder getImage() {
        return image;
    }

    public void setImage(ImageHolder image) {
        this.image = image;
    }

    public Double getCoingeckoScore() {
        return coingeckoScore;
    }

    public void setCoingeckoScore(Double coingeckoScore) {
        this.coingeckoScore = coingeckoScore;
    }

    public Double getDeveloperScore() {
        return developerScore;
    }

    public void setDeveloperScore(Double developerScore) {
        this.developerScore = developerScore;
    }

    public Double getCommunityScore() {
        return communityScore;
    }

    public void setCommunityScore(Double communityScore) {
        this.communityScore = communityScore;
    }

    public Double getPublicInterestScore() {
        return publicInterestScore;
    }

    public void setPublicInterestScore(Double publicInterestScore) {
        this.publicInterestScore = publicInterestScore;
    }
}
