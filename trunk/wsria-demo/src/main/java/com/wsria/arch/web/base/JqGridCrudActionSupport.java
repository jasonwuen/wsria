package com.wsria.arch.web.base;

import net.sf.json.JSONObject;

import org.springside.modules.orm.Page;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.wsria.arch.util.ajax.JqGridUtil;
import com.wsria.arch.util.json.JSONUtil;


/**
 * 提供jqGrid列表插件的CRUD操作
 *
 * @author HenryYan
 *
 */
public abstract class JqGridCrudActionSupport<T> extends CrudActionSupport<T> {

	private static final long serialVersionUID = 1L;

	//-- 页面属性 --//
	protected String jqid;
	protected Long id;
	protected T entity;
	protected Page<T> page = new Page<T>();

	//-- ModelDriven 与 Preparable函数 --//

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置实体的ID，如果jqGrid发送来的ID为<code>JqGridUtil.ID_EMPTY</code>则设置实体对象的ID为NULL，否则转换成LONG型
	 * @param jqid	jqGrid发送的实体ID
	 */
	public void setJqid(String jqid) {
		this.jqid = jqid;
		if (JqGridUtil.ID_EMPTY.equals(jqid)) {
			setId(null);
		} else {
			setId(Long.parseLong(jqid));
		}
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	//-- 页面属性访问函数 --//

	/**
	 * list页面显示用户分页列表.
	 */
	public abstract Page<T> getPage();

	//-- Action需要使用的函数 --/
	/**
	 * 读取请求参数解析为JSON数据格式
	 *
	 * @param request
	 * @return json格式的String对象
	 * @throws Exception
	 */
	protected JSONObject readJson() throws Exception {
		return JSONUtil.readJson(Struts2Utils.getRequest());
	}

}
