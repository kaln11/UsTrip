package com.ustrip.service.asset.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ustrip.service.asset.AssetDao;
import com.ustrip.service.domain.Asset;

@Repository("assetDaoImpl")
public class AssetDaoImpl implements AssetDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public AssetDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@Override
	public int addAsset(Asset asset)throws Exception{
		return sqlSession.insert("AssetMapper.addAsset",asset);
	}

	@Override
	public List<Asset> getAsset(int travNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("AssetMapper.getAsset",travNo);
	}

	@Override
	public int updateAsset(Asset asset) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("AssetMapper.updateAsset",asset);
	}

	@Override
	public int deleteAsset(int assetNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("AssetMapper.deleteAsset",assetNo);
	}
	
	@Override
	public Asset getAssetOne(int assetNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("AssetMapper.oneAsset",assetNo);
	}

	@Override
	public List<Asset> getAssetByBlogNo(int blogNo) throws Exception {
		return sqlSession.selectList("AssetMapper.getAssetByBlogNo",blogNo);
	}

}
