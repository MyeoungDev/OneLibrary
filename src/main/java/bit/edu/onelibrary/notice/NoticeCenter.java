package bit.edu.onelibrary.notice;

import bit.edu.onelibrary.notice.dto.BoardDTO;
import bit.edu.onelibrary.notice.service.BoardService;
import bit.edu.onelibrary.user.service.UserService;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class NoticeCenter {

	BoardService boardService = new BoardService();

	public void clear(Scanner scan) {
		// TODO Auto-generated method stub
		System.out.println("전체 삭제작업을 시작합니다.");
		boolean flag = this.displayConfirm(scan);
		if(flag) {
			//삭제작업
			boardService.removeAll();
			System.out.println("전체 삭제작업을 완료했습니다.");
		} else {
			System.out.println("전체 삭제작업을 취소했습니다.");
		}
	}

	public void read(Scanner scan) {
		System.out.println("[게시물 읽기]");
		System.out.print("게시물 번호 : ");
		String command = scan.nextLine();
		this.displayDetail(command,scan);
	}

	public void create(Scanner scan) {
		BoardDTO board = new BoardDTO();
		System.out.println("[새 게시물 입력]");
		System.out.print("제목: "); 	
		board.setBtitle(scan.nextLine());
		System.out.print("내용: "); 	
		board.setBcontent(scan.nextLine());
		System.out.print("글쓴이: "); 	
		board.setBwriter(scan.nextLine());
		System.out.println();
		boolean flag = this.displayConfirm(scan);
		if(flag) {
			//삽입작업
			boardService.register(board);
			System.out.println("삽입완료");
		} else {
			System.out.println("삽입취소");
		}
		
	}

	public void displayList() {
		ArrayList<BoardDTO> boardDTOS = boardService.readAll();
		StringBuilder sb = new StringBuilder();
		System.out.println("[ 전체 목록 ]");
		System.out.println("번호\t 제목\t\t\t\t글쓴이\t\t작성일");
		for(int i = 0; i < boardDTOS.size() ; i++){
			sb.append("  " + boardDTOS.get(i).getBno()+"\t");
			sb.append(boardDTOS.get(i).getBtitle()+"\t\t");
			sb.append(boardDTOS.get(i).getBwriter()+"\t\t");
			sb.append(boardDTOS.get(i).getBdate()+"\n");
		}
		System.out.println(sb);
	}

	public void displayDetail(String bno,Scanner scan) {
		System.out.println("[ \n"+bno+"번 상세 내용 ]");
		UserService userService = new UserService();
		StringBuilder sb = new StringBuilder();
		BoardDTO read = boardService.read(Integer.parseInt(bno));
		if (Objects.isNull(read)) {
			System.out.println("-------------\n존재하지 않는 글입니다.\n-------------");
			return;
		}
		sb.append("제목 : "+ read.getBtitle()+"\n");
		sb.append("글쓴이 : "+ read.getBwriter()+"\n");
		sb.append("날짜 : "+ read.getBdate()+"\n");
		sb.append("내용 \n"+ read.getBcontent()+"\n");
		System.out.print(sb);
		try{
			if(userService.isAdmin()){
				this.displaySubMenu();
				String command = scan.nextLine();
				boolean flag = false;
				if(command.equals("1")) {
					System.out.println("수정작업");
					flag = this.displayConfirm(scan);
					if(flag) {
						//수정작업
						BoardDTO modifyB = new BoardDTO();
						System.out.println("[수정]");
						System.out.print("제목: ");
						modifyB.setBtitle(scan.nextLine());
						System.out.print("내용: ");
						modifyB.setBcontent(scan.nextLine());
						System.out.print("글쓴이: ");
						modifyB.setBwriter(scan.nextLine());
						System.out.println();
						modifyB.setBno(Integer.parseInt(bno));
						boardService.modify(modifyB);
						System.out.println("수정완료");
					} else {
						System.out.println("수정취소");
						this.displayDetail(bno, scan);
					}
				} else if(command.equals("2")) {
					System.out.println("삭제작업");
					flag = this.displayConfirm(scan);
					if(flag) {
						//삭제작업
						boardService.remove(Integer.parseInt(bno));
						System.out.println("삭제완료");
					} else {
						System.out.println("삭제취소");
						this.displayDetail(bno, scan);
					}
				}
			}else {
				this.userDisplaySubMenu();
				String command = scan.nextLine();
				if (command.equals("1")) {
				}
		}
		}catch (Exception e) {
			this.userDisplaySubMenu();
			String command = scan.nextLine();
			if (command.equals("1")) {
			}
		}
	}

	public void displayMainMenu() {
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println("메인메뉴: 1.작성 | 2.상세보기 | 3.전체삭제 | 4.나가기");
		System.out.print("메뉴선택: ");
		
	}
	public void userDisplayMainMenu() {
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println("메인메뉴: 1.상세보기 | 2.나가기");
		System.out.print("메뉴선택: ");

	}

	public boolean displayConfirm(Scanner scan) {
		boolean flag = false;
		System.out.println("------------------------------------------------");
		System.out.println("메뉴: 1.확인 | 2.취소");
		System.out.print("메뉴선택: ");
		String command = scan.nextLine();
		if(command.equals("1")) {
			flag = true;
		}
		return flag;
	}

	public void displaySubMenu() {
		System.out.println("------------------------------------------------");
		System.out.println("메뉴: 1.수정 | 2.삭제 | 3.목록으로 돌아가기");
		System.out.print("메뉴선택: ");
	}
	public void userDisplaySubMenu() {
		System.out.println("------------------------------------------------");
		System.out.println("메뉴: 1.목록으로 돌아가기");
		System.out.print("메뉴선택: ");
	}
}
