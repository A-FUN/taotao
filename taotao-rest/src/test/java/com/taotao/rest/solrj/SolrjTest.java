package com.taotao.rest.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.w3c.dom.css.DocumentCSS;

public class SolrjTest {
	/**
	 * @author: yuanj
	 * @date: 2018年7月1日 下午5:49:03
	 * @Title: addDocument
	 * @Description: 向索引库添加一条数据
	 * @throws Exception
	 * @return void
	 * @throws
	 */
	public void addDocument() throws Exception {
		// 创建一个连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.1.112:8080/solr");
		// 创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "测试商品2");
		document.addField("item_price", "54321");
		// 把文档导入索引库
		solrServer.add(document);
		// 提交
		solrServer.commit();
	}

	/**
	 * @author: yuanj
	 * @date: 2018年7月1日 下午5:49:52
	 * @Title: deleteDocument
	 * @Description: 删除索引库数据
	 * @throws Exception
	 * @return void
	 * @throws
	 */
	public void deleteDocument() throws Exception {
		// 创建一连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.1.111:8080/solr");
		// solrServer.deleteById("test001");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}

	/**
	 * @author: yuanj
	 * @date: 2018年7月2日 下午8:57:08
	 * @Title: queryDocument
	 * @Description: 查询测试
	 * @throws SolrServerException
	 * @return void
	 * @throws
	 */
	public void queryDocument() throws SolrServerException {
		// 创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.43.53:8080/solr");
		// 創建查询对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery("*:*");
		// 设置查询开始行
		query.setStart(20);
		query.setRows(50);
		// 执行查询
		QueryResponse response = solrServer.query(query);
		// 取查询结果
		SolrDocumentList documentList = response.getResults();
		System.out.println("共查询出记录数：" + documentList.getNumFound());
		for (SolrDocument document : documentList) {
			System.out.println(document.get("id"));
			System.out.println(document.get("item_title"));
			System.out.println(document.get("item_price"));
			System.out.println(document.get("item_image"));
		}
	}
}
