package com.RBR.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.RBR.model.Conclusion;
import com.RBR.model.Condition;
import com.RBR.model.MetaData;
import com.RBR.model.Rules;
import com.RBR.service.MetaDataService;
import com.RBR.service.RulesService;

public class RulesDAO extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(Rules.class);
	protected void initDao() {
		// do nothing
	}

	/**
	 *  在规则表中保存一条规则
	 * @param rules
	 */
	public int save(Rules rules) {
		log.debug("saving Rules instance");
		try {
			getHibernateTemplate().save(rules);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return rules.getId();
	}

	
	/**
	 *  在规则表中保存一条规则,并保存前件与后件
	 * @param rules
	 * @param right 
	 */
	public int save(Rules rules, Map<Integer, Integer> leftMap, Set<Integer> right) {
//		log.debug("saving Rules instance");
//		HibernateTemplate ht = getHibernateTemplate();
//		try {
//			getHibernateTemplate().save(rules);
//			log.debug("save successful");
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//		Session session = HibernateUtils.getCurrentSession();
//		session.save(rules);
		
		for (Entry<Integer, Integer> map : leftMap.entrySet()) {
			Condition condition = new Condition();
			MetaData metaData = (MetaData) getHibernateTemplate().load(MetaData.class, map.getKey());
			condition.setMetaData(metaData);
			condition.setWeight(map.getValue());
			condition.setRules(rules);
			getHibernateTemplate().save(condition);
		}
		
		for (Integer integer : right) {
			Conclusion conclusion = new Conclusion();
			MetaData metaData = (MetaData) getHibernateTemplate().load(MetaData.class, integer);
			conclusion.setMetaData(metaData);
			conclusion.setRules(rules);
			getHibernateTemplate().save(conclusion);
		}
//		int id = rules.getId();
		return rules.getId();
	}
	
	/**
	 *  在规则表中删去一条规则
	 * @param rules
	 */
	public boolean delete(Rules rule) {
		log.debug("delete MetaData instance with property:"+rule.toString());
		boolean flag = false;
		try {
			getHibernateTemplate().delete(rule);
			flag = true;
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		return flag;
//		Session session = HibernateUtils.getCurrentSession();
//		Rules rules = (Rules) session.get(Rules.class, id);
//		session.delete(rules);
	}
	
	/**
	 *  在规则表中更新一条规则
	 * @param rules
	 */
	public boolean upDateState (Rules rules) {
		log.debug("update Rules instance");
		boolean flag = false;
		try {
			getHibernateTemplate().update(rules);
			flag = true;
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
		return flag;
	}
	
	/**
	 *  在规则表中更新一条规则
	 * @param rules
	 */
	public boolean upDate (Rules rules) {
		log.debug("update Rules instance");
		boolean flag = false;
		try {
			getHibernateTemplate().update(rules);
			flag = true;
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
		return flag;
	}
	
	/**
	 * 通过id在规则表中查询一条规则
	 * @param id
	 * @return rules对象
	 */
	public Rules getById (int id) {
		log.debug("finding Rules instance with property: "+id);
		try {
			Rules rules = (Rules) getHibernateTemplate().get(Rules.class, id);
			log.debug("getById successful");
			return rules;
		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
//		Session session = HibernateUtils.getCurrentSession();
////		Transaction tx = session.beginTransaction();
//		Rules rules = (Rules) session.get(Rules.class, id);
//
//		return rules;
	}
	public List<Rules> getByFastSort(){
		log.debug("finding Rules instance with property: state in(1,4) and sort = 1");
		try {
			String queryString = "from Rules as model where model.state in ("+RulesService.PASSED+","+RulesService.USEDRULE+") and model.sort=1 order by model.id asc";
			return (List<Rules>) getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Rules> getByProperty(String propertyName, Object value){
		log.debug("finding Rules instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Rules as model where model."
					+ propertyName + "= ? order by model.id asc";
			return (List<Rules>) getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}	

	/**
	 * 根据状态查询规则
	 * @param state
	 * @return 给定状态的规则
	 */
	public List<Rules> getByState(Integer state) {
		return (List<Rules>) getByProperty("state", state);
//		Session session = HibernateUtils.getCurrentSession();
////		Transaction tx = session.beginTransaction();
//		List<Rules> list= session.createQuery("From Rules r Where r.state = " + state).list();
//		return list;
	}
	
	public List<Rules> getByListProperty(String propertyName, Object ...values) {
		log.debug("finding Rules instance with property: " + propertyName
				+ ", value: " + values);
		
		try {
			String queryString = "from Rules as model where model."
					+ propertyName +" = ? or model."+propertyName+" = ? order by model.id asc";
			return (List<Rules>) getHibernateTemplate().find(queryString, values);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
//		return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Rules>>() {
//
//			public List<Rules> doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				// TODO Auto-generated method stub
//				return session.createQuery(hql).setParameterList("set", set).list();
//			}
//
//			
//		});
	}
	/**
	 * 根据前件查规则
	 * @param set
	 * @return 前件相同的规则list
	 */
//	public List<Rules> getByCondition(Set<Conclusion> set) {
//		String hql = "From Rules r Where r.conditionSet In (:set) order by r.id asc";
//		return (List<Rules>) getByListProperty(hql,set);
////		List<Rules> list = new ArrayList<Rules>();
////		for(Conclusion c : set){
////			
////		}
////		Session session = HibernateUtils.getCurrentSession();
////		List<Rules> list= session.createQuery("From Rules r Where r.conditionSet In (:set) order by r.id asc")
////			.setParameterList("set", set)
////			.list();
////		return list;
//	}
	
	/**
	 * 根据后件查规则
	 * @param set
	 * @return 后件相同的规则list
	 */
	public List<Rules> getByConclusion(Set<Conclusion> set) {
		return (List<Rules>) getByListProperty("conclusionSet",set);
//		Session session = HibernateUtils.getCurrentSession();
//		List<Rules> list= session.createQuery("From Rules r Where r.conclusionSet = " + set).list();
//		return list;
	}
	/**
	 *根据规则不同状态获取规则
	 *@param set状态集合
	 *@return 规则
	 */
	public List<Rules> getPassedRules(final Set<Integer> set){
		return (List<Rules>) getHibernateTemplate().execute(new HibernateCallback () {

			public List doInHibernate(Session s) throws HibernateException,SQLException {
				return s.createQuery("From Rules r Where r.state In (:set) order by r.id asc").setParameterList("set", set).list();

			}

			
			
		});
		
	}
	/**
	 * 查询规则表中所有规则
	 * @return
	 */
	public List<Rules> findAll() {
		log.debug("finding all Rules instances");
		try {
			String queryString = "from Rules r order by r.id asc";
			return (List<Rules>) getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
//		Session session = HibernateUtils.getCurrentSession();
//		List<Rules> list = session.createQuery("FROM Rules r order by r.id asc")
//				//.setCacheable(true)
//				.list();
//
//		return list;
	}
	
	public Rules merge(Rules detachedInstance) {
		log.debug("merging Rules instance");
		try {
			Rules result = (Rules) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Rules instance) {
		log.debug("attaching dirty Rules instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Rules instance) {
		log.debug("attaching clean Rules instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RulesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RulesDAO) ctx.getBean("RulesDAO");
	}
//	/**
//	 * ��ҳ��ѯ����������й���
//	 * @param firstResult һҳ��ʼ�����
//	 * @param mixResults һҳ������
//	 * @return Rules List
//	 */
//	@SuppressWarnings("unchecked")
//	public List<Rules> findAll(int firstResult, int mixResults) {
//	
//		Session session = HibernateUtils.getCurrentSession();
//		List<Rules> list = session.createQuery("FROM Rules r order by r.id asc")
//				.setFirstResult(firstResult)
//				.setMaxResults(mixResults)
//				.list();
//
//		return list;
//	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		RulesService rs  = (RulesService) ctx.getBean("rulesService");
		System.out.println("chenggong");
//		System.out.println(rs.findSomeRules());
//		Set<Integer> set = new HashSet<Integer>();
//		set.add(RulesService.PASSED);
//		set.add(RulesService.USEDRULE);
//		System.out.println(ms.getPassedRules(set));
	}
}
