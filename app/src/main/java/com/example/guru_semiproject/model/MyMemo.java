package com.example.guru_semiproject.model;

import java.io.Serializable;

public class MyMemo implements Serializable {
    private String memoContent;
    private String memoDate;
    private int imageId;

    public MyMemo(){}

    public MyMemo(String memoContent, String memoDate, int imageId) {
        this.memoContent = memoContent;
        this.memoDate = memoDate;
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "MyMemo{" +
                "memoContent='" + memoContent + '\'' +
                ", memoDate='" + memoDate + '\'' +
                ", imageId=" + imageId +
                '}';
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public String getMemoDate() {
        return memoDate;
    }

    public void setMemoDate(String memoDate) {
        this.memoDate = memoDate;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
