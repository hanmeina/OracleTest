package com.wx.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wx.model.WxAuthority;

/**
 * A data access object (DAO) providing persistence and search support for
 * WxAuthority entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wx.model.WxAuthority
 * @author MyEclipse Persistence Tools
 */

public class WxAuthorityDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(WxAuthorityDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String MENUURL = "menuurl";

	protected void initDao() {
		// do nothing
	}

	public Long getChildSize(String pid){
		String hql = "select count(*) from WxAuthority a where a.wxAuthority.authorityid = '"+pid+"'";
		Long count = (Long)getHibernateTemplate().find(hql).listIterator().next();
		return count;
	}
	
	public List getAllParentAuthority(){
		String hql = "from WxAuthority a where a.wxAuthority.authorityid is null order by a.authorityid";
		return getHibernateTemplate().find(hql);
	}
	
	public void save(WxAuthority transientInstance) {
		log.debug("saving WxAuthority instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(WxAuthority persistentInstance) {
		log.debug("deleting WxAuthority instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WxAuthority findById(java.lang.Integer id) {
		log.debug("getting WxAuthority instance with id: " + id);
		try {
			WxAuthority instance = (WxAuthority) getHibernateTemplate().get(
					"com.wx.model.WxAuthority", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(WxAuthority instance) {
		log.debug("finding WxAuthority instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding WxAuthority instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from WxAuthority as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByMenuurl(Object menuurl) {
		return findByProperty(MENUURL, menuurl);
	}

	public List findAll() {
		log.debug("finding all WxAuthority instances");
		try {
			String queryString = "from WxAuthority";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public WxAuthority merge(WxAuthority detachedInstance) {
		log.debug("merging WxAuthority instance");
		try {
			WxAuthority result = (WxAuthority) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(WxAuthority instance) {
		log.debug("attaching dirty WxAuthority instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(WxAuthority instance) {
		log.debug("attaching clean WxAuthority instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static WxAuthorityDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (WxAuthorityDAO) ctx.getBean("WxAuthorityDAO");
	}
}