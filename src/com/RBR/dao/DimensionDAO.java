package com.RBR.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.RBR.model.Condition;
import com.RBR.model.Dimension;
import com.RBR.model.Rules;
import com.RBR.service.RulesService;
import com.wx.dao.WxAuthorityDAO;
import com.wx.model.WxAuthority;

public class DimensionDAO extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory
			.getLogger(Condition.class);
	protected void initDao() {
		// do nothing
	}
	public boolean save(Dimension dimension) {
		log.debug("saving Condition instance");
		boolean flag = false;
		try {
			getHibernateTemplate().save(dimension);
			flag = true;
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return flag;
	}
	/**
	 * 根据id查询一个Dimension对象
	 * @param id
	 * @return
	 */
	public Dimension getById(String id){
		log.debug("finding Dimension instance with property: "+id);
		try {
			Dimension dimension = (Dimension) getHibernateTemplate().get(Dimension.class, id);
			log.debug("getById successful");
			return dimension;
		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
	}
	
	public List getByName(String name){
		return getByProperty("name", name);
	}
	
	public List getByProperty(String propertyName, Object value){
		log.debug("finding Dimension instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Dimension as model where model."
					+ propertyName + "= ?";
			return (List) getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	
	public List<Dimension> findAll() {
		return null;
		
	}
	public Dimension merge(Dimension detachedInstance) {
		log.debug("merging Dimension instance");
		try {
			Dimension result = (Dimension) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Dimension instance) {
		log.debug("attaching dirty Dimension instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dimension instance) {
		log.debug("attaching clean Dimension instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DimensionDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DimensionDAO) ctx.getBean("DimensionDAO");
	}
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		DimensionDAO dd  = (DimensionDAO) ctx.getBean("DimensionDAO");
		System.out.println("chenggong");
		System.out.println(dd.getByName("RBR").get(0));
	}
}
