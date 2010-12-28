package com.wsria.demo.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsria.demo.entity.base.DataLibrary;
import com.wsria.demo.util.DataLibraryUtil;

/**
 * 数据字典初始化类
 * 
 * @author 咖啡兔
 * 
 */
@Service
public class DataLibraryList {

	@Autowired
	private DataLibraryManager datalibraryManager;

	private DataLibraryList() {

	}

	/**
	 * 数据字典初始化：将数据写入缓存
	 */
	public void init() {
		List<DataLibrary> dataLibraryList = datalibraryManager.getAllDataLibrarys();
		for (int i = 0; i < dataLibraryList.size(); i++) {
			DataLibrary datalibrary = dataLibraryList.get(i);
			String key = datalibrary.getLibraryCode();
			
			/*
			 * 所有的数组字典
			 */
			DataLibraryUtil.getDataLibraryList().put(key, datalibrary);
			
			/*
			 * 根据library类型分组添加到Map中
			 */
			DataLibraryUtil.addToTypeMap(datalibrary);
		}
	}

	/**
	 * 重载数据字典
	 */
	public void reload() {
		DataLibraryUtil.clearDatas();
		init();
	}
	
}
