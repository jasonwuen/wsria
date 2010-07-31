package cn.wsria.demo.functional.jstree;

import org.junit.Test;
import org.openqa.selenium.By;
import org.springside.modules.test.groups.Groups;

import cn.wsria.demo.functional.BaseFunctionalTestCase;

public class CityManagerTest extends BaseFunctionalTestCase {

	/**
	 * 检验OverViewPage.
	 */
	@Test
	@Groups(BaseFunctionalTestCase.DAILY)
	public void overviewPage() {
		driver.findElement(By.xpath("//a[@id='getChildCity']")).click();
	}
	
}
