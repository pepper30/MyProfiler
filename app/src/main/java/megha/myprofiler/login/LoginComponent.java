package megha.myprofiler.login;



import dagger.Component;
import megha.myprofiler.di.NetComponent;
import megha.myprofiler.di.UserScope;

/**
 * Created by zorail on 24-May-17.
 */
@UserScope
@Component(modules = LoginModule.class,dependencies = NetComponent.class)
public interface LoginComponent {
    void inject(LoginAccountFragment fragment);
}
