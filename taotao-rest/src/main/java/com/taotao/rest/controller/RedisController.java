package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.RedisService;

/**
 * @ClassName: RedisController
 * @Description: 缓存同步controller
 * @author: yuanj
 * @date: 2018年6月28日 下午9:04:25
 * @version v1.0.0
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {
	@Autowired
	private RedisService redisService;

	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	public TaotaoResult contentCacheSync(@PathVariable long contentCid) {
		TaotaoResult resutl = redisService.syncContent(contentCid);
		return resutl;
	}
}
