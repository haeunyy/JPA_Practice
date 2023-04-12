package com.greedy.springjpa.menu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greedy.springjpa.menu.dto.CategoryDTO;
import com.greedy.springjpa.menu.dto.MenuDTO;
import com.greedy.springjpa.menu.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	private MenuService menuService;
	
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	/***** 메뉴 조회 *****/
	
	/* 메뉴 조회 */
	@GetMapping("/list")
	public String menuSearch(Model model) {
		
		List<MenuDTO> menuList = menuService.menuSearch();
		model.addAttribute("menuList", menuList);
		System.out.println(menuList);
		
		return "menu/list";
	}
	
	/***** 메뉴 등록 *****/
	
	/* 메뉴 등록 페이지 */
	@GetMapping("/regist")
	public void newMenu() {}
	
	/* 메뉴 등록페이지 - 카테고리 조회, 비동기 */
	@GetMapping(value="/category", produces= "application/json; charset=UTF-8")
	@ResponseBody
	public List<CategoryDTO> findCategoryList(Model model) {
		
		return menuService.findCategoryList();
	}
	
	@PostMapping("/regist")
	public String registMenu(@ModelAttribute MenuDTO menu) {
		
		menuService.registMenu(menu);
		
		return "redirect:/menu/list";
	}
	
	
	/***** 메뉴 수정 *****/
	
	/* 수정하고자 하는 메뉴리스트 선택 페이지 */
	@GetMapping("/modifyList")
	public String modifyListPage(Model model) {
		
		List<MenuDTO> menuList = menuService.menuSearch();
		model.addAttribute("menuList", menuList);
		System.out.println(menuList);
		
		return "menu/modifyList";
	}
	
	/* 선택한 메뉴 수정 상세페이지 */
	@GetMapping("/modify/{menuCode}")
	public String modifyPage(@PathVariable int menuCode, Model model) {
		
		MenuDTO menu = menuService.modifyMenuDetail(menuCode);
		model.addAttribute("menu", menu);
		
		return "menu/modify";
	}
	
	/* 메뉴 수정 */
	@PostMapping("/modify")
	public String modifyOrderableStatus(@ModelAttribute MenuDTO menu) {
		
		menuService.modifyMenu(menu);
		
		return "redirect:/menu/list";
	}
	
	
	/***** 메뉴 삭제 *****/
	
	/* 메뉴 삭제 페이지 */
	@GetMapping("/delete")
	public void deletePage() {}
	
	/* 메뉴 삭제 */
	@PostMapping("/delete")
	public String menuDelete(@ModelAttribute int menuCode) {
		
		menuService.deleteMenu(menuCode);
		System.out.println(menuCode);
		return "redirect:/menu/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
