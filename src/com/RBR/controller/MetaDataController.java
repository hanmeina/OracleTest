package com.RBR.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.RBR.model.MetaData;
import com.RBR.model.ReasoningTree;
import com.RBR.model.Rules;
import com.RBR.modelEncapsulation.MetaDataAndRules;
import com.RBR.service.DimensionService;
import com.RBR.service.LogService;
import com.RBR.service.MetaDataService;
import com.RBR.service.ReasoningTreeService;
import com.RBR.service.RulesService;




/**
 * 元数据管理 与页面的交互
 * 
 * @author Gao Haoyang 高浩阳2014/12
 * 
 */
// @Controller:标记此类是一个Handler处理器
//@RequestMapping:指定该方法对应的url链接
@Controller
@RequestMapping(value = "/metaData")
public class MetaDataController {
	@Resource
	private MetaDataService metaDataService;
	@Resource
	private RulesService rulesService;
//	@Resource
//	private LogService logService;
	@Resource
	private ReasoningTreeService reasoningTreeService;
	@Resource
	private DimensionService dimensionService;
	/**
	 * 点击大标题的时候进入的元数据Tab页面
	 * @return
	 */
	@RequestMapping(value = "/tab")
	public String metaDataTab(){
		return "/RBR/metaData/tab";
	}

	/**
	 * 查询数据到页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryMetaData.action")
	public String metaDataQuery(Model model) {


		// 通过model将信息显示到页面
		// 将树展示出来
		model.addAttribute("metaDataList",
				metaDataService.generateTreeArrayListofMetaDatas(0));
		return "/RBR/metaData/metaDataQuery";
	}
	

	
//	@RequestMapping(value = "/addPage.action")
//	public String metaDataAddPage(Model model) {
//		model.addAttribute("metaDataList",
//				metaDataService.generateTreeArrayListofMetaDatas(0));
//		model.addAttribute("scrollY", 0);
//		return "/RBR/metaData/metaDataAdd";
//	}
	
	/**
	 * @param model
	 * @param scrollY 得到滚动条位置，载入时直接到这里
	 * @return
	 */
	@RequestMapping(value = "/add.action")
	public String metaDataAdd(Model model,String scrollY) {
		model.addAttribute("metaDataList",
				metaDataService.generateTreeArrayListofMetaDatas(0));
		model.addAttribute("scrollY", scrollY);
//		model.addAttribute("trendDescribeWord", trendDescribeWord);
		return "/RBR/metaData/metaDataAdd";
	}

	/**
	 * 增加元数据2 提交表单时调用POST方法
	 * @param pid 父id
	 * @param inputText 用户输入的内容
	 * @param reliability 可信度
	 * @param scrollY 滚动条位置
	 * @return
	 */
	@RequestMapping(value = "/addMetaData.action" ,method = RequestMethod.POST)
	public String metaDataAdd(HttpServletRequest request,String pid,String inputText, String reliability, String scrollY,RedirectAttributes model) {
		System.out.println(scrollY+"==========");
		System.out.println("添加元数据的POST方法，inputText=" + inputText + ", pid="
				+ pid);
		// 页面上的参数已经成功传入, 对于inputText已经在客户端验证过了
		String trendDescribeWord = "";
		String inverseInputText = "";
		System.out.println("request.getAttribut:"+request.getAttribute("username"));
		String managerNameString = "admin";//这里需要在建一张用户表，直接user.getName()，这样得到当前用户名！【】【】【】【待解决】
		int id = metaDataService.saveUnderParentId(Integer.valueOf(pid), inputText, reliability, managerNameString,0);// 调用service中的方法，存入数据库
		if(!"6".equals(pid)){
			List<MetaData> list = metaDataService.read(6);//趋向性描述词id	
			for(MetaData md : list){
				if(inputText.contains(md.getName())){
					trendDescribeWord = md.getName();
				  System.out.println("trendDescribeWord:"+trendDescribeWord);
				}
				
			}
			if(!"".equals(trendDescribeWord)){
				List<MetaData> meteDataLlist = metaDataService.getMetaDataByName(trendDescribeWord);
				System.out.println("meteDataLlist:"+meteDataLlist);
				if(!meteDataLlist.isEmpty()){
					
					inverseInputText = inputText.replace(trendDescribeWord, metaDataService.getMetaDataById(meteDataLlist.get(0).getInverseid()).getName());
				}
//				metaDataService.addInversedMetaData(id);
				metaDataService.addInversedMetaData(id, Integer.valueOf(pid), inverseInputText, reliability, managerNameString);
			}
		}
		if(id!=0){
			
			System.out.println("inverseInputText:"+inverseInputText);
//		model.addAttribute("inverseInputText", inverseInputText);
			model.addAttribute("scrollY", scrollY);
//		model.addAttribute("trendDescribeWord", trendDescribeWord);
			return "redirect:/metaData/add.action";// 重定向
		}else{
			return "../RBR/error";
		}
	}

	/**
	 * 元数据修改1 进入页面GET方法，不包含提交表单
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/change.action")
	public String metaDataChange(Model model) {
		model.addAttribute("metaDataList",
				metaDataService.generateTreeArrayListofMetaDatas(0));
		return "/RBR/metaData/metaDataChange";
	}

	/**
	 * 元数据修改2 提交表单时调用POST方法（新加入修改检查，本方法弃用）
	 * 
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value = "/change", method = RequestMethod.POST)
	public String metaDataChange(String inputText, String id) {
		System.out
				.println("�޸�Ԫ��ݵ�POST������inputText=" + inputText + ", id=" + id);
		metaDataService.updateName(Integer.valueOf(id), inputText);// ������ݿ�
		return "redirect:/metaData/change";// �ض���
	}*/

	/**
	 * 修改时的检查
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/changeCheck.action")
	public String metaDataChangeCheck(String id,Model model){
		System.out.println("修改检查传入的id："+id);
		int idForCheck = Integer.valueOf(id);
		
		//当前数据是否被引用
				//是
					//传入一个标记位到页面flag=1，表示被引用
					//将当前这个对象传递到页面，在页面获取规则
				//否
					//传递标记位0，表示未被引用
				//将当前对象传递到页面，input中预填写相应内容
		List<Integer> thisDataUsedInRulesIdList = metaDataService.isIdInRulesJustMe(idForCheck);//得到被使用的id组
		System.out.println("thisDataUsedInRulesIdList-->"+thisDataUsedInRulesIdList);
		
		if (thisDataUsedInRulesIdList!=null) {
			System.out.println("第0个元素"+thisDataUsedInRulesIdList.get(0));
			System.out.println("getgetRuleById-->"+rulesService.getRuleById(1));
			
			System.out.println("不为空");
			List<Rules> rulesList = new ArrayList<Rules>();
			rulesList.clear();
			for(int i = 0; i < thisDataUsedInRulesIdList.size();i++){//遍历规则id，得到规则对象组
				System.out.println(thisDataUsedInRulesIdList.get(i));
				
				rulesList.add(rulesService.getRuleById(thisDataUsedInRulesIdList.get(i)));
			}
			
			model.addAttribute("isUsed", 1);					//传标记位到页面
			model.addAttribute("rulesListThisData", rulesList);		//将当前元数据关联的规则list传入页面
		} else {
			System.out.println("为空");
			model.addAttribute("isUsed", 0);					//传标记位到页面
		}
		
		model.addAttribute("metaDataNormal", metaDataService.getMetaDataById(idForCheck));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//创建日期格式化
		model.addAttribute("metaDataDataNow", dateFormat.format(new Date()));//将日期传到页面
		
		return "/RBR/metaData/metaDataChangeCheck";
	}
	
	/**
	 * 修改提交
	 * @param id
	 * @param metaDataName
	 * @param metaDataReliability
	 * @param metaDataManagerName
	 * @param metaDataDate
	 * @return
	 */
	@RequestMapping(value = "/changeCheckPost.action", method = RequestMethod.POST )
	public String metaDataChangeCheck(String id, String metaDataName, String metaDataReliability, String metaDataManagerName, String metaDataDate){
		boolean flag = metaDataService.updateMetaData(Integer.parseInt(id), metaDataName, metaDataReliability, metaDataManagerName);//metaDataDate��service����棬�����Ҫת��ʽ�Ƚ��鷳
		if(flag){
			
			return "/RBR/metaData/metaDataChange";//页面用的无刷新表单提交，这里的指向已经没有意义
		}else{
			return "../RBR/error";
		}	
	}

	/**
	 * 删除的交互逻辑是：
	 * 载入删除界面
	 * 点击条目
	 * id传给后台
	 * 后台判断是否被规则库引用
	 * 		否：
	 * 			跳转删除确认页面，确认删除
	 * 		是：
	 * 			跳转到删除警告页面
	 * 			给出一个集合，集合中包含当前节点及所有子孙被引用的情况
	 * 			在页面中呈现所有相关规则
	 * 			禁止删除当前元数据，先去规则库中删除规则
	 * 
	 */
		
		/**
		 * 元数据删除 进入页面GET方法，不包含提交表单
		 * 
		 * @param model
		 * @return
		 */
	@RequestMapping(value = "/delete.action")
	public String metaDataDelete(Model model) {
		model.addAttribute("metaDataList",
				metaDataService.generateTreeArrayListofMetaDatas(0));
		return "/RBR/metaData/metaDataDelete";
	}


	/**
	 * 元数据删除警告，当该条元数据被规则库引用是展示该界面。
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteWarning.action")
	public String metaDataDeleteWarning(String id, Model model) {
		System.out.println("调用删除警告方法，传入的id=" + id);
		metaDataService.getTreeList().clear();
		// 删除前先检查该元数据是否被规则引用
		int idForCheck = Integer.valueOf(id); // 先转换类型
		
		//先看自己有没有被引用
			//是，
				//子孙被引用情况
					//是3
					//否1
			//否，
				//子孙被引用情况
					//是2
					//否0
		
		//start
		List<Integer> thisDataUsedList = metaDataService.isIdInRulesJustMe(idForCheck);//当前元数据是否被引用
		
		MetaDataAndRules metaDataAndRules =  metaDataService.isIdInRules(idForCheck);			//子孙节点的被引用的规则和元数据对象
		List<Integer> childrenIntegersList = metaDataAndRules.getRulesIdList();
		List<MetaData> childrenMetaDatasList = metaDataAndRules.getMetaDatasList();
		
		if (thisDataUsedList!=null) {							//自己被引用
			
			List<Rules> thisDataRulesList = new ArrayList<Rules>();//当前元数据相关的规则规则对象List
			thisDataRulesList.clear();
			for(int i=0;i<thisDataUsedList.size();i++){//遍历规则id，得到规则对象组
				thisDataRulesList.add(rulesService.getRuleById(thisDataUsedList.get(i)));
			}
			model.addAttribute("thisDataRulesList", thisDataRulesList);//将当前元数据引用的规则对象List传到页面
			
			if (!childrenIntegersList.isEmpty()) {		//子孙被引用
				System.out.println("-->3");
				model.addAttribute("selfOrChildrenInRules", 3);	//标识符，集合中既有当前节点，又有子孙节点
			} else {
				System.out.println("-->1");
				model.addAttribute("selfOrChildrenInRules", 1);	//标识符，集合中只有当前节点，没有子孙节点
			}
		} else {												//自己没被引用
			if (!childrenIntegersList.isEmpty()) {		//子孙没被引用
				System.out.println("-->2");
				model.addAttribute("selfOrChildrenInRules", 2);	//标识符，集合中没有当前节点，只有子孙节点
			} else {
				System.out.println("规则库没有引用这一条元数据,在数据库中删除");
				return "redirect:/metaData/deleteConfirm.action?id="+id;
			}
		}
		
		model.addAttribute("thisMetaData", metaDataService.getMetaDataById(idForCheck));//把当前元数据传入页面
		
		//子孙被引用的规则对象
		List<Rules> childrenRulesList = new ArrayList<Rules>();
		childrenRulesList.clear();
		for (int i = 0; i < childrenIntegersList.size(); i++) {
			childrenRulesList.add(rulesService.getRuleById(childrenIntegersList.get(i)));
		}
		model.addAttribute("childrenRulesList", childrenRulesList);
		
		//子孙被引用的元数据对象
		model.addAttribute("childrenMetaDatasList", childrenMetaDatasList);
		//做一个数组传到页面
		List<String> childrenNameList = new ArrayList<String>();
		childrenNameList.clear();
		for (int i = 0; i < childrenMetaDatasList.size(); i++) {
			childrenNameList.add("\""+childrenMetaDatasList.get(i).getName()+"\"");//给每个String加双引号，为了满足前端js数组的格式
		}
		model.addAttribute("childrenNameList", childrenNameList);
		
		model.addAttribute("littleTree", metaDataService.generateTreeArrayListofMetaDatas(idForCheck));//我的小树
		
		
		
		return "/RBR/metaData/metaDataDeleteWarning";
	}

	
	/**
	 * 删除确认，载入页面时加载
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deleteConfirm.action")
	public String metaDataDeleteConfirm(String id, Model model){
		int intID = Integer.parseInt(id); 
		model.addAttribute("metaData",metaDataService.getMetaDataById(intID));	//将当前节点传到页面
		
		List<MetaData> childrenList = metaDataService.generateTreeArrayListofMetaDatas(intID);//查出当前节点下的所有子节点
		
		if (childrenList.isEmpty()) {			//判断是否有孩子
			model.addAttribute("haveChildrenOrNot", 0);//没有，给页面传一个标记为0
		} else {
			model.addAttribute("haveChildrenOrNot", 1);
			model.addAttribute("childrenList", childrenList);//将所有子节点传到页面
		}
		return "/RBR/metaData/metaDataDeleteConfirm";
	}
	
	/**
	 * 删除确认提交，POST方法
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteConfirmPOST.action", method = RequestMethod.POST)
	public String metaDataDeleteConfirm(HttpSession session,String id){
//		String managerNameString = "高浩阳";//这里需要在建一张用户表，直接user.getName()，这样得到当前用户名！【】【】【】【待解决】
		String managerNameString = (String) session.getAttribute("username");
		boolean flag = metaDataService.deleteChildren(Integer.valueOf(id), managerNameString);//数据库中删除
		if(flag){
			return "redirect:/metaData/delete.action";				//重定向到删除页面
		}else{
			return "../RBR/error";
		}
	}
}