package com.ustrip.service.asset;

import java.util.List;

import com.ustrip.service.domain.Asset;

public interface AssetDao {

	public int addAsset(Asset asset) throws Exception;
	
	public List<Asset> getAsset(int travNo) throws Exception;
	
	public int updateAsset(Asset asset) throws Exception;
	
	public int deleteAsset(int assetNo) throws Exception;
	
	public Asset getAssetOne(int assetNo) throws Exception;

	public List<Asset> getAssetByBlogNo(int blogNo) throws Exception;
	
}
