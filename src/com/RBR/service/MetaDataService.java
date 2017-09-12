package com.RBR.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.RBR.dao.MetaDataDAO;
import com.RBR.model.Conclusion;
import com.RBR.model.Condition;
import com.RBR.model.MetaData;
import com.RBR.model.ReasoningTree;
import com.RBR.modelEncapsulation.MetaDataAndRules;


@Service
public class MetaDataService {
	@Resource
	private MetaDataDAO metaDataDao;
	@Resource
	private LogService logService;
	@Resource
	private DimensionService dimensionService;
	@Resource
	private ReasoningTreeService reasoningTreeService;

	private List<MetaData> treeList = new ArrayList<MetaData>(); // 存放查好顺序的对象
	
	
	public List<MetaData> getTreeList(){
		return treeList;
	}
	/**
	 * 根据id查询一个MetaData对象
	 * @param id
	 * @return
	 */
	public MetaData getMetaDataById(int id) {
		return metaDataDao.getById(id);
	}
	/**
	 * 根据姓名查询元数据集合
	 * @param name
	 * @return
	 */
	public List<MetaData> getMetaDataByName(String name) {
		return metaDataDao.getByName(name);
	}

	/**
	 * 得到元数据树形List
	 * 通过根节点id查询元数据，返回树形的元数据List
	 * @param rootId根节点
	 * @return 元数据List
	 */
	public List<MetaData> getMetaDatasTreeList(int rootId) {
		read(rootId);
		return treeList;
	}

	/**
	 * 通过父id查询所有子节点
	 * @param pid
	 */
	public List<MetaData> read(int pid) {
		List<MetaData> list = metaDataDao.getByParentId(pid); // 查出所有子节点

		if (list.isEmpty() == false) { // 判断子节点是否为空
			for (int i = 0; i < list.size(); i++) { // 循环子节点
				treeList.add(list.get(i)); // 将树形存入列表中
				read(list.get(i).getId()); // 递归调用再次读取
				
			}
		}
		return treeList;
	}
	
	/**
	 * 通过父id查询所有子节点完整信息
	 * @param pid
	 */
	public void readLazyFalse(int pid) {
		List<MetaData> list = metaDataDao.getByParentId(pid); // 查出所有子节点

		if (list.isEmpty() == false) { // 判断子节点是为空
			for (int i = 0; i < list.size(); i++) { // 循环子节点
				treeList.add(list.get(i)); // 将树形存入列表中
				readLazyFalse(list.get(i).getId()); // 递归调用再次读取
			}
		}
	}


	/**
	 * 格式化元数据List
	 * 根据每条元数据对象的level，加上相应的<h>标签，大于6级，都按6级
	 * @param originList，原始List
	 * @return 格式化好的元数据List
	 */
	public List<MetaData> formatMetaDatasTreeList(List<MetaData> list) {
		
		for (int i = 0; i < list.size(); i++) {
			switch (list.get(i).getLevel()) {
			case 1:
				list.get(i).setName("<h1><span>" + list.get(i).getName() + "</span></h1>");
				break;
			case 2:
				list.get(i).setName("<h2><span>" + list.get(i).getName() + "</span></h2>");
				break;
			case 3:
				list.get(i).setName("<h3><span>" + list.get(i).getName() + "</span></h3>");
				break;
			case 4:
				list.get(i).setName("<h4><span>" + list.get(i).getName() + "</span></h4>");
				break;
			case 5:
				list.get(i).setName("<h5><span>" + list.get(i).getName() + "</span></h5>");
				break;
			default:
				list.get(i).setName("<h6><span>" + list.get(i).getName() + "</span></h6>");
				break;
			}
		}
		return list;
	}
	
	/**
	 * 生成展示到页面上的元数据树列表，
	 * 调用read(int), formatMetaDatasTreeList(List), 
	 * 将上面的方法结合
	 * @author Ghy
	 * @param root根节点
	 * @return List<MetaData>格式化好的树形List
	 */
	public List<MetaData> generateTreeArrayListofMetaDatas(int root) {
		treeList.clear();	//执行前先清空我的小树，这很重要，否则就会重复了~~~
		
		read(root); // 遍历数据库中的树，root为0

		List<MetaData> list = new ArrayList<MetaData>();// list来存元数据对象
		list = formatMetaDatasTreeList(treeList);// 格式化树

		// 展现到控制台
		/*for (int i = 0; i < list.size(); i++) {
		System.out.println(list.get(i).getName()); }*/
		 
		return list;
	}
	
	/**
	 * 生成展示到页面上的元数据树列表，
	 * 调用read(int), formatMetaDatasTreeList(List), 
	 * 将上面的方法结合
	 * @author Ghy
	 * @param root根节点
	 * @return List<MetaData>格式化好的树形List
	 */
	public List<MetaData> generateTreeArrayListofMetaDatas(int root,String managerName) {
		treeList.clear();	//执行前先清空我的小树，这很重要，否则就会重复了~~~
		read(root); // 遍历数据库中的树，root为0
		
		List<MetaData> list = new ArrayList<MetaData>();// list来存元数据对象
		list = formatMetaDatasTreeList(treeList);// 格式化树
		
		// 展现到控制台
		/*for (int i = 0; i < list.size(); i++) {
		System.out.println(list.get(i).getName()); }*/
//		logServ.saveLog("查询元数据", 1, "查询元数据", managerName,110);
		return list;
	}
	
	/**
	 * 创建元数据，无返回值
	 * @param pid，父id，鼠标单击条目时得到
	 * @param inputText，元数据内容
	 * @param reliability，元数据可信度
	 * @param managerName，作者
	 */
	public void save(String inputText, String reliability, String managerName,Integer inverseid) {
		MetaData metaData = new MetaData();
		
		metaData.setPid(0);
		metaData.setName(inputText.trim());
		metaData.setLevel(1);
		metaData.setLeaf(true);
		metaData.setReliability(Integer.valueOf(reliability.trim()));
		metaData.setManagerName(managerName);
		metaData.setInverseid(inverseid);
		
		Date date = new Date();
		metaData.setPassTime(date);
		
		int id = metaDataDao.save(metaData);
//		String logtext = inputText + "(" + reliability + ")";
//		logServ.saveLog("新增元数据", id, logtext, managerName,110);
	}
	/**
	 * 创建元数据，无返回值
	 * @param pid，父id，鼠标单击条目时得到
	 * @param inputText，元数据内容
	 * @param reliability，元数据可信度
	 * @param managerName，作者
	 * @param inverseid,相反元数据id
	 * @return
	 */
	public int saveUnderParentId(int pid, String inputText, String reliability, String managerName,int inverseid) {
		MetaData metaData = new MetaData();
		MetaData pMeData = metaDataDao.getById(pid);
		
		metaData.setPid(pid);
		metaData.setName(inputText.trim());
		metaData.setLevel(pMeData.getLevel() + 1);
		metaData.setLeaf(true);
		metaData.setReliability(Integer.valueOf(reliability.trim()));
		metaData.setManagerName(managerName);  //需要用户名
		metaData.setInverseid(inverseid);
		Date date = new Date();
		metaData.setPassTime(date);
		
		int id = metaDataDao.save(metaData, pid);
		String logtext = inputText + "(" + reliability + ")";
		logService.saveLog(managerName, logtext,reasoningTreeService.getReasoningTreeById(0), dimensionService.getDimensionById("3"), dimensionService.getDimensionById("5"), dimensionService.getDimensionById("19"));
         
		return id;
	}
	
	
	/**
	 * 创建元数据，无返回值
	 * @param pid，父id，鼠标单击条目时得到
	 * @param inputText，元数据内容
	 * @param reliability，元数据可信度
	 * @param managerName，作者
	 */
	public void saveUnderParentId(int pid, String inputText, String reliability, String managerName) {
		MetaData metaData = new MetaData();
		MetaData pMeData = metaDataDao.getById(pid);
		
		metaData.setPid(pid);
		metaData.setName(inputText.trim());
		metaData.setLevel(pMeData.getLevel() + 1);
		metaData.setLeaf(true);
		metaData.setReliability(Integer.valueOf(reliability.trim()));
		metaData.setManagerName(managerName);  //需要用户名
		
		Date date = new Date();
		metaData.setPassTime(date);
		
		int id = metaDataDao.save(metaData, pid);
		String logtext = inputText + "(" + reliability + ")";
		logService.saveLog(managerName, logtext,reasoningTreeService.getReasoningTreeById(0), dimensionService.getDimensionById("3"), dimensionService.getDimensionById("5"), dimensionService.getDimensionById("19"));
	}

	/**
	 * 修改元数据/更新元数据
	 * @param id 当前要修改的元数据id
	 * @param inputText 要修改的内容
	 */
	public void updateMetaData(int id, String inputText, String managerName) {
		MetaData metaData = metaDataDao.getById(id);
		String oldText = metaData.getName();
		
		metaData.setName(inputText);
		metaData.setManagerName(managerName);  //需要用户名
		
		Date date = new Date();
		metaData.setPassTime(date);
		
		metaDataDao.update(metaData);
		
		String logText = oldText + " 改为 " + inputText;
		logService.saveLog(managerName, logText,reasoningTreeService.getReasoningTreeById(0), dimensionService.getDimensionById("3"), dimensionService.getDimensionById("6"), dimensionService.getDimensionById("19"));

	}

	/**
	 * 修改元数据/更新元数据(重载)
	 * @param id
	 * @param inputText
	 * @param reliability 可信度
	 */
	public boolean updateMetaData(int id, String inputText, String reliability,String managerName) {
		MetaData metaData = metaDataDao.getById(id);
		String oldText = metaData.getName();
		int oldReliability = metaData.getReliability();
		
		metaData.setName(inputText);
		metaData.setReliability(Integer.valueOf(reliability.trim()));
		metaData.setManagerName(managerName);  //需要用户名
		
		Date date = new Date();
		metaData.setPassTime(date);
		
		boolean flag = metaDataDao.update(metaData);
		String logText = oldText+ "(" + oldReliability + ")" + " 改为 " + inputText + "(" + reliability + ")";
		logService.saveLog(managerName, logText,reasoningTreeService.getReasoningTreeById(0), dimensionService.getDimensionById("3"), dimensionService.getDimensionById("6"), dimensionService.getDimensionById("19"));
		return flag;
	}

	/**
	 * 判断当前元数据是否在规则库中被引用
	 * @param id，当前元数据的id
	 * @return List<Integer>，返回id号的list
	 */
	public List<Integer> isIdInRulesJustMe(int id) {
//		MetaData metaData = metaDataDao.getByIdLazyFalse(id);
		MetaData metaData = metaDataDao.getById(id);
		Set<Integer> rulesIdSet = new TreeSet<Integer>();
		List<Integer> rulesIdList = new ArrayList<Integer>();
	
			System.out.println("metaData.getConditionSet():"+metaData.getConditionSet());
		
		
		if(!(metaData.getConditionSet().isEmpty() && metaData.getConclusionSet().isEmpty())) {
			for(Condition condition : metaData.getConditionSet()) {
				rulesIdSet.add(condition.getRules().getId());
			}
			for(Conclusion conclusion : metaData.getConclusionSet()) {
				rulesIdSet.add(conclusion.getRules().getId());
			}
			rulesIdList.addAll(rulesIdSet);
			return rulesIdList;
		}
		return null;
	}
	
	/**
	 * 判断当前元数据的子孙节点是否有被规则库用到的（不考虑当前节点，仅考虑子孙）
	 * @param id 当前元数据的id
	 * @return Set<MetaData>元数据对象集合
	 */
	public MetaDataAndRules isIdInRules(int id) {
		MetaDataAndRules mAndR = new MetaDataAndRules();
		mAndR.getMetaDatasList().clear();
		mAndR.getRulesIdList().clear();
		Set<Integer> rulesIdSet = new TreeSet<Integer>();
		rulesIdSet.clear();
		List<MetaData> metaDataList = mAndR.getMetaDatasList();
		List<Integer> rulesList = mAndR.getRulesIdList();
		readLazyFalse(id);
		for(MetaData metaData : treeList) {
			if(!(metaData.getConditionSet().isEmpty() && metaData.getConclusionSet().isEmpty())) {
				metaDataList.add(metaData);
				for(Condition condition : metaData.getConditionSet()) {
					rulesIdSet.add(condition.getRules().getId());
				}
				for(Conclusion conclusion : metaData.getConclusionSet()) {
					rulesIdSet.add(conclusion.getRules().getId());
				}
			}
		}
		rulesList.addAll(rulesIdSet);
		System.out.println("isIdInRules:"+rulesList.size());
			
		return mAndR;
	}
	
	
	/**
	 * 增加相反元数据
	 * @param id 当前元数据的id
	 */
	public void addInversedMetaData(int id){
		MetaData mdLater = metaDataDao.getById(id);
		mdLater.setInverseid(id-1);
		metaDataDao.save(mdLater);
		MetaData mdFirst = metaDataDao.getById(id-1);
		mdFirst.setInverseid(id);
		metaDataDao.save(mdFirst);
	}
	
	
	public boolean addInversedMetaData(int previousId,int pid,String inputText, String reliability,String managerName){
		boolean flag = false;
		MetaData mdPrevious = metaDataDao.getById(previousId);
		MetaData md = new MetaData();
		md.setPid(pid);
		md.setName(inputText.trim());
		md.setLevel(mdPrevious.getLevel());
		md.setLeaf(true);
		md.setReliability(Integer.valueOf(reliability.trim()));
		md.setManagerName(managerName);  //��Ҫ�û���
		md.setInverseid(previousId);
		Date date = new Date();
		md.setPassTime(date);
		metaDataDao.save(md);
		mdPrevious.setInverseid(md.getId());
		flag = metaDataDao.update(mdPrevious);
		String logtext = inputText + "(" + reliability + ")";
		logService.saveLog(managerName, logtext,reasoningTreeService.getReasoningTreeById(0), dimensionService.getDimensionById("3"), dimensionService.getDimensionById("5"), dimensionService.getDimensionById("19"));
		return flag;
	}
	
	
	/**
	 * 在数据库中删除当前id下的所有子孙节点
	 * @param id 当前节点id
	 * @param managerName 管理员姓名
	 * @return
	 */
	public boolean deleteChildren(int id, String managerName) {
		boolean flag = false;
		read(id);
		metaDataDao.delete(treeList);
		String lText = metaDataDao.getById(id).getName();
		
		for(MetaData metaData : treeList) {
			String logText = metaData.getName();
			logService.saveLog(managerName, logText,new ReasoningTree(), dimensionService.getDimensionById("3"), dimensionService.getDimensionById("7"), dimensionService.getDimensionById("19"));

		}
		
		flag = metaDataDao.delete(id);
		logService.saveLog(managerName, lText,reasoningTreeService.getReasoningTreeById(0), dimensionService.getDimensionById("3"), dimensionService.getDimensionById("7"), dimensionService.getDimensionById("19"));
		return flag;
	}
//	@Test
//	public void test() {
//		//saveUnderParentId(4, "2����", "100", "liu");
//		System.out.println(getMetaDataById(6));
//		//System.out.println(getMetaDatasTreeList(0));
//		//System.out.println(formatMetaDatasTreeList(treeList));
//		//System.out.println(generateTreeArrayListofMetaDatas(0));
//		//updateMetaData(9, "������", "liu");
//		//System.out.println(justMeIsIdInRules(1));
//		//System.out.println(isIdInRules(1));
//		//deleteChildren(10);
//		/*MetaDataAndRules mAndR = isIdInRules(1);
//		for(MetaData metaData : mAndR.getMetaDatasList()) {
//			System.out.println(metaData);
//		}
//		for(Integer rulesId : mAndR.getRulesIdList()) {
//			System.out.println(rulesId);
//		}*/
//		/*for(Integer rulesId : isIdInRulesJustMe(6)) {
//			System.out.println(rulesId);
//		}*/
//	}
	public static void main(String[] args) {
//		MetaDataService mds = new MetaDataService();
//		List<MetaData> list = mds.read(73);
//		for(MetaData md : list){
//			System.out.println(md.getName());
//		}
//		
//		new MetaDataService().addInversedMetaData(15);
//		System.out.println("�ɹ�");
	}
	
}
