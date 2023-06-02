package bit.edu.onelibrary;

import bit.edu.onelibrary.community.dto.CommunityDto;
import bit.edu.onelibrary.community.dto.CommunityRequest;
import bit.edu.onelibrary.community.service.CommunityService;
import bit.edu.onelibrary.user.service.UserService;
import bit.edu.onelibrary.notice.NoticeCenter;
import bit.edu.onelibrary.util.AuthenticationStorage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    Scanner scan = new Scanner(System.in);

    private CommunityService communityService;

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.openCenter();
        } catch (SQLException | IOException e) {
            System.out.println("시스템이 이상합니다.");
            System.exit(1);
        }
    }

    public void openCenter() throws SQLException, IOException{
        boolean isClose = false;
        System.out.println("--------------------------------------");
        System.out.println("\n안녕하세요! 도서관 커뮤니티 센터입니다.");
        Scanner scan = new Scanner(System.in);
        String command;
        while(!isClose) {
            this.displayMainMenu();
            command = scan.nextLine();
            System.out.println("--------------------------------------");
            switch(command) {
                case "1" : displayNotice(); break;
                case "2" : displayLogin(); isClose=true; break;
                case "3" : displayRegister(); break;
                case "4" : isClose=true;
            }
        }
    }

    // 메인메뉴 ui
    public void displayMainMenu(){
        System.out.println("--------------------------------------");
        System.out.println(" 1.공지사항 \n 2.로그인 \n 3.회원가입 \n 4.종료");
        System.out.print("\n * 메뉴선택: ");
    }


    // 공지사항 ui 메소드
    public void displayNotice(){
        UserService us = new UserService();
        NoticeCenter notice = new NoticeCenter();
        boolean isClose = false;
        Scanner scan = new Scanner(System.in);
        String command = "4";
        try{
            System.out.println("--------------공지사항--------------");
            if(us.isAdmin()==true){
                System.out.println("관리자로 로그인 되었습니다.");
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
            }else{
                System.out.println("사용자로 로그인 되었습니다.");
                while(!isClose) {
                    notice.displayList();
                    notice.userDisplayMainMenu();
                    command = scan.nextLine();
                    switch(command) {
                        case "1" : notice.read(scan); break;
                        case "2" : isClose = true;
                    }
                }
            }
        }catch (NullPointerException e){
            System.out.println("비회원 입니다.");
            while(!isClose) {
                notice.displayList();
                notice.userDisplayMainMenu();
                command = scan.nextLine();
                switch(command) {
                    case "1" : notice.read(scan); break;
                    case "2" : isClose = true; break;
                }
            }
        }
    }

    // 로그인 ui 메소드
    public void displayLogin() throws SQLException, IOException{
        while (true){
            System.out.println("\n\n----*----로그인----*----");
            UserService user = new UserService();
            String id;
            String pw;

            System.out.print("아이디: ");
            id = scan.nextLine();
            System.out.print("비밀번호: ");
            pw = scan.nextLine();
            System.out.println("\n1.확인  2.메인으로");
            String command = scan.nextLine();
            if (command.equals("2")){
                displayUserMain();
                break;
            }

            if (command.equals("1")){
                boolean isLoginSucceed = user.login(id, pw);
                if (isLoginSucceed){
                    System.out.println("\n로그인 성공 \n" );
                    this.displayUserMain();
                    break;
                } else{
                    System.out.println("\n아이디나 비밀번호가 잘못되었습니다. 다시 시도해주세요.\n");
                }
            }
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

        System.out.println("\n\n========== 회원가입 ==========");
        System.out.print("아이디 : ");
        id = scan.nextLine();
        System.out.print("비밀번호 : ");
        pw = scan.nextLine();
        System.out.print("이름 : ");
        name = scan.nextLine();
        System.out.print("전화번호 : ");
        phone = scan.nextLine();
        System.out.print("주소 : ");
        address = scan.nextLine();
        System.out.print("이메일 : ");
        email = scan.nextLine();
        System.out.println("===============================");

        // id 중복시
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
    public void displayUserMain() throws SQLException, IOException{
        System.out.println("환영합니다.");
        boolean isClose = false;
        while(!isClose) {
            System.out.println("--------------------------------------");
            System.out.println(" 1.공지사항 \n 2.커뮤니티 \n 3.종료");
            System.out.print("\n * 메뉴선택: ");
            String command = scan.nextLine();
            System.out.println("--------------------------------------");
            switch(command) {
                case "1" : displayNotice(); break;
                case "2" : displayCommunity(); break;
                case "3" : isClose = true; break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }

    }

    public void displayCommunity() throws SQLException, IOException {
        System.out.println("===커뮤니티===");
        System.out.println(" 1.전체 독후감 조회 \n 2.독후감 작성 \n 3.내가 쓴 독후감 조회 \n");
        String command;
        command = scan.nextLine();

        switch(command) {
            case "1" : displayAllCommunities();
                break;
            case "2" : ; break;
            case "3" : communityService.getMyCommunities(AuthenticationStorage.getAuthentication().getUserNo()); break;
        }
    }

    public void displayAllCommunities() throws SQLException, IOException {
        System.out.println("===전체 독후감===");

        List<CommunityDto> allCommunities = communityService.getAllCommunities();
        for (int i = 0; i < allCommunities.size(); i++) {
            System.out.println(i + ". " + allCommunities.get(i).getTitle());
        }

        displayCommunity();
    }
    public void displayCreateCommunity() throws SQLException, IOException {
        System.out.println("===독후감 작성===");
        System.out.println("제목 : ");
        String title = scan.nextLine();
        System.out.println("내용 : ");
        String content = scan.nextLine();
        String name = AuthenticationStorage.getAuthentication().getUserName();
        CommunityRequest request = new CommunityRequest(AuthenticationStorage.getAuthentication().getUserNo(), title, content);
        communityService.createCommunity(request);
    }

}
