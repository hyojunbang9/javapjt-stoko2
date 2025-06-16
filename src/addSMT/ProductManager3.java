package addSMT;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import model.ProductVO;

public class ProductManager3 {
	// 제품 목록
	public void list() throws Exception {
		ProductDAO ld = new ProductDAO();
		System.out.println("제품 전체 리스트");
		ArrayList<ProductVO> productList = ld.selectAll();
		if (productList.size() == 0) {
			System.out.println("제품 전체 리스트 내용이 없습니다.");
			return;
		} else if (productList == null) {
			System.out.println("제품 전 리스트 에러발생");
			return;
		}
		for (ProductVO data : productList) {
			System.out.println(data.toString());
		}
	}

	// 제품 검색(제품 번호) 관리
	public void select() throws Exception {
		Scanner scan = new Scanner(System.in);

		ProductDAO pd = new ProductDAO();
		ProductVO pvo = new ProductVO();
		int stokoNum = -1;

		System.out.print("검색할 제품 번호>");
		stokoNum = Integer.parseInt(scan.nextLine());

		ArrayList<ProductVO> productList = pd.selectAll();

		for (ProductVO data : productList) {
			if (stokoNum == data.getStokoNum()) {
				System.out.println(data.toString());
			} else {
				continue;
			}
		}

		System.out.println("------------------");
		ProductVO findData = pd.selectByStokoNum(stokoNum);
		System.out.println(findData);
		if (productList.size() == 0) {
			System.out.println("제품 전체 리스트 내용이 없습니다.");
			return;
		} else if (productList == null) {
			System.out.println("제품 전 리스트 에러발생");
			return;
		}

	}

	// 제품 입고(추가) 관리
	public void register() throws Exception {
		Scanner scan = new Scanner(System.in);

		ProductDAO pd = new ProductDAO();
		ProductVO pvo = new ProductVO();

		String productName; // 제품 이름
		int quantity; // 제품 입고 수량
		int price; // 제품 입고 가격
		String stockInDate; // 제품 입고 날짜

		System.out.println("입고 제품 정보 입력");
		System.out.print("제품 이름>");
		productName = scan.nextLine();
		System.out.print("제품 입고 수량>");
		quantity = Integer.parseInt(scan.nextLine());
		System.out.print("제품 입고 가격>");
		price = Integer.parseInt(scan.nextLine());
		System.out.print("제품 입고 날짜 입력>");
		stockInDate = scan.nextLine();

		pvo.setProductName(productName);
		pvo.setQuantity(quantity);
		pvo.setPrice(price);
		pvo.setStockInDate(stockInDate);

		pd.insert(pvo);

		System.out.println();
		list();
		System.out.println();
	}

	// 제품 수정 관리
	public void update() throws Exception {
		Scanner scan = new Scanner(System.in);

		ProductDAO pd = new ProductDAO();
		ProductVO pvo = new ProductVO();

		int stokoNum;
		String productName; // 제품 이름
		int quantity; // 제품 입고 수량
		int price; // 제품 입고 가격
		String namestockInDate; // 제품 입고 날짜

		System.out.println("제품 전체 리스트");
		list();
		System.out.println();

		System.out.println("수정할 제품번호 입력");
		System.out.print("제품 번호>");
		stokoNum = Integer.parseInt(scan.nextLine());

		System.out.println();
		System.out.println("입고 제품 정보 입력");
		System.out.print("제품 이름>");
		productName = scan.nextLine();
		System.out.print("제품 입고 수량>");
		quantity = Integer.parseInt(scan.nextLine());
		System.out.print("제품 입고 가격>");
		price = Integer.parseInt(scan.nextLine());
		System.out.print("제품 입고 날짜 입력>");
		namestockInDate = scan.nextLine();

		pvo.setProductName(productName);
		pvo.setQuantity(quantity);
		pvo.setPrice(price);
		pvo.setStockInDate(namestockInDate);
		pvo.setStokoNum(stokoNum);
		int count = pd.update(pvo);

		if (count == 0) {
			System.out.println("제품정보 수정 오류발생 조치바람");
			return;
		} else {
			System.out.println("제품 정보 수정완료");
		}

		System.out.println();
		list();
		System.out.println();
	}

	// 제품 삭제 관리(Product에서 삭제)
	public void delete() throws Exception {
		Scanner scan = new Scanner(System.in);

		ProductDAO pd = new ProductDAO();
		ProductVO pvo = new ProductVO();

		int stokoNum; // 일련번호
		list();
		System.out.println();

		System.out.println("삭제할 제품 번호 입력");
		System.out.print("제품 번호>");
		stokoNum = Integer.parseInt(scan.nextLine());
		pvo.setStokoNum(stokoNum);
		int count = pd.deleteByNum(pvo);
		if (count == 0) {
			System.out.printf("%s 삭제 문제 발생 조치바람\n", stokoNum);
		} else {
			System.out.printf("%s 제품 삭제 성공 ^_^ \n", stokoNum);
		}
		System.out.println();
		list();
		System.out.println();
	}

	// 제품 구매(Product에서 차감)
	public void cusDelete() throws Exception {
		Scanner scan = new Scanner(System.in);
		boolean stopFlag = false;

		shoppingCart();
		while (!stopFlag) {
			int no = Integer.parseInt(scan.nextLine());
			switch (no) {
			case 1:
				// 구매 반복 시작
				while (true) {
					ProductDAO pd = new ProductDAO();
					ArrayList<ProductVO> productList = pd.selectAll();
					ProductVO pvo = new ProductVO();
					ProductVO findData = null;

					int stokoNum;
					int delQuantity;

					list();
					System.out.println();

					System.out.println("구매할 제품 입력");
					System.out.print("구매할 제품 번호>");
					stokoNum = Integer.parseInt(scan.nextLine());

					for (ProductVO data : productList) {
						if (data.getStokoNum() == stokoNum) {
							findData = data;
						}
					}

					System.out.print("구매할 제품 수량>");
					delQuantity = Integer.parseInt(scan.nextLine());

					// 재고 차감
					findData.setQuantity(findData.getQuantity() - delQuantity);

					int result = pd.pdDeleteSQL(findData);
					if (result > 0) {
						System.out.println("수량 차감 성공: " + findData.toString());
					} else {
						System.out.println("수량 차감 실패 (재고 부족 또는 조건 불충족)");
					}

					System.out.println();
					list();
					System.out.println();

					// 구매 계속 여부 확인
					System.out.print("계속 구매하시겠습니까? (y/n): ");
					String answer = scan.nextLine();

					if (!answer.equals("y")) {
						break; // 'n' 또는 다른 문자 → 구매 반복 종료 → 메인 메뉴로
					} else {
						break;
					}
				}
				break;
			case 2:
				break;
			case 3:
				stopFlag = true;
				break;
			}
		}

	}

	private void shoppingCart() {
		System.out.println("════SHOPPING CART════");
		System.out.println("1. 장바구니에 담기");
		System.out.println("2. 장바구니 수정");
		System.out.println("3. 종료");
		System.out.println("══════════════════════");
		System.out.print("▶ 메뉴 번호를 선택하세요: ");
	}
}
