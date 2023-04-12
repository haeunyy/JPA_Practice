package com.greedy.springjpa.menu.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.springjpa.menu.dto.CategoryDTO;
import com.greedy.springjpa.menu.dto.MenuDTO;
import com.greedy.springjpa.menu.entity.Category;
import com.greedy.springjpa.menu.entity.Menu;
import com.greedy.springjpa.menu.repository.MenuRepository;

@Service
public class MenuService {
	
	@PersistenceContext
	private EntityManager entityManager;
	private ModelMapper modelMapper;
	private MenuRepository menuRepository;
	
	public MenuService(MenuRepository menuRepository, ModelMapper modelMapper) {
		this.menuRepository = menuRepository;
		this.modelMapper = modelMapper;
	}

	/* 메뉴 조회 */
	public List<MenuDTO> menuSearch(){
		
		List<Menu> menuList = menuRepository.menuSearch(entityManager);
		return menuList.stream().map(menu -> modelMapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
	}
	
	/* 메뉴 등록 */
	@Transactional
	public void registMenu(MenuDTO menu) {
		
		menuRepository.registMenu(entityManager, modelMapper.map(menu, Menu.class));
	}
	
	/* 카테고리 전체 조회 */
	public List<CategoryDTO> findCategoryList(){
		
		List<Category> cateList = menuRepository.findCategoryList(entityManager);
		
		return cateList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
	}
	
	/***** 메뉴 수정 *****/
	/* 수정할 메뉴 선택 상세페이지 */
	public MenuDTO modifyMenuDetail(int menuCode) {

		return modelMapper.map(menuRepository.modifyMenuDetail(entityManager, menuCode), MenuDTO.class); 
	}
	
	/* 메뉴 수정 */
	@Transactional
	public void modifyMenu(MenuDTO menu) {
		
		menuRepository.modifyMenu(entityManager, modelMapper.map(menu, Menu.class));
	}

	/***** 메뉴 삭제 *****/
	@Transactional
	public void deleteMenu(MenuDTO menu) {
		
		menuRepository.deleteMenu(entityManager, modelMapper.map(menu, Menu.class));
	}
	

}
