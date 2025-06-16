package khstokomain;

import java.sql.Connection;
import java.util.Scanner;

import controller.DBUtil;
import controller.ProductManager;
import controller.SalesManager;
import view.AdminChoice;
import view.CustomerChoice;
import view.Menu;
import view.ModeChoice;

public class StokoMain {
	public static Scanner scan = new Scanner(System.in);
	public static SalesManager sm = new SalesManager();
	public static ProductManager pm = new ProductManager();

	public static void main(String[] args) {
		Connection con = DBUtil.getConnection();
		int choice = 0;
		boolean exitFlag = false;

		if (con == null) {
			System.out.println("디비연결오류");
			return;
		}
		// 화면설계진행
		while (!exitFlag) {
			try {
				Menu.selectMenu();
				choice = Integer.parseInt(scan.nextLine());

				switch (choice) {
				case ModeChoice.관리자모드:
					adminMenu();
					break;
				case ModeChoice.사용자모드:
					customerMenu(sm);
					break;
				case ModeChoice.프로그램종료:
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
				exitFlag = true;
			}
		} // end of while
		System.out.println("프로그램 종료");
	}// end of main

	// 관리자 메뉴
	public static void adminMenu() {
		int choice = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				Menu.adminMenu();
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case AdminChoice.재고입고:
					pm.register();
					break;
				case AdminChoice.재고현황:
					pm.list();
					break;
				case AdminChoice.재고검색:
					pm.select();
					break;
				case AdminChoice.재고반품:
					pm.delete();
					break;
				case AdminChoice.재고수정:
					pm.update();
					break;
				case AdminChoice.매출현황:
					sm.list();
					break;
				case AdminChoice.종료:
					System.out.println("관리자 메뉴 종료");
					exitFlag = true;
					break;
				} // end of while
			} catch (Exception e) {
				System.out.println("학과정보 예외발생 조치바람");
			}
		} // while
	}// end of subjectMenu

	// 영수증 관리 메뉴
	public static void customerMenu(SalesManager sm) {
		int choice = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				Menu.customerMenu();
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case CustomerChoice.구매:
					pm.cusDelete();
					sm.register();
					break;
				case CustomerChoice.영수증요청:
					sm.selectCus();
					break;
				case CustomerChoice.환불:
					sm.delete();
					break;
				case CustomerChoice.종료:
					System.out.println("소비자 메뉴 종료");
					exitFlag = true;
					break;
				} // end of while
			} catch (Exception e) {
				System.out.println("학생관리 예외발생 조치바람");
			}
		} // while
	}// end of subjectMenu

}
