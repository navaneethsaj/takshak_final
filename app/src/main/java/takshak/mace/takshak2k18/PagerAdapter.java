package takshak.mace.takshak2k18;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position)
        {

            case 0:
                Tab3 tab3 = new Tab3();
                return tab3;
            case 1:
                Tab1 tab1 = new Tab1();
                return tab1;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
