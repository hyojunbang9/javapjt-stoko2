package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SalesVO;

public class SalesDAO {
	private String selectSQL = "SELECT * FROM SALES order by RECEIPT_NUM ASC";
	private String selectByReceipNumSQL = "SELECT *  FROM SALES where RECEIPT_NUM = ?";
	private String selectByProductNameCusSQL = "SELECT *  FROM SALES where PRODUCT_NAME_CUS = ?";
	private String insertSQL = "INSERT INTO SALES  VALUES (SALES_SEQ.NEXTVAL, ?, ?, ?, TO_DATE(?, 'YYYY/MM/DD'))";
	private String updateSQL = "update SALES set PRODUCT_NAME_CUS = ?, QUANTITY_CUS = ?, TOTAL_PRICE_CUS = ?,  STOCK_IN_DATE_CUS = ? where RECEIPT_NUM = ?";
	private String deleteSQL = "DELETE FROM SALES WHERE RECEIPT_NUM = ?";

	// 영수증 목록
	public ArrayList<SalesVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SalesVO> salesList = new ArrayList<SalesVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int receiptNo = rs.getInt("RECEIPT_NUM");
				String productNameCus = rs.getString("PRODUCT_NAME_CUS");
				int quantityCus = rs.getInt("QUANTITY_CUS");
				int totalPriceCus = rs.getInt("TOTAL_PRICE_CUS");
				String stockInDateCus = rs.getString("STOCK_IN_DATE_CUS");
				SalesVO salesVO = new SalesVO(receiptNo, productNameCus, quantityCus, totalPriceCus, stockInDateCus);
				salesList.add(salesVO);
			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return salesList;
	}

	// 영수증 검색
	public SalesVO selectByReceipNum(int selectNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SalesVO _salesVO = null;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectByReceipNumSQL);
			pstmt.setInt(1, selectNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int receiptNo = rs.getInt("RECEIPT_NUM");
				String productNameCus = rs.getString("PRODUCT_NAME_CUS");
				int quantityCus = rs.getInt("QUANTITY_CUS");
				int totalPriceCus = rs.getInt("TOTAL_PRICE_CUS");
				String stockInDateCus = rs.getString("STOCK_IN_DATE_CUS");
				_salesVO = new SalesVO(receiptNo, productNameCus, quantityCus, totalPriceCus, stockInDateCus);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _salesVO;
	}

	// 영수증 이름 검색
	public SalesVO selectByProductNameCus(SalesVO salesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SalesVO _salesVO = null;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectByProductNameCusSQL);
			pstmt.setString(1, salesVO.getProductNameCus());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int receiptNo = rs.getInt("RECEIPT_NUM");
				String productNameCus = rs.getString("PRODUCT_NAME_CUS");
				int quantityCus = rs.getInt("QUANTITY_CUS");
				int totalPriceCus = rs.getInt("TOTAL_PRICE_CUS");
				String stockInDateCus = rs.getString("STOCK_IN_DATE_CUS");
				_salesVO = new SalesVO(receiptNo, productNameCus, quantityCus, totalPriceCus, stockInDateCus);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _salesVO;
	}

	// 영수증 입력
	public int insert(SalesVO salesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, salesVO.getProductNameCus());
			pstmt.setInt(2, salesVO.getQuantityCus());
			pstmt.setInt(3, salesVO.getTotalPriceCus());
			pstmt.setString(4, salesVO.getStockInDateCus());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 영수증 수정
	public int update(SalesVO salesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, salesVO.getProductNameCus());
			pstmt.setInt(2, salesVO.getQuantityCus());
			pstmt.setInt(3, salesVO.getTotalPriceCus());
			pstmt.setString(4, salesVO.getStockInDateCus());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 영수증 삭제
	public int deleteByNo(SalesVO salesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1, salesVO.getReceiptNo());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

}
