package megha.myprofiler.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import megha.myprofiler.Util.SchedulerProvider;
import megha.myprofiler.data.AuthSource;
import megha.myprofiler.data.FireBaseAuthService;
import megha.myprofiler.data.database.DataBaseSource;
import megha.myprofiler.data.database.FirebaseDatabaseService;


/**
 * Created by Megha Chauhan on 24-Dec-17.
 */
@Module
public class NetModule {
    @Singleton
    @Provides
    CompositeDisposable provideDisposable()
    {
        return new CompositeDisposable();
    }
    @Singleton
    @Provides
    AuthSource provideAuthSource()
    {
        return FireBaseAuthService.getInstance();
    }
    @Singleton
    @Provides
    SchedulerProvider provideSchedulerProvider()
    {
        return SchedulerProvider.getInstance();
    }
    @Singleton
    @Provides
    DataBaseSource provideDataBaseSource(){return FirebaseDatabaseService.getInstance();}
}
