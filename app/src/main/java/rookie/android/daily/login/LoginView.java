package rookie.android.daily.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by rale on 5/19/16.
 */
public interface LoginView extends MvpView {

    public void showLoginForm();

    public void showLoginError();

    public void showLoading();

    public void loginSuccessful();
}
