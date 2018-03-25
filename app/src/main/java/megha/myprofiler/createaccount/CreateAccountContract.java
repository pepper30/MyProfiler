package megha.myprofiler.createaccount;

import android.support.annotation.StringRes;

import megha.myprofiler.BasePresenter;
import megha.myprofiler.BaseView;


/**
 * Created by Megha Chauhan on 16-Dec-17.
 */

public interface CreateAccountContract {

    interface View extends BaseView<Presenter> {
        void makeToast(@StringRes int stringId);

        String getEmail();

        String getPassword();

        String getPasswordConfirmation();

        String getName();

        //TODO: add name input to this component

        void startLoginActivity();

        void startProfilePageActivity();


        void setPresenter(CreateAccountContract.Presenter presenter);

        void showProgressIndicator(boolean show);
    }

    interface Presenter extends BasePresenter {
        void onCreateAccountClick();

        void subscribe();

        void unsubscribe();
    }

}
