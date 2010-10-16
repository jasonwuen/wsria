package cn.wsria.demo.unit.service.area;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTxTestCase;

import cn.wsria.demo.data.MysqlDbUnitUtils;
import cn.wsria.demo.entity.area.AreaInfo;
import cn.wsria.demo.service.area.AreaInfoService;
import cn.wsria.demo.util.AreaInfoUtil;

@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class AreaInfoServiceTest extends SpringTxTestCase {

	@Autowired AreaInfoService service;
	
	@Before
	public void loadDefaultDatae() throws Exception {
		MysqlDbUnitUtils.loadData(dataSource, "/data/area-data.xml");
	}
	
	@Test
	public void testAll() {
		// 数量统计
		int countRowsInTable = countRowsInTable("wsria_demo_area_info");
		List<AreaInfo> allArea = service.getAllArea();
		assertEquals(allArea.size(), countRowsInTable);
		
		for (AreaInfo areaInfo : allArea) {
			AreaInfoUtil.addArea(areaInfo);
		}
		assertEquals(AreaInfoUtil.getAllArea().size(), countRowsInTable);
		
		// 初始化测试
		service.init();
		int size = AreaInfoUtil.getAllArea().size();
		countRowsInTable = countRowsInTable("wsria_demo_area_info");
		assertEquals(size, countRowsInTable);
		
		assertEquals(3, AreaInfoUtil.getLevelAreas().size());
		
		String areaName = AreaInfoUtil.getFullAreaName(5218l);
		assertEquals("北京市-东城区-朝阳门街道", areaName);
		
		// findByLevel
		List<AreaInfo> areaByTopLevel = service.getAreaByLevel(1);
		assertEquals(34, areaByTopLevel.size());
		
		List<AreaInfo> area = service.getAreaByParent(7356l);
		assertEquals(17, area.size());
		
		// findLayerIds
		Long[] findLayerIds = service.findLayerIds(9252l);
		assertEquals(0, findLayerIds[0].longValue());
		assertEquals(8260, findLayerIds[1].longValue());
		assertEquals(9250, findLayerIds[2].longValue());
		
	}
	
}
