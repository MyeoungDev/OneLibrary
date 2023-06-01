package bit.edu.onelibrary;

import bit.edu.onelibrary.user.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService user = new UserService();
        user.login();
    }

    // 메인메뉴 ui
    public void displayMainMenu(){
        System.out.println("----도서관----");

        //1. 공지사항
        //2. 로그인
        //3. 회원가입
    }


    // 공지사항 ui 메소드
    public void displayNotice(){
        System.out.println("-- 공지 --");
    }

    // 로그인 ui 메소드
    public void displayLogin(){
        System.out.println("---로그인---");

        // 로그인 실패시
        System.out.println("잘못됨");

        // 로그인 성공 시 권한 확인해서 전역변수 변경
        // 커뮤니티 -> user_no도 전역변수 설정해줘야하는지? 명관님한테 물어보자
    }


    // 회원가입 ui 메소드
    public void displayRegister(){
        System.out.println("---회원가입---");

        // id 중복시
        System.out.println("이미 존재하는 아이디입니다.");
    }

    // 확인, 취소 입력받는 메소드
    public boolean displayConfirm(Scanner scan) {
        boolean flag = false;
        System.out.println("-------------------------------------------------------------------");
        System.out.println("보조메뉴: 1.확인 | 2.취소");
        System.out.print("메뉴선택: ");
        String command = scan.nextLine();
        if (command.equals("1")) {
            flag = true;
        }
        return flag;
    }

    // 로그인 후 메인 메뉴 ui
    public void displayUserMain(){
        System.out.println("환영합니다.");
        // 1. 공지사항으로 이동
        // 2. 커뮤니티로 이동
    }

    public void displayCommunity(){
        System.out.println("===커뮤니티===");
    }

}
