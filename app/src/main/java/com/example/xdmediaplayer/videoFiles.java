package com.example.xdmediaplayer;

public class videoFiles {
    String id;
    String path;
    String title;
    String fileName;
    String size;
    String dateAdded;
    String duration;

    public videoFiles(String id, String path, String fileName, String duration, String size, String dateAdded, String title) {
        this.id = id;
        this.path = path;
        this.fileName = fileName;
        this.duration = duration;
        this.size = size;
        this.dateAdded = dateAdded;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
