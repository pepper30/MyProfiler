package megha.myprofiler.login;


import android.content.Context;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import megha.myprofiler.R;
import megha.myprofiler.Util.SchedulerProvider;
import megha.myprofiler.Util.SessionManager;
import megha.myprofiler.data.AuthSource;
import megha.myprofiler.data.Credentials;


/**
 *  Created by Megha Chauhan on 16-Dec-17.
 */

public class LoginAccountPresenter implements LoginAccountContract.Presenter {

    LoginAccountContract.View view;
    CompositeDisposable disposable;
    AuthSource auth;
    SchedulerProvider schedulerProvider;
    SessionManager sessionManager ;
    @Inject
    public LoginAccountPresenter(LoginAccountContract.View view, AuthSource auth, SchedulerProvider schedulerProvider, CompositeDisposable disposable, Context context){
        this.view = view;
        this.schedulerProvider = schedulerProvider;
        this.auth = auth;
        this.disposable = disposable;
        sessionManager = new SessionManager(context);
        view.setPresenter(this);
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe()
    {
        disposable.clear();
    }

    @Override
    public void onLogInClick() {

        validateCredetials(view.getEmail(),view.getPassword());
    }

    @Override
    public void onCreateClick() {
        view.startCreateAccountActivity();
    }


    private void validateCredetials(String email,String password){
        if (email.isEmpty()) {
            view.makeToast(R.string.error_empty_input);
        } else if (password.isEmpty()) {
            view.makeToast(R.string.error_empty_input);
        } else if (!email.contains("@")) {
            view.makeToast(R.string.error_invalid_email);
        } else if (password.length() > 19) {
            view.makeToast(R.string.error_password_too_long);
        } else {
            attemptLogIn(new Credentials(password, "", email));
        }
    }
    public void attemptLogIn(Credentials cred) {
        view.showProgressIndicator(true);
        disposable.add(
                auth.attemptLogin(cred)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                sessionManager.setLogin(true);
                                view.startProfileActivity();
                                view.showProgressIndicator(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.showProgressIndicator(false);
                                view.makeToast(e.toString());
                            }
                        })
        );
    }
}
