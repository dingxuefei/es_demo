package com.es_demo.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.es_demo.model.Message;
import com.es_demo.service.MessageService;
import com.es_demo.web.controller.handler.ElasticSearchHandler;
import com.es_demo.web.listener.InitDataListener;
import com.google.gson.Gson;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Gson gson = new Gson();
	
	/**
	 * 留言列表
	 * @param model
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/")
	public String job(Model model, HttpServletRequest request){
		
		String keyword = request.getParameter("keyword");
		model.addAttribute("keyword", keyword);
		
		if(keyword == null){
			List<Message> messages = messageService.findMessages();
			model.addAttribute("messages", messages);
		}else{
			
			//得到客户端对象
			Client client = InitDataListener.getClient();
			
			//查询条件
	        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, "title", "content");
			
	        List<Message> result = ElasticSearchHandler.searcher(queryBuilder, InitDataListener.getIndexName(), InitDataListener.getIndexType(), client);
	        for(int i=0; i<result.size(); i++){
	        	Message message = result.get(i);
	        	logger.debug(gson.toJson(message));
	        }
	        model.addAttribute("messages", result);
	        
	        //关闭对象
	        client.close();
		}
		return "message/index";
	}
	
	
	/**
	 * 添加信息页面
	 * @param redirectAttributes
	 * @param message
	 * @return
	 */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPage(RedirectAttributes redirectAttributes, Message message){
		return "message/add";
	}
	
	
	
	/**
	 * 保存添加的信息
	 * @param redirectAttributes
	 * @param message
	 * @return
	 */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
	public String delete(RedirectAttributes redirectAttributes, Message message){
    	message.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		Message msg = messageService.insert(message);
		if(msg != null){
			redirectAttributes.addFlashAttribute("msg", "添加留言成功");
			InitDataListener.createIndexResponse(gson.toJson(msg));
		}else{
			redirectAttributes.addFlashAttribute("msg", "添加留言失败");
		}
		return "redirect:/";
	}
}
