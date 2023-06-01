package bit.edu.onelibrary.user.entity;

public class User {
    private int userNo;
    private String userId;
    private String userPassword;
    private String userName;
    private String userAddress;
    private String userPhone;
    private String userEmail;
    private boolean isAdmin;

    public int getUserNo() {
        return userNo;
    }

    public User(int userNo, String userId, String userPassword, String userName,
                String userAddress, String userPhone, String userEmail, boolean isAdmin) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.isAdmin = isAdmin;
    }
}
