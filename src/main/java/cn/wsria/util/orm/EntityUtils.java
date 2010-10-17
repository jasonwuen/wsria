package cn.wsria.util.orm;

/**
 * 实体工具类
 *
 * @author HenryYan
 *
 */
public class EntityUtils {

	/**
	 * 判断是否为新实体的特征
	 * @param entityId	实体ID
	 * @return
	 */
	public static boolean isNew(Long entityId) {
		return entityId == null || entityId == 0 ? true : false;
	}
	
}
