package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	/**
	 * @Title: getItemById
	 * @Description: 根据商品ID获取该商品信息
	 * @param itemId
	 * @see com.taotao.service.ItemService#getItemById(long) 
	 */
	@Override
	public TbItem getItemById(long itemId) {
		// TbItem item = itemMapper.selectByPrimaryKey(itemId);
		// 添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	/**
	 * @Title: getItemList
	 * @Description: 获取全部商品列表并分页
	 * @param page
	 * @param rows
	 * @see com.taotao.service.ItemService#getItemList(int, int) 
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		// 查询商品列表
		TbItemExample example = new TbItemExample();
		// 进行分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		// 创建一个返回结果对象
		EUDataGridResult euDataGridResult = new EUDataGridResult();
		euDataGridResult.setRows(list);
		// 取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		euDataGridResult.setTotal(pageInfo.getTotal());
		return euDataGridResult;
	}

	/**
	 * @Title: createItem
	 * @Description:添加一个商品信息
	 * @param item
	 * @see com.taotao.service.ItemService#createItem(com.taotao.pojo.TbItem) 
	 */
	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemPram) throws Exception {
		// item补全
		// 生成商品ID
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 商品信息插入到数据库
		itemMapper.insert(item);
		// 添加商品描述信息
		TaotaoResult result = insertItemDesc(itemId, desc);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		// 添加规格参数
		result = insertItemParamItem(itemId, itemPram);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		return TaotaoResult.ok();
	}

	/**
	 * @author: yuanj
	 * @date: 2018年6月4日 下午9:22:25
	 * @Title: insertItemDesc
	 * @Description: 添加商品描述
	 * @param itemId
	 * @param desc
	 * @return TaotaoResult
	 * @throws
	 */
	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		tbItemDescMapper.insert(tbItemDesc);
		return TaotaoResult.ok();
	}

	/**
	 * @author: yuanj
	 * @date: 2018年6月6日 下午7:55:36
	 * @Title: insertItemParamItem
	 * @Description: 添加规格参数
	 * @param itemId
	 * @param itemParam
	 * @return TaotaoResult
	 * @throws
	 */
	private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
		// 创建一个pojo
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setParamData(itemParam);
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setUpdated(new Date());
		// 向表中插入数据
		tbItemParamItemMapper.insert(tbItemParamItem);
		return TaotaoResult.ok();
	}
}
