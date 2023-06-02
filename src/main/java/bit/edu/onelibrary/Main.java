package bit.edu.onelibrary;

import bit.edu.onelibrary.community.dao.CommunityDao;
import bit.edu.onelibrary.community.dto.CommunityDto;
import bit.edu.onelibrary.community.dto.CommunityModifyDTO;
import bit.edu.onelibrary.community.dto.CommunityRequest;
import bit.edu.onelibrary.community.dto.MyCommunity;
import bit.edu.onelibrary.community.service.CommunityService;
import bit.edu.onelibrary.community.service.impl.CommunityServiceImpl;
import bit.edu.onelibrary.user.dao.UserDao;
import bit.edu.onelibrary.user.service.UserService;
import bit.edu.onelibrary.notice.NoticeCenter;
import bit.edu.onelibrary.util.AuthenticationStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    Scanner scan = new Scanner(System.in);

    private CommunityService communityService = new CommunityServiceImpl(new CommunityDao());

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.openCenter();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println("시스템이 이상합니다.");
            System.exit(1);
        }
    }

    public void openCenter() throws SQLException, IOException {
        boolean isClose = false;
        System.out.println("\n\n------------------------------------------------");
        System.out.println("안녕하세요! 도서관 커뮤니티 센터입니다.");
        Scanner scan = new Scanner(System.in);
        String command;
        while (!isClose) {
            this.displayMainMenu();
            command = scan.nextLine();
            System.out.println("------------------------------------------------");
            switch (command) {
                case "1":
                    displayNotice();
                    break;
                case "2":
                    displayLogin();
                    isClose = true;
                    break;
                case "3":
                    displayRegister();
                    break;
                case "4":
                    System.out.println("이용해주셔서 감사합니다.");
                    isClose = true;
                    break;
                default:
                    System.out.println("\n입력값이 잘못되었습니다. 다시 입력해주세요.");
                    break;
            }
        }
    }

    // 메인메뉴 ui
    public void displayMainMenu() {
        System.out.println("------------------------------------------------");
        System.out.println(" 1.공지사항 \n 2.로그인 \n 3.회원가입 \n 4.종료");
        System.out.print("\n * 메뉴선택: ");
    }


    // 공지사항 ui 메소드
    public void displayNotice() {
        UserService userService = new UserService();
        System.out.println("\n\n=================== 공지사항 ====================");
        try {
            this.displayAuth(userService.isAdmin());
        } catch (NullPointerException e) {
            System.out.println("* 비회원으로 이용중입니다.");
            System.out.println("------------------------------------------------");
            this.displayAuth(false);
        }
    }

    public void displayAuth(boolean auth) {
        NoticeCenter notice = new NoticeCenter();
        boolean isClose = false;
        String command = "4";
        if (auth) {
            System.out.println("관리자로 이용중입니다.");
            System.out.println("------------------------------------------------");
            while (!isClose) {
                notice.displayList();
                notice.displayMainMenu();
                command = scan.nextLine();
                switch (command) {
                    case "1":
                        notice.create(scan);
                        break;
                    case "2":
                        notice.read(scan);
                        break;
                    case "3":
                        notice.clear(scan);
                        break;
                    case "4":
                        isClose = true;
                }
            }
        } else {
            while (!isClose) {
                notice.displayList();
                notice.userDisplayMainMenu();
                command = scan.nextLine();
                switch (command) {
                    case "1":
                        notice.read(scan);
                        break;
                    case "2":
                        isClose = true;
                        break;
                }
            }
        }
    }

    // 로그인 ui 메소드
    public void displayLogin() throws SQLException, IOException {
        while (true) {
            System.out.println("\n\n----*----로그인----*-----");
            UserService user = new UserService();
            String id;
            String pw;

            System.out.print("아이디: ");
            id = scan.nextLine();
            System.out.print("비밀번호: ");
            pw = scan.nextLine();
            System.out.println("------------------------");
            System.out.println("\n1.확인  2.메인으로");
            System.out.print("* 메뉴선택: ");
            String command = scan.nextLine();
            if (command.equals("2")) {
                openCenter();
                break;
            }

            if (command.equals("1")) {
                boolean isLoginSucceed = user.login(id, pw);
                if (isLoginSucceed) {
                    System.out.println("\n로그인 성공 \n");
                    this.displayUserMain();
                    break;
                } else {
                    System.out.println("\n아이디나 비밀번호가 잘못되었습니다. 다시 시도해주세요.\n");
                }
            } else{
                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }


        }
    }


    // 회원가입 ui 메소드
    public void displayRegister() {
        UserService user = new UserService();
        String id;
        String pw;
        String name;
        String phone;
        String address;
        String email;

        System.out.println("\n\n============= 회원가입 =============");
        System.out.print("아이디 : ");
        id = scan.nextLine();
        System.out.print("비밀번호 : ");
        pw = scan.nextLine();
        System.out.print("이름 : ");
        name = scan.nextLine();

        // 전화번호에 숫자만 입력하도록 처리
        boolean validPhone = false;
        do {
            System.out.print("전화번호 (숫자만 입력해주세요): ");
            phone = scan.nextLine();
            // \\d는 숫자로만 이루어진 정규식
            //matches를 사용하여 입력된 문자열이 숫자로만 이루어져있는지 확인
            // + 는 하나 이상의 연속된 숫자로 이루어져야 함을 나타냄
            if (phone.matches("\\d+")) {
                validPhone = true;
            } else {
                System.out.println("전화번호에는 숫자만 입력해주세요.");
            }
        } while (!validPhone);

        System.out.print("주소 : ");
        address = scan.nextLine();
        System.out.print("이메일 : ");
        email = scan.nextLine();
        System.out.println("===================================");

        // id 중복시
        try {
            if (user.isIdDuplicated(id)) {
                System.out.println("이미 존재하는 아이디입니다.");
                System.out.println("회원가입을 다시 시도해주세요.");
                this.displayRegister();
            } else {
                System.out.println("회원가입이 완료되었습니다.\n");
                user.register(id, pw, name, phone, address, email);
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
    public void displayUserMain() throws SQLException, IOException {
        UserService service = new UserService();
        System.out.println("\n------------------------------------------------");
        System.out.println("환영합니다.");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean isClose = false;
        while (!isClose) {
            System.out.println("------------------------------------------------");
            System.out.println(" 1.공지사항 \n 2.커뮤니티 \n 3.로그아웃 \n 4.종료");
            System.out.println("------------------------------------------------");
            System.out.print("\n * 메뉴선택: ");
            String command = br.readLine();
//            String command = scan.nextLine();
            switch (command) {
                case "1":
                    displayNotice();
                    break;
                case "2":
                    displayCommunityCenter();
                    break;
                case "3":
                    service.logout();
                    isClose = true;
                    openCenter();
                    break;
                case "4":
                    isClose = true;
                    break;
                default:
                    System.out.println("\n입력값이 잘못되었습니다. 다시 입력해주세요.");
            }
        }

    }

    public void displayCommunityCenter() throws SQLException, IOException {
        System.out.println("_     _      _     _      _     _      _     _      _     _      _     _      _     _      _     _      _     _   \n" +
                "  (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)  \n" +
                "   / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\   \n" +
                " __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__ \n" +
                "(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)\n" +
                "   || C ||      || O ||      || M ||      || M ||      || U ||      || N ||      || I ||      || T ||      || Y ||   \n" +
                " _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._ \n" +
                "(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)\n" +
                " `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'");
        System.out.println(" 1.전체 독후감 조회 \n 2.독후감 작성 \n 3.내가 쓴 독후감 조회 \n");
        String command;
        command = scan.nextLine();

        switch(command) {
            case "1" : displayAllCommunities();
                break;
            case "2" : displayCreateCommunity(); break;
            case "3" : displayMyCommunity(); break;
        }
    }

    public void displayAllCommunities() throws SQLException, IOException {
        System.out.println(".-. .-')                           .-. .-')    _  .-')    ('-.     _ (`-.              _  .-')   .-') _    \n" +
                "\\  ( OO )                          \\  ( OO )  ( \\( -O ) _(  OO)   ( (OO  )            ( \\( -O ) (  OO) )   \n" +
                " ;-----.\\ .-'),-----.  .-'),-----. ,--. ,--.   ,------.(,------. _.`     \\ .-'),-----. ,------. /     '._  \n" +
                " | .-.  |( OO'  .-.  '( OO'  .-.  '|  .'   /   |   /`. '|  .---'(__...--''( OO'  .-.  '|   /`. '|'--...__) \n" +
                " | '-' /_)   |  | |  |/   |  | |  ||      /,   |  /  | ||  |     |  /  | |/   |  | |  ||  /  | |'--.  .--' \n" +
                " | .-. `.\\_) |  |\\|  |\\_) |  |\\|  ||     ' _)  |  |_.' (|  '--.  |  |_.' |\\_) |  |\\|  ||  |_.' |   |  |    \n" +
                " | |  \\  | \\ |  | |  |  \\ |  | |  ||  .   \\    |  .  '.'|  .--'  |  .___.'  \\ |  | |  ||  .  '.'   |  |    \n" +
                " | '--'  /  `'  '-'  '   `'  '-'  '|  |\\   \\   |  |\\  \\ |  `---. |  |        `'  '-'  '|  |\\  \\    |  |    \n" +
                " `------'     `-----'      `-----' `--' '--'   `--' '--'`------' `--'          `-----' `--' '--'   `--'\n");

        List<CommunityDto> allCommunities = communityService.getAllCommunities();
        for (int i = 0; i < allCommunities.size(); i++) {
            System.out.println(i +1 + ". " + allCommunities.get(i).getTitle());
        }

        System.out.println("0. 이전 페이지로");
        int command;
        command = scan.nextInt();


        if (command == 0) {
//            displayCommunityCenter();
            return;
        } else if (command > allCommunities.size() || command < 1) {
            System.out.println("다시 입력해주세요.");
//            displayMyCommunity();
        }else {// 커뮤니티 상세
            System.out.println("제목 : "+ allCommunities.get(command - 1).getTitle());
            System.out.println("내용 : "+ allCommunities.get(command - 1).getCommunityContent());
            System.out.println("작성시간 : "+ allCommunities.get(command - 1).getCreateAt());
        }

//        displayCommunityCenter();
    }
    public void displayCreateCommunity() throws SQLException, IOException {
        System.out.println("===독후감 작성===");
        System.out.println("제목 : ");
        String title = scan.nextLine();
        System.out.println("내용 : ");
        String content = scan.nextLine();

        System.out.println("0. 작성 취소");
        System.out.println("1. 작성 완료");

        int command = scan.nextInt();
        if(command == 0){
//            displayCommunityCenter();
            return;
        }
        if(command==1){
            String name = AuthenticationStorage.getAuthentication().getUserName();
            CommunityRequest request = new CommunityRequest(AuthenticationStorage.getAuthentication().getUserNo(), title, content, name);
            communityService.createCommunity(request);
//            displayCommunityCenter();
        }
    }

    public void displayMyCommunity() throws SQLException, IOException {
        System.out.println(".-. .-')                           .-. .-')    _  .-')    ('-.     _ (`-.              _  .-')   .-') _    \n" +
                "\\  ( OO )                          \\  ( OO )  ( \\( -O ) _(  OO)   ( (OO  )            ( \\( -O ) (  OO) )   \n" +
                " ;-----.\\ .-'),-----.  .-'),-----. ,--. ,--.   ,------.(,------. _.`     \\ .-'),-----. ,------. /     '._  \n" +
                " | .-.  |( OO'  .-.  '( OO'  .-.  '|  .'   /   |   /`. '|  .---'(__...--''( OO'  .-.  '|   /`. '|'--...__) \n" +
                " | '-' /_)   |  | |  |/   |  | |  ||      /,   |  /  | ||  |     |  /  | |/   |  | |  ||  /  | |'--.  .--' \n" +
                " | .-. `.\\_) |  |\\|  |\\_) |  |\\|  ||     ' _)  |  |_.' (|  '--.  |  |_.' |\\_) |  |\\|  ||  |_.' |   |  |    \n" +
                " | |  \\  | \\ |  | |  |  \\ |  | |  ||  .   \\    |  .  '.'|  .--'  |  .___.'  \\ |  | |  ||  .  '.'   |  |    \n" +
                " | '--'  /  `'  '-'  '   `'  '-'  '|  |\\   \\   |  |\\  \\ |  `---. |  |        `'  '-'  '|  |\\  \\    |  |    \n" +
                " `------'     `-----'      `-----' `--' '--'   `--' '--'`------' `--'          `-----' `--' '--'   `--'\n");

        List<MyCommunity> myCommunityList = communityService.getMyCommunities(AuthenticationStorage.getAuthentication().getUserNo());
        for (int i = 0; i < myCommunityList.size(); i++) {
            System.out.println(i + 1 + ". " + myCommunityList.get(i).getTitle());
        }
        System.out.println("0. 이전 페이지로");
        int command = scan.nextInt();

        if (command == 0) {
//            displayCommunityCenter();
            return;
        } else if (command > myCommunityList.size() || command < 1) {
            System.out.println("다시 입력해주세요.");
//            displayMyCommunity();
        }else {// 커뮤니티 상세
            System.out.println("제목 : "+ myCommunityList.get(command - 1).getTitle());
            System.out.println("내용 : "+ myCommunityList.get(command - 1).getCommunityContent());
            System.out.println("작성시간 : "+ myCommunityList.get(command - 1).getCreateAt());

            System.out.println("1. 수정");
            System.out.println("2. 삭제");
            System.out.println("0. 목록으로");

            int menuCommand = scan.nextInt();


            switch (menuCommand){
                case 1 :
                    displayUpdateCommunity(myCommunityList.get(command - 1).getCommunityNo());
                    break;
                case 2 :
                    displayDeleteCommunity(myCommunityList.get(command - 1).getCommunityNo());
                    break;
                case 0:
                    displayCommunityCenter();
                    break;
            }



        }

    }

    public void displayUpdateCommunity(long communityNo) throws IOException {

        System.out.println("수정 페이지 입니다.");
        System.out.println("제목을 입력하세요: ");
        String title = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        title = br.readLine();
//        String title = scan.nextLine();

        System.out.println("내용을 입력하세요: ");
        String content = "";
        content = br.readLine();

        ;
        CommunityModifyDTO communityModifyDTO = new CommunityModifyDTO(
                communityNo,
                title,
                content,
                AuthenticationStorage.getAuthentication().getUserNo()
        );

        try {
            communityService.updateCommunity(communityModifyDTO);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void displayDeleteCommunity(long communityNo) throws SQLException, IOException {
        System.out.println("정말 삭제하시겠습니까?");
        System.out.println("1. 확인  2. 취소");
        int command = scan.nextInt();

        if (command == 1) {
            communityService.deleteCommunity(communityNo);
            System.out.println("삭제하였습니다.");
        }

        if (command == 2) {
            System.out.println("삭제를 취소하였습니다.");
        }

//        displayCommunityCenter();

    }


}
