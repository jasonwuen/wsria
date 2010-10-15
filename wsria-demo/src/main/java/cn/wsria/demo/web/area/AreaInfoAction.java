package cn.wsria.demo.web.area;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cn.wsria.demo.entity.area.AreaInfo;
import cn.wsria.demo.service.area.AreaInfoService;
import cn.wsria.demo.web.CrudActionSupport;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 地区信息Action
 *
 * @author HenryYan
 *
 */
@Controller
@Result(name = CrudActionSupport.JSON, type = CrudActionSupport.JSON)
public class AreaInfoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(AreaInfoAction.class);

	@Autowired AreaInfoService areaService;
	
	private Integer level;
	private Long parentId;
	private Long childId;
	
	/**
	 * 根据级别、父ID信息输出JSON格式的地区信息
	 * @return
	 */
	public String findArea() {
		List<AreaInfo> areas = null;
		// 第一级
		if (level != null && level == 1) {
			areas = areaService.getAreaByLevel(level);
			logger.debug("获取第一级地区，个数：{}", areas.size());
		} else if (parentId != null) {
			// 子级别
			areas = areaService.getAreaByParent(parentId);
			logger.debug("获取父级：{}，地区，个数：{}", parentId, areas.size());
		} else {
			areas = new ArrayList<AreaInfo>();
		}
		Struts2Utils.renderJson(areas);
		return null;
	}
	
	/**
	 * 生成完整的带默认值的select元素HTML代码
	 */
	public String generateSelectHtmlCode() {
		try {
			String htmlCode = areaService.generateSelectHtmlCode(childId);
			Struts2Utils.renderHtml(htmlCode);
		} catch (Exception e) {
			logger.error("生成完整的带默认值的select元素HTML代码：", e);
		}
		return null;
	}
	
	/* getters and setters */
	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}
	
}
