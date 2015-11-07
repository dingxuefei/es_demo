package com.es_demo.web.controller.handler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.es_demo.model.Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 搜索处理器
 * @author dingxuefei
 *
 */
public class ElasticSearchHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ElasticSearchHandler.class);
    
	private static Gson gson = new Gson();
	
    /**
     * 执行搜索
     * @param queryBuilder
     * @param indexname  索引名称
     * @param type  索引类型
     * @return
     * @throws ParseException 
     */
    public static List<Message>  searcher(QueryBuilder queryBuilder, String indexname, String type, Client client){
        List<Message> list = new ArrayList<Message>();
        
        // 创建查询索引
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(indexname);
		
        // 设置查询索引类型,setTypes("productType1", "productType2","productType3");
        // 用来设定在多个类型中搜索
        searchRequestBuilder.setTypes(type);
        
        // 设置查询关键词
        searchRequestBuilder.setQuery(queryBuilder);
        
        // 设置是否按查询匹配度排序
        searchRequestBuilder.setExplain(true);
        
        // 设置高亮显示
        searchRequestBuilder.addHighlightedField("title").addHighlightedField("content");
        searchRequestBuilder.setHighlighterPreTags("<span style=\"color:red\">");
        searchRequestBuilder.setHighlighterPostTags("</span>");
        
        // 执行搜索,返回搜索响应信息
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		
		//SearchHits是SearchHit的复数形式，表示这个是一个列表 
		SearchHits hits = searchResponse.getHits();
		
        SearchHit[] searchHists = hits.getHits();
        if(searchHists.length > 0){
            for(SearchHit hit : searchHists){
            	
            	//将文档中的每一个对象转换json串值
            	String json = hit.getSourceAsString();
            	
            	//将json串值转换成对应的实体对象
            	Message message = gson.fromJson(json, new TypeToken<Message>() {}.getType());
            	
            	//获取对应的高亮域
                Map<String, HighlightField> result = hit.highlightFields();  
                
                //从设定的高亮域中取得指定域
                HighlightField titleField = result.get("title");  
                HighlightField contentField = result.get("content");  
                
                String title = ""; 
                String content = "";
                
                if(titleField != null){
                	//取得定义的高亮标签
                    Text[] titleTexts =  titleField.fragments();
                    
                    //增加自定义的高亮标签
                    for(Text text : titleTexts){    
                          title += text;  
                    }
                    //将追加了高亮标签的串值重新填充到对应的对象
                    message.setTitle(title);
                }
                if(contentField != null){
                	//取得定义的高亮标签
                    Text[] contentTexts =  contentField.fragments();
                    
                    //增加自定义的高亮标签
                    for(Text text : contentTexts){    
                    	content += text;  
                    }
                    //将追加了高亮标签的串值重新填充到对应的对象
                    message.setContent(content);
                }
                
                list.add(message);
            }
        }
        logger.debug("查询到记录数：" + hits.getTotalHits());
        return list;
    }
    
}