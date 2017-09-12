package com.RBR.dao;


import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.RBR.model.MetaData;
import com.RBR.service.MetaDataService;


public class MetaDataDAO extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(MetaData.class);
	protected void initDao() {
		// do nothing
	}
	/**	 保存元数据
	 * @param metaData
	 */
	public int save(MetaData metaData) {
		log.debug("saving MetaData instance");
		try {
			getHibernateTemplate().save(metaData);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
//		Session session = HibernateUtils.getCurrentSession();
//		session.save(metaData);
		int id = metaData.getId();
		
		return id;
	}
	
	/**
	 * ������Ԫ��ݣ��޸��丸�ڵ�Ҷ�ӽڵ���Ϣ
	 * @param metaData
	 */
	public int save(MetaData metaData, Integer pid) {
		log.debug("saving MetaData instance,pid");
//		Session session = HibernateUtils.getCurrentSession();
		MetaData pMetaData = getById(pid);
		pMetaData.setLeaf(false);
		HibernateTemplate ht = getHibernateTemplate();
		
		try {
			ht.save(metaData);
			log.debug("save(MetaData metaData, Integer pid) successful");
		} catch (RuntimeException re) {
			log.error("save(MetaData metaData, Integer pid) failed", re);
			throw re;
		}
		try {
			ht.update(pMetaData);
			log.debug("save(MetaData metaData, Integer pid),update successful");
		} catch (RuntimeException re) {
			log.error("save(MetaData metaData, Integer pid),update failed", re);
			throw re;
		}
//		session.save(metaData);

		int id = metaData.getId();
		
		return id;
	}
	
	/**
	 * ����һ��Ԫ���
	 * @param metaData
	 */
	public boolean update(MetaData metaData){
		log.debug("update MetaData instance");
		boolean flag = false;
		try {
			getHibernateTemplate().update(metaData);
			flag = true;
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
		return flag;
	}
	
	/**
	 * 删除元数据
	 * @param id
	 */
	public boolean delete(int id){
		log.debug("delete MetaData instance with property:"+id);
		boolean flag = false;
		try {
			getHibernateTemplate().delete(getById(id));
			flag = true;
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		return flag;
	}
	
	/**
	 * 
	 * @param list
	 */
	public void delete(List<MetaData> list){
		log.debug("delete MetaData instance with List<MetaData>");
		for(MetaData metaData : list) {
			try {
				getHibernateTemplate().delete(metaData);
				log.debug("delete List<MetaData> list successful");
			} catch (RuntimeException re) {
				log.error("delete List<MetaData> list failed", re);
				throw re;
			}
		}
	}
	
	
	/**
	 * 通过id获取元数据
	 * @param id
	 * @return	MetaData
	 */
	public MetaData getById(int id){
		log.debug("finding MetaData instance with property: "+id);
//		MetaData metaData = new MetaData();
		try {
			MetaData metaData = (MetaData) getHibernateTemplate().get(MetaData.class, id);
			log.debug("getById successful");
			return metaData;
		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
		
		
		
	}
	public List<MetaData> getByName(String name){
		log.debug("finding MetaData instance with property: "+name);
//		MetaData metaData = new MetaData();
		try {
			List<MetaData> metaDataList = getByProperty("name", name);
			log.debug("getById successful");
			return metaDataList;
		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
		
		
		
	}
	
	public MetaData getByIdLazyFalse(int id){
		log.debug("finding MetaData LazyFalse instance with property: "+id);
//		MetaData metaData = new MetaData();
		try {
			MetaData metaData = getHibernateTemplate().get(MetaData.class, id);
			Hibernate.initialize(metaData.getConditionSet());
			Hibernate.initialize(metaData.getConclusionSet());
			log.debug("getByIdLazyFalse successful");
			return metaData;
		} catch (RuntimeException re) {
			log.error("getByIdLazyFalse failed", re);
			throw re;
		}
		
	}
	
	
	public List<MetaData> getByProperty(String propertyName, Object value){
		log.debug("finding MetaData instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MetaData as model where model."
					+ propertyName + "= ? order by model.id asc";
			//System.out.println("查询语句："+queryString);
			return (List<MetaData>) getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
//		Session session = HibernateUtils.getCurrentSession();
////		Transaction tx = session.beginTransaction();
//		List<MetaData> list = new ArrayList<MetaData>();
//		list = session.createQuery("from MetaData where pid="+pid+"").list();
//		session.clear();
		
//		return list;
	
	}
	
	/**
	 * 通过父id查询所有子节点
	 * @param pid
	 * @return
	 */	
	public List<MetaData> getByParentId(Object pid){
		return (List<MetaData>) getByProperty("pid", pid);
	}
	/**
	 * ��ݸ�id��ɲ�ѯһ��MetaData����
	 * @param pid
	 * @return
	 */
	public List<MetaData> getByParentIdLazyFalse(Object pid){
		List<MetaData> list = getByProperty("pid", pid);
		for(MetaData metaData : list) {
			Hibernate.initialize(metaData.getConditionSet());
			Hibernate.initialize(metaData.getConclusionSet());
		}
		
		return list;
	}
	
	/**
	 * 查找所有元数据
	 * @return
	 */
	public List<MetaData> findAll() {
		log.debug("finding all MetaData instances");
		try {
			String queryString = "from MetaData as m order by m.id asc ";
			return (List<MetaData>) getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
//		Session session = HibernateUtils.getCurrentSession();
//		List<MetaData> list = new ArrayList<MetaData>();
//		list = session.createQuery("from MetaData m order by m.id asc").list();
//		session.clear();
//		
//		return list;
	}
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

	public static MetaDataDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MetaDataDAO) ctx.getBean("MetaDataDAO");
	}
/*	@SuppressWarnings("unchecked")
	public List<MetaData> findAll() {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<MetaData> list = new ArrayList<MetaData>();
			list = session.createQuery("from MetaData").list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		main
	}
*/
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		MetaDataService metaDataService  = (MetaDataService) ctx.getBean("metaDataService");
		System.out.println("chenggong");
//		System.out.println(ms.read(0));
//		String inputText = "增大压强";
//		String trendDescribeWord = "增大"; 
//		List<MetaData> meteDataLlist = metaDataService.getMetaDataByName(trendDescribeWord);
//		String inverseInputText = inputText.replace(trendDescribeWord, metaDataService.getMetaDataById(meteDataLlist.get(0).getInverseid()).getName());
//		System.out.println("inverseInputText:"+inverseInputText);
		metaDataService.readLazyFalse(9);
		System.out.println(metaDataService.getTreeList());
	}
}
