package com.wx.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wx.model.WxUrl;

/**
 * A data access object (DAO) providing persistence and search support for WxUrl
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.wx.model.WxUrl
 * @author MyEclipse Persistence Tools
 */

public class WxUrlDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(WxUrlDAO.class);
	// property constants
	public static final String URL = "url";

	protected void initDao() {
		// do nothing
	}

	public void save(WxUrl transientInstance) {
		log.debug("saving WxUrl instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(WxUrl persistentInstance) {
		log.debug("deleting WxUrl instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WxUrl findById(java.lang.Integer id) {
		log.debug("getting WxUrl instance with id: " + id);
		try {
			WxUrl instance = (WxUrl) getHibernateTemplate().get(
					"com.wx.model.WxUrl", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(WxUrl instance) {
		log.debug("finding WxUrl instance by example");
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
		log.debug("finding WxUrl instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from WxUrl as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all WxUrl instances");
		try {
			String queryString = "from WxUrl";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public WxUrl merge(WxUrl detachedInstance) {
		log.debug("merging WxUrl instance");
		try {
			WxUrl result = (WxUrl) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(WxUrl instance) {
		log.debug("attaching dirty WxUrl instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(WxUrl instance) {
		log.debug("attaching clean WxUrl instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static WxUrlDAO getFromApplicationContext(ApplicationContext ctx) {
		return (WxUrlDAO) ctx.getBean("WxUrlDAO");
	}
}