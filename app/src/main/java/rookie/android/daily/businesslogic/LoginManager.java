package rookie.android.daily.businesslogic;

import rookie.android.daily.enumerate.LoginResult;
import rookie.android.daily.presentationmodel.AuthCredentials;
import rookie.android.daily.presentationmodel.SignUpInfo;

/**
 * Created by rale on 5/19/16.
 */
public interface LoginManager {

    //验证登陆信息的输入是否正确
    public LoginResult validate(AuthCredentials authCredentials);

    //判断是否登陆成功
    public LoginResult login(AuthCredentials authCredentials);

    //返回是否注册成功
    public boolean signup(SignUpInfo signUpInfo);

    //返回注册信息是否正确
    public LoginResult validateSignup(SignUpInfo signUpInfo);
}
