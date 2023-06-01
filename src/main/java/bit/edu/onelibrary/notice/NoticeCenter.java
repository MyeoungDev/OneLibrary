package bit.edu.onelibrary.notice;

import java.util.Scanner;

public class NoticeCenter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NoticeCenter center = new NoticeCenter();
		center.openCenter();
	}
	BoardService bs =new BoardService();
	public void openCenter() {
		boolean isClose = false;
		System.out.println("---------공지사항---------\n\n");
		Scanner scan = new Scanner(System.in);
		String command = "4";
		while(!isClose) {
			this.displayList();
			this.displayMainMenu();
			command = scan.nextLine();
			switch(command) {
				case "1" : create(scan); break;
				case "2" : read(scan); break;
				case "3" : clear(scan); break;
				case "4" : isClose = true;
			}
		}
		scan.close();
		System.out.println("\n\n이용해 주셔서 감사합니다.");
	}
	

	private void clear(Scanner scan) {
		// TODO Auto-generated method stub
		System.out.println("전체 삭제작업을 시작합니다.");
		boolean flag = this.displayConfirm(scan);
		if(flag) {
			//삭제작업
			bs.removeAll();
			System.out.println("전체 삭제작업을 완료했습니다.");
		} else {
			System.out.println("전체 삭제작업을 취소했습니다.");
		}
	}

	private void read(Scanner scan) {
		// TODO Auto-generated method stub
		System.out.println("[게시물 읽기]");
		System.out.print("bno: "); 
		String command = scan.nextLine();
		this.displayDetail(command,scan);
	}

	private void create(Scanner scan) {
		// TODO Auto-generated method stub
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
			bs.register(board);
			System.out.println("삽입완료");
		} else {
			System.out.println("삽입취소");
		}
		
	}

	private void displayList() {
		bs.readAll();
		for(int i = 0 ; i < bs.readAll().size() ; i++){
			System.out.print(bs.readAll().get(i).getBno()+"  ");
			System.out.print(bs.readAll().get(i).getBtitle()+"  ");
			System.out.print(bs.readAll().get(i).getBcontent()+"  ");
			System.out.print(bs.readAll().get(i).getBwriter()+"  ");
			System.out.println(bs.readAll().get(i).getBdate());
		}
		System.out.println(bs.readAll());
		System.out.println("전체 리스트");
	}
	
	private void displayDetail(String bno,Scanner scan) {
		System.out.println(bno+"번 상세 내용");
		
		System.out.println();
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
				bs.modify(modifyB);
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
				bs.remove(Integer.parseInt(bno));
				System.out.println("삭제완료");
			} else {
				System.out.println("삭제취소");
				this.displayDetail(bno, scan);
			}
		} 
	}
	
	private void displayMainMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("메인메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
		System.out.print("메뉴선택: ");
		
	}	
	
	private boolean displayConfirm(Scanner scan) {
		boolean flag = false;
		System.out.println("-------------------------------------------------------------------");
		System.out.println("보조메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴선택: ");
		String command = scan.nextLine();
		if(command.equals("1")) {
			flag = true;
		}
		return flag;
	}
	
	private void displaySubMenu() {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("보조메뉴: 1.Update | 2.Delete | 3.List");
		System.out.print("메뉴선택: ");
	}

}
