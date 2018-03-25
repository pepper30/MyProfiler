package megha.myprofiler.photogallery;


import android.content.ContentResolver;

import dagger.Module;
import dagger.Provides;
import megha.myprofiler.data.photos.PhotoService;
import megha.myprofiler.data.photos.PhotoSource;
import megha.myprofiler.di.UserScope;


/**
 * Created by Megha Chauhan on 24-Dec-17.
 */
@Module
public class PhotoGalleryModule {
    PhotoGalleryContract.View view;
    ContentResolver resolver;
    public PhotoGalleryModule(PhotoGalleryContract.View view,ContentResolver resolver)
    {
        this.view = view;
        this.resolver = resolver;
    }
    @UserScope
    @Provides
    PhotoGalleryContract.View provideView()
    {return view;}
    @UserScope
    @Provides
    ContentResolver provideResolver()
    {
        return resolver;
    }
    @UserScope
    @Provides
    PhotoSource providePhotoSource()
    {
        return PhotoService.getInstance();
    }

}
