package bit.edu.onelibrary.user.dto;

public class UserDto {
    private int no;
    private String id;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String email;
    private boolean isAdmin;

    public UserDto(int no, String id, String password, String name, String address, String phone, String email, boolean isAdmin) {
        this.no = no;
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public int getNo() {
        return no;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "no=" + no +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role=" + isAdmin +
                '}';
    }

}
