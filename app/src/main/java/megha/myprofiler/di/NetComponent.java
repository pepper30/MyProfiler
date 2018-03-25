package megha.myprofiler.di;

import java.util.Map;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;
import megha.myprofiler.Util.SchedulerProvider;
import megha.myprofiler.data.AuthSource;
import megha.myprofiler.data.database.DataBaseSource;
import megha.myprofiler.di.Binder.Binder;


/**
 *Created by Megha Chauhan on 24-Dec-17.
 */
@Singleton
@Component(modules = {NetModule.class, Binder.class})
public interface NetComponent {
    CompositeDisposable disposable();
    SchedulerProvider provider();
    AuthSource source();
    DataBaseSource databaseSource();
    Map<Class<?>,Provider<SubComponentBuilder>> subcomponentBuilders();
}
