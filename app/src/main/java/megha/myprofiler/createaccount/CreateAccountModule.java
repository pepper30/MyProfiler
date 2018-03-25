package megha.myprofiler.createaccount;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import megha.myprofiler.di.UserScope;


/**
 * Created by Megha Chauhan on 16-Dec-17.
 */
@Module
public class CreateAccountModule {
    CreateAccountContract.View view;
    Context context;
    public CreateAccountModule(CreateAccountContract.View view,Context context)
    {
        this.context = context;
        this.view = view;
    }
    @UserScope
    @Provides
    CreateAccountContract.View provideView()
    {return  view;}
    @UserScope
    @Provides
    Context provideContext()
    {
        return context;
    }
}
