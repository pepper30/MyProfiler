package megha.myprofiler.login;

import android.support.annotation.StringRes;

import megha.myprofiler.BasePresenter;
import megha.myprofiler.BaseView;


/**
 *  Created by Megha Chauhan on 16-Dec-17.
 */

public interface LoginAccountContract {

    interface View extends BaseView<Presenter> {
        void makeToast(@StringRes int stringId);

        void makeToast(String message);

        String getEmail();

        String getPassword();

        void startProfileActivity();

        void startCreateAccountActivity();

        void setPresenter(Presenter presenter);

        void showProgressIndicator(boolean show);
    }

    interface Presenter extends BasePresenter {
        void onLogInClick();

        void onCreateClick();


    }
}
