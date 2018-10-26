package takshak.mace.takshak2k18;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.ArrayList;

public class WorkShopEventAdapter extends ArrayAdapter<EventObject> {
    Context context;
    Fragment frag;
    ArrayList<EventObject> eventObjects;
    SharedPreferences sharedPreferences;
    public final String UserPREFERENCES = "userinfo";
    public final String NAME = "username";
    public final String USERID = "usserid";
    public final String MOBILENO = "mobileno";
    public final String EMAILID = "emailid";
    Activity activity;
    String uid;

    public WorkShopEventAdapter(@NonNull Context context, int resource, @NonNull ArrayList<EventObject> objects, Activity activity) {
        super(context, resource, objects);
        eventObjects = objects;
        this.activity=activity;
        sharedPreferences = context.getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
        this.uid = sharedPreferences.getString(USERID,"NOT REGISTERED");
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.event_item_layout, null);

        final TextView eventNameView = (TextView) v.findViewById(R.id.eventName);
        TextView eventIdView = v.findViewById(R.id.eventId);
        TextView eventDeptView = v.findViewById(R.id.eventDept);
        TextView eventCategory = v.findViewById(R.id.eventCategory);
        TextView eventDesc = v.findViewById(R.id.eventDesc);
        TextView reg_button = v.findViewById(R.id.register);

        final ImageView imageView = (ImageView) v.findViewById(R.id.eventImage);
        final ImageView expandImg = v.findViewById(R.id.expandicon);
        final TextView knowmore = v.findViewById(R.id.knowmore);

        final LinearLayout linearLayout = v.findViewById(R.id.moreinfo);
        LinearLayout item = v.findViewById(R.id.itemlayout);

        eventNameView.setText(eventObjects.get(position).getEvent_name());
        eventIdView.setText(eventObjects.get(position).getEvent_id());
        if(eventObjects.get(position).getEvent_category().toString().equals("def")){
            eventCategory.setWidth(0);
            eventCategory.setHeight(0);
        }
        else
            eventCategory.setText(eventObjects.get(position).getEvent_category());
        if(eventObjects.get(position).getEvent_dept().toString().equals("def"))
        {    eventDeptView.setHeight(0);
            eventDeptView.setWidth(0);}
        else
            eventDeptView.setText(eventObjects.get(position).getEvent_dept());
        eventDesc.setText(eventObjects.get(position).getEventDesc());
        //eventCategory.setText(eventObjects.get(position).getEvent_category());
        //eventDesc.setText(eventObjects.get(position).getEventDesc());
        /*Glide.with(getContext())
                .load(eventObjects.get(position).getImgBitmap())
                .into(imageView);*/
        Picasso.get().load("file:///android_asset/"+eventObjects.get(position).getImageUniqueName()).into(imageView);
        //Glide.with(context).load(eventObjects.get(position).getUri()).into(imageView);
        //imageView.setImageBitmap(eventObjects.get(position).getImgBitmap());
        final String eventid = eventObjects.get(position).getEvent_id();

        linearLayout.setVisibility(View.GONE);
        item.setOnClickListener(new View.OnClickListener() {
            boolean expanded = false;
            @Override
            public void onClick(View view) {
                if (!expanded){
                    expandImg.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                    knowmore.setText("less");
                    linearLayout.setVisibility(View.VISIBLE);
                    //imageView.getLayoutParams().width =(int) (200 * getContext().getResources().getDisplayMetrics().density);
                    //imageView.getLayoutParams().height =(int) (200 * getContext().getResources().getDisplayMetrics().density);
                    expanded = true;

                }else {
                    expandImg.setImageResource(R.drawable.ic_more_vert_white_24dp);
                    linearLayout.setVisibility(View.GONE);
                    knowmore.setText("more");
                    //imageView.getLayoutParams().width =(int) (110 * getContext().getResources().getDisplayMetrics().density);
                    //imageView.getLayoutParams().height =(int) (110 * getContext().getResources().getDisplayMetrics().density);
                    expanded = false;
                }
            }
        });

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = eventObjects.get(position).getEvent_link();
                /*Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                activity.startActivity(i);*/

                new FinestWebView.Builder(activity).webViewJavaScriptEnabled(true).webViewJavaScriptCanOpenWindowsAutomatically(true).showProgressBar(true).show(url);
            }
        });

        return v;
    }
}
