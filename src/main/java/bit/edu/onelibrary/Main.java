package bit.edu.onelibrary;

import bit.edu.onelibrary.community.dto.CommunityDto;
import bit.edu.onelibrary.community.dto.CommunityRequest;
import bit.edu.onelibrary.community.service.CommunityService;
import bit.edu.onelibrary.community.service.impl.CommunityServiceImpl;
import bit.edu.onelibrary.user.service.UserService;
import bit.edu.onelibrary.notice.NoticeCenter;
import bit.edu.onelibrary.util.AuthenticationStorage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    Scanner scan = new Scanner(System.in);

    private CommunityService communityService;

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
        notice.openCenter();
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
