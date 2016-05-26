package rookie.android.daily.login;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import rookie.android.daily.businesslogic.LoginManager;
import rookie.android.daily.businesslogicimpl.LoginManagerImpl;
import rookie.android.daily.enumerate.LoginResult;
import rookie.android.daily.model.Person;
import rookie.android.daily.presentationmodel.AuthCredentials;

/**
 * Created by rale on 5/19/16.
 */
public class LoginPresenter extends MvpBasePresenter<LoginView>{

    LoginManager loginManager;
    Person person;

    @Inject
    public LoginPresenter(LoginManager loginManager){
        this.loginManager = loginManager;
        person = new Person();
    }

    public LoginPresenter(){
        this(new LoginManagerImpl());
    }

    public void doLogin(AuthCredentials authCredentials){
        if(isViewAttached()){
            getView().showLoading();
        }
        if(isViewAttached() && loginManager.login(authCredentials)== LoginResult.SUCCESS){
            person = loginManager.findById(authCredentials.getUserId());//更新本地的用户信息
            getView().loginSuccessful();
        }else {
            getView().showLoginError();
        }

    }

    @Override
    public void attachView(LoginView view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
