package megha.myprofiler.profiledetail;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import megha.myprofiler.di.UserScope;


/**
 * Created by Megha Chauhan on 24-Dec-17.
 */
@Module
public class ProfileDetailModule {
    ProfileDetailContract.View view;
    public ProfileDetailModule(ProfileDetailContract.View view)
    {
        this.view = view;
    }
    @UserScope
    @Provides
    ProfileDetailContract.View provideView()
    {return  view;}
}
