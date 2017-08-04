package com.ustrip.web.message;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ustrip.common.Page;
import com.ustrip.common.Search;
import com.ustrip.service.domain.Message;
import com.ustrip.service.domain.User;
import com.ustrip.service.message.MessageService;

@Controller
@RequestMapping("/message/*")
public class MessageController {
	
	///Field
	@Autowired
	@Qualifier("messageServiceImpl")
	private MessageService messageService;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	public MessageController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="sendMsg", method=RequestMethod.GET )
	public String sendMsg(@RequestParam(value="msgNo", required=false) String msgNo, 
			@RequestParam(value="receiver", required=false) String receiver, Model model) throws Exception{
	
		System.out.println("/message/sendMsg : GET");
		
		if(  msgNo != null ) {
			Message message = messageService.getMsg(Integer.parseInt(msgNo));
			(message.getMsgContent()).replaceAll("\n", "<BR>");
			model.addAttribute("message", message	);
		}
		if( receiver != null ) {
			model.addAttribute("receiver", receiver);
		}
		
		return "forward:/view/message/sendMsg.jsp";
	}
	
	@RequestMapping( value="sendMsg", method=RequestMethod.POST )
	public String sendMsg( @ModelAttribute("message") Message message) throws Exception{
	
		System.out.println("/message/sendMsg : POST");
		
		messageService.sendMsg(message);
		System.out.println("message :: " + message);
		
		return "redirect:/message/listSendMsg";
	}
	
	@RequestMapping( value="listSendMsg")
	public String listSendMsg( @ModelAttribute("search") Search search, Model model, HttpSession session ) throws Exception{
		
		System.out.println("/message/listSendMsg");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		String sender=((User)session.getAttribute("user")).getUserId();
		System.out.println("sendId ¹¹´× :: " + sender);
		// Business logic ¼öÇà
		Map<String , Object> map=messageService.listSendMsg(search, sender);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model °ú View ¿¬°á
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/view/message/listSendMsg.jsp";
	}
	
	@RequestMapping( value="listReceivMsg")
	public String listReceivMsg( @ModelAttribute("search") Search search, Model model, HttpSession session ) throws Exception{
		
		System.out.println("/message/listSendMsg");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		String receiver=((User)session.getAttribute("user")).getUserId();
		System.out.println("receiver ¹¹´× :: " + receiver);
		// Business logic ¼öÇà
		Map<String , Object> map=messageService.listReceivMsg(search, receiver);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model °ú View ¿¬°á
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/view/message/listReceivMsg.jsp";
	}
	
	@RequestMapping( value="getMsg", method=RequestMethod.GET )
	public String getMsg( @RequestParam("msgNo") int msgNo, 
			@RequestParam(value="receiver", required=false) String receiver, HttpSession session, Model model ) throws Exception{
	
		System.out.println("/message/getMsg : GET");
		
		Message message = messageService.getMsg(msgNo);
		
		String sessionId = ((User)session.getAttribute("user")).getUserId();
//		System.out.println("sessionId / receiver :::::::: " + sessionId + " / " + receiver);
		
		if(sessionId.equals(receiver) && message.getIsRead()==0) {				
			messageService.updateIsRead(msgNo);
		}
//		System.out.println("message :: " + message);
		
		model.addAttribute("message", message);
		
		return "forward:/view/message/getMsg.jsp";
	}
	
	@RequestMapping( value="deleteMsg", method=RequestMethod.POST )
	public String deleteMsg( @RequestParam(value="chbox", required=false) List<String> values, 
				HttpSession session, Search search) throws Exception{
	
		System.out.println("/message/deleteMsg : POST");
		
		Message message = null;
		String destinate = "forward:/message/listSendMsg";
		String sessionId = ((User)session.getAttribute("user")).getUserId();

		if(values != null && values.size()>0){
			for(int i=0; i<values.size(); i++){
				messageService.deleteMsg(Integer.parseInt(values.get(i)), sessionId);
			}
			message = messageService.getMsg(Integer.parseInt(values.get(0)));
		}
		if( sessionId.equals(message.getReceiver())) {
			destinate = "forward:/message/listReceivMsg";
		}
		return destinate;
	}
	
	@RequestMapping( value="isReadMsg", method=RequestMethod.POST )
	public void isReadMsg( HttpSession session, Model model ) throws Exception{
	
		System.out.println("/message/isReadMsg : GET");
		
		String receiver = ((User)session.getAttribute("user")).getUserId();
		
		int isReadMsg = messageService.isReadMsg(receiver);
		
		System.out.println(" ¾ÈÀÐÀº ÂÊÁö °³¼ö ????????? " + isReadMsg);
		
		model.addAttribute("isReadMsg", isReadMsg);
		
	}
	
}
