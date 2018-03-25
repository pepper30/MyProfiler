package megha.myprofiler.profilepage;

import android.content.Context;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import megha.myprofiler.R;
import megha.myprofiler.Util.SchedulerProvider;
import megha.myprofiler.Util.SessionManager;
import megha.myprofiler.data.AuthSource;
import megha.myprofiler.data.User;
import megha.myprofiler.data.database.DataBaseSource;
import megha.myprofiler.data.database.Profile;

/**
 * Created by Megha Chauhan on 24-Dec-17.
 */

public class ProfilePagePresenter implements ProfilePageContract.Presenter {

    AuthSource auth;
    ProfilePageContract.View view;
    SchedulerProvider schedulerProvider;
    CompositeDisposable disposable;
    DataBaseSource database;
    User currentUser;
    SessionManager sessionManager;
    Realm realm;
    RealmConfiguration realmConfiguration;
    Context context;
    @Inject
    public ProfilePagePresenter(AuthSource auth,ProfilePageContract.View view,SchedulerProvider schedulerProvider,DataBaseSource database,Context context,CompositeDisposable disposable)
    {
        this.database = database;
        this.auth = auth;
        this.view = view;
        this.schedulerProvider = schedulerProvider;
        this.disposable = disposable;
        this.context = context;
        sessionManager = new SessionManager(context);
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
     getUserData();
    }

    @Override
    public void unsubscribe() {
        disposable.clear();
    }

    @Override
    public void onThumbnailClick() {
        view.startPhotoGalleryActivity();
    }

    @Override
    public void onEditProfileClick() {
        view.startProfileDetailActivity();
    }

    @Override
    public void onLogoutClick() {
        view.showLogoutSnackbar();
    }

    @Override
    public void onLogoutConfirmed() {

        disposable.add(
                auth.logUserOut()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                sessionManager.setLogin(false);
                                realm.close();
                                Realm.deleteRealm(realmConfiguration);
                                view.startLoginActivity();
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.makeToast(e.getMessage());
                            }
                        })
        );
    }

    @Override
    public void onAccountSettingsClick() {
        view.startProfileSettingsActivity();
    }

    @Override
    public void onThumbnailLoaded() {
        view.setThumbnailLoadingIndicator(false);

    }
    private void getUserData() {

        view.setThumbnailLoadingIndicator(true);
        view.setDetailLoadingIndicators(true);
        disposable.add(
                auth.getUser().subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribeWith(
                                new DisposableMaybeObserver<User>() {

                                    @Override
                                    public void onError(Throwable e) {
                                        view.makeToast(R.string.error_retrieving_data);
                                        view.startLoginActivity();
                                    }

                                    @Override
                                    public void onComplete() {
                                        view.makeToast(R.string.error_retrieving_data);
                                        view.startLoginActivity();
                                    }

                                    @Override
                                    public void onSuccess(User user) {
                                        ProfilePagePresenter.this.currentUser = user;
                                        getUserProfileFromDatabase();
                                    }
                                }
                        )
        );
    }

    private void getUserProfileFromDatabase() {
        if(!realm.isEmpty())
        {
            view.setDetailLoadingIndicators(false);
            Profile profile = realm.where(Profile.class).equalTo("uid",currentUser.getUserId()).findAll().last();
            view.setBio(profile.getBio());
            view.setInterests(profile.getInterests());
            view.setName(profile.getName());
            view.setEmail(profile.getEmail());
            String sub = profile.getPhotoURL().replace("file://", "");
            if (profile.getPhotoURL().equals("")) {
                view.setDefaultProfilePhoto();
            } else if (new File(sub).isFile()) {
                view.setProfilePhotoURL(profile.getPhotoURL());
            }
        }
        else {
            disposable.add(database.getProfile(currentUser.getUserId())
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribeWith(new DisposableMaybeObserver<Profile>() {
                        @Override
                        public void onSuccess(Profile profile) {
                            view.setBio(profile.getBio());
                            view.setInterests(profile.getInterests());
                            view.setName(profile.getName());
                            view.setEmail(profile.getEmail());

                            view.setDetailLoadingIndicators(false);
                            String sub = profile.getPhotoURL().replace("file://", "");
                            if (profile.getPhotoURL().equals("")) {
                                view.setDefaultProfilePhoto();
                            } else if (new File(sub).isFile()) {
                                view.setProfilePhotoURL(profile.getPhotoURL());
                            } else {
                                provideUrl();
                            }
                            realm.beginTransaction();
                            realm.copyToRealm(profile);
                            realm.commitTransaction();

                        }

                        @Override
                        public void onError(Throwable e) {
                            view.makeToast(e.getMessage());
                            view.startLoginActivity();
                        }

                        @Override
                        public void onComplete() {
                            view.startLoginActivity();
                        }
                    })
            );
        }

    }
    private void provideUrl()
    {
        disposable.add(database.downloadUrl(currentUser)
                               .subscribeOn(schedulerProvider.io())
                               .observeOn(schedulerProvider.ui())
                               .subscribeWith(new DisposableMaybeObserver<String>() {
                                   @Override
                                   public void onSuccess(@NonNull String s) {
                                       realm.beginTransaction();
                                       Profile p = realm.where(Profile.class).equalTo("uid",currentUser.getUserId()).findAll().last();
                                       p.setPhotoURL("file://"+s);
                                       realm.commitTransaction();
                                       view.setProfilePhotoURL(p.getPhotoURL());
                                   }

                                   @Override
                                   public void onError(@NonNull Throwable e) {
                                       view.makeToast(e.getMessage());
                                   }

                                   @Override
                                   public void onComplete() {

                                   }
                               }));
    }
    public void initializeRealm(Realm realm,RealmConfiguration realmConfiguration)
    {
        ProfilePagePresenter.this.realm = realm;
        ProfilePagePresenter.this.realmConfiguration = realmConfiguration;
    }

}
