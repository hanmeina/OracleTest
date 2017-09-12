package com.RBR.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.RBR.model.Dimension;
import com.RBR.model.Log;
import com.RBR.model.Rules;
import com.RBR.service.RulesService;
import com.wx.dao.WxAuthorityDAO;
import com.wx.model.WxAuthority;

public class LogDAO extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory
			.getLogger(Log.class);
	protected void initDao() {
		// do nothing
	}
	/**
	 * 保存日志
	 * @param l
	 * @return
	 */
	public boolean save(Log l) {
		log.debug("saving Log instance");
		boolean flag=false;
		try {
			getHibernateTemplate().save(l);;
			log.debug("save successful");
			flag=true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return flag;
	}
	
	public Log getById(int id){
		log.debug("finding Log instance with property: "+id);
//		MetaData metaData = new MetaData();
		try {
			Log l = (Log) getHibernateTemplate().get(Log.class, id);
			log.debug("getById successful");
			return l;
		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
	}
	
	/**
	 * 根据使用的规则查询所有的日志
	 * @return
	 */
	public List<Log> getByUsedRules(){
		log.debug("finding all RBR logs which are not null");
//		MetaData metaData = new MetaData();
		try {
			String queryString = "from Log as model where model.reasoningTree.usedRules is not null order by model.id desc";
			log.debug("getById successful");
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
	}
	
	
	public List<Log> getByWxAuthorityProperty(final Set<Integer> wxAuthorities,final int offset ,final int pageSize){
		return (List<Log>) getHibernateTemplate().execute(new HibernateCallback () {

				public List doInHibernate(Session s) throws HibernateException,SQLException {
					return s.createQuery("From Log r Where r.authorityId In (:set) order by r.id asc").setParameterList("set", wxAuthorities).
							setFirstResult(offset)
							.setMaxResults(pageSize).list();
				}
			});
			
		
//		log.debug("finding Log instance with property:value"+wxAuthority);
//		try {
//			String queryString = "from Log as model where model.wxAuthority = ? order by model.id asc";
//			log.debug("getById successful");
//			return findByPage(queryString, wxAuthority, offset, pageSize);
//		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
//			throw re;
//		}
		
	}
	public Long getByWxAuthorityProperty(final Set<Integer> wxAuthorities){
		return (Long)getHibernateTemplate().execute(new HibernateCallback () {
			
			public Object doInHibernate(Session s) throws HibernateException,SQLException {
				return s.createQuery("select count(*) From Log r Where r.authorityId In (:set) order by r.id asc").setParameterList("set", wxAuthorities).
						list().listIterator().next();
			}
		});
		
		
		
	}

	
	
	public List<Log> getBySubSystemDimensionProperty(Object[] subSystem, Set<Integer> wxAuthorities,int offset ,int pageSize){
		log.debug("finding Log instance with property:value"+subSystem);
		try {
			String queryString = "from Log as model where model.subSystem = ? and model.authorityId in (:set) order by model.id asc";
			log.debug("getById successful");
			return findByPage(queryString,wxAuthorities, subSystem, offset, pageSize);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	public Long getByTotalSubSystemDimensionProperty(Object[] subSystem, Set<Integer> wxAuthorities){
//		System.out.println(wxAuthorities.toString());
//		System.out.println(wxAuthorities.toString().substring(1, wxAuthorities.toString().length()-1));
		log.debug("finding Log instance with property:value"+subSystem);
		try {
			String queryString = "select count(*) from Log as model where model.subSystem = ? and model.authorityId in ("+wxAuthorities.toString().substring(1, wxAuthorities.toString().length()-1)+") order by model.id asc";
			log.debug("getById successful");
			return findLogCount(queryString, subSystem);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	public List<Log> getBySubSystemAndOperateDimensionProperty(Object[] subSystemAndOperate,int offset ,int pageSize, Set<Integer> wxAuthorities){
		log.debug("finding Log instance with property:subSystemAndoperate"+subSystemAndOperate);
		try {
			String queryString = "from Log as model where model.subSystem = ? and model.operate = ? and model.authorityId in (:set) order by model.id asc";
			log.debug("getById successful");
			return findByPage(queryString, wxAuthorities,subSystemAndOperate, offset, pageSize);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	public Long getByTotalSubSystemAndOperateDimensionProperty(Object[] subSystemAndOperate, Set<Integer> wxAuthorities){
		log.debug("finding Log instance with property:subSystemAndoperate"+subSystemAndOperate);
		try {
			String queryString = "select count(*) from Log as model where model.subSystem = ? and model.operate = ? and model.authorityId in ("+wxAuthorities.toString().substring(1, wxAuthorities.toString().length()-1)+") order by model.id asc";
			log.debug("getById successful");
			return findLogCount(queryString, subSystemAndOperate);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	public List<Log> getBySubSystemAndOperateSubjectDimensonPrioperty(Object[] subSystemAndOperateSubject,int offset ,int pageSize, Set<Integer> wxAuthorities){
		log.debug("finding Log instance with property:subSystemAndOperateSubject"+subSystemAndOperateSubject);
		try {
			String queryString = "from Log as model where model.subSystem = ? and model.operateSubject = ? and model.authorityId in (:set) order by model.id asc";
			log.debug("getById successful");
			return findByPage(queryString, wxAuthorities,subSystemAndOperateSubject, offset, pageSize);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	public Long getByTotalSubSystemAndOperateSubjectDimensonPrioperty(Object[] subSystemAndOperateSubject, Set<Integer> wxAuthorities){
		log.debug("finding Log instance with property:subSystemAndOperateSubject"+subSystemAndOperateSubject);
		try {
			String queryString = "select count(*) from Log as model where model.subSystem = ? and model.operateSubject = ? and model.authorityId in ("+wxAuthorities.toString().substring(1, wxAuthorities.toString().length()-1)+") order by model.id asc";
			log.debug("getById successful");
			return findLogCount(queryString, subSystemAndOperateSubject);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	public List<Log> getBySubSystemAndOperateAndOperateSubjectDimensionProperty(Object[] subSystemAndOperateAndOperateSubject,int offset ,int pageSize, Set<Integer> wxAuthorities){
		log.debug("finding Log instance with property:subSystemAndOperateAndOperateSubject"+subSystemAndOperateAndOperateSubject);
		try {
			String queryString = "from Log as model where model.subSystem = ? and model.operate = ? and model.operateSubject = ? and model.authorityId in (:set) order by model.id asc";
			log.debug("getById successful");
			return findByPage(queryString, wxAuthorities, subSystemAndOperateAndOperateSubject, offset, pageSize);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	public Long getByTotalSubSystemAndOperateAndOperateSubjectDimensionProperty(Object[] subSystemAndOperateAndOperateSubject, Set<Integer> wxAuthorities){
		log.debug("finding Log instance with property:subSystemAndOperateAndOperateSubject"+subSystemAndOperateAndOperateSubject);
		try {
			String queryString = "select count(*) from Log as model where model.subSystem = ? and model.operate = ? and model.operateSubject = ? and model.authorityId in ("+wxAuthorities.toString().substring(1, wxAuthorities.toString().length()-1)+") order by model.id asc";
			log.debug("getById successful");
			return findLogCount(queryString, subSystemAndOperateAndOperateSubject);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	public List<Log> getByOperateDimensionProperty(Object[] operate,int offset ,int pageSize, Set<Integer> wxAuthorities){
		log.debug("finding Log instance with property:operate"+operate);
		try {
			String queryString = "from Log as model where model.operate = ? and model.authorityId in (:set) order by model.id asc";
			log.debug("getById successful");
			return findByPage(queryString, wxAuthorities, operate, offset, pageSize);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	public Long getByToalOperateDimensionProperty(Object[] operate, Set<Integer> wxAuthorities){
		log.debug("finding Log instance with property:operate"+operate);
		try {
			String queryString = "select count(*) from Log as model where model.operate = ? and model.authorityId in ("+wxAuthorities.toString().substring(1, wxAuthorities.toString().length()-1)+") order by model.id asc";
			log.debug("getById successful");
			return findLogCount(queryString, operate);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
//	public List<Log> getByTwoSubsystemDimensionProperty(Object[] subSystem,int offset ,int pageSize, Set<Integer> wxAuthorities){
//		log.debug("finding Log instance with property:subSystem"+subSystem);
//		try {
//			String queryString = "from Log as model where model.subSystem = ? or model.subSystem = ? and model.authorityId in (:set) order by model.id asc";
//			log.debug("getById successful");
//			return findByPage(queryString, wxAuthorities, subSystem, offset, pageSize);
//		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
//			throw re;
//		}
//		
//	}
//	public Long getByTotalTwoSubsystemDimensionProperty(Object[] subSystem, Set<Integer> wxAuthorities){
//		log.debug("finding Log instance with property:subSystem"+subSystem);
//		try {
//			String queryString = "select count(*) from Log as model where model.subSystem = ? or model.subSystem = ? and model.authorityId in ("+wxAuthorities.toString().substring(1, wxAuthorities.toString().length()-1)+") order by model.id asc";
//			log.debug("getById successful");
//			return findLogCount(queryString, subSystem);
//		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
//			throw re;
//		}
//		
//	}
//	public List<Log> getByThreeSubsystemDimensionProperty(Object[] subSystem,int offset ,int pageSize, Set<Integer> wxAuthorities){
//		log.debug("finding Log instance with property:subSystem"+subSystem);
//		try {
//			String queryString = "from Log as model where model.subSystem = ? or model.subSystem = ? or model.subSystem = ? and model.authorityId in (:set) order by model.id asc";
//			log.debug("getById successful");
//			return findByPage(queryString, wxAuthorities, subSystem, offset, pageSize);
//		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
//			throw re;
//		}
//		
//	}
//	public Long getByTotalThreeSubsystemDimensionProperty(Object[] subSystem, Set<Integer> wxAuthorities){
//		log.debug("finding Log instance with property:subSystem"+subSystem);
//		try {
//			String queryString = "select count(*) from Log as model where model.subSystem = ? or model.subSystem = ? or model.subSystem = ? and model.authorityId in ("+wxAuthorities.toString().substring(1, wxAuthorities.toString().length()-1)+") order by model.id asc";
//			log.debug("getById successful");
//			return findLogCount(queryString, subSystem);
//		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
//			throw re;
//		}
//		
//	}
//	public List<Log> getByFourSubsystemDimensionProperty(Object[] subSystem,int offset ,int pageSize, Set<Integer> wxAuthorities){
//		log.debug("finding Log instance with property:subSystem"+subSystem);
//		try {
//			String queryString = "from Log as model where model.subSystem = ? or model.subSystem = ? or model.subSystem = ? or model.subSystem = ? and model.authorityId in (:set) order by model.id asc";
//			log.debug("getById successful");
//			return findByPage(queryString, wxAuthorities, subSystem, offset, pageSize);
//		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
//			throw re;
//		}
//		
//	}
//	public Long getByTotalFourSubsystemDimensionProperty(Object[] subSystem, Set<Integer> wxAuthorities){
//		log.debug("finding Log instance with property:subSystem"+subSystem);
//		try {
//			String queryString = "select count(*) from Log as model where model.subSystem = ? or model.subSystem = ? or model.subSystem = ? or model.subSystem = ? and model.authorityId in ("+wxAuthorities.toString().substring(1, wxAuthorities.toString().length()-1)+") order by model.id asc";
//			log.debug("getById successful");
//			return findLogCount(queryString, subSystem);
//		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
//			throw re;
//		}
//		
//	}
	public List<Log> getByOperateSubjectDimensionProperty(Object[] operateSubject,int offset ,int pageSize, Set<Integer> wxAuthorities){
		log.debug("finding Log instance with property:operateSubject"+operateSubject);
		try {
			String queryString = "from Log as model where model.operateSubject = ? and model.authorityId in (:set) order by model.id asc";
			log.debug("getById successful");
			return findByPage(queryString,wxAuthorities, operateSubject, offset, pageSize);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	public Long getByTotalOperateSubjectDimensionProperty(Object[] operateSubject, Set<Integer> wxAuthorities){
		log.debug("finding Log instance with property:operateSubject"+operateSubject);
		try {
			String queryString = "select count(*) from Log as model where model.operateSubject = ? and model.authorityId in ("+wxAuthorities.toString().substring(1, wxAuthorities.toString().length()-1)+") order by model.id asc";
			log.debug("getById successful");
			return findLogCount(queryString, operateSubject);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	public List<Log> getByOperateAndOperateSubjectDimensionProperty(Object[] operateAndOperateSubject,int offset ,int pageSize, Set<Integer> wxAuthorities){
		log.debug("finding Log instance with property:operateAndOperateSubject"+operateAndOperateSubject);
		try {
			String queryString = "from Log as model where model.operate = ? and model.operateSubject = ? and model.authorityId in (:set) order by model.id asc";
			log.debug("getById successful");
			return findByPage(queryString,wxAuthorities, operateAndOperateSubject, offset, pageSize);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
		
	}
	public Long getByTotalOperateAndOperateSubjectDimensionProperty(Object[] operateAndOperateSubject, Set<Integer> wxAuthorities){
		log.debug("finding Log instance with property:operateAndOperateSubject"+operateAndOperateSubject);
		try {
			String queryString = "select count(*) from Log as model where model.operate = ? and model.operateSubject = ? and model.authorityId in ("+wxAuthorities.toString().substring(1, wxAuthorities.toString().length()-1)+") order by model.id asc";
			log.debug("getById successful");
			return findLogCount(queryString, operateAndOperateSubject);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
		
	}
	public List findByPage(final String hql,final Set<Integer> wxAuthorities, final Object[] values,
			final int offset, final int pageSize)
		{
			List list = getHibernateTemplate()
				.execute(new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException, SQLException
				{
					Query query = session.createQuery(hql);
					for (int i = 0 ; i < values.length ; i++)
					{
						query.setParameter( i, values[i]);
					}
					
					List result = query.setParameterList("set", wxAuthorities).setFirstResult(offset)
						.setMaxResults(pageSize)
						.list();
					return result;
				}
			});
			return list;
		}
	public Long findLogCount(final String hql, final Object[] values){
		Long count =  getHibernateTemplate().execute(new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException, SQLException
				{
					Query query = session.createQuery(hql);
					for (int i = 0 ; i < values.length ; i++)
					{
						query.setParameter( i, values[i]);
					}
					return query.list().listIterator().next();
//					return query.list().size();
					
				}
			});
		return count;
//		return  getHibernateTemplate().execute(new HibernateCallback () {
//			
//			public Object doInHibernate(Session s) throws HibernateException,SQLException {
//				return s.createQuery("From Log r Where r.authorityId In (:set) order by r.id asc").setParameterList("set", wxAuthorities).
//						list().size();
//			}
//		});
	}
	public List findRBRInferenceInfo(final String hql, final Object[] values){
		List list = getHibernateTemplate()
				.execute(new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException, SQLException
				{
					Query query = session.createQuery(hql);
					for (int i = 0 ; i < values.length ; i++)
					{
						query.setParameter( i, values[i]);
					}
					List result = query.list();
					return result;
				}
			});
		return list;
	}
	
	
	public List<Log> getByProperty(String propertyName, Object value){
		log.debug("finding Log instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Log as model where model."
					+ propertyName + "= ? order by model.id asc";
			return (List<Log>) getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}	
	
//	public Long findLogSize(){
//		String hql = "select count(*) from Log";
//		Long count = (Long) getHibernateTemplate().find(hql).listIterator().next();
//		return count;
//	}
	
	/**
	 * 鏌ヨ鏃ュ織琛ㄤ腑鎵�湁鏃ュ織璁板綍
	 * @return
	 */
	public List<Log> findAll(final int offset , final int pageSize) {
		log.debug("finding all Rules instances");
		List list = getHibernateTemplate()
				.execute(new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException, SQLException
				{
					String hql = "from Log l order by l.id asc";
					Query query = session.createQuery(hql);
					List result = query.setFirstResult(offset)
						.setMaxResults(pageSize)
						.list();
					return result;
				}
			});
			return list;
	}
	
	public Log merge(Log detachedInstance) {
		log.debug("merging Log instance");
		try {
			Log result = (Log) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Log instance) {
		log.debug("attaching dirty Log instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Log instance) {
		log.debug("attaching clean Log instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LogDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LogDAO) ctx.getBean("LogDAO");
	}
//	/**
//	 * 使锟斤拷hql锟斤拷锟斤拷锟叫凤拷页锟斤拷询
//	 * @param hql 锟斤拷要锟斤拷询锟斤拷hql锟斤拷锟�
//	 * @param offset 锟斤拷一锟斤拷锟斤拷录锟斤拷锟斤拷
//	 * @param pageSize 每页锟斤拷要锟斤拷示锟侥硷拷录锟斤拷
//	 * @return 锟斤拷前页锟斤拷锟斤拷锟叫硷拷录
//	 */
//	public List findByPage(final String hql, 
//		final int offset, final int pageSize)
//	{
//		//通锟斤拷一锟斤拷HibernateCallback锟斤拷锟斤拷锟斤拷执锟叫诧拷询
//		List list = getHibernateTemplate()
//			.executeFind(new HibernateCallback()
//		{
//			//实锟斤拷HibernateCallback锟接口憋拷锟斤拷实锟街的凤拷锟斤拷
//			public Object doInHibernate(Session session)
//				throws HibernateException, SQLException
//			{
//				//执锟斤拷Hibernate锟斤拷页锟斤拷询
//				List result = session.createQuery(hql)
//					.setFirstResult(offset)
//					.setMaxResults(pageSize)
//					.list();
//				return result;
//			}
//		});
//		return list;
//	}
//
//	/**
//	 * 使锟斤拷hql锟斤拷锟斤拷锟叫凤拷页锟斤拷询
//	 * @param hql 锟斤拷要锟斤拷询锟斤拷hql锟斤拷锟�
//	 * @param value 锟斤拷锟絟ql锟斤拷一锟斤拷锟斤拷锟斤拷锟斤拷要锟斤拷锟诫，value锟斤拷锟角达拷锟斤拷hql锟斤拷锟侥诧拷锟斤拷
//	 * @param offset 锟斤拷一锟斤拷锟斤拷录锟斤拷锟斤拷
//	 * @param pageSize 每页锟斤拷要锟斤拷示锟侥硷拷录锟斤拷
//	 * @return 锟斤拷前页锟斤拷锟斤拷锟叫硷拷录
//	 */
//	public List findByPage(final String hql , final Object value ,
//		final int offset, final int pageSize)
//	{
//		//通锟斤拷一锟斤拷HibernateCallback锟斤拷锟斤拷锟斤拷执锟叫诧拷询
//		List list = getHibernateTemplate()
//			.executeFind(new HibernateCallback()
//		{
//			//实锟斤拷HibernateCallback锟接口憋拷锟斤拷实锟街的凤拷锟斤拷
//			public Object doInHibernate(Session session)
//				throws HibernateException, SQLException
//			{
//				//执锟斤拷Hibernate锟斤拷页锟斤拷询
//				List result = session.createQuery(hql)
//					//为hql锟斤拷浯拷锟斤拷锟斤拷
//					.setParameter(0, value) 
//					.setFirstResult(offset)
//					.setMaxResults(pageSize)
//					.list();
//				return result;
//			}
//		});
//		return list;
//	}
//
//	/**
//	 * 使锟斤拷hql锟斤拷锟斤拷锟叫凤拷页锟斤拷询
//	 * @param hql 锟斤拷要锟斤拷询锟斤拷hql锟斤拷锟�
//	 * @param values 锟斤拷锟絟ql锟叫讹拷锟斤拷锟斤拷锟斤拷锟斤拷锟揭拷锟斤拷耄瑅alues锟斤拷锟角达拷锟斤拷hql锟侥诧拷锟斤拷锟斤拷锟斤拷
//	 * @param offset 锟斤拷一锟斤拷锟斤拷录锟斤拷锟斤拷
//	 * @param pageSize 每页锟斤拷要锟斤拷示锟侥硷拷录锟斤拷
//	 * @return 锟斤拷前页锟斤拷锟斤拷锟叫硷拷录
//	 */
//	public List findByPage(final String hql, final Object[] values,
//		final int offset, final int pageSize)
//	{
//		//通锟斤拷一锟斤拷HibernateCallback锟斤拷锟斤拷锟斤拷执锟叫诧拷询
//		List list = getHibernateTemplate()
//			.executeFind(new HibernateCallback()
//		{
//			//实锟斤拷HibernateCallback锟接口憋拷锟斤拷实锟街的凤拷锟斤拷
//			public Object doInHibernate(Session session)
//				throws HibernateException, SQLException
//			{
//				//执锟斤拷Hibernate锟斤拷页锟斤拷询
//				Query query = session.createQuery(hql);
//				//为hql锟斤拷浯拷锟斤拷锟斤拷
//				for (int i = 0 ; i < values.length ; i++)
//				{
//					query.setParameter( i, values[i]);
//				}
//				List result = query.setFirstResult(offset)
//					.setMaxResults(pageSize)
//					.list();
//				return result;
//			}
//		});
//		return list;
//	}
	
		public static void main(String[] args) {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			LogDAO ld  = (LogDAO) ctx.getBean("LogDAO");
			DimensionDAO dd  = (DimensionDAO) ctx.getBean("DimensionDAO");
			System.out.println("chenggong");	
	//		Log l = new Log();
	//		l.setName("ty");
	//		l.setContent("杈冩繁瑁傜汗");
	//		l.setOperateTime(new Date());
	//		l.setSubSystem(dd.getById(2));
	//		l.setOperate(dd.getById(4));
	//		l.setOperateSubject(dd.getById(16));
	//		System.out.println(ld.save(l));
	//		Log l = ld.findAll().get(0);
	//		System.out.println(l.getLogId());
	//		System.out.println(l.getSubSystem().getType()+"-->"+l.getSubSystem().getName());
	//		System.out.println(l.getOperate().getType()+"-->"+l.getOperate().getName());
	//		System.out.println(l.getOperateSubject().getType()+"-->"+l.getOperateSubject().getName());
	//		System.out.println(l.getOperateTime());
	//		System.out.println(l.getContent());
	//		Dimension d = new Dimension();
	//		d.setType("瀛愮郴缁�);
	//		d.setName("RBR");
	//		System.out.println(dd.getByName("RBR").get(0));
//			Log log = ld.getBySubSystemDimensionProperty((Dimension)dd.getByName("RBR").get(0)).get(0);
	//		Log log = (Log) ld.getBySession().get(0);
//			System.out.println(log.getOperateTime());
		}	
}
