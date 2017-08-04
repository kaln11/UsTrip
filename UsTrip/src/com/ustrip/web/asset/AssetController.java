 package com.ustrip.web.asset;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import com.ustrip.service.asset.AssetService;
import com.ustrip.service.domain.Asset;

@Controller
@RequestMapping("/asset/*")
public class AssetController {
	
	@Autowired
	@Qualifier("assetServiceImpl")
	private AssetService assetService;
	
	public AssetController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="addAsset",method=RequestMethod.POST)
	   public String addAsset(@ModelAttribute("asset")Asset asset
	                    ,HttpSession session,Model model
	                     ) throws Exception{
		Date visit = new Date(90/10/10);
		asset.setVisitDate(visit);
		/*int a = assetService.addAsset(asset);
		model.addAttribute("result", a);*/
		return "forward:/view/asset/addAsset.jsp";
		
	}
	
	@RequestMapping(value="getAsset",method=RequestMethod.POST)
	   public String getAsset(@RequestParam("travNo")int travNo
	                    ,HttpSession session,Model model
	                     ) throws Exception{
		
		List<Asset> assetList = assetService.getAsset(travNo);
		int sum = 0;
		
		for(Asset asset : assetList){
			sum += asset.getCharge();
		}
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("sum", sum);
		
		return "forward:/view/asset/getAssetView.jsp";
		
	}
	
	@RequestMapping( value="getAssetJSON/{travNo}", method=RequestMethod.GET )
	public void getAssetJSON(	@PathVariable int travNo, 
									 			Model model) throws Exception{
		
		List<Asset> assetList = assetService.getAsset(travNo);
		
		List<Asset> traffic = new ArrayList<Asset>();
		List<Asset> food  = new ArrayList<Asset>();
		List<Asset> entrance = new ArrayList<Asset>();
		List<Asset> shopping = new ArrayList<Asset>();
		List<Asset> room  = new ArrayList<Asset>();
		List<Asset> etc = new ArrayList<Asset>();
		List<Asset> round = new ArrayList<Asset>();	
		
		 Asset sumFood = new Asset();
		 sumFood.setAssetCategory("식비");
		 int foodSum = 0;
		 
		 Asset sumTraffic = new Asset();
		 sumTraffic.setAssetCategory("교통비");
		 int trafficSum = 0;
		 
		 Asset sumShopping = new Asset();
		 sumShopping.setAssetCategory("쇼핑");
		 int shoppingSum = 0;
		 
		 Asset sumEntrance = new Asset();
		 sumEntrance.setAssetCategory("입장료");
		 int entranceSum = 0;
		 
		 Asset sumRoom = new Asset();
		 sumRoom.setAssetCategory("숙박비");
		 int roomSum = 0;
		 
		 Asset sumEtc = new Asset();
		 sumEtc.setAssetCategory("기타");
		 int etcSum = 0;
		 
		 for(Asset asset:assetList){	
			
			 if(asset.getAssetCategory().equals("식비")){
				 food.add(asset);				 
				 foodSum += asset.getCharge();				 
				 sumFood.setCharge(foodSum);				 
			 }else if(asset.getAssetCategory().equals("교통비")){
				 traffic.add(asset);
				 trafficSum += asset.getCharge();				 
				 sumTraffic.setCharge(trafficSum);				 
			 }else if(asset.getAssetCategory().equals("입장료")){
				 entrance.add(asset);
				 entranceSum += asset.getCharge();				 
				 sumEntrance.setCharge(entranceSum);				 
			 }else if(asset.getAssetCategory().equals("쇼핑")){
				 shopping.add(asset);
				 shoppingSum += asset.getCharge();				 
				 sumShopping.setCharge(shoppingSum);				 
			 }else if(asset.getAssetCategory().equals("숙박비")){
				 room.add(asset);
				 roomSum += asset.getCharge();				 
				 sumRoom.setCharge(roomSum);				 
			 }else if(asset.getAssetCategory().equals("기타")){
				 etc.add(asset);
				 etcSum += asset.getCharge();				 
				 sumEtc.setCharge(etcSum);			 
			 }
		 }
		 
		 round.add(sumFood);
		 round.add(sumTraffic);
		 round.add(sumShopping);
		 round.add(sumEntrance);
		 round.add(sumRoom);
		 round.add(sumEtc);
		 
		model.addAttribute("asset", assetList);
		model.addAttribute("traffic", traffic);
		model.addAttribute("food", food);
		model.addAttribute("entrance", entrance);
		model.addAttribute("shopping", shopping);
		model.addAttribute("room", room);
		model.addAttribute("etc", etc);
		model.addAttribute("round", round);
		
	}
	
	@RequestMapping( value="getAssetOneJSON/{assetNo}", method=RequestMethod.GET )
	public void getAssetOneJSON(	@PathVariable int assetNo, 
									 			Model model) throws Exception{
		
		Asset getAsset = assetService.getAssetOne(assetNo);
		Calendar endTrav = Calendar.getInstance();
		endTrav.setTime(getAsset.getStartDate());
		endTrav.add(Calendar.DATE, getAsset.getTotalDate());
		getAsset.setEndDate(endTrav.getTime());
		model.addAttribute("asset",getAsset);
	}
	
	@RequestMapping( value="updateAssetJSON", method=RequestMethod.POST )
	public void updateAssetJSON(	@ModelAttribute("asset")Asset asset, 
									 			Model model) throws Exception{
		assetService.updateAsset(asset);		
	}
	
	@RequestMapping( value="deleteAssetJSON/{assetNo}", method=RequestMethod.GET )
	public void deleteAssetJSON(	@PathVariable int assetNo,
									 			Model model) throws Exception{
		assetService.deleteAsset(assetNo);
	}
		
}
