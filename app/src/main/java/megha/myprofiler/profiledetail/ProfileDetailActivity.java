package megha.myprofiler.profiledetail;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import megha.myprofiler.ActivityUtils;
import megha.myprofiler.R;

/**
 * Created by Megha Chauhan on 17-Dec-17.
 */

public class ProfileDetailActivity extends AppCompatActivity {

    private static final String DETAIL_FRAGMENT = "DETAIL_FRAGMENT";

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        manager = this.getFragmentManager();

        ProfileDetailFragment fragment = (ProfileDetailFragment) manager.findFragmentByTag(DETAIL_FRAGMENT);

        if (fragment == null){
            fragment = ProfileDetailFragment.newInstance();
        }

        ActivityUtils.addFragmentToActivity(manager,
                fragment,
                R.id.cont_profile_detail_fragment,
                DETAIL_FRAGMENT
        );

    }
}
