package view;

public class Menu {

	// 메인 메뉴
	public static void selectMenu() {
		System.out.println("═════MODE SELECT═════");
		System.out.println(" 1. 관리자 모드");
		System.out.println(" 2. 사용자 모드");
		System.out.println(" 3. 프로그램 종료");
		System.out.println("═════════════════════");
		System.out.print("▶ 모드를 선택하세요: ");
	}

	// 관리자 메뉴
	public static void adminMenu() {
		System.out.println("═══════ADMIN MODE═══════");
		System.out.println("1. 재고 입고");
		System.out.println("2. 재고 현황");
		System.out.println("3. 재고 검색");
		System.out.println("4. 재고 반품");
		System.out.println("5. 재고 수정");
		System.out.println("6. 매출 현황");
		System.out.println("7. 종료");
		System.out.println("════════════════════════");
		System.out.print("▶ 메뉴 번호를 선택하세요: ");
	}

	public static void arrayMenu() {
		System.out.println("══════ARRAY OPTION══════");
		System.out.println("1. 오름차순 정렬");
		System.out.println("2. 내림차순 정렬");
		System.out.println("════════════════════════");
		System.out.print("▶ 정렬 방식을 선택하세요: ");
	}

	// 사용자 메뉴
	public static void customerMenu() {
		System.out.println("═══════CUSTOMER MENU═══════");
		System.out.println("1. 구매");
		System.out.println("2. 영수증 요청");
		System.out.println("3. 환불");
		System.out.println("4. 종료");
		System.out.println("═══════════════════════════");
		System.out.print("▶ 메뉴 번호를 선택하세요: ");
	}

}
