package com.es_demo.web.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.es_demo.factory.DataFactory;

public class InitDataListener implements ServletContextListener {

	private static List<String> jsondatas = new ArrayList<String>();
	
	private static final Logger logger = LoggerFactory.getLogger(InitDataListener.class);
	
	private static String INDEXNAME = "";  //索引名称（值一定要小写）
	private static String INDEXTYPE = "";  //索引类型（值一定要小写）
	private static String IP = "";
	private static Integer PORT = 0;
	
	
	static{
		/**
         * 获取配置文件
         */
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		if (bundle == null) {
			logger.error("找不到配置文件：jdbc.properties");
        }
		
		INDEXNAME = bundle.getString("es.index_name");
		INDEXTYPE = bundle.getString("es.index_type");
		IP = bundle.getString("es.ip");
		PORT = Integer.valueOf(bundle.getString("es.port"));
	}
	
	/**
	 * 获取es客户端对象
	 * @return
	 */
	@SuppressWarnings("resource")
	public static Client getClient(){
		Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(IP, PORT));
		return client;
	}
    
	
	@Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("Initializing context...");

        ServletContext context = sce.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);

        logger.debug("================ bean loaded list begin ==================");
        for (String beanName : ctx.getBeanDefinitionNames()) {
            logger.debug(beanName);
        }
        logger.debug("================ bean loaded list end ==================");
        
        initData();
        logger.debug("================ 初始化数据成功，加载数据"+jsondatas.size()+"条 ==================");
        
        batchDelIndex();
        createIndexResponse();
        logger.debug("================ 清空索引并且重新创建索引成功 ==================");
        
    }

	
	/**
	 * 批量清空索引
	 */
	public static void batchDelIndex(){
		ClusterStateResponse response = getClient().admin().cluster().prepareState().execute().actionGet();
		
		//获取所有索引
		String[] indexs = response.getState().getMetaData().getConcreteAllIndices(); 
		for(String index : indexs) {  
			DeleteIndexResponse deleteIndexResponse = getClient().admin().indices().prepareDelete(index).execute().actionGet(); 
			logger.debug("删除索引："+index+" Header："+deleteIndexResponse.getHeaders());
		}
	}
	

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
	/**
	 * 初始化数据
	 */
	public static void initData(){
		jsondatas = DataFactory.getInitJsonData();
	}

	

	/**
     * 系统初始化建立索引（批量）,索引建立好之后,会在..\data\elasticsearch\nodes\0创建索引文件
     * @param indexname  为索引库名，一个es集群中可以有多个索引库。 名称必须为小写
     * @param type  Type为索引类型，是用来区分同索引库下不同类型的数据的，一个索引库下可以有多个索引类型。
     * @param jsondata   json格式的数据集合
     * 
     * @return
     */
	public static void createIndexResponse(){
		//创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
        IndexRequestBuilder requestBuilder = getClient().prepareIndex(INDEXNAME, INDEXTYPE).setRefresh(true);
        for(int i=0; i<jsondatas.size(); i++){
            requestBuilder.setId((i+1)+"").setSource(jsondatas.get(i)).execute().actionGet();
        }
        
        //关闭客户端
        //closeElasticSearch();
	}

	
	/**
     * 删除索引（单个）
     * @param id  索引对象的ID
     */
    public static DeleteResponse deleteResponse(String id){
    	DeleteResponse response = getClient().prepareDelete(INDEXNAME, INDEXTYPE, id).execute().actionGet();  
    	
    	logger.debug("删除的索引ID："+response.getId());
    	logger.debug("删除的索信息："+response.getHeaders());
    	
    	//关闭客户端
        //closeElasticSearch();
    	return response;
    }
    
    
    
    /**
     * 创建索引（单个）
     * @param jsondata
     * @return
     */
    public static IndexResponse createIndexResponse(String jsondata){
        IndexResponse response = getClient().prepareIndex(INDEXNAME, INDEXTYPE).setSource(jsondata).execute().actionGet();
        
        logger.debug("添加的索引ID："+response.getId());
    	logger.debug("添加索的信息："+response.getHeaders());
    	
        //关闭客户端
        //closeElasticSearch();
        return response;
    }
	
    
    /**
     * 关闭ElasticSearch
     */
    public static void closeElasticSearch(){
    	getClient().close();
    }
    
    
	/**
	 * 返回json格式数据集合
	 * @return
	 */
	public static List<String> getJsondatas() {
		return jsondatas;
	}
	
	
	/**
	 * 返回索引名称
	 * @return
	 */
	public static String getIndexName(){
		return INDEXNAME;
	}
	
	
	/**
	 * 返回索引类型
	 * @return
	 */
	public static String getIndexType(){
		return INDEXTYPE;
	}
}
