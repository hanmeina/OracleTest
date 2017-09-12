package com.wx.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wx.model.WxUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * WxUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wx.model.WxUser
 * @author MyEclipse Persistence Tools
 */

public class WxUserDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(WxUserDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String PWD = "pwd";

	protected void initDao() {
		// do nothing
	}

	public void modify(WxUser user){
		getHibernateTemplate().update(user);
	}
	
	public String findRoleByName(String username){
		//查询部分属性
		String hql = "select u.wxRole.rolename from WxUser u where u.username = ? ";
		Object[] param = new Object[]{username};
		//Object[] result = (Object[]) getHibernateTemplate().find(hql, param).get(0);
		//只查询一列时不需转为Object[]
		return (String) getHibernateTemplate().find(hql, param).get(0);
	}
	
	//通过username查询userid
	public String findIdByName(String username){
		//查询部分属性
		System.out.println("username:"+username);
		String hql = "select userid from WxUser u where u.username = ?";
		System.out.println(hql);
		Object[] param = new Object[]{username};
		//Object[] result = (Object[]) getHibernateTemplate().find(hql, param).get(0);
		//查询部分属性
		return (String) getHibernateTemplate().find(hql, param).get(0);
	}
	
	public void save(WxUser transientInstance) {
		log.debug("saving WxUser instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(WxUser persistentInstance) {
		log.debug("deleting WxUser instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WxUser findById(java.lang.String id) {
		log.debug("getting WxUser instance with id: " + id);
		try {
			WxUser instance = (WxUser) getHibernateTemplate().get(
					"com.wx.model.WxUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(WxUser instance) {
		log.debug("finding WxUser instance by example");
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
		log.debug("finding WxUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from WxUser as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByPwd(Object pwd) {
		return findByProperty(PWD, pwd);
	}

	public List findAll() {
		log.debug("finding all WxUser instances");
		try {
			String queryString = "from WxUser";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public WxUser merge(WxUser detachedInstance) {
		log.debug("merging WxUser instance");
		try {
			WxUser result = (WxUser) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(WxUser instance) {
		log.debug("attaching dirty WxUser instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(WxUser instance) {
		log.debug("attaching clean WxUser instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static WxUserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (WxUserDAO) ctx.getBean("WxUserDAO");
	}
}