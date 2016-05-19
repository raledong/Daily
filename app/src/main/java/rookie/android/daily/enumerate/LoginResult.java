package rookie.android.daily.enumerate;

/**
 * Created by rale on 5/19/16.
 * 登陆时可能的报错
 */
public enum LoginResult {
    SUCCESS(0, "操作成功"),
    VALID(9, "正确"),

    LOGIN_ERROR(1, "用户名或者密码填写错误"),
    LOGIN_ERROR_ID_NULL(2, "用户名不能为空"),
    LOGIN_ERROR_PASSWORD_NULL(8, "密码不能为空"),

    SIGN_UP_ERROR_MOBILE_WRONG_FORMAT(3, "号码格式错误"),
    SIGN_UP_ERROR_MOBILE_INVALID(4, "号码不存在"),
    SIGN_UP_ERROR_MOBILE_ALREADY_TAKEN(5, "号码已经被注册"),

    SIGN_UP_ERROR_PASSWORDS_NOT_MATCH(6, "两次输入的密码不同"),
    SIGN_UP_ERROR_PASSWORD_INVALID(7, "密码的标准为------");

    private int errorCode;
    private String errorMessage;

    private LoginResult(int code, String message){
        this.errorCode = code;
        this.errorMessage = message;
    }

    public int getErrorCode(){
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
