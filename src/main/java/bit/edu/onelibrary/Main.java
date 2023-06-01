package bit.edu.onelibrary;

import bit.edu.onelibrary.user.service.UserService;
import bit.edu.onelibrary.notice.NoticeCenter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.openCenter();
    }

    public void openCenter(){
        System.out.println("----도서관----");
        boolean isClose = false;
        System.out.println("");
        System.out.println("안녕하세요! 도서관 커뮤니티 센터입니다.");
        Scanner scan = new Scanner(System.in);
        String command;
        while(!isClose) {
            this.displayMainMenu();
            command = scan.nextLine();
            switch(command) {
                case "1" : displayNotice(); break;
                case "2" : displayLogin(); break;
                case "3" : displayRegister(); break;
            }
        }
        scan.close();
        System.out.println("\n\n이용해 주셔서 감사합니다.");
    }

    // 메인메뉴 ui
    public void displayMainMenu(){
        System.out.println("--------------------------------------");
        System.out.println(" 1.공지사항 \n 2.로그인 \n 3.회원가입 \n");
        System.out.print(" * 메뉴선택: ");
    }


    // 공지사항 ui 메소드
    public void displayNotice(){
        System.out.println("-- 공지 --");
        NoticeCenter notice = new NoticeCenter();
        boolean isClose = false;
        System.out.println("--------------공지사항--------------");
        Scanner scan = new Scanner(System.in);
        String command = "4";
        while(!isClose) {
            notice.displayList();
            notice.displayMainMenu();
            command = scan.nextLine();
            switch(command) {
                case "1" : notice.create(scan); break;
                case "2" : notice.read(scan); break;
                case "3" : notice.clear(scan); break;
                case "4" : isClose = true;
            }
        }
        this.openCenter();
    }

    // 로그인 ui 메소드
    public void displayLogin(){
        System.out.println("---*---로그인---*---");
        UserService user = new UserService();
        String id;
        String pw;

        System.out.print("아이디: ");
        id = scan.nextLine();
        System.out.print("비밀번호: ");
        pw = scan.nextLine();
        boolean isSuccess = user.login(id, pw);

        if (isSuccess){
            System.out.println("로그인 성공");
        } else {
            System.out.println("아이디나 비밀번호가 잘못되었습니다. 다시 시도해주세요.");
        }
    }


    // 회원가입 ui 메소드
    public void displayRegister(){
        UserService user = new UserService();
        String id;
        String pw;
        String name;
        String phone;
        String address;
        String email;

        System.out.println("-------회원가입------");
        System.out.println("아이디 :");
        id = scan.nextLine();
        System.out.println("비밀번호 :");
        pw = scan.nextLine();
        System.out.println("이름 : ");
        name = scan.nextLine();
        System.out.println("전화번호 : ");
        phone = scan.nextLine();
        System.out.println("주소 : ");
        address = scan.nextLine();
        System.out.println("이메일 : ");
        email = scan.nextLine();
        System.out.println("--------------------");

        // id 중복시
        if(user.isIdDuplicated(id)) {
            System.out.println("이미 존재하는 아이디입니다.");
            System.out.println("회원가입을 다시 시도해주세요.");
        } else {
            try {
                if(user.register(id, pw, name, phone, address, email)){
                    System.out.println("회원가입이 완료되었습니다.");
                    this.displayMainMenu();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
