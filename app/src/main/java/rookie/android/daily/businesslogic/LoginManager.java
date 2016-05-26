package rookie.android.daily.businesslogic;

import rookie.android.daily.enumerate.LoginResult;
import rookie.android.daily.model.Person;
import rookie.android.daily.presentationmodel.AuthCredentials;
import rookie.android.daily.presentationmodel.SignUpInfo;

/**
 * Created by rale on 5/19/16.
 */
public interface LoginManager {

    //验证登陆信息的输入是否正确
    LoginResult validate(AuthCredentials authCredentials);

    //判断是否登陆成功
    LoginResult login(AuthCredentials authCredentials);

    //获取用户信息
    Person findById(String id);

    //返回是否注册成功
    boolean signup(SignUpInfo signUpInfo);

    //返回注册信息是否正确
    LoginResult validateSignup(SignUpInfo signUpInfo);
}
