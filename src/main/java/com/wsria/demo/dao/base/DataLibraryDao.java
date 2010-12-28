package com.wsria.demo.dao.base;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.wsria.demo.entity.base.DataLibrary;

/**
 * 数据字典 对象泛型Dao
 * 
 * @author 咖啡兔
 * 
 */
@Component
public class DataLibraryDao extends HibernateDao<DataLibrary, Long> {

}
