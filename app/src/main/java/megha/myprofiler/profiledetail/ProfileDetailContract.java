package megha.myprofiler.profiledetail;

import android.support.annotation.StringRes;

import megha.myprofiler.BasePresenter;
import megha.myprofiler.BaseView;


/**
 * Created by Megha Chauhan on 17-Dec-17.
 */

public interface ProfileDetailContract {

    interface View extends BaseView<Presenter> {

        void setBioText(String bio);

        void setInterestsText(String interests);

        String getInterests();

        String getBio();

        void startProfilePageActivity();

        void setPresenter(Presenter presenter);

        void makeToast(@StringRes int message);
    }

    interface Presenter extends BasePresenter {
        void onBackButtonClick();

        void onDoneButtonClick();
    }
}
