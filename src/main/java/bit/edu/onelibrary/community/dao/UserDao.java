package bit.edu.onelibrary.community.dao;

public class UserDao {


    // 회원가입
    public boolean insertUser(){
        boolean flag = false;

        return flag;
    }

    // 아이디 중복 체크: 같은 아이디인 유저 목록 들고오기
    public boolean selectByIdCount(){
        boolean flag = false;

        return flag;
    }


    // 로그인: 같은 아이디인 유저를 찾아서 비밀번호 비교
    public boolean selectById(){
        // 입력한 id와 같은 id의 유저 조회
        boolean flag = false;

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



}
