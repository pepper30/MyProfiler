package megha.myprofiler.profilesettings;

import dagger.Subcomponent;
import megha.myprofiler.di.SubComponentBuilder;
import megha.myprofiler.di.UserScope;


/**
 * Created by Megha Chauhan on 24-Dec-17.
 */
@UserScope
@Subcomponent(modules = ProfileSettingsModule.class)
public interface ProfileSettingsComponent {
    void inject(ProfileSettingsFragment fragment);
    @Subcomponent.Builder
    interface Builder extends SubComponentBuilder<ProfileSettingsComponent> {
        Builder profileSettingsModule(ProfileSettingsModule module);
    }
}
