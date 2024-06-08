// Updated MarkDomain.java
package com.example.studyapp2023.Domain;

public class MarkDomain {
    private String sub;
    private String textMark;
    private int G;
    private String date;

    public MarkDomain(String sub, String textMark, int G, String date) {
        this.sub = sub;
        this.textMark = textMark;
        this.G = G;
        this.date = date;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getTextMark() {
        return textMark;
    }

    public void setTextMark(String textMark) {
        this.textMark = textMark;
    }

    public int getG() {
        return G;
    }

    public void setG(int G) {
        this.G = G;
    }

    public String getDate() {
        return date;
    }

}
