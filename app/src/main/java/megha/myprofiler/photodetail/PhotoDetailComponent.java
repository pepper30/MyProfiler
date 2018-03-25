package  megha.myprofiler.photodetail;

import dagger.Component;
import megha.myprofiler.di.NetComponent;
import megha.myprofiler.di.UserScope;


/**
 *  Created by Megha Chauhan on 24-Dec-17.
 */
@UserScope
@Component(modules = PhotoDetailModule.class,dependencies = NetComponent.class)
public interface PhotoDetailComponent {
    void inject(PhotoDetailFragment fragment);
}
