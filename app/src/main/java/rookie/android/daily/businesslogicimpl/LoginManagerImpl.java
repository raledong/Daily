package rookie.android.daily.businesslogicimpl;

import rookie.android.daily.businesslogic.LoginManager;
import rookie.android.daily.enumerate.LoginResult;
import rookie.android.daily.model.Person;
import rookie.android.daily.presentationmodel.AuthCredentials;
import rookie.android.daily.presentationmodel.SignUpInfo;

/**
 * Created by rale on 5/19/16.
 *
 * 登陆的bl实现
 */
public class LoginManagerImpl implements LoginManager {

    @Override
    public LoginResult validate(AuthCredentials authCredentials) {
        if(authCredentials.getUserId().equals("")){
            return LoginResult.LOGIN_ERROR_ID_NULL;
        }
        if(authCredentials.getPassword().equals("")){
            return LoginResult.LOGIN_ERROR_PASSWORD_NULL;
        }
        return LoginResult.VALID;
    }

    @Override
    public LoginResult login(AuthCredentials authCredentials) {
        if(validate(authCredentials) == LoginResult.VALID){
            //
            //
            //实现bl的判断
            //
            if(authCredentials.getUserId().equals("rale")&&authCredentials.getPassword().equals("rale"))
                return LoginResult.SUCCESS;
            else
                return LoginResult.FAIL;

        }else{
            return validate(authCredentials);
        }

    }

    @Override
    public Person findById(String id) {
        return new Person();
    }

    @Override
    public boolean signup(SignUpInfo signUpInfo) {
        if(validateSignup(signUpInfo) == LoginResult.VALID){
            //实现注册的远程实现
            return true;
        }
        return false;
    }

    @Override
    public LoginResult validateSignup(SignUpInfo signUpInfo) {

        return LoginResult.VALID;
    }
}
