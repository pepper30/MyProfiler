package megha.myprofiler.profilepage;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import megha.myprofiler.di.UserScope;

/**
 * Created by Megha Chauhan on 24-Dec-17.
 */
@Module
public class ProfilePageModule {
    ProfilePageContract.View view;
    Context context;
    public ProfilePageModule(ProfilePageContract.View view,Context context)
    {
        this.context = context;
        this.view = view;
    }
    @UserScope
    @Provides
    ProfilePageContract.View provideView()
    {
        return view;
    }
    @UserScope
    @Provides
    Context provideContext()
    {
        return context;
    }

}
