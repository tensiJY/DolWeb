package com.sundol.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import 	org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.VO.ShopManagerVO;

public class ShopManagerDAO extends SqlSessionDaoSupport {
	@Autowired
	public SqlSessionTemplate	sqlSession;
	
	public ArrayList getLCategory() {
		return (ArrayList) sqlSession.selectList("shopManager.selectLCate");
	}
	
	public void insertLCategory(String lname) {
		sqlSession.insert("shopManager.LcateInsert", lname);
	}
	
	public void insertMCategory(HashMap map) {
		sqlSession.insert("shopManager.McateInsert", map);
	}
	
	public void insertSCategory(HashMap map) {
		sqlSession.insert("shopManager.ScateInsert", map);
	}
	/*
	 * 	중 카테고리 검색 질의 실행함수
	 */
	public ArrayList	selectMCategory(String lcode) {
		return (ArrayList) sqlSession.selectList("shopManager.selectMCate", lcode);
	}
	
	/*
	 * 	소카테고리 검색 질의 실행함수
	 */
	public ArrayList	selectSCategory(String mcode) {
		return (ArrayList) sqlSession.selectList("shopManager.selectSCate", mcode);
	}
	
	/*
	 * 	상품 등록 질의 실행 함수
	 */
	public void insertGoods(ShopManagerVO vo) {
		sqlSession.insert("shopManager.insertGoods", vo);
	}
	
	/*
	 * 	상품 목록 질의 실행 함수
	 */
	public ArrayList getGoodsList(String code) {
		return (ArrayList) sqlSession.selectList("shopManager.goodsList", code);
	}
}







