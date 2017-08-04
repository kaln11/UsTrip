package com.ustrip.service.asset.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ustrip.service.asset.AssetDao;
import com.ustrip.service.asset.AssetService;
import com.ustrip.service.domain.Asset;

@Service("assetServiceImpl")
public class AssetServiceImpl implements AssetService{
	
	@Autowired
	@Qualifier("assetDaoImpl")
	private AssetDao assetDao;
	public void setAssetDao(AssetDao assetDao) {
		this.assetDao = assetDao;
	}
	
	public AssetServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@Override
	public void addAsset(Asset asset)throws Exception{
		assetDao.addAsset(asset);
	}

	@Override
	public List<Asset> getAsset(int travNo) throws Exception {
		// TODO Auto-generated method stub
		return assetDao.getAsset(travNo);
	}

	@Override
	public int updateAsset(Asset asset) throws Exception {
		// TODO Auto-generated method stub
		return assetDao.updateAsset(asset);
	}

	@Override
	public int deleteAsset(int assetNo) throws Exception {
		// TODO Auto-generated method stub
		return assetDao.deleteAsset(assetNo);
	}
	
	@Override
	public Asset getAssetOne(int assetNo) throws Exception {
		// TODO Auto-generated method stub
		return assetDao.getAssetOne(assetNo);
	}

	@Override
	public List<Asset> getAssetByBlogNo(int blogNo) throws Exception {
		return assetDao.getAssetByBlogNo(blogNo);
	}

}
