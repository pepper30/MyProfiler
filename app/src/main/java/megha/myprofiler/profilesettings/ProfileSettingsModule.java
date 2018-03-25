package megha.myprofiler.profilesettings;

import dagger.Module;
import dagger.Provides;
import megha.myprofiler.di.UserScope;


/**
 * Created by Megha Chauhan on 24-Dec-17.
 */
@Module
public class ProfileSettingsModule {
    ProfileSettingsContract.View view;
    public ProfileSettingsModule(ProfileSettingsContract.View view)
    {
        this.view = view;
    }
    @UserScope
    @Provides
    ProfileSettingsContract.View provideView()
    {
        return view;
    }
}
