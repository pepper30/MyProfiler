package megha.myprofiler.profilepage;

import dagger.Component;
import megha.myprofiler.di.NetComponent;
import megha.myprofiler.di.UserScope;


/**
 * Created by Megha Chauhan on 24-Dec-17.
 */
@UserScope
@Component(modules = ProfilePageModule.class,dependencies = NetComponent.class)
public interface ProfilePageComponent {
    void inject(ProfilePageFragment fragment);
}
