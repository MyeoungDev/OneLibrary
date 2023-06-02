package bit.edu.onelibrary.user.dto;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class UserAuthenticationDto {
    private long userNo;
    private String userName;
    private boolean isAdmin;

    public UserAuthenticationDto(long userNo, String userName, boolean isAdmin) {
        this.userNo = userNo;
        this.userName = userName;
        this.isAdmin = isAdmin;
    }

    public long getUserNo() {
        return userNo;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getUserName(){
        return userName;
    }
}
