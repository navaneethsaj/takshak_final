package takshak.mace.takshak2k18;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import ss.com.bannerslider.ImageLoadingService;
import ss.com.bannerslider.Slider;

public class About extends AppCompatActivity  {
    private Slider slider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Slider.init(new PicassoImageLoadingService(this));
        slider = findViewById(R.id.banner_slider1);
        slider.setAdapter(new MainSliderAdapter());
        slider.setSelectedSlide(2);

    }
}
