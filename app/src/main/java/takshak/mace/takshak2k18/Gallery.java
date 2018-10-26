package takshak.mace.takshak2k18;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Gallery extends AppCompatActivity {
    private static final String TAG = "imageurl";
    FirebaseDatabase database;
    DatabaseReference myRef;
    GridView gridView;
    ImageView expimgview;
    ActionBar ab;
    LinearLayout expndlayout;
    RelativeLayout containerview;
    AlertDialog alertDialog;
    AlertDialog nointernet;
    Button sendimagebtn;
    AlertDialog.Builder builder,nwbuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        database = FirebaseDatabase.getInstance();
        expimgview = findViewById(R.id.expandedimgview);
        expndlayout = findViewById(R.id.expandedlayout);
        sendimagebtn=findViewById(R.id.sendimagebtn);
        setTitle("Takshak Gallery");
        ab = getSupportActionBar();
        //containerview = findViewById(R.id.containerlayout);

        /*String[] i = {"https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Cat_poster_1.jpg/1200px-Cat_poster_1.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Cat_November_2010-1a.jpg/200px-Cat_November_2010-1a.jpg",
        "https://lh3.googleusercontent.com/aR34MxRBretppyADbJcfqIZp-LraO1ELhk00lTZw0Q7MF1ebUKZeggeQkjBuZCCmYRSYNzr8=w640-h400-e365",
        "https://cdn.pixabay.com/photo/2018/04/28/22/03/dawn-3358468_1280.jpg",
        "https://cdn.pixabay.com/photo/2017/04/16/01/53/girl-2233820_960_720.jpg",
        "https://cdn.pixabay.com/photo/2017/07/12/22/52/woman-2498668_1280.jpg"};
*/

        sendimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uri uri = Uri.parse("smsto:" + "+917994244829");
                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
                sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators("917994244829")+"@s.whatsapp.net");//phone number without "+" prefix
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
                Toast.makeText(getApplicationContext(),"Send Your Images To this whatsapp contact",Toast.LENGTH_LONG).show();
            }
        });

        gridView = findViewById(R.id.gridview);
        gridView = (GridView) findViewById(R.id.gridview);

        nwbuilder=new AlertDialog.Builder(this);
        nwbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setMessage("Turn On Internet");

        nointernet=nwbuilder.create();


        builder = new AlertDialog.Builder(this);
        builder.setMessage("Getting ready in a moment...").setCancelable(false);
        alertDialog = builder.create();

        expndlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expndlayout.setVisibility(View.GONE);
            }
        });

        myRef = database.getReference("imageurl");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (alertDialog.isShowing()){
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Click to Expand",Toast.LENGTH_SHORT).show();
                }
                ArrayList<String> urls = new ArrayList<>();
                for (DataSnapshot urlSnapshot: dataSnapshot.getChildren()) {
                    String value = urlSnapshot.getValue(String.class);
                    Log.d(TAG, "Value is: " + value);
                    urls.add(value);

                }
                Collections.reverse(urls);
                String[] i = urls.toArray(new String[0]);
                gridView.setAdapter(new ImageAdapter(Gallery.this,i,expimgview,expndlayout));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        if (!isnetworkconnected()){
            if (!nointernet.isShowing()){
                nointernet.show();
            }
        }else {
            if (!alertDialog.isShowing()){
                alertDialog.show();
            }
        }
    }

    public boolean isnetworkconnected()
    {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            connected = true;
        }
        else
            connected = false;

        return connected;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
