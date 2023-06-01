package bit.edu.onelibrary.user.service;

import bit.edu.onelibrary.user.dao.UserDao;
import bit.edu.onelibrary.user.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;

public class UserService {

    public void register(){

    }

    public boolean isIdDuplicated(){
        boolean flag = false;

        return flag;

    }

    // 로그인
    public boolean login(){
        boolean flag = false;
        UserDto user = null;
        try {
            UserDao dao = new UserDao();
            user = dao.selectById("wnddusssd");
        } catch (IOException | SQLException e){
            e.printStackTrace();
        }

        if (user != null){
            System.out.println("있음");
        } else{
            System.out.println("없음");
        }
        // 아이디가 있으면
            // 비밀번호를 확인
                // 맞으면
                    // 권한확인 :
                // 아니면
                    // 틀렸습니다.
        // 아이디가 없으면
            // 틀렸습니다.
        return flag;
    }

    // 관리자인지 아닌지 확인
    public boolean isAdmin(){
        // 유저 dto role 확인해서 어드민인지 확인
        boolean isAdmin = true;

        return isAdmin;
    }

}
