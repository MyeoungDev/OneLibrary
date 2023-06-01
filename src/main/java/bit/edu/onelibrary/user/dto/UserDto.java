package bit.edu.onelibrary.user.dto;

public class UserDto {
    private int no;
    private String id;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int role;

    public UserDto(int no, String id, String password, String name, String address, String phone, String email, int role) {
        this.no = no;
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.role = role;
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

    public int isRole() {
        return role;
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
                ", role=" + role +
                '}';
    }

}
