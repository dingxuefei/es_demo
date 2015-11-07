package com.es_demo.factory;
import java.util.ArrayList;
import java.util.List;

import com.es_demo.model.Message;
import com.es_demo.service.MessageService;
import com.es_demo.util.SpringUtils;
import com.google.gson.Gson;


/**
 * 数据工厂
 * @author dingxuefei
 */
public class DataFactory {
    
    public static DataFactory dataFactory = new DataFactory();
    
    private static Gson gson = new Gson();
    
    private DataFactory(){
        
    }
    
    public DataFactory getInstance(){
        return dataFactory;
    }
    
    public static List<String> getInitJsonData(){
    	MessageService messageService = SpringUtils.getBean(MessageService.class);
        List<String> list = new ArrayList<String>();
        List<Message> messages = messageService.findMessages();
        for(Message message : messages){
        	list.add(gson.toJson(message));
        }
        return list;
    }
}