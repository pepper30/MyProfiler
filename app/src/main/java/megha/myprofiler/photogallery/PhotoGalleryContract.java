package megha.myprofiler.photogallery;

import android.app.Activity;
import android.support.annotation.StringRes;

import java.util.List;

import megha.myprofiler.BasePresenter;
import megha.myprofiler.BaseView;
import megha.myprofiler.data.photos.Photo;

/**
 * Created by Megha Chauhan on 17-Dec-17.
 */

public interface PhotoGalleryContract {

    interface View extends BaseView<Presenter> {
        void setAdapterData(List<Photo> photos);

        void setNoListDataFound();

        Activity getActivityContext();

        void makeToast(@StringRes int message);

        void setPresenter(Presenter presenter);

        void startPhotoDetailActivity(String photoURL);

        void showProgressIndicator(boolean show);

    }

    interface Presenter extends BasePresenter {
        void onPhotoItemClick(int itemPosition);
    }
}
