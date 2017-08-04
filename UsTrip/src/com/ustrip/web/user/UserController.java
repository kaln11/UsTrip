package com.ustrip.web.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ustrip.common.Page;
import com.ustrip.common.Search;
import com.ustrip.service.asset.AssetService;
import com.ustrip.service.blog.BlogService;
import com.ustrip.service.domain.Asset;
import com.ustrip.service.domain.Blog;
import com.ustrip.service.domain.City;
import com.ustrip.service.domain.Follow;
import com.ustrip.service.domain.Image;
import com.ustrip.service.domain.LikeTravel;
import com.ustrip.service.domain.Travel;
import com.ustrip.service.domain.User;
import com.ustrip.service.plan.PlanService;
import com.ustrip.service.user.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("planServiceImpl")
	private PlanService planService;
	
	@Autowired
	@Qualifier("blogServiceImpl")
	private BlogService blogService;
	
	@Autowired
	@Qualifier("assetServiceImpl")
	private AssetService assetService;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@Autowired
	 private JavaMailSender mailSender;
	
	public UserController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="addUser", method=RequestMethod.GET )
	public String addUser() throws Exception{
	
		System.out.println("/user/addUser : GET");
		
		return "redirect:/view/user/addUser.jsp";
	}
	
	@RequestMapping( value="addUser", method=RequestMethod.POST )
	public String addUser( @ModelAttribute("user") User user, 
					@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {

		System.out.println("/user/addUser : POST");		
		
		String fileName = file.getOriginalFilename();
		String newFileName=UUID.randomUUID().toString()+"."+fileName.substring(fileName.lastIndexOf(".")+1);		
		user.setProfileImage(newFileName);		
		//System.out.println("fileName :: " + fileName);
		//System.out.println("newFileName :: " + newFileName);
		
		try{
			
			File imageFile = new File(request.getSession().getServletContext().getRealPath("/") + "images/upload/profile/", newFileName );
			System.out.println("imageFile :: " + imageFile);
			file.transferTo(imageFile);
		} catch(IOException e) {
			e.printStackTrace();
		}	
		//Business Logic
		userService.addUser(user);
		
		return "forward:/view/user/login.jsp";
	}
	/// 타계정 로그인하고 추가정보 입력 후 addUser 처리, 비밀번호는 임시비밀번호 넣기
	@RequestMapping( value="extraUserInfo", method=RequestMethod.POST )
	public String extraUserInfo( @ModelAttribute("user") User user, HttpSession session) throws Exception{
	
		System.out.println("/user/extraUserInfo : POST");
		user.setUserId(user.getUserId().replace(",", "."));
		
		userService.extraUserInfo(user);
		System.out.println("userId 가져오닝:: " +user.getUserId());
		
		User dbUser=userService.getUser(user.getUserId());
		System.out.println("user 뭐닝" + dbUser);
		session.setAttribute("user", dbUser);
		
		
		return "forward:/view/user/getUser.jsp";
	}
	
	@RequestMapping( value="checkUserId/{userId}", method=RequestMethod.GET)
	public void checkUserId(  @PathVariable String userId, Model model) throws Exception {
		
		System.out.println("/user/checkUserId : GET");
		
		userId = userId.replace(",", ".");
		boolean result = userService.checkUserId(userId);
		System.out.println("result 무엇이냐 :: " + result);
		
		model.addAttribute("result", new Boolean(result));
		
	}
	
	@RequestMapping( value="checkNickName/{nickName}", method=RequestMethod.GET)
	public void checkNickName(  @PathVariable String nickName, Model model) throws Exception {
		
		System.out.println("/user/checkNickName : GET");
		
		boolean result = userService.checkNickName(nickName);
		
		model.addAttribute("result", new Boolean(result));
		
	}
	
	@RequestMapping( value="login", method=RequestMethod.GET )
	public String login() throws Exception{
		
		System.out.println("/user/login : GET");

		return "redirect:/view/user/login.jsp";
	}
	
	@RequestMapping( value="login", method=RequestMethod.POST )
	public String login(@ModelAttribute("user") User user , HttpSession session, Model model ) throws Exception{
		
		System.out.println("/user/login : POST");

		String destinate = "redirect:/user/login";
		
		User dbUser=userService.getUser(user.getUserId());
		System.out.println("user 뭐닝" + dbUser);
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
			model.addAttribute("user", user);
			/*destinate="redirect:/user/getUser?userId="+dbUser.getUserId();*/
			/*destinate="redirect:/view/user/listTravel.jsp";*/
			/* destinate="forward:/user/getContents?userId="+user.getUserId();*/
			destinate = "redirect:/index.jsp";
		} 		
		System.out.println(session.getAttribute("user"));
		
		return destinate;
//		return "redirect:/user/getUser?userId="+dbUser.getUserId();
	}
	
	@RequestMapping( value="logout", method=RequestMethod.GET )
	public String logout(HttpSession session ) throws Exception{
		
		System.out.println("/user/logout : GET");
		
		session.invalidate();
		
		return "redirect:/index.jsp";
	}
	
	@RequestMapping( value="updateUser", method=RequestMethod.GET )
	public String updateUser( @RequestParam("userId") String userId , Model model ) throws Exception{

		System.out.println("/user/updateUser : GET");

		User user = userService.getUser(userId);

		model.addAttribute("user", user);
		
		return "forward:/view/user/updateUser.jsp";
	}

	@RequestMapping( value="updateUser", method=RequestMethod.POST )
	public String updateUser( @ModelAttribute("user") User user, @RequestParam("file") MultipartFile file,
			Model model , HttpSession session, HttpServletRequest request) throws Exception{

		System.out.println("/user/updateUser : POST");
		
		String fileName = file.getOriginalFilename();
		String newFileName=UUID.randomUUID().toString()+"."+fileName.substring(fileName.lastIndexOf(".")+1);		
		user.setProfileImage(newFileName);		
		//System.out.println("fileName :: " + fileName);
		//System.out.println("newFileName :: " + newFileName);
		
		try{
			//request.getSession().getServletContext().getRealPath("/")
			File imageFile = new File(request.getSession().getServletContext().getRealPath("/") + "images/upload/profile/", newFileName );
			System.out.println("imageFile :: " + imageFile);
			file.transferTo(imageFile);
		} catch(IOException e) {
			e.printStackTrace();
		}	

		userService.updateUser(user);
		
		String sessionId=((User)session.getAttribute("user")).getUserId();
		if(sessionId.equals(user.getUserId())){
			session.setAttribute("user", user);
		}
		return "redirect:/user/getUser?userId="+user.getUserId();
	}
	
	@RequestMapping( value="getUser", method=RequestMethod.GET )
	public String getUser( @RequestParam("userId") String userId , Model model,  HttpSession session ) throws Exception {
		
		System.out.println("/user/getUser : GET");
		//Business Logic
		User user = userService.getUser(userId);
		// Model 과 View 연결
		session.setAttribute("user", user);
		model.addAttribute("user", user);
		
		return "forward:/view/user/getUser.jsp";
	}
	
	@RequestMapping( value="getUserNickName", method=RequestMethod.GET )
	public String getUserNickName( @RequestParam("nickName") String nickName , Model model ) throws Exception {
		
		System.out.println("/user/getUserNickName : GET");
		//Business Logic
		User user = userService.getUserNickName(nickName);
		// Model 과 View 연결
		model.addAttribute("user", user);
		
		return "forward:/view/user/getUser.jsp";
	}

	@RequestMapping( value="withdrawUser", method=RequestMethod.GET )
	public String withdrawUser( @RequestParam("userId") String userId, Model model ) throws Exception {
		
		System.out.println("/user/withdrawUser : GET");

		 model.addAttribute("userId", userId);

		return "forward:/view/user/withdrawUser.jsp";
	}
	
	@RequestMapping( value="withdrawUser", method=RequestMethod.POST )
	public String withdrawUser( @RequestParam("userId") String userId) throws Exception {
		
		System.out.println("/user/withdrawUser : POST");

		 userService.withdrawUser(userId);

		return "forward:/index.jsp";
	}
	
	@RequestMapping( value="listUser" )
	public String listUser( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/user/getUserList : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map=userService.listUser(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/view/user/listUser.jsp";
	}
	
	@RequestMapping(value = "findPwd", method=RequestMethod.POST)
	public String findPwd( @RequestParam("userId") String userId) throws Exception {

		System.out.println("/user/mailSending :: GET");
	  
	   String setfrom = "eun090878@gmail.com";
	   userId = userId.replace(",", ".");
//	    tomail  = tomail.replace(",", ".");       // 받는 사람 이메일
	    System.out.println("userId :: " + userId);
	    String title   = "UsTrip :: test :: 임시비밀번호입니다.";      // 보내는 사람 이메일
	   
	    //임시비밀번호생성	    
	    String password = userService.getRandomPassword(6);
	    //임시비밀번호로 변경
	    
	    User user = userService.getUser(userId);
	    user.setPassword(password);
	    userService.updateUser(user);
//	    System.out.println("user정보 바뀌었니??????????????????? :: " + user);
	  
	    String content = "\n\nUsTrip test :: 임시비밀번호보내기\n\n임시비밀번호는 \"" + password + "\" 입니다.\n반드시 로그인 후 비밀번호를 수정해주세요!";
	    
	    try {
	      MimeMessage message = mailSender.createMimeMessage();
	      MimeMessageHelper messageHelper 
	                        = new MimeMessageHelper(message, true, "EUC-KR");
	 
	      messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
	      messageHelper.setTo(userId);     // 받는사람 이메일
	      messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	      messageHelper.setText(content);  // 메일 내용
	     
	      mailSender.send(message);
	    } catch(Exception e){
	      System.out.println(e);
	    }
	   
	    return "redirect:/user/login";
	  }
	
	@RequestMapping( value="addFollow/{targetUserId}", method=RequestMethod.GET )
	public void addFollow( @PathVariable String targetUserId, HttpSession session, Model model ) throws Exception {
		
		System.out.println("/user/addFollow : GET");
		
		targetUserId=targetUserId.replace(",", ".");
		String sessionId=((User)session.getAttribute("user")).getUserId();
		
		if(sessionId != targetUserId) {
			userService.addFollow(targetUserId, sessionId);
			Follow follow = userService.getFollow(sessionId, targetUserId);
			model.addAttribute("follow", follow);
		}
		

	}
	
	@RequestMapping( value="getListTravel" )
	public String getListTravel( Search search, HttpSession session, Model model, 
						@RequestParam(value="travUserId", required=false) String travUserId ) throws Exception {

		System.out.println("/user/getListTravel : POST");
		
		String sessionId = "";
		if( session.getAttribute("user") != null ) {
			sessionId = ((User)session.getAttribute("user")).getUserId();
		}
		
		if( travUserId != null ) {
			if(  ! (sessionId.equals(travUserId)) ) {
				sessionId = travUserId;
			}
		}
		
		User user = userService.getUser(sessionId);
		search.setSearchKeyword(sessionId);
		
		Map<String, Object> map = planService.getListTravel(search);
		
		System.out.println("map 맵 " + map);
		System.out.println("list 뭐 담겻니 ::" +  map.get("list"));
		System.out.println("userrrrrrrrrrrrr :: " + user);
		
/*		if( targetUserId != null ) {
			Follow follow = userService.getFollow(sessionId, targetUserId);
			
			model.addAttribute("follow", follow);
		}*/
		model.addAttribute("user", user);
		model.addAttribute("travel", map.get("list"));
		
		return "forward:/view/user/listTravel.jsp";
	}
	
	@RequestMapping( value="allListTravel" )
	public String allListTravel( @ModelAttribute("search") Search search, Model model) throws Exception {

		System.out.println("/user/allListTravel ");
		System.out.println("search " + search);
		Map<String, Object> map = planService.getListTravel(search);
		ArrayList<Travel> a =  (ArrayList<Travel>) map.get("list");
		for( Travel b : a)  {
			String[] c = b.getTravTitle().split("_");
			b.setTravTitle(c[0]);
		}
		map.put("travel", a);
		
		System.out.println("map 맵 " + map);
		System.out.println("list 뭐 담겻니 ::" +  map.get("list"));
		
		model.addAttribute("travel", map.get("list"));
		
		return "forward:/view/user/allListTravel.jsp";
	}
	
	@RequestMapping( value="listFollow")
	public String listFollow( @ModelAttribute("search") Search search, HttpSession session, Model model,
												@RequestParam(value="travUserId", required=false) String travUserId ) throws Exception {
		
		System.out.println("/user/listFollow ");
		
		String sessionId = "";
		if( session.getAttribute("user") != null ) {
			sessionId = ((User)session.getAttribute("user")).getUserId();
		}
		
		if( travUserId != null ) {
			travUserId = travUserId.replace(",", ".");
			if(  ! (sessionId.equals(travUserId)) ) {
				sessionId = travUserId;
			}
		}
		User user = userService.getUser(sessionId);
		
		System.out.println("userrrrrrrrrrrrrrrrrrrrrrrrrrrrr :: " + user);
		
		Map<String , Object> map=userService.listFollow(search, sessionId);
		System.out.println("mappp :: " + map);
		
		model.addAttribute("list", map.get("list"));
		/*model.addAttribute("follow", map);*/
/*		model.addAttribute("search", search);*/
		model.addAttribute("user", user);
		
		return "forward:/view/user/listFollow.jsp";
	}
	
	@RequestMapping( value="listFollowing")
	public String listFollowing( @ModelAttribute("search") Search search, HttpSession session, Model model,
			@RequestParam(value="travUserId", required=false) String travUserId ) throws Exception {
		
		System.out.println("/user/listFollowing ");
		
		String sessionId = "";
		if( session.getAttribute("user") != null ) {
			sessionId = ((User)session.getAttribute("user")).getUserId();
		}
		
		if( travUserId != null ) {
			if(  ! (sessionId.equals(travUserId)) ) {
				sessionId = travUserId;
			}
		}
		User user = userService.getUser(sessionId);
		Map<String , Object> map=userService.listFollowing(search, sessionId);
		System.out.println("map :::::::::::: " + map);
		
		
		model.addAttribute("list", map.get("list"));
/*		model.addAttribute("search", search);*/
		model.addAttribute("user", user);
		
		return "forward:/view/user/listFollowing.jsp";
	}
	
	@RequestMapping( value="deleteFollow/{targetUserId}", method=RequestMethod.GET )
	public void deleteFollow( HttpSession session, Model model, @PathVariable String targetUserId ) throws Exception {

		System.out.println("/user/deleteFollow : GET");
		
		targetUserId = targetUserId.replace(",", ".");
		String sessionId=((User)session.getAttribute("user")).getUserId();
		
		userService.deleteFollow(sessionId, targetUserId);
			
	}
	
	@RequestMapping( value="getTravel",  method=RequestMethod.GET )
	public String getTravel( HttpSession session, Model model, @RequestParam("travNo") int travNo, 
							@RequestParam( value="userId", required=false ) String travUserId) throws Exception {

		System.out.println("/user/getTravel :");
		
		String sessionId = "";
		if( session.getAttribute("user") != null ) {
			sessionId = ((User)session.getAttribute("user")).getUserId();
		}
		
		if( travUserId != null ) {
			if(  ! (sessionId.equals(travUserId)) ) {
				sessionId = travUserId;
			}
		}
		User user = userService.getUser(sessionId);
		
/*		
		if( travUserId != null ) {
			User user = userService.getUser(travUserId);
			model.addAttribute("user", user);
		}
		
		String userId=((User)session.getAttribute("user")).getUserId();*/
		System.out.println("userId????????????????" + sessionId);
		System.out.println("dddddddd " + travNo);
		
		Travel travel = planService.getTravel(travNo);
		List<City> city = planService.getCity(travNo);
		
		System.out.println("traaaaaaaaaaaaaaaaaa " + travel);
		System.out.println("traaaaaaaaaaaaaaaaaa " + city);
		String[] travelSplit = travel.getTravTitle().split("_");
		travel.setTravTitle(travelSplit[0]);
		
	
	List<LikeTravel> list = blogService.checkLikeTravel(travNo);
		int result = 0;
		if( list.size() >0 ) {
			for( int i=0; i< list.size(); i++) {
				if( sessionId.equals(list.get(i).getUserId()) ) {
					result=1;
				}
			}
		}
		//////////////////////////////////////////////////////////////////////////////////
		int islike=0;
				
		int checkBlogStart=planService.checkBlogStart(travNo);
		
		if(checkBlogStart == 1){
			
			List<Blog> blog=blogService.listBlog(travNo);
			for(int i=0; i<blog.size(); i++){
				List<Asset> asset=assetService.getAssetByBlogNo(blog.get(i).getBlogNo());
				for(int j=0; j<asset.size(); j++){
					blog.get(i).setSumCharge(blog.get(i).getSumCharge()+asset.get(j).getCharge());
				}
				blog.get(i).setAssets(asset);
			}
			
			if(sessionId != null){
				List<LikeTravel> travNoLike = blogService.checkLikeTravel(travNo);
				for(LikeTravel T : travNoLike){
					if(T.getUserId().equals(sessionId)){
						islike=1;
					}
				}
			}
			
			List<Blog> checkBlog = new ArrayList();
			for(Blog deleteBlog : blog){
				if(deleteBlog.getDeleteFlag() == 0){
					checkBlog.add(deleteBlog);
				}
			}
			System.out.println("*************************"+checkBlog);
			model.addAttribute("list", checkBlog);
			model.addAttribute("isLiked",islike);
		}else{
			model.addAttribute("checkBlogStart", checkBlogStart);
		}		
		//////////////////////////////////////////////////////////////////////////////////
		List<Asset> assetList = assetService.getAsset(travNo);
		int sum = 0;
		
		for(Asset asset : assetList){
			sum += asset.getCharge();
		}
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("sum", sum);
	
		model.addAttribute("isLike", result);
		model.addAttribute("travel", travel);
	/*	model.addAttribute("city", city);*/
		
		List<City> listCity = planService.blogCity(travNo);
		for(City c : listCity){
			String[] replaceCity = c.getCity().split("_");
			c.setCity(replaceCity[0]);
		}
		model.addAttribute("listCity",listCity);
		model.addAttribute("travNo",travNo);
/*		List<City> listCity2 = planService.blogCity(travNo);
		model.addAttribute("listCity",listCity2);
	*/
		model.addAttribute("user", user);
		return "forward:/view/user/getTravel.jsp";
	}
	
	@RequestMapping( value="listLikeTravel")
	public String listLikeTravel( @ModelAttribute("search") Search search, HttpSession session, Model model,
			@RequestParam(value="travUserId", required=false) String travUserId ) throws Exception {
		
		System.out.println("/user/listLikeTravel ");

		String sessionId = "";
		if( session.getAttribute("user") != null ) {
			sessionId = ((User)session.getAttribute("user")).getUserId();
		}
		
		if( travUserId != null ) {
			if(  ! (sessionId.equals(travUserId)) ) {
				sessionId = travUserId;
			}
		}
		User user = userService.getUser(sessionId);
		search.setSearchKeyword(sessionId);
		
		List<LikeTravel> listLikeTravel=blogService.getListLikeTravel(search);
		System.out.println("listLikeTravel :::::::::::: " + listLikeTravel);
		
		model.addAttribute("likeTravel", listLikeTravel);
		model.addAttribute("user", user);
		
		return "forward:/view/user/listLikeTravel.jsp";
	}
	
	@RequestMapping(value={"addJSONTumbnail/{travNo}"}, method=RequestMethod.POST)
	public void getJsontest(MultipartHttpServletRequest file, @PathVariable int travNo) throws Exception {
		System.out.println("ok~!!!");
		List<MultipartFile> uploadFiles = file.getFiles("thumbNailFile");	
		System.out.println("uploadFiles :: !!!!!!!!!!!!!!!!!!! "+uploadFiles);
		Travel travel = new Travel();
		
		String saveFolder = "C:/Users/B/git/UsTrip/UsTrip/WebContent/images/upload/blog";		
		
		for(int i=0 ; i<uploadFiles.size() ; i++) {			          
			String fileName = uploadFiles.get(i).getOriginalFilename();
			String newFileName=UUID.randomUUID().toString()+"."+fileName.substring(fileName.lastIndexOf(".")+1);
			System.out.println("new~~~~~~~~~~~~file : "+newFileName);
	        File uploadFile = new File(saveFolder,newFileName);
	        uploadFiles.get(i).transferTo(uploadFile);
	        travel.setTravelNo(travNo);
			travel.setThumbNail(newFileName);
		}		
		System.out.println("travel~~~~~~~~~~~~~:"+travel);
		planService.updateThumbnail(travel);
	}

}
