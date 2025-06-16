package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.ProductVO;
import model.SalesVO;

public class SalesManager {
	// 영수증 목록
	public void list() throws Exception {
		SalesDAO sd = new SalesDAO();
		System.out.println("영수증 전체 리스트");
		ArrayList<SalesVO> salesList = sd.selectAll();
		if (salesList.size() == 0) {
			System.out.println("영수증 전체 리스트 내용이 없습니다.");
			return;
		} else if (salesList == null) {
			System.out.println("영수증 전체 리스트 에러발생");
			return;
		}
		for (SalesVO data : salesList) {
			System.out.println(data.toString());
		}
	}

	// 영수증 등록
	public void register() throws Exception {
		Scanner scan = new Scanner(System.in);

		SalesDAO sd = new SalesDAO();
		SalesVO svo = new SalesVO();

		String productNameCus; // 영수증 속 제품 이름
		int quantityCus; // 영수증 속 제품 수량
		int totalPriceCus; // 영수증 속 제품 입고 가격
		String stockInDateCus; // 영수증 속 제품 입고 날짜

		System.out.println("입고 영수증 정보 입력");
		System.out.print("구매 제품 이름>");
		productNameCus = scan.nextLine();
		System.out.print("구매 제품 수량>");
		quantityCus = Integer.parseInt(scan.nextLine());
		System.out.print("구매 제품 가격>");
		totalPriceCus = Integer.parseInt(scan.nextLine());
		System.out.print("구매 제품 입고날짜>");
		stockInDateCus = scan.nextLine();

		svo.setProductNameCus(productNameCus);
		svo.setQuantityCus(quantityCus);
		svo.setTotalPriceCus(totalPriceCus);
		svo.setStockInDateCus(stockInDateCus);

		sd.insert(svo);

		System.out.println();
		list();
		System.out.println();
	}

	public void selectCus() throws Exception {
		Scanner scan = new Scanner(System.in);

		SalesDAO sd = new SalesDAO();
		SalesVO svo = new SalesVO();
		int receiptNo = -1;

		System.out.print("검색할 제품 번호>");
		receiptNo = Integer.parseInt(scan.nextLine());

		ArrayList<SalesVO> salesList = sd.selectAll();

		for (SalesVO data : salesList) {
			if (receiptNo == data.getReceiptNo()) {
				System.out.println(data.toString());
			} else {
				continue;
			}
		}

		System.out.println("------------------");
		SalesVO findData = sd.selectByReceipNum(receiptNo);
		System.out.println(findData);
		if (salesList.size() == 0) {
			System.out.println("영수증 리스트 내용이 없습니다.");
			return;
		} else if (salesList == null) {
			System.out.println("영수증 리스트 에러발생");
			return;
		}
	}

	/*
	 * // 영수증 수정 관리 public void update() throws Exception { Scanner scan = new
	 * Scanner(System.in);
	 * 
	 * SalesDAO sd = new SalesDAO(); SalesVO svo = new SalesVO();
	 * 
	 * int receiptNo; String productNameCus; // 영수증 속 제품 이름 int quantityCus; // 영수증
	 * 속 제품 수량 int totalPriceCus; // 영수증 속 제품 입고 가격 String stockInDateCus; // 영수증 속
	 * 제품 입고 날짜
	 * 
	 * System.out.println("영수증 전체 리스트"); list(); System.out.println();
	 * 
	 * System.out.println("수정할 영수증번호 입력"); System.out.print("영수증 번호>"); receiptNo =
	 * Integer.parseInt(scan.nextLine());
	 * 
	 * System.out.println(); System.out.println("입고 영수증 정보 입력");
	 * System.out.print("영수증 이름>"); productNameCus = scan.nextLine();
	 * System.out.print("영수증 입고 수량>"); quantityCus =
	 * Integer.parseInt(scan.nextLine()); System.out.print("영수증 입고 가격>");
	 * totalPriceCus = Integer.parseInt(scan.nextLine());
	 * System.out.print("영수증 이름>"); stockInDateCus = scan.nextLine();
	 * 
	 * svo.setProductNameCus(productNameCus); svo.setQuantityCus(quantityCus);
	 * svo.setTotalPriceCus(totalPriceCus); svo.setStockInDateCus(stockInDateCus);
	 * 
	 * int count = sd.update(svo);
	 * 
	 * if (count == 0) { System.out.println("영수증정보 수정 오류발생 조치바람"); return; } else {
	 * System.out.println("영수증 정보 수정완료"); }
	 * 
	 * System.out.println(); list(); System.out.println(); }
	 */

	// 환불
	public void delete() throws Exception {
		Scanner scan = new Scanner(System.in);

		SalesDAO sd = new SalesDAO();
		SalesVO svo = new SalesVO();

		int receiptNo; // 영수증 번호
		list();
		System.out.println();

		System.out.println("삭제할 영수증 번호 입력");
		System.out.print("영수증 번호>");
		receiptNo = Integer.parseInt(scan.nextLine());
		svo.setReceiptNo(receiptNo);
		int count = sd.deleteByNo(svo);
		if (count == 0) {
			System.out.printf("%s 삭제 문제 발생 조치바람\n", receiptNo);
		} else {
			System.out.printf("%s 영수증 삭제 성공 ^_^ \n", receiptNo);
		}
		System.out.println();
		list();
		System.out.println();
	}

}
