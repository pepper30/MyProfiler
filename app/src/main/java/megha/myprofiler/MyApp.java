package megha.myprofiler;

import android.app.Application;


import io.realm.Realm;
import io.realm.RealmConfiguration;
import megha.myprofiler.di.DaggerNetComponent;
import megha.myprofiler.di.NetComponent;
import megha.myprofiler.di.NetModule;


/**
 * Created by Megha Chauhan on 24-Dec-17.
 */

public class MyApp extends Application {
    NetComponent component;
    RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
         realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        component = DaggerNetComponent.builder().netModule(new NetModule()).build();
    }
    public NetComponent getComponent()
    {
        return component;
    }

    public RealmConfiguration getCOnfig()
    {
        return realmConfiguration;
    }
}
