package megha.myprofiler.di.Binder;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.multibindings.IntoMap;
import megha.myprofiler.di.Key.SubComponentKey;
import megha.myprofiler.di.SubComponentBuilder;
import megha.myprofiler.photogallery.PhotoGalleryComponent;
import megha.myprofiler.profilesettings.ProfileSettingsComponent;


/**
 * Created by Megha Chauhan on 24-Dec-17.
 */
@Module(subcomponents = {ProfileSettingsComponent.class, PhotoGalleryComponent.class})
public abstract class Binder {
    @Binds
    @IntoMap
    @SubComponentKey(ProfileSettingsComponent.Builder.class)
    public abstract SubComponentBuilder myActivity(ProfileSettingsComponent.Builder impl);

    @Binds
    @IntoMap
    @SubComponentKey(PhotoGalleryComponent.Builder.class)
    public abstract SubComponentBuilder myGallery(PhotoGalleryComponent.Builder impl);

}
