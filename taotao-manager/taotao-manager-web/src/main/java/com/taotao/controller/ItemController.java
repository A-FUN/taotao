package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	/**
	 * @author: yuanj
	 * @date: 2018年5月31日 下午9:50:06
	 * @Title: getItemById
	 * @Description: 根据商品id返回该商品信息
	 * @param itemId
	 * @return TbItem
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}

	/**
	 * @author: yuanj
	 * @date: 2018年5月31日 下午9:52:08
	 * @Title: getItemList
	 * @Description: 查询商品列表并分页
	 * @param page
	 * @param rows
	 * @return EUDataGridResult
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult eUDataGridResult = itemService.getItemList(page, rows);
		return eUDataGridResult;
	}

	/**
	 * @author: yuanj
	 * @date: 2018年6月3日 下午9:38:41
	 * @Title: createItem
	 * @Description: 增加一个商品信息
	 * @param item
	 * @return TaotaoResult
	 * @throws
	 */
	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	private TaotaoResult createItem(TbItem item, String desc, String itemParams) throws Exception {
		TaotaoResult result = itemService.createItem(item, desc, itemParams);
		return result;
	}
}
