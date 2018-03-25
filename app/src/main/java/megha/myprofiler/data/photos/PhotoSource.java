package megha.myprofiler.data.photos;

import android.content.ContentResolver;

import java.util.List;

import io.reactivex.Maybe;


/**
 * Created by Megha Chauhan on 16-Dec-17.
 */

public interface PhotoSource {
    Maybe<List<Photo>> getThumbnails(ContentResolver resolver);

    void setReturnFail(boolean bool);
}
