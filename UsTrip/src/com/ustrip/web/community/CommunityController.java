package com.ustrip.web.community;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ustrip.common.Page;
import com.ustrip.common.Search;
import com.ustrip.service.board.BoardService;
import com.ustrip.service.comment.CommentService;
import com.ustrip.service.domain.Board;
import com.ustrip.service.domain.City;
import com.ustrip.service.domain.Comment;
import com.ustrip.service.domain.Place;
import com.ustrip.service.domain.Travel;
import com.ustrip.service.domain.User;
import com.ustrip.service.plan.PlanService;
import com.ustrip.service.user.UserService;

@Controller
@RequestMapping("/community/*")
public class CommunityController {
	
	@Autowired
	@Qualifier("boardServiceImpl")
	private BoardService boardService;
	
	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	
	@Autowired
	@Qualifier("planServiceImpl")
	private PlanService planService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	public CommunityController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping(value="addBoardForm", method=RequestMethod.GET)
	public String addBoardForm( @RequestParam("userId") String userId, Model model) throws Exception {
		
		System.out.println("/addBoardForm : GET");
		
		List<Travel> travels = boardService.addBoardForm(userId);
		for(Travel T : travels){
			String[] titleSplit = T.getTravTitle().split("_");
			T.setTravTitle(titleSplit[0]);
		}
		model.addAttribute("travels", travels);
		
		return "forward:/view/community/addBoardView.jsp";
	}
	
	@RequestMapping(value="updateBoardForm", method=RequestMethod.GET)
	public String updateBoardForm( @RequestParam("boardNo") int boardNo, Model model) throws Exception {
		
		System.out.println("/updateBoardForm : GET");
		
		Board board = boardService.getBoard(boardNo);
		Travel travel = planService.getTravel(board.getTravNo());
		model.addAttribute("travel",travel);
		model.addAttribute("board", board);
		
		return "forward:/view/community/updateBoardView.jsp";
	}
	
	@RequestMapping(value="updateBoard")
	   public String updateBoard(@ModelAttribute("search") Board board ,HttpSession session ) throws Exception{
		
		System.out.println("/updateBoard : POST");
		
		boardService.updateBoard(board);
		System.out.println("-------------------------"+board);
		return "forward:/community/listCommunity";
	}
	
	@RequestMapping(value="addBoard")
	   public String addBoard(@ModelAttribute("search") Board board ,HttpSession session ) throws Exception{
		
		System.out.println("/addBoard : POST");
		
		boardService.addBoard(board);
		
		return "forward:/community/listCommunity";
	}
	
	@RequestMapping( value="deleteBoardJSON/{boardNo}", method=RequestMethod.GET )
	public void deleteBoardJSON(@PathVariable int boardNo, Model model) throws Exception{
		boardService.deleteBoard(boardNo);
	}	
	
	@RequestMapping(value="listCommunity")
	   public String listBoard(@ModelAttribute("search") Search search 
	                    ,HttpSession session,Model model
	                     ) throws Exception{
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		if(search.getOrder() == null || search.getOrder().equals("") || search.getOrder().length() < 1){
			search.setOrder("DESC");
		}
		search.setPageSize(pageSize);
		List<Board> board = boardService.listBoard(search);	
		
		@SuppressWarnings("unchecked")
		List<Integer> noList = new ArrayList<Integer>();
		List<Comment> selComment = new ArrayList<Comment>();
		HashMap boards = new HashMap();
		
		for(Board b : board){			
				noList.add(b.getBoardNo());				
		}			
		boards.put("boards", noList);			
		if(noList.size() > 0){
		List<Comment> comment = commentService.listComment(boards);
		for(Board b : board){
			for(Comment c : comment){
				if(b.getBoardNo() == c.getBoardNo()){
					selComment.add(c);
				}//if end
			}// for c end
			b.setCountComment(selComment.size());
			selComment = new ArrayList<Comment>();			
		}//for b end	
		}
		int totalCount = boardService.getTotalCount(search);
		Page resultPage = new Page( search.getCurrentPage(), totalCount, pageUnit, pageSize);
		
		model.addAttribute("list", board);
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		if(search.getBoardCategory() == 0){	
		return "forward:/view/community/listMentorView.jsp";
		}else{			
		return "forward:/view/community/listPartyView.jsp";	
		}
		
	}
	
	@RequestMapping(value="getBoard")
	   public String getBoard(@ModelAttribute("search") Search search ,
			   @RequestParam("boardNo")int boardNo
	                    ,HttpSession session,Model model
	                     ) throws Exception{
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		if(search.getOrder() == null || search.getOrder().equals("") || search.getOrder().length() < 1){
			search.setOrder("DESC");
		}
		search.setPageSize(pageSize);
		List<Board> board = boardService.listBoard(search);	
		
		@SuppressWarnings("unchecked")
		List<Integer> noList = new ArrayList<Integer>();
		List<Comment> selComment = new ArrayList<Comment>();
		HashMap boards = new HashMap();
		
		for(Board b : board){			
				noList.add(b.getBoardNo());				
		}		
		boards.put("boards", noList);			
		
		List<Comment> comment = commentService.listComment(boards);
		for(Board b : board){
			for(Comment c : comment){
				if(b.getBoardNo() == c.getBoardNo()){
					selComment.add(c);
				}//if end
			}// for c end
			b.setCountComment(selComment.size());
			selComment = new ArrayList<Comment>();			
		}//for b end	
		int totalCount = boardService.getTotalCount(search);
		Page resultPage = new Page( search.getCurrentPage(), totalCount, pageUnit, pageSize);
		
		Board getBoard = boardService.getBoard(boardNo);
		List<Comment> boardComment = commentService.getComment(boardNo);
		getBoard.setCountComment(boardComment.size()); 
		getBoard.setHits(getBoard.getHits()+1);
		boardService.updateBoard(getBoard);
		
		List<City> listCity = planService.blogCity(getBoard.getTravNo());
		for(City c : listCity){
			String[] replaceCity = c.getCity().split("_");
			c.setCity(replaceCity[0]);
		}
		Travel travel = planService.getTravel(getBoard.getTravNo());
		String[] splitTitle = travel.getTravTitle().split("_");
		travel.setTravTitle(splitTitle[0]);
		Calendar endTrav = Calendar.getInstance();
	/*	endTrav.setTime(travel.getStartDate());*/
		endTrav.add(Calendar.DATE, travel.getTotalDate());
		model.addAttribute("travel",travel);
		model.addAttribute("endTrav",endTrav.getTime());
		model.addAttribute("listCity",listCity);
		model.addAttribute("travNo",getBoard.getTravNo());
		
		model.addAttribute("list", board);
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("board",getBoard);
		model.addAttribute("comments",boardComment);
		
		if(search.getBoardCategory() == 0){	
		return "forward:/view/community/getMentorView.jsp";
		}else{			
		return "forward:/view/community/getPartyView.jsp";	
		}
		
	}
	
	/*@RequestMapping( value="getCommunityListJSON/{communityCategory}", method=RequestMethod.GET )
	public void getAssetOneJSON(@PathVariable int communityCategory, Model model) throws Exception{
		
		List<Board> board = boardService.listBoard(communityCategory);
		List<Integer> noList = new ArrayList<Integer>();
		List<Comment> selComment = new ArrayList<Comment>();
		HashMap boards = new HashMap();
		
		for(Board b : board){
			noList.add(b.getBoardNo());
			System.out.println(b.getBoardNo());
		}		
		boards.put("boards", noList);			
		
		List<Comment> comment = commentService.listComment(boards);
		for(Board b : board){
			for(Comment c : comment){
				if(b.getBoardNo() == c.getBoardNo()){
					selComment.add(c);
				}//if end
			}// for c end
			b.setMyComment(selComment);
			selComment = new ArrayList<Comment>();
			b.setCountComment(b.getMyComment().size());
			String format = new SimpleDateFormat("yyyy/mm/dd").format(b.getRegDate());
			@SuppressWarnings("deprecation")
			Date transDate = new Date(format);
			b.setRegDate(transDate);
		}//for b end		
		
		model.addAttribute("board",board);
	}*/
	@RequestMapping( value="addCommentJSON", method=RequestMethod.POST )
	public void addCommentJSON(@ModelAttribute("comment") Comment comment, Model model) throws Exception{
		int response = commentService.addComment(comment);
		model.addAttribute("result",response);
	}	
	
	@RequestMapping( value="deleteCommentJSON/{commentNo}", method=RequestMethod.GET )
	public void deleteCommentJSON(@PathVariable int commentNo, Model model) throws Exception{
		int response = commentService.deleteComment(commentNo);
		model.addAttribute("result",response);
	}
	
	@RequestMapping( value="updateCommentJSON", method=RequestMethod.POST )
	public void updateCommentJSON(@ModelAttribute("comment") Comment comment, Model model) throws Exception{

		System.out.println("**********updateCommunity****"+comment);
		int response = commentService.updateComment(comment);
		model.addAttribute("result",response);
	}	
	
	@RequestMapping( value="oneCommentJSON/{commentNo}", method=RequestMethod.GET )
	public void oneCommentJSON(@PathVariable int commentNo, Model model) throws Exception{
		Comment comment = commentService.oneComment(commentNo);
		model.addAttribute("result",comment);
	}
	
	@RequestMapping( value="addFollow/{nickName}", method=RequestMethod.GET )
	public void addFollow( @PathVariable String nickName, HttpSession session, Model model ) throws Exception {
		
		System.out.println("/community/addFollow : GET");
		String targetUserId = userService.getUserId(nickName);
		
		targetUserId=targetUserId.replace(",", ".");		
		String sessionId=((User)session.getAttribute("user")).getUserId();
		
		if(sessionId != targetUserId) {
			userService.addFollow(targetUserId, sessionId);
		}
	}
	
	@RequestMapping( value="getTravList/{nickName}", method=RequestMethod.GET )
	public void getTravList( @PathVariable String nickName,Model model ) throws Exception {
		
		System.out.println("/community/getTravList : GET");		
		String userId = userService.getUserId(nickName);
		System.out.println("****************************"+userId);
		model.addAttribute("userId",userId);
	}

}
