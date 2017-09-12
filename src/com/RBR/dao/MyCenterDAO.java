package com.RBR.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.RBR.model.MetaData;
import com.RBR.service.RulesService;

public class MyCenterDAO extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(MetaData.class);
	protected void initDao() {
		// do nothing
	}
	
	
	public List getMyMetaData(String managerName){
		log.debug("finding MetaData instance with property: " + managerName
				);
		try {
			String queryString = "from MetaData as model where model.managerName = ? order by model.id asc";
			return (List) getHibernateTemplate().find(queryString, managerName);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List getMyPassedRules(String managerName){
		return getByRulesProperty("userName", managerName,RulesService.PASSED);
	}
	public List getMyNoPassRules(String managerName){
		return getByRulesProperty("userName", managerName,RulesService.NOTPASS);
	}
	public List getMySleepRules(String managerName){
		return getByRulesProperty("userName", managerName,RulesService.SLEEP);
	}
	public List getMyPendingRules(String managerName){
		return getByRulesProperty("userName", managerName,RulesService.PENDING);
	}
	public List getAllMyRules(String managerName){
		return getByRulesProperty("userName", managerName);
	}
	public List getByRulesProperty(String propertyName, Object value ,Object state){
		log.debug("finding Rules instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Rules as model where model."
					+ propertyName + "= ? and model.state = "+state+" order by model.id asc";
			return (List) getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List getByRulesProperty(String propertyName, Object value){
		log.debug("finding Rules instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Rules as model where model."
					+ propertyName + " = ? order by model.id asc";
			return (List) getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
//	public List getByInferenceInfoProperty(String propertyName, Object value){
//		log.debug("finding Log instance with property: " + propertyName
//				+ ", value: " + value);
//		try {
//			String queryString = "from Log as model where model."
//					+ propertyName + " = ? order by model.id asc";
//			return (List) getHibernateTemplate().find(queryString, value);
//		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
//			throw re;
//		}
//	}
	public MetaData merge(MetaData detachedInstance) {
		log.debug("merging MetaData instance");
		try {
			MetaData result = (MetaData) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MetaData instance) {
		log.debug("attaching dirty MetaData instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MetaData instance) {
		log.debug("attaching clean MetaData instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MyCenterDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MyCenterDAO) ctx.getBean("MyCenterDAO");
	}
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		MyCenterDAO md  = (MyCenterDAO) ctx.getBean("MyCenterDAO");
		System.out.println(md.getMyPassedRules("userName").size());;
	}
}
