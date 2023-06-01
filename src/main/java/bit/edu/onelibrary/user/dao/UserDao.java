package bit.edu.onelibrary.user.dao;

import bit.edu.onelibrary.user.dto.UserDto;
import bit.edu.onelibrary.util.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {


    // 회원가입
    public boolean insertUser(String id, String password, String name, String phone, String address, String email) throws IOException, SQLException {
        boolean flag = false;
        //insert
        Connection con = ConnectionManager.getConnection();
        String sql = "insert user(user_id, user_password, user_name, user_phone, user_address, user_email, is_admin) values (?,?,?,?,?,?,0)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, password);
        pstmt.setString(3, name);
        pstmt.setString(4, phone);
        pstmt.setString(5, address);
        pstmt.setString(6, email);

        int affectedCount = pstmt.executeUpdate();
        if(affectedCount > 0) {
            flag = true;
        }
        ConnectionManager.closeConnection(con, pstmt, null);
        return flag;
    }

    // 아이디 중복 체크: 같은 아이디인 유저 목록 들고오기
    public boolean selectByIdCount(String id) throws IOException, SQLException {
        boolean flag = false;
        //select
        Connection con = ConnectionManager.getConnection();
        String sql = "select count(user_id) from user where user_id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            if(rs.getInt(1)>0){
                flag = true;
            }
        }
        return flag;
    }


    // 로그인: 같은 아이디인 유저를 찾아서 비밀번호 비교
    public UserDto selectById(String userId) throws IOException, SQLException {
        UserDto user;

        // 커넥션
        Connection con = ConnectionManager.getConnection();
        String sql = "SELECT * FROM bitedu.user where user_id= ? ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, userId);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            // 일치하는 id 있는 경우 DTO 생성
            user = new UserDto(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getBoolean(8));
        } else {
            // 일치하는 id 없는 경우 null 반환
            return null;
        }

        return user;
    }



}
