package takshak.mace.takshak2k18;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.animation.FlingAnimation;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Tab3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageView logo;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public final String UserPREFERENCES = "userinfo";
    public final String NAME = "username";
    public final String USERID = "usserid";
    public final String MOBILENO = "mobileno";
    public final String EMAILID = "emailid";

    TextView rank;
    LinearLayout ranklayout;
    SharedPreferences sharedpreferences;
    private OnFragmentInteractionListener mListener;

    LinearLayout civil,cse,mech,eee,ece,mca,core;

    public Tab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3 newInstance(String param1, String param2) {
        Tab3 fragment = new Tab3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        sharedpreferences = getActivity().getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_3, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//Setup anim with desired properties
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(1); //Repeat animation indefinitely
        anim.setDuration(200);

        rank = view.findViewById(R.id.yourrank);
        ranklayout = view.findViewById(R.id.ranklayout);
        ranklayout.setVisibility(View.GONE);

        if (sharedpreferences.getString(USERID,null) != null && sharedpreferences.getString(MOBILENO,null) !=null){
            ConnectivityManager conMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                    || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {
                // notify user you are online

                String url = "https://us-central1-takshakapp18.cloudfunctions.net/getrank?id=";
                new RankAsyncTask().execute(url + sharedpreferences.getString(USERID,"NOID"));
            }
            else if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                    || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

            }
        }




        core = view.findViewById(R.id.core);
        civil = view.findViewById(R.id.civil);
        cse = view.findViewById(R.id.cse);
        mech = view.findViewById(R.id.mech);
        eee = view.findViewById(R.id.eee);
        //ece = view.findViewById(R.id.ece);
        //mca = view.findViewById(R.id.mca);
        core.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category","premium");
                Intent intent = new Intent(getActivity(),EventListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category","expo");
                Intent intent = new Intent(getActivity(),EventListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category","technical");
                Intent intent = new Intent(getActivity(),EventListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category","informal");
                Intent intent = new Intent(getActivity(),EventListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        eee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category","workshop");
                Intent intent = new Intent(getActivity(),EventListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
    class RankAsyncTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .readTimeout(6, TimeUnit.SECONDS)
                    .writeTimeout(6,TimeUnit.SECONDS)
                    .connectTimeout(6,TimeUnit.SECONDS).build();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("request","timedout");
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null){
                ranklayout.setVisibility(View.VISIBLE);
                rank.setText(s.toString());
            }
        }
    }
}