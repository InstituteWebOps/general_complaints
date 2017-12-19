package com.example.harisanker.generalcomplaints;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralComplaintActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener , View.OnClickListener{

        final String hostel_url = "https://students.iitm.ac.in/studentsapp/studentlist/get_hostel.php";
        private Toolbar toolbar;
        private TabLayout tabLayout;
        private ViewPager viewPager;
        private FloatingActionButton fab;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_general_complaint);

            final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPref.edit();

            /*toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

            // TODO shift this code to loginActivity
            StringRequest stringRequest = new StringRequest(Request.Method.POST, hostel_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String hostel, room_no, code;

                    try {
                        JSONObject jsObject = new JSONObject(response);

                        hostel = jsObject.getString("hostel");
                        room_no = jsObject.getString("roomno");
                        code = jsObject.getString("code");
                        editor.putString("hostel", hostel);
                        editor.putString("roomno", room_no);
                        editor.putString("code", code);
                        editor.apply();
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
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("rollno", "ME15B123");
                    return params;
                }
            };
            MySingleton.getInstance(this).addToRequestQueue(stringRequest);

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);

            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

            fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setVisibility(View.GONE);
            fab.setOnClickListener(this);
        }

        private void setupViewPager(ViewPager viewPager) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new LatestThreadFragment(), "Latest thread");
            adapter.addFragment(new TrendingThreadFragment(), "Trending thread");
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(this);
        }

        @Override
        public void onBackPressed() {
            //Intent intent = new Intent(MainActivity.this, ComplaintBoxActivity.class);
            //startActivity(intent);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == 0)
                fab.show();
            else
                fab.hide();
        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0)
                fab.show();
            else
                fab.hide();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(GeneralComplaintActivity.this, NewCustomComplaintActivity.class);
            startActivity(intent);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main_menu, menu);
            return true;
        }

        /*@Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_about) {
                Intent intent = new Intent(GeneralComplaintActivity.this, AboutUsActivity.class);
                startActivity(intent);
                return true;
            } else if (id == R.id.action_log_out) {
                LogOutAlertClass lg = new LogOutAlertClass();
                lg.isSure(GeneralComplaintActivity.this);
                return true;
            } else if (id == R.id.home) {
                onBackPressed();
                return true;
            }
            return super.onOptionsItemSelected(item);

        }*/

        class ViewPagerAdapter extends FragmentPagerAdapter {
            private final List<Fragment> mFragmentList = new ArrayList<>();
            private final List<String> mFragmentTitleList = new ArrayList<>();

            public ViewPagerAdapter(FragmentManager manager) {
                super(manager);
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            public void addFragment(Fragment fragment, String title) {
                mFragmentList.add(fragment);
                mFragmentTitleList.add(title);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentTitleList.get(position);
            }
        }
    }

