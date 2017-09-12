package com.RBR.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.RBR.model.MetaData;
import com.RBR.model.ReasoningTree;

public class ReasoningTreeDAO extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(MetaData.class);
	protected void initDao() {
		// do nothing
	}
	/**	 保存推理数据
	 * @param metaData
	 */
	public boolean save(ReasoningTree reasoningTree) {
		log.debug("saving ReasoningTree instance");
		try {
			getHibernateTemplate().save(reasoningTree);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
//		Session session = HibernateUtils.getCurrentSession();
//		session.save(metaData);
		
		
		return true;
	}
	
	/**
	 * 保存推理链树
	 * @param treeData
	 * @param usedRules
	 * @return
	 */
	public int save(String treeData,String usedRules) {
		log.debug("saving ReasoningTree instance");
		ReasoningTree rt = new ReasoningTree();
		rt.setTreeData(treeData);
		rt.setUsedRules(usedRules);;
		
		try {
			getHibernateTemplate().save(rt);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
		
		
		return rt.getTreeId();
	}
	
	/**
	 * 通过treeid查找推理树
	 * @param id
	 * @return
	 */
	public ReasoningTree getById(int id){
		log.debug("finding ReasoningTree instance with property: "+id);
//		MetaData metaData = new MetaData();
		try {
			ReasoningTree reasoningTree = (ReasoningTree) getHibernateTemplate().get(ReasoningTree.class, id);
			log.debug("getById successful");
			return reasoningTree;
		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
		
	}
	
	
	public List getByProperty(String propertyName, Object value){
		log.debug("finding ReasoningTree instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ReasoningTree as model where model."
					+ propertyName + "= ?";
			return  getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
		public ReasoningTree merge(ReasoningTree detachedInstance) {
			log.debug("merging ReasoningTree instance");
			try {
				ReasoningTree result = (ReasoningTree) getHibernateTemplate().merge(
						detachedInstance);
				log.debug("merge successful");
				return result;
			} catch (RuntimeException re) {
				log.error("merge failed", re);
				throw re;
			}
		}

		public void attachDirty(ReasoningTree instance) {
			log.debug("attaching dirty ReasoningTree instance");
			try {
				getHibernateTemplate().saveOrUpdate(instance);
				log.debug("attach successful");
			} catch (RuntimeException re) {
				log.error("attach failed", re);
				throw re;
			}
		}

		public void attachClean(ReasoningTree instance) {
			log.debug("attaching clean ReasoningTree instance");
			try {
				getHibernateTemplate().lock(instance, LockMode.NONE);
				log.debug("attach successful");
			} catch (RuntimeException re) {
				log.error("attach failed", re);
				throw re;
			}
		}

		public static ReasoningTreeDAO getFromApplicationContext(
				ApplicationContext ctx) {
			return (ReasoningTreeDAO) ctx.getBean("ReasoningTreeDAO");
		}
}
