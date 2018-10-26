package takshak.mace.takshak2k18;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.thefinestartist.finestwebview.FinestWebView;

import static takshak.mace.takshak2k18.R.color.black;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Tab1.OnFragmentInteractionListener,Tab3.OnFragmentInteractionListener {

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    VideoView vid;
    String path;
    Uri videouri;
    FloatingActionButton galleryFab;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    private static final int REQUEST_WRITE_STORAGE = 112;
    String geturl = "https://demo4664908.mockable.io/happy";
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // toolbar.setBackgroundColor(Color.rgb(0,0,0));
        toolbar.setTitleTextColor(Color.WHITE);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        //tabLayout.addTab(tabLayout.newTab().setText("Premium"));
        tabLayout.addTab(tabLayout.newTab().setText("Reach Radio"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        galleryFab = findViewById(R.id.fabgallery);
        //rateusFab = findViewById(R.id.fabrateus);
        tabLayout.setTabTextColors(Color.rgb(242,244,244),Color.WHITE);
        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        builder = new AlertDialog.Builder(this,R.style.AlertDialog);
        builder.setCancelable(false).setMessage("I'm Booting Up...");

        alertDialog = builder.create();

        galleryFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Gallery.class);
                startActivity(intent);
            }
        });
//        rateusFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.takshak.mace.takshak2k18user")));
//            }
//        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setRippleColor(Color.WHITE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!alertDialog.isShowing()){
                    alertDialog.show();
                }
                startActivity(new Intent(MainActivity.this,chatbot.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        boolean hasPermission = (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();
            AlertDialog br = new AlertDialog.Builder(this)
                    .setTitle(Html.fromHtml("<font color='#000000'>Exit</font>"))
                    .setMessage(Html.fromHtml("<font color='#000000'>Want to exit ?</font>"))
                    .setCancelable(true)
                    .setNeutralButton("Rate us", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.takshak.mace.takshak2k18user")));
                        }
                    })
                    .setPositiveButton(Html.fromHtml("<font color='#4286f4'>Yes</font>"), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton(Html.fromHtml("<font color='#4286f4'>No</font>"), null)
                    .show();
            Button pos = br.getButton(DialogInterface.BUTTON_NEGATIVE);
            pos.setTextColor(Color.rgb(66, 134, 244));
            Button nev = br.getButton(DialogInterface.BUTTON_POSITIVE);
            nev.setTextColor(Color.rgb(66, 134, 244));
            br.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(Color.rgb(66, 134, 244));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    protected void onResume() {
        if (alertDialog.isShowing()){
            alertDialog.dismiss();
        }
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.rate) {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.takshak.mace.takshak2k18user")));
        }
        if (id == R.id.notify){
            goto_notification();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.login) {
            // notificatons
            Intent i = new Intent(getApplicationContext(),profile.class);
            startActivity(i);
        }
        else if (id == R.id.profile){
           // profile
            Intent pro = new Intent(getApplicationContext(),ProfileCardView.class);
            startActivity(pro);
        }
         else if (id == R.id.about) {
            //about
            startActivity(new Intent(MainActivity.this,About.class));

        } else if (id == R.id.locate) {
            //locate in maps
            double latitude = 10.053952;
            double longitude = 76.619336;
            String label = "We are Here !";
            String uriBegin = "geo:" + latitude + "," + longitude;
            String query = latitude + "," + longitude + "(" + label + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=5";
            Uri uri = Uri.parse(uriString);
            Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
            startActivity(mapIntent);

        } else if (id ==R.id.facebook){
            //facebook
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/274889042525005"));
                startActivity(intent);
            } catch(Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/TakshakOfficial/")));
            }
        } else if (id == R.id.instagram){
            //insta
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("com.instagram.android.https://www.instagram.com/takshak18"));
                startActivity(intent);
            } catch(Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/takshak18")));
            }
        }else if (id == R.id.twitter){
            try {
                // get the Twitter app if possible
                getApplicationContext().getPackageManager().getPackageInfo("com.twitter.android", 0);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=OfficialTakshak"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (Exception e) {
                // no Twitter app, revert to browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/OfficialTakshak"));
                startActivity(intent);
            }
        }else if (id == R.id.website){
            Toast.makeText(getApplicationContext(),"Click register and scroll down",Toast.LENGTH_LONG).show();
            new FinestWebView.Builder(MainActivity.this).webViewJavaScriptEnabled(true).webViewJavaScriptCanOpenWindowsAutomatically(true).showProgressBar(true).show("http://takshak.in");
        }
        else if(id == R.id.dev){
            startActivity(new Intent(MainActivity.this,devs.class));
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
    public void get(View v){
        Toast.makeText(this,"sss",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void goto_notification(){
        Intent iv = new Intent(getApplicationContext(),profile.class);
        startActivity(iv);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //reload my activity with permission granted or use the features what required the permission
                } else
                {
                    Toast.makeText(MainActivity.this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
