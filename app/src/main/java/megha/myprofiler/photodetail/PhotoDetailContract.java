package  megha.myprofiler.photodetail;

import android.support.annotation.StringRes;

import megha.myprofiler.BasePresenter;
import megha.myprofiler.BaseView;


/**
 *  Created by Megha Chauhan on 16-Dec-17.
 */

public interface PhotoDetailContract {

    //You must specify Type of Presenter
        interface View extends BaseView<Presenter> {

            void setBitmap();

            void startProfilePageActivity();

            void startPhotoGalleryActivity();

            void makeToast(@StringRes int message);

            void setPresenter(Presenter presenter);

            void showProgressIndicator(boolean show);

            String getPhotoURL();
        }

    interface Presenter extends BasePresenter {
            void onBackButtonPress();

            void onDoneButtonPress();

            void onImageLoaded();

            void onImageLoadFailure();
    }
}
