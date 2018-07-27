package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PageController
 * @Description: 页面跳转controller
 * @author: yuanj
 * @date: 2018年5月30日 下午10:19:40
 * @version v1.0.0
 */
@Controller
public class PageController {
	/**
	 * @author: yuanj
	 * @date: 2018年5月30日 下午10:20:12
	 * @Title: showIndex
	 * @Description: 打开首页
	 * @return String
	 * @throws
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	/**
	 * @author: yuanj
	 * @date: 2018年5月30日 下午10:22:38
	 * @Title: showIndex
	 * @Description: 展示其它页面
	 * @param page
	 * @return String
	 * @throws
	 */
	@RequestMapping("/{page}")
	public String showIndex(@PathVariable String page) {
		return page;
	}
}
