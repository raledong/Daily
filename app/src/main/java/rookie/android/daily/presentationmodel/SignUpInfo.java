package rookie.android.daily.presentationmodel;

/**
 * Created by rale on 5/19/16.
 * 用户注册的表格信息
 */
public class SignUpInfo {
    //手机
    private String mobile;

    //密码
    private String password;

    //再次输入密码
    private String repassword;


    public SignUpInfo(String mobile, String password, String repassword) {
        this.mobile = mobile;
        this.password = password;
        this.repassword = repassword;
    }

    public String getMobile(){
        return mobile;
    }

    public String getPassword(){
        return password;
    }

    public String getRepassword() {
        return repassword;
    }
}
