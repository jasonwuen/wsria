package com.wsria.demo.web.jqgrid;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.wsria.arch.util.orm.PropertyFilterUtils;
import com.wsria.arch.util.string.HtmlUtil;
import com.wsria.arch.web.base.JqGridCrudActionSupport;
import com.wsria.demo.entity.base.DataLibrary;
import com.wsria.demo.service.base.DataLibraryList;
import com.wsria.demo.service.base.DataLibraryManager;
import com.wsria.demo.util.DataLibraryUtil;
import com.wsria.demo.web.CrudActionSupport;

/**
 * 数据字典管理 Action
 * 
 * @author 咖啡兔
 * 
 */
@Controller
@Results( { @Result(name = CrudActionSupport.JSON, type = CrudActionSupport.JSON) })
public class DataLibraryAction extends JqGridCrudActionSupport<DataLibrary> {
	private static final long serialVersionUID = 1L;

	@Autowired protected DataLibraryManager manager;
	@Autowired protected DataLibraryList dataList;

	// -- ModelDriven 与 Preparable函数 --//

	public DataLibrary getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() {
		if (id != null) {
			// 获取单个数据字典
			entity = manager.getDataLibrary(id);
		} else {
			entity = new DataLibrary();
		}
	}

	// -- CRUD Action 函数 --//
	@Override
	public String save() {
		try {
			manager.saveDataLibrary(entity);
		} catch (Exception e) {
			logger.error("保存单个数据字典", e);
		}
		return null;
	}

	@Override
	public String delete() {
		try {
			manager.deleteDataLibrary(id);
		} catch (Exception e) {
			logger.error("删除单个数据字典", e);
		}
		return null;
	}

	@Override
	public String list() {
		try {
			List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
			PropertyFilterUtils.handleFilter(page, DataLibrary.class, filters);
			
			page = manager.searchProperty(page, filters);
		} catch (Exception e) {
			logger.error("数据字典列表 ", e);
		}
		return JSON;
	}

	// 用途名称验证 唯一性
	public String validateLibraryCode() {
		String newId = Struts2Utils.getParameter("newId");
		String libraryCode = Struts2Utils.getParameter("libraryCode");
		if (manager.existLibrarycode(newId, libraryCode)) {
			Struts2Utils.renderText("false");
		} else {
			Struts2Utils.renderText("true");
		}

		return null;
	}

	/**
	 * 根据类型查询数据字典
	 * @return
	 */
	public String findTypes() {
		String type = Struts2Utils.getParameter("type");
		if (StringUtils.isNotEmpty(type)) {
			List<DataLibrary> dataLibraryList = DataLibraryUtil.getTypeGroupDataLibraryList(type);
			Struts2Utils.renderJson(dataLibraryList);
		}
		return null;
	}

	/**
	 * 根据类型查询数据字典
	 * 生成html的select代码
	 */
	public String findTypeForSelect() {
		String type = Struts2Utils.getParameter("type");
		boolean withTip = Boolean.valueOf(Struts2Utils.getParameter("withTip"));
		if (StringUtils.isNotEmpty(type)) {
			List<DataLibrary> dataLibraryList = DataLibraryUtil.getTypeGroupDataLibraryList(type);
			if (dataLibraryList != null && !dataLibraryList.isEmpty()) {
				Map<String, String> parseToJson = DataLibraryUtil.parseToJson(dataLibraryList);
				String select = HtmlUtil.generateSelect(parseToJson, withTip);
				Struts2Utils.renderText(select);
			}
			Struts2Utils.renderText("");
		}
		return null;
	}
	
	/**
	 * 重载数据字典
	 * @return
	 */
	public String reload() {
		try {
			dataList.reload();
			logger.info("重载数据字典成功！");
			Struts2Utils.renderText(SUCCESS);
		} catch (Exception e) {
			logger.error("重载数据字典出错！", e);
			Struts2Utils.renderText("重载失败！");
		}
		return null;
	}

	// -- 页面属性访问函数 --//

	/**
	 * list页面显示用户分页列表.
	 */
	public Page<DataLibrary> getPage() {
		return page;
	}

}
