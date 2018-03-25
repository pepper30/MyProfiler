package  megha.myprofiler;

import android.support.annotation.StringRes;

/**
 * Created by Megha Chauhan on 16-Dec-17.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    void makeToast(@StringRes int stringId);

    void makeToast(String message);
}
