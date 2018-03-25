package megha.myprofiler.profilesettings;


import megha.myprofiler.BasePresenter;
import megha.myprofiler.BaseView;

/**
 * Created by Megha Chauhan on 16-Dec-17.
 */

public interface ProfileSettingsContract {

    interface View extends BaseView<Presenter> {
        void startLogInActivity();

        void showAuthCard(boolean show);

        void showProgressIndicator(boolean show);
    }

    interface Presenter extends BasePresenter {
        void onDeleteAccountPress();

        void onDeleteAccountConfirmed(String password);
    }
}
