package rookie.android.daily.presentationmodel;

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
}
