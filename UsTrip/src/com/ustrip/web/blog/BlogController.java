package com.ustrip.web.blog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ustrip.service.asset.AssetService;
import com.ustrip.service.blog.BlogService;
import com.ustrip.service.domain.Asset;
import com.ustrip.service.domain.Blog;
import com.ustrip.service.domain.City;
import com.ustrip.service.domain.HashTag;
import com.ustrip.service.domain.Image;
import com.ustrip.service.domain.LikeTravel;
import com.ustrip.service.domain.User;
import com.ustrip.service.domain.Place;
import com.ustrip.service.domain.Travel;
import com.ustrip.service.plan.PlanService;



@Controller
@RequestMapping("/blog/*")
public class BlogController {

	@Autowired
	@Qualifier("blogServiceImpl")
	private BlogService blogService;
	
	@Autowired
	@Qualifier("assetServiceImpl")
	private AssetService assetService;
	
	@Autowired
	@Qualifier("planServiceImpl")
	private PlanService planService;
	
	public BlogController(){
		System.out.println(this.getClass());
	}
	
		
	@RequestMapping(value="addBlog", method=RequestMethod.GET)
	public String addBlog( @RequestParam("travelNo") int travelNo, Model model ) throws Exception {

		System.out.println("/addBlog : GET");
		
		List<Place> list=planService.listPlace(travelNo);	
		System.out.println("¾å" +list);
		blogService.addBlog(list);
		planService.startBlog(travelNo);
		
		return "forward:/user/getTravel?travNo="+travelNo;
	}
	
	@RequestMapping(value="addJsonTag", method=RequestMethod.POST)
	public void addJsonTag( @ModelAttribute("hashTag")HashTag hashTag, Model model ) throws Exception {
		
		System.out.println("/addJsonTag : POST");
				
		blogService.addJsonTag(hashTag);
		
		model.addAttribute("hashTag", hashTag);
	}
	
	@RequestMapping(value="addJsonAsset", method=RequestMethod.POST)
	public void addJsonAsset( @ModelAttribute("asset")Asset asset, Model model ) throws Exception {
		System.out.println("================"+asset);
		System.out.println("/addJsonAsset : POST");
		Blog blog=blogService.getJsonBlog(asset.getBlogNo());
		asset.setVisitDate(blog.getVisitDate());
		
		assetService.addAsset(asset);
		
		model.addAttribute("asset", asset);
	}
		
	@RequestMapping(value={"addJsonLike/{travNo}"}, method=RequestMethod.GET)
	public void addJsonLike( @PathVariable int travNo, Model model, HttpSession session ) throws Exception {
		
		System.out.println("/addJsonLike : GET");
		String userId=((User)session.getAttribute("user")).getUserId();
		blogService.addJsonLike(travNo, userId);
	}
	
	
	@RequestMapping(value={"getJsonBlog/{blogNo}"}, method=RequestMethod.GET)
	public void getJsonBlog( @PathVariable int blogNo, Model model ) throws Exception {
		
		System.out.println("/getJsonBlog : GET");
		
		Blog blog=blogService.getJsonBlog(blogNo);
		List<Asset> asset=assetService.getAssetByBlogNo(blogNo);
		blog.setAssets(asset);
		
		System.out.println(blog);
		model.addAttribute("blog", blog);
	}

	
	@RequestMapping(value="listBlog", method=RequestMethod.GET)
	public String listBlog( @RequestParam("travNo") int travNo, 
			@RequestParam(value="userId", required=false) String userId, Model model ) throws Exception {

		System.out.println("/listBlog : GET");
		String destination="forward:/view/blog/listBlog.jsp";
		int islike=0;
				
			int travel=planService.checkBlogStart(travNo);
			
			if(travel == 1){
				
				List<Blog> blog=blogService.listBlog(travNo);
				for(int i=0; i<blog.size(); i++){
					List<Asset> asset=assetService.getAssetByBlogNo(blog.get(i).getBlogNo());
					for(int j=0; j<asset.size(); j++){
						blog.get(i).setSumCharge(blog.get(i).getSumCharge()+asset.get(j).getCharge());
					}
					blog.get(i).setAssets(asset);
				}
				
				if(userId != null){
					List<LikeTravel> travNoLike = blogService.checkLikeTravel(travNo);
					for(LikeTravel T : travNoLike){
						if(T.getUserId().equals(userId)){
							islike=1;
						}
					}
				}
				
				List<Blog> checkBlog = new ArrayList<Blog>();
				for(Blog deleteBlog : blog){
					if(deleteBlog.getDeleteFlag() == 0){
						checkBlog.add(deleteBlog);
					}
				}
				System.out.println("*************************"+checkBlog);
				model.addAttribute("list", checkBlog);
				model.addAttribute("isLiked",islike);
			}else{
				model.addAttribute("travel", travel);
				destination="forward:/view/blog/addBlog.jsp";
			}		
		
		return destination;
	}
	
	@RequestMapping(value="listPicture", method=RequestMethod.GET)
	public String listPicture( @RequestParam("travelNo") int travelNo, Model model ) throws Exception {

		System.out.println("/listPicture : GET");
		
		List<Integer> blogNo=blogService.listBlogNo(travelNo);
		System.out.println("**************************");
		List<Blog> list=blogService.listImgByBlogNo(blogNo);
		
		model.addAttribute("list", list);
		
		return "forward:/view/blog/listPicture.jsp";
	}
	
	@RequestMapping(value="updateBlog", method=RequestMethod.GET)
	public String updateBlog( @RequestParam("blogNo") int blogNo, Model model) throws Exception {
		
		System.out.println("/updateBlog : GET");
		
		Blog blog=blogService.getJsonBlog(blogNo);
		System.out.println("*********"+blog.getVisitDate());
		List<Asset> asset=assetService.getAssetByBlogNo(blogNo);
		blog.setAssets(asset);
		model.addAttribute("blog", blog);
		
		return "forward:/view/blog/updateBlog.jsp";
	}
	
	@RequestMapping(value="updateBlogJSON/{blogNo}", method=RequestMethod.GET)
	public void updateBlogJSON( @PathVariable int blogNo, Model model) throws Exception {
		
		System.out.println("/updateBlogJSON : GET");
		
		Blog blog=blogService.getJsonBlog(blogNo);
		List<Asset> asset=assetService.getAssetByBlogNo(blogNo);
		blog.setAssets(asset);
		model.addAttribute("blog", blog);
		
	}
	
	@RequestMapping(value={"updateJsonScore/{score}/{blogNo}"}, method=RequestMethod.GET)
	public void updateJsonScore( @ModelAttribute Blog blog ) throws Exception {
		
		System.out.println("/updateJsonScore : GET");

		blogService.updateScore(blog);
	}
	
	/*@RequestMapping(value="updatePlace", method=RequestMethod.GET)
	public String updatePlace( @RequestParam("travelNo") int travelNo, @RequestParam("visitDate") String visitDate, Model model ) throws Exception {

		System.out.println("/updatePlace : GET");
		Search search=new Search();
		
		search.setSearchKeyword(Integer.toString(travelNo));
		search.setSearchDate(visitDate);
		List<Integer> listPlaceNo=planService.listPlaceNo(search);
		
		search.setPlaceOrder(listPlaceNo);
		List<Blog> blog=blogService.listBlog(search);
		
		model.addAttribute("blog", blog);
		
		return "forward:/view/blog/updatePlace.jsp";
	}*/
	
	@RequestMapping(value="updateJsonReview", method=RequestMethod.POST)
	public void updateJsonReview( @ModelAttribute("blog") Blog blog, Model model ) throws Exception {
		
		System.out.println("/updateJsonReview : POST");

		blogService.updateJsonReview(blog);
	}
	
	@RequestMapping(value={"deleteJsonTag/{tagNo}"}, method=RequestMethod.GET)
	public void deleteJsonTag( @PathVariable int tagNo ) throws Exception {
		
		System.out.println("/deleteJsonTag : GET");

		blogService.deleteTag(tagNo);
	}
	
	@RequestMapping(value={"deleteJsonLike/{travNo}"}, method=RequestMethod.GET)
	public void deleteJsonLike( @PathVariable int travNo ) throws Exception {
		
		System.out.println("/deleteJsonLike : GET");
		String userId="user01";
		blogService.deleteJsonLike(travNo, userId);
	}
	
	@RequestMapping(value={"deleteJsonImage/{imgNo}"}, method=RequestMethod.GET)
	public void deleteJsonImage( @PathVariable int imgNo ) throws Exception {
		
		System.out.println("/deleteJsonImage : GET");
		blogService.deleteJsonImage(imgNo);
	}
	
	@RequestMapping(value="deleteBlog", method=RequestMethod.GET)
	public String deleteBlog( @RequestParam("blogNo") int blogNo,@RequestParam("travNo") int travNo, Model model ) throws Exception {
		
		System.out.println("/deleteBlog : GET");
		blogService.deleteBlog(blogNo);
		return "forward:/user/getTravel?travNo="+travNo;
	}
	
	public void checkLikeTravel( int travelNo) throws Exception {
		
		System.out.println("/checkLikeTravel");

		blogService.checkLikeTravel(travelNo);
	}
	
	
	@RequestMapping(value={"addJSONImage/{blogNo}/{travNo}"}, method=RequestMethod.POST)
	public void getJsontest(MultipartHttpServletRequest file,
			   Model model,@PathVariable int blogNo,@PathVariable int travNo)
					   throws Exception {
		
		List<MultipartFile> uploadFiles = file.getFiles("blogFile[]");		
		List<Image> images = new ArrayList(); 
		
		String saveFolder = "C:/Users/B/git/UsTrip/UsTrip/WebContent/images/upload/blog/"+travNo;
		File targetDir = new File(saveFolder);  
        
        if(!targetDir.exists()) {   
         targetDir.mkdirs();
        }

		System.out.println(uploadFiles);
		
		for(int i=0 ; i<uploadFiles.size() ; i++) {			
			System.out.println(i);
	          
				String fileName = uploadFiles.get(i).getOriginalFilename();
	            String newFileName=UUID.randomUUID().toString()+"."+fileName.substring(fileName.lastIndexOf(".")+1);
	            	           
				System.out.println(fileName);
				System.out.println(newFileName);
				System.out.println(uploadFiles.get(i));
	            File uploadFile = new File(saveFolder,newFileName);
	            uploadFiles.get(i).transferTo(uploadFile);
	            Image image=new Image();
                image.setServerImgName(newFileName);
                image.setOriginalName(fileName);
                image.setBlogNo(blogNo);
                image.setTravNo(travNo);
                images.add(image);
	          
		}		
		blogService.addImage(images);
		model.addAttribute("add",images)	;	
	}
	
	@RequestMapping(value="getMap", method=RequestMethod.GET)
	public String getMap( @RequestParam("travNo") int travNo, Model model ) throws Exception {
		List<City> listCity = planService.blogCity(travNo);
		for(City c : listCity){
			String[] replaceCity = c.getCity().split("_");
			c.setCity(replaceCity[0]);
		}
		model.addAttribute("listCity",listCity);
		model.addAttribute("travNo",travNo);
		return "forward:/view/blog/mapFrame.jsp";
	}
	
	@RequestMapping(value={"getMapCity/{travNo}"}, method=RequestMethod.GET)
	public void getMapCity( @PathVariable int travNo, Model model ) throws Exception {
		List<City> listCity = planService.blogCity(travNo);
		for(City c : listCity){
			String[] replaceCity = c.getCity().split("_");
			c.setCity(replaceCity[0]);
		}
		model.addAttribute("listCity",listCity);
	}
	
	@RequestMapping(value={"getMapPlace/{cityNo}"}, method=RequestMethod.GET)
	public void getMapPlace( @PathVariable int cityNo, Model model ) throws Exception {
		List<Place> listPlace = planService.blogPlace(cityNo);
		for(Place p : listPlace){
			String[] replacePlace = p.getPlace().split("_");
			p.setPlace(replacePlace[0]);
		}
		model.addAttribute("listPlace",listPlace);
	}
		
		

}
