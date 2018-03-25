package megha.myprofiler.createaccount;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import io.realm.Realm;
import megha.myprofiler.MyApp;
import megha.myprofiler.R;
import megha.myprofiler.login.LoginAccountActivity;
import megha.myprofiler.profilepage.ProfilePageActivity;


/**
 *Created by Megha Chauhan on 16-Dec-17.
 */

public class CreateAccountFragment extends Fragment implements CreateAccountContract.View {

    private Button createAccount;
    private TextView emailLabel, passwordLabel, confirmLabel, nameLabel;
    private EditText emailInput, passwordInput, confirmInput, nameInput;
    private ProgressBar progressBar;
    private View contentContainer;
    Realm realm;


    @Inject
    CreateAccountPresenter presenter;

    public CreateAccountFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerCreateAccountComponent.builder()
                .netComponent(((MyApp)getActivity().getApplication()).getComponent())
                .createAccountModule(new CreateAccountModule(this,getActivity().getApplicationContext()))
                .build().inject(this);
        realm = Realm.getDefaultInstance();
        presenter.initializeRealm(realm);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_account, container, false);

        progressBar = (ProgressBar) v.findViewById(R.id.pro_create_account_loading);

        contentContainer = v.findViewById(R.id.cont_create_account_content);

        nameInput = (EditText) v.findViewById(R.id.edt_create_name);
        emailInput = (EditText) v.findViewById(R.id.edt_create_email);
        passwordInput = (EditText) v.findViewById(R.id.edt_create_password);
        confirmInput = (EditText) v.findViewById(R.id.edt_create_confirmation);

        nameLabel = (TextView) v.findViewById(R.id.lbl_create_name_sub);
        emailLabel = (TextView) v.findViewById(R.id.lbl_create_email_sub);
        passwordLabel = (TextView) v.findViewById(R.id.lbl_create_password_sub);
        confirmLabel = (TextView) v.findViewById(R.id.lbl_create_confirmation_sub);

        createAccount = (Button) v.findViewById(R.id.btn_create_account);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCreateAccountClick();
            }
        });
        setUpListeners();
        emailInput.requestFocus();
        return v;
    }

    public static CreateAccountFragment newInstance(){
        return new CreateAccountFragment();
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeToast(@StringRes int stringId) {
        Toast.makeText(getActivity().getApplicationContext(), getString(stringId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getEmail() {
        return emailInput.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordInput.getText().toString();
    }

    @Override
    public String getPasswordConfirmation() {
        return confirmInput.getText().toString();
    }

    @Override
    public String getName() {
        return nameInput.getText().toString();
    }

    @Override
    public void startLoginActivity() {
     Intent i = new Intent(getActivity(), LoginAccountActivity.class);
        startActivity(i);
    }

    @Override
    public void startProfilePageActivity() {
        Intent i =new Intent(getActivity(), ProfilePageActivity.class);
        startActivity(i);
    }

    @Override
    public void setPresenter(CreateAccountContract.Presenter presenter) {
        this.presenter = (CreateAccountPresenter)presenter;
    }

    @Override
    public void showProgressIndicator(boolean show) {

        if (show){
            progressBar.setVisibility(View.VISIBLE);
            contentContainer.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            contentContainer.setVisibility(View.VISIBLE);
        }
    }
    public void setUpListeners () {

        nameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View label, boolean hasFocus) {
                if (hasFocus){
                    nameLabel.setTextColor(
                            ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorAccent)
                    );


                } else {
                    nameLabel.setTextColor(
                            ContextCompat.getColor(getActivity().getApplicationContext(), android.R.color.white)
                    );
                }
            }
        });

        emailInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View label, boolean hasFocus) {
                if (hasFocus){
                    emailLabel.setTextColor(
                            ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorAccent)
                    );


                } else {
                    emailLabel.setTextColor(
                            ContextCompat.getColor(getActivity().getApplicationContext(), android.R.color.white)
                    );
                }
            }
        });

        passwordInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View label, boolean hasFocus) {
                if (hasFocus){
                    passwordLabel.setTextColor(
                            ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorAccent)
                    );


                } else {
                    passwordLabel.setTextColor(
                            ContextCompat.getColor(getActivity().getApplicationContext(), android.R.color.white)
                    );
                }
            }
        });

        confirmInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View label, boolean hasFocus) {
                if (hasFocus){
                    confirmLabel.setTextColor(
                            ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorAccent)
                    );


                } else {
                    confirmLabel.setTextColor(
                            ContextCompat.getColor(getActivity().getApplicationContext(), android.R.color.white)
                    );
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }
}
