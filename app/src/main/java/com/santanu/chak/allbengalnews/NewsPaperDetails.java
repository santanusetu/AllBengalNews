package com.santanu.chak.allbengalnews;


public class NewsPaperDetails {
    String newspaperName;
    int newsPaperImage;
    String newspaperURL;

    public NewsPaperDetails(String newspaperName, int newsPaperImage, String newspaperURL) {
        this.newspaperName = newspaperName;
        this.newsPaperImage = newsPaperImage;
        this.newspaperURL = newspaperURL;
    }

    public String getNewspaperName() {
        return newspaperName;
    }

    public int getNewsPaperImage() {
        return newsPaperImage;
    }


    public String getNewspaperURL() {
        return newspaperURL;
    }

}
