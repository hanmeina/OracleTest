package com.RBR.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.RBR.model.Condition;
import com.wx.dao.WxAuthorityDAO;
import com.wx.model.WxAuthority;

public class ConditionDAO extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory
			.getLogger(Condition.class);
	protected void initDao() {
		// do nothing
	}
	public void save(Condition condition) {
		log.debug("saving Condition instance");
		try {
			getHibernateTemplate().save(condition);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void updata(Condition condition) {
		
	}
	
	public void delete(int id) {
		
	}
	
//	public Condition getById(int id) {
//		Session session = HibernateUtils.openSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			Condition condition = (Condition)session.get(Condition.class, id);
//			System.out.println(condition);
//			System.out.println(condition.getMetaData());
//			tx.commit();
//			return condition;
//		} catch (RuntimeException e) {
//			tx.rollback();
//			throw e;
//		}	finally {
//			session.close();
//		}
//	}
	
	public List<Condition> findAll() {
		return null;
		
	}
	public Condition merge(Condition detachedInstance) {
		log.debug("merging Condition instance");
		try {
			Condition result = (Condition) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Condition instance) {
		log.debug("attaching dirty Condition instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Condition instance) {
		log.debug("attaching clean Condition instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ConditionDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ConditionDAO) ctx.getBean("ConditionDAO");
	}
}
