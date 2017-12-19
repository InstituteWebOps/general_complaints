package com.example.harisanker.generalcomplaints;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NewCustomComplaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_custom_complaint);


        final SharedPreferences sharedPref = NewCustomComplaintActivity.this.getPreferences(Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        //final String url = "https://students.iitm.ac.in/studentsapp/complaints_portal/hostel_complaints/addComplaint.php";
        final String url = "https://rockstarharshitha.000webhostapp.com/addComplaint.php";
        final String hostel_url = "https://students.iitm.ac.in/studentsapp/studentlist/get_hostel.php";
        final String roll_no = Utils.getprefString(UtilStrings.ROLLNO, this);
        final String name = Utils.getprefString(UtilStrings.NAME, this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, hostel_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String hostel, room_no, code;

                try {
                    hostel = response.getString("hostel");
                    room_no = response.getString("roomno");
                    code = response.getString("code");
                    editor.putString("hostel", hostel);
                    editor.putString("roomno", room_no);
                    editor.putString("code", code);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(NewCustomComplaintActivity.this).addToRequestQueue(jsonObjectRequest);


        Button saveCustomCmplnt = (Button) findViewById(R.id.button_save);
        final EditText tv_title = (EditText) findViewById(R.id.editText_complaint_title);
        final EditText tv_description = (EditText) findViewById(R.id.editText_complaint_description);
        final EditText tv_tags = (EditText) findViewById(R.id.editText_tags);

        saveCustomCmplnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String title = tv_title.getText().toString();
                final String description = tv_description.getText().toString();
                final String tags = tv_tags.getText().toString();
                final String mUUID = UUID.randomUUID().toString();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsObject = new JSONObject(response);
                            String status = jsObject.getString("status");
                            if (status.equals("1")) {
                                // finish();
                                Intent intent = new Intent(NewCustomComplaintActivity.this, GeneralComplaintActivity.class);
                                startActivity(intent);
                            } else if (status.equals("0")) {
                                Toast.makeText(NewCustomComplaintActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        String hostel_name = sharedPref.getString("hostel", "narmada");
                        String room = sharedPref.getString("roomno", "1004");
                        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                        params.put("HOSTEL", hostel_name);
                        //TODO get name from prefs
                        params.put("NAME", "Omkar Patil");
                        //TODO get rollno from prefs
                        params.put("ROLL_NO", "me15b123");
                        params.put("ROOM_NO", room);
                        params.put("TITLE", title);
                        params.put("PROXIMITY", "");
                        params.put("DESCRIPTION", description);
                        params.put("UPVOTES", "0");
                        params.put("DOWNVOTES", "0");
                        params.put("RESOLVED", "0");
                        params.put("UUID", mUUID);
                        params.put("TAGS", tags);
                        params.put("DATETIME", date);
                        params.put("COMMENTS", "0");
                        return params;
                    }
                };
                MySingleton.getInstance(NewCustomComplaintActivity.this).addToRequestQueue(stringRequest);
            }
        });


    }
}
