package rookie.android.daily.presentationmodel;

import rookie.android.daily.enumerate.LoginResult;

/**
 * Created by rale on 5/19/16.
 *
 * 登录名和密码的验证
 */
public class AuthCredentials {

    private String userId;

    private String password;

    public AuthCredentials(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId(){
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public LoginResult validate(String userId, String password){
        if(userId.equals(""))
            return LoginResult.LOGIN_ERROR_ID_NULL;
        if(password.equals(""))
            return LoginResult.LOGIN_ERROR_PASSWORD_NULL;
        return LoginResult.VALID;
    }
}
