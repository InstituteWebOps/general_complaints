package com.example.harisanker.generalcomplaints;

import android.content.Context;
import android.util.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by harshitha on 19/12/17.
 */

public class CmntDataParser {
    Context context;
    private InputStream stream;
    private ArrayList<CommentObj> commentArray;

    public CmntDataParser(String string, Context c) {
        stream = new ByteArrayInputStream(string.getBytes(Charset.forName("UTF-8")));
        commentArray = new ArrayList<>();
        context = c;
    }

    public ArrayList<CommentObj> pleaseParseMyData() throws IOException {

        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
            reader.setLenient(true);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            return readCommentsArray(reader);
        } finally {

            reader.close();

        }

    }

    public ArrayList<CommentObj> readCommentsArray(JsonReader reader) throws IOException {
        ArrayList<CommentObj> comments = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            comments.add(readComment(reader));
        }
        reader.endArray();
        return comments;

    }

    public CommentObj readComment(JsonReader reader) throws IOException {

        CommentObj commentObj = new CommentObj();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                commentObj.setName(reader.nextString());
            } else if (name.equals("rollno")) {
                commentObj.setRollNo(reader.nextString());
            } else if (name.equals("roomno")) {
                commentObj.setRoomNo(reader.nextString());
            } else if (name.equals("comment")) {
                commentObj.setCommentStr(reader.nextString());
            } else if (name.equals("datetime")) {
                commentObj.setDate(reader.nextString());
            } else if (name.equals("error")) {
                reader.nextString();
            } else if (name.equals("status")) {
                reader.nextString();
                reader.endObject();
                return CommentObj.getErrorCommentObject();

            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return commentObj;
    }
}
