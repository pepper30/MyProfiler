package  megha.myprofiler.photodetail;

import dagger.Module;
import dagger.Provides;
import megha.myprofiler.di.UserScope;


/**
 * Created by Megha Chauhan on 24-Dec-17.
 */
@Module
public class PhotoDetailModule {
    PhotoDetailContract.View view;
    public PhotoDetailModule(PhotoDetailContract.View view)
    {this.view = view;}
    @UserScope
    @Provides
    PhotoDetailContract.View provideView()
    {
        return view;
    }
}
