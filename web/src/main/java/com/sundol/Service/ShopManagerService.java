package com.sundol.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.DAO.ShopManagerDAO;
import com.sundol.VO.ShopManagerVO;

public class ShopManagerService {
	@Autowired
	private	ShopManagerDAO		smDAO;
	public ArrayList getLCategory() {
		return smDAO.getLCategory();
	}
	
	public void insertLCategory(String lname) {
		smDAO.insertLCategory(lname);
	}
	
	public void insertMCategory(ShopManagerVO vo) {
		HashMap map = new HashMap();
		map.put("lcate", vo.getLcode());
		map.put("mname", vo.getMname());
		
		smDAO.insertMCategory(map);
	}
	
	public void insertSCategory(ShopManagerVO vo) {
		HashMap map = new HashMap();
		map.put("mcate", vo.getMcode());
		map.put("sname", vo.getSname());
		
		smDAO.insertSCategory(map);
	}

	/*
	 * 	중카테고리 검색 처리 함수
	 */
	public ArrayList selectMCate(String lcode) {
		return smDAO.selectMCategory(lcode);
	}
	/*
	 * 	소카테고리 검색 처리 함수
	 */
	public ArrayList selectSCate(String mcode) {
		return smDAO.selectSCategory(mcode);
	}
	
	/*
	 * 	상품 등록 처리 함수
	 */
	public void insertGoods(ShopManagerVO vo) {
		smDAO.insertGoods(vo);
	}
	
	/*
	 * 	상품 목록보기 처리 함수
	 */
	public ArrayList	getGoodsList(String code) {
		ArrayList	list = smDAO.getGoodsList(code);
		return list;
	}
}










