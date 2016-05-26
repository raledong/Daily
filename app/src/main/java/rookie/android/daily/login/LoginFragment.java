package rookie.android.daily.login;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hkm.ui.processbutton.iml.ActionProcessButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rookie.android.daily.MainActivity;
import rookie.android.daily.R;
import rookie.android.daily.presentationmodel.AuthCredentials;
import rookie.android.daily.signup.SignupActivity;

/**
 * Created by rale on 5/20/16.
 */
public class LoginFragment extends MvpViewStateFragment<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.user_id)
    EditText userId;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.login_button)
    ActionProcessButton loginButton;

    @BindView(R.id.login_form)
    ViewGroup loginForm;

    @BindView(R.id.error_view)
    TextView errorView;

    @BindView(R.id.sign_in)
    TextView signin;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onNewViewStateInstance() {
        showLoginForm();
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton.setMode(ActionProcessButton.Mode.ENDLESS);

        int startDelay = getResources().getInteger(android.R.integer.config_mediumAnimTime) + 100;
        LayoutTransition transition = new LayoutTransition();
        transition.enableTransitionType(LayoutTransition.CHANGING);
        transition.setStartDelay(LayoutTransition.APPEARING, startDelay);
        transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, startDelay);
        loginForm.setLayoutTransition(transition);
    }

    @Override
    public void showLoginForm() {
        LoginViewState loginViewState = (LoginViewState) viewState;
        loginViewState.setShowLoginForm();

        errorView.setVisibility(View.GONE);
        setFormEnabled(true);
        loginButton.setProgress(0);
    }

    @Override
    public void showLoginError() {
        LoginViewState vs = (LoginViewState) viewState;
        vs.setShowError();

        setFormEnabled(true);
        loginButton.setProgress(0);

        if (!isRestoringViewState()) {
            // Enable animations only if not restoring view state
            loginForm.clearAnimation();
            Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
            loginForm.startAnimation(shake);
        }

        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        LoginViewState vs = (LoginViewState) viewState;
        vs.setShowLoading();
        errorView.setVisibility(View.GONE);
        setFormEnabled(false);
        // any progress between 0 - 100 shows animation
        loginButton.setProgress(30);
    }

    @Override
    public void loginSuccessful() {
        loginButton.setProgress(100); // We are done
        MainActivity.menuActionStart(getActivity(), null);
        getActivity().finish();
        getActivity().overridePendingTransition(0, R.anim.zoom_out);
    }

    @Override
    public ViewState createViewState() {
        return new LoginViewState();
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    private void setFormEnabled(boolean enabled) {
        userId.setEnabled(enabled);
        password.setEnabled(enabled);
        loginButton.setEnabled(enabled);
    }

    @OnClick(R.id.login_button)
    public void onClick(){
        Toast.makeText(getContext(),"on click", Toast.LENGTH_SHORT).show();
        String uid = userId.getText().toString();
        String pass = password.getText().toString();

        loginForm.clearAnimation();

        if (TextUtils.isEmpty(uid)) {
            userId.clearAnimation();
            Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
            userId.startAnimation(shake);
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            password.clearAnimation();
            Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
            password.startAnimation(shake);
            return;
        }

        presenter.doLogin(new AuthCredentials(uid, pass));

    }

    @OnClick(R.id.sign_in)
    public void toSigninPage(){
        Intent intent = new Intent(getActivity(), SignupActivity.class);
        startActivity(intent);
    }

//    @Override
//    public void onNewViewStateInstance() {
//        showLoginForm();
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }
//
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        unbinder = ButterKnife.bind(this, view);
//        userId = (EditText) view.findViewById(R.id.user_id);
//        loginButton = (ActionProcessButton) view.findViewById(R.id.login_button);
//        password = (EditText)view.findViewById(R.id.password);
//        errorView = (TextView)view.findViewById(R.id.error_view);
//        loginForm = (ViewGroup)view.findViewById(R.id.login_form);
//
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("yes", "yes");
//            }
//        });
//        loginButton.setMode(ActionProcessButton.Mode.ENDLESS);
//
//        int startDelay = getResources().getInteger(android.R.integer.config_mediumAnimTime) + 100;
//        LayoutTransition transition = new LayoutTransition();
//        transition.enableTransitionType(LayoutTransition.CHANGING);
//        transition.setStartDelay(LayoutTransition.APPEARING, startDelay);
//        transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, startDelay);
//        loginForm = (ViewGroup)view.findViewById(R.id.login_form);
//        loginForm.setLayoutTransition(transition);
//    }
//
//    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                                 Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_login, container, false);
//    }
//    @Override
//    public void showLoginForm() {
//        LoginViewState vs = (LoginViewState) viewState;
//        vs.setShowLoginForm();
//
//        errorView = (TextView)getView().findViewById(R.id.error_view);
//
//        errorView.setVisibility(View.GONE);
//        setFormEnabled(true);
//        loginButton.setProgress(0);
//    }
//
//    @Override
//    public void showLoginError() {
//        LoginViewState vs = (LoginViewState) viewState;
//        vs.setShowError();
//
//        setFormEnabled(true);
//        loginButton.setProgress(0);
//
//        if (!isRestoringViewState()) {
//            // Enable animations only if not restoring view state
//            loginForm.clearAnimation();
//            Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
//            loginForm.startAnimation(shake);
//        }
//    }
//
//    @Override
//    public void showLoading() {
//        LoginViewState vs = (LoginViewState) viewState;
//        vs.setShowLoading();
//        errorView.setVisibility(View.GONE);
//        setFormEnabled(false);
//        // any progress between 0 - 100 shows animation
//        loginButton.setProgress(30);
//    }
//
//    @Override
//    public void loginSuccessful() {
//        loginButton.setProgress(100); // We are done
//        getActivity().finish();
//        getActivity().overridePendingTransition(0, R.anim.zoom_out);
//    }
//
//    @Override
//    public ViewState createViewState() {
//        return new LoginViewState();
//    }
//
//    @Override
//    public LoginPresenter createPresenter() {
//        return new LoginPresenter();
//    }
//
//    private void setFormEnabled(boolean enabled) {
//        userId.setEnabled(enabled);
//        password.setEnabled(enabled);
//        loginButton.setEnabled(enabled);
//    }
//


}
