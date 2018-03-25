package megha.myprofiler.photogallery;

import dagger.Subcomponent;
import megha.myprofiler.di.SubComponentBuilder;
import megha.myprofiler.di.UserScope;


/**
 * Created by Megha Chauhan on 24-Dec-17.
 */
@UserScope
@Subcomponent(modules = PhotoGalleryModule.class)
public interface PhotoGalleryComponent {
    void inject(PhotoGalleryFragment fragment);
    @Subcomponent.Builder
    interface Builder extends SubComponentBuilder<PhotoGalleryComponent> {
        Builder photoGalleryModule(PhotoGalleryModule photoGalleryModule);
    }
}
