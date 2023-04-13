package com.greedy.springjpa.menu.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.greedy.springjpa.menu.entity.Category;
import com.greedy.springjpa.menu.entity.Menu;

@Repository
public class MenuRepository {

	/* 메뉴 조회 */
	public List<Menu> menuSearch(EntityManager em){
		
		String jpql = "SELECT m FROM Menu as m ORDER BY m.menuCode ASC";
		
		return em.createQuery(jpql, Menu.class).getResultList();
	}
	
	
	/* 메뉴 등록 */
	public void registMenu(EntityManager em, Menu menu) {
		
		em.persist(menu);
	}
	
	/* 카테고리 전체 조회 */
	public List<Category> findCategoryList(EntityManager em){
		
		String jpql = "SELECT c FROM Category as c ORDER BY c.categoryCode ASC";
		
		return em.createQuery(jpql, Category.class).getResultList();
	}
	
	
	/* 메뉴 수정 */
	public void modifyMenu(EntityManager em, Menu menu) {
		
		Menu selectMenu = em.find(Menu.class, menu.getMenuCode());
		
		selectMenu.setMenuName(menu.getMenuName());
		selectMenu.setOrderableStatus(menu.getOrderableStatus());
	}

	/* 메뉴 수정 상세페이지  */
	public Menu modifyMenuDetail(EntityManager em, int menuCode) {

		return em.find(Menu.class, menuCode);
	}
	
	
	/* 메뉴 삭제 */
	public void deleteMenu(EntityManager em, Menu menu) {
		
		Menu deletMenu = em.find(Menu.class, menu.getMenuCode());
		em.remove(deletMenu);
	}
	
	
	
	
}
