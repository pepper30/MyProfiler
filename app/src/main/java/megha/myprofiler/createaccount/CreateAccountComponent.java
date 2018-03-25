package megha.myprofiler.createaccount;


import dagger.Component;
import megha.myprofiler.di.NetComponent;
import megha.myprofiler.di.UserScope;


/**
 * Created by Megha Chauhan on 24-Dec-17.
 */
@UserScope
@Component(modules = CreateAccountModule.class,dependencies = NetComponent.class)
public interface CreateAccountComponent {
    void inject(CreateAccountFragment fragment);
}
