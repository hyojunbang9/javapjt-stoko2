package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ProductVO;

public class ProductDAO {
	private String selectSQL = "SELECT * FROM PRODUCT order by STOKO_NUM ASC";
	private String selectByStokoNumSQL = "SELECT * FROM PRODUCT where STOKO_NUM = ?";
	private String selectByProductNameSQL = "SELECT *  FROM PRODUCT where PRODUCT_NAME = ?";
	private String insertSQL = "INSERT INTO PRODUCT  VALUES (PRODUCT_SEQ.nextval, ?, ?, ?, TO_DATE(?, 'YYYY/MM/DD'))";
	private String updateSQL = "update PRODUCT set PRODUCT_NAME = ?, QUANTITY = ?, PRICE = ?,  STOCK_IN_DATE = TO_DATE(?, 'YYYY/MM/DD') where STOKO_NUM = ?";
	private String deleteSQL = "DELETE FROM PRODUCT WHERE STOKO_NUM = ?";
	private String pdDeleteSQL = "UPDATE PRODUCT SET QUANTITY = QUANTITY - ? WHERE STOKO_NUM = ? AND QUANTITY >= ?";

	// 제품 전체 리스트
	public ArrayList<ProductVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int stokoNum = rs.getInt("STOKO_NUM");
				String productName = rs.getString("PRODUCT_NAME");
				int quantity = rs.getInt("QUANTITY");
				int price = rs.getInt("PRICE");
				String stockInDate = rs.getString("STOCK_IN_DATE");
				ProductVO productVO = new ProductVO(stokoNum, productName, quantity, price, stockInDate);
				productList.add(productVO);
			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return productList;
	}

	// 제품 검색(제품 번호)
	public ProductVO selectByStokoNum(int selectNum) {
		Connection con = null; // 자바랑 디비 연결하는 놈
		PreparedStatement pstmt = null; // 자바에서 디비 정보 가져오는 놈(쿼리문 사용)
		ResultSet rs = null; // 가져온 정보 담는 놈
		ProductVO _productVO = null;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectByStokoNumSQL);
			pstmt.setInt(1, selectNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int stokoNum = rs.getInt("STOKO_NUM");
				String productName = rs.getString("PRODUCT_NAME");
				int quantity = rs.getInt("QUANTITY");
				int price = rs.getInt("PRICE");
				String stockInDate = rs.getString("STOCK_IN_DATE");
				_productVO = new ProductVO(stokoNum, productName, quantity, price, stockInDate);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _productVO;
	}

	/*
	 * // 제품 검색(이름) public ProductVO selectByProductName(ProductVO productVO) {
	 * Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * ProductVO _productVO = null; try { con = DBUtil.getConnection(); if (con ==
	 * null) { System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다."); return null; }
	 * pstmt = con.prepareStatement(selectByProductNameSQL); pstmt.setString(1,
	 * productVO.getProductName()); rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) { int stokoNum = rs.getInt("STOKONUM"); String productName =
	 * rs.getString("PRODUCT_NAME"); int quantity = rs.getInt("QUANTITY"); int price
	 * = rs.getInt("PRICE"); String stockInDate = rs.getString("STOCK_IN_DATE");
	 * _productVO = new ProductVO(stokoNum, productName, quantity, price,
	 * stockInDate); } } catch (SQLException e) {
	 * System.out.println("createStatement 오류발생"); } finally { DBUtil.dbClose(con,
	 * pstmt, rs); } return _productVO; }
	 */

	// 제품 입고(추가)
	public int insert(ProductVO productVO) {
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
			pstmt.setString(1, productVO.getProductName());
			pstmt.setInt(2, productVO.getQuantity());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setString(4, productVO.getStockInDate());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 제품 수정
	public int update(ProductVO productVO) {
//		private String updateSQL = "update PRODUCT set PRODUCT_NAME = ?, QUANTITY = ?, PRICE = ?,  STOCK_IN_DATE = TO_DATE(?, 'YYYY/MM/DD') where STOKO_NUM = ?";
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
			pstmt.setString(1, productVO.getProductName());
			pstmt.setInt(2, productVO.getQuantity());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setString(4, productVO.getStockInDate());
			pstmt.setInt(5, productVO.getStokoNum());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생" + e.getMessage());
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 제품 삭제(Product에서 삭제)
	public int deleteByNum(ProductVO productVO) {
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
			pstmt.setInt(1, productVO.getStokoNum());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 제품 구매(Product에서 차감)
	public int pdDeleteSQL(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(pdDeleteSQL);
			pstmt.setInt(1, productVO.getQuantity());
			pstmt.setInt(2, productVO.getStokoNum());
			pstmt.setInt(3, productVO.getQuantity());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생" + e.getMessage());
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

}
