package megha.myprofiler.profiledetail;

import dagger.Component;
import megha.myprofiler.di.NetComponent;
import megha.myprofiler.di.UserScope;


/**
 * Created by Megha Chauhan on 24-Dec-17.
 */
@UserScope
@Component(modules = ProfileDetailModule.class,dependencies = NetComponent.class)
public interface ProfileDetailComponent {
    void inject(ProfileDetailFragment fragment);
}
