package com.example.harisanker.generalcomplaints;

/**
 * Created by harisanker on 22/6/17.
 */

public class Complaints {
    String hostel;
    String name;
    String rollNo;
    String roomNo;
    String title;
    String subject;
    String proximity;
    String description;
    String tags;
    int upvotes;
    int downvotes;
    int type;
    boolean resolved;
    String uid;

    public Complaints() {
    }

    public Complaints(String hostel, String name, String rollNo, String roomNo, String title, String subject, String proximity, String description, String tags, int upvotes, int downvotes, int type, boolean resolved, String uid) {
        this.hostel = hostel;
        this.name = name;
        this.rollNo = rollNo;
        this.roomNo = roomNo;
        this.title = title;
        this.subject = subject;
        this.proximity = proximity;
        this.description = description;
        this.tags = tags;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.type = type;
        this.resolved = resolved;
        this.uid = uid;
    }
}
