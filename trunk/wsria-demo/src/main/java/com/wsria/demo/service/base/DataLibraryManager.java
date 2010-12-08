package com.wsria.demo.service.base;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.wsria.demo.dao.base.DataLibraryDao;
import com.wsria.demo.entity.base.DataLibrary;
import com.wsria.demo.util.DataLibraryUtil;

/**
 * 
 * 数据字典管理类，包含添加，删除，修改，查询，条件查询
 * 
 * @author 咖啡兔
 * 
 */
@Service
@Transactional
public class DataLibraryManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(DataLibraryManager.class);

	@Autowired
	private DataLibraryDao datalibraryDao;

	/**
	 * 根据id获取某个数据字典
	 * 
	 * @param id
	 *            数据字典id
	 * @return
	 */
	public DataLibrary getDataLibrary(Long id) {
		return datalibraryDao.get(id);
	}

	/**
	 * @return 获取所有数据字典集合
	 */
	@Transactional(readOnly = true)
	public List<DataLibrary> getAllDataLibrarys() {
		return datalibraryDao.getAll();
	}

	/**
	 * 添加数据字典
	 * 
	 * @param entity
	 */
	public void saveDataLibrary(DataLibrary entity) {
		datalibraryDao.save(entity);
		DataLibraryUtil.addDataLibrary(entity);
	}

	/**
	 * 删除标识为id值的数据字典
	 * 
	 * @param id
	 *            数据字典标识
	 */
	public void deleteDataLibrary(Long id) {
		DataLibrary datalibrary = datalibraryDao.get(id);
		logger.info("id:{}数据字典被用户{}删除!", new Object[] { id, SpringSecurityUtils.getCurrentUserName() });
		datalibraryDao.delete(id);
		DataLibraryUtil.deleteDataLibrary(datalibrary.getLibraryCode());
	}

	/**
	 * 使用属性过滤条件查询属性.
	 */
	@Transactional(readOnly = true)
	public Page<DataLibrary> searchProperty(final Page<DataLibrary> page, final List<PropertyFilter> filters) {
		return datalibraryDao.findPage(page, filters);
	}

	/**
	 * 验证规则名称的唯一性， 存在返回false,不存在返回true
	 */
	public boolean existLibrarycode(String id, String libraryCode) {
		DataLibrary datalibrary = datalibraryDao.findUniqueBy("libraryCode", libraryCode);
		// 添加时无id时，做唯一验证
		if (StringUtils.isEmpty(id) || "_empty".equals(id)) {
			if (datalibrary != null) {
				return true;
			} else {
				return false;
			}
		} else {
			// 修改时id已存在，做唯一验证
			if (datalibrary == null) {
				return false;
			} else {
				// 当对象存在时，判断原id与新id值是否一致
				Long newId = Long.valueOf(id);
				Long oldId = datalibrary.getId();
				if (oldId.equals(newId)) {
					return false;
				} else {
					return true;
				}
			}
		}
	}

}
