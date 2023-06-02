package bit.edu.onelibrary.user.service;

import bit.edu.onelibrary.user.dao.UserDao;
import bit.edu.onelibrary.user.dto.UserAuthenticationDto;
import bit.edu.onelibrary.user.dto.UserDto;

import bit.edu.onelibrary.util.AuthenticationStorage;
import java.io.IOException;
import java.sql.SQLException;

public class UserService {
    private UserDao dao = new UserDao();
    public boolean register(String id, String password, String name, String phone, String address, String email) throws SQLException, IOException {
        boolean flag = false;
        if(dao.insertUser(id, password, name, phone, address, email)){
            flag = true;
        }
        return flag;
    }

    public boolean isIdDuplicated(String id) throws SQLException, IOException {
        boolean flag = false;
        if(dao.selectByIdCount(id)){
            flag = true;
        }

        return flag;

    }

    // 로그인
    public boolean login(String id, String password){
        // 로그인 성공 여부
        boolean flag = false;

        UserDto user = null;

        try {
            UserDao dao = new UserDao();
            user = dao.selectById(id);
        } catch (IOException | SQLException e){
            e.printStackTrace();
        }

        if (user != null){
            if (password.equals(user.getPassword())){
                UserAuthenticationDto userAuthenticationDto =
                        new UserAuthenticationDto(
                                user.getNo(),
                                user.getName(),
                                user.isAdmin()
                        );
                AuthenticationStorage.saveAuthentication(userAuthenticationDto);
                flag = true;
            }
        }

        return flag;
    }

    // 관리자인지 아닌지 확인
    public boolean isAdmin(){
        UserAuthenticationDto authentication = AuthenticationStorage.getAuthentication();
        return authentication.isAdmin();
    }

    public void logout(){
        AuthenticationStorage.removeAuthentication();
    }
}
