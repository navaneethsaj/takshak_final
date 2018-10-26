package takshak.mace.takshak2k18;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {
    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide(R.drawable.abs);
                break;
            case 1:
                viewHolder.bindImageSlide(R.drawable.ab);
                break;
            case 2:
                viewHolder.bindImageSlide(R.drawable.abss);
                break;
        }
    }
}
