package com.wx.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.dao.WxAuthorityDAO;
import com.wx.dao.WxRoleAuthorityDAO;
import com.wx.dao.WxRoleDAO;
import com.wx.model.WxAuthority;
import com.wx.model.WxRole;
import com.wx.model.WxRoleAuthority;

@Service
public class AuthorityService {
	@Resource
	private WxAuthorityDAO wxAuthorityDAO;
	@Resource
	private WxRoleDAO wxRoleDAO;
	@Resource
	private WxRoleAuthorityDAO wxRoleAuthorityDAO;
	
	public String createAuthorityTree(String roleid){
		//���roleid���е�Ȩ��
		Set<Integer> roleA = getAuthorityByRole(roleid);
		//���ȫ��Ȩ��
		List<WxAuthority> fathers = wxAuthorityDAO.getAllParentAuthority();
		//System.out.println(father.size());
		StringBuffer js = new StringBuffer();
		js.append("var arr = [];");
		for(WxAuthority father:fathers){
			//��ȡ���ڵ���ӽڵ�
			Set<WxAuthority> childs = father.getWxAuthorities();
			Set<WxAuthority> sortChlids = new TreeSet<WxAuthority>(childs);
			int checkedChildSize = 0;//��ǰ���ڵ���Ӧ��ѡ�е��ӽڵ����
			js.append("var subarr = [];");
			for(WxAuthority chlid:sortChlids){
				int checkstate = 0;//roleid��Ӧ�Ľ�ɫ�Ƿ��е�ǰȨ�ޣ��ӽڵ�ѡ��״̬��
				if(roleA.contains(chlid.getAuthorityid())){
					checkstate = 1;
					checkedChildSize++; 
				}
				//�ڵ���������(�ӽڵ�)
				js.append("subarr.push({" +
						  "'id' : '"+chlid.getAuthorityid()+"'," +
						  "'text' : '"+chlid.getName()+"'," +
						  "'value' : '"+chlid.getAuthorityid()+"'," +
						  "'showcheck' : true," +
						  " complete : true," +
						  "'isexpand' : false," +
						  "'checkstate' : "+checkstate+"," +
						  "'hasChildren' : false });");
			}
			int fatherState = 0; //���ڵ��ѡ��״̬
			int allchildSize = childs.size();
			//��Ҫ���ǵ�ǰ���ڵ�û���ӽڵ�����
			if((checkedChildSize>0 && checkedChildSize==allchildSize) 
					|| (roleA.contains(father.getAuthorityid())))
				fatherState = 1; //ȫѡ��
			else if(checkedChildSize>0 && checkedChildSize<allchildSize)
				fatherState = 2; //��ѡ��
			//��Ӹ��ڵ�(���ڵ��valueǰ�Ӹ�f���)
			js.append("arr.push( {"+
					  "'id' : '"+father.getAuthorityid()+"'," +
					  "'text' : '"+father.getName()+"'," +
					  "'value' : 'f"+father.getAuthorityid()+"'," +
					  "'showcheck' : true," +
					  " complete : true," +
					  "'isexpand' : false," +
					  "'checkstate' : "+fatherState+"," +
					  "'hasChildren' : true," +
					  "'ChildNodes' : subarr});");
		}
		return js.toString();
	}
	
	//���ش���roleid��Ӧ�����н�ɫ��Ȩ��id����
	public Set<Integer> getAuthorityByRole(String roleid){
		
		List<Integer> list = wxRoleAuthorityDAO.findAuthorityByRole(roleid);
		//System.out.println(list.get(0));
		Set<Integer> set = new HashSet<Integer>(list);
		return set;
	}
	
	//���ĳ����Ȩ�޽ڵ��Ӧ���ӽڵ���
	public Long getChildSize(String pid){
		Long count = wxAuthorityDAO.getChildSize(pid);
		System.out.println(count);
		return count;
	}
	//ɾ��ĳ����ɫ��Ӧ������Ȩ��
	public void delAuthorityByRole(String roleid){
		wxRoleAuthorityDAO.delAuthorityByRole(roleid);
	}

	//Ϊĳ����ɫ���Ȩ��
	public void addAuthorityForRole(String roleid,String authorityid){
		WxRole role = wxRoleDAO.findById(roleid);
		WxAuthority authority = wxAuthorityDAO.findById(Integer.parseInt(authorityid));
		WxRoleAuthority ra = new WxRoleAuthority();
		ra.setWxAuthority(authority);
		ra.setWxRole(role);
		wxRoleAuthorityDAO.save(ra);
	}
	
	//�޸�roleid��Ӧ��ɫ��Ȩ��(��ɾ��ԭ��Ȩ���������Ȩ�ޣ��ʴ˷���Ӧ���������)
	@Transactional(rollbackFor={Exception.class})
	public void modifyAuthority(String roleid, String authoritys){
		//1.��ɾ��roleAuthority��������role��Ӧ��Ȩ��
		delAuthorityByRole(roleid);
		//if(true)throw new Exception();
		//2.�ٴӽ���ѡ���Ȩ�޼���roleAuthority��
		String[] as= authoritys.split(","); 
		for(String a:as){
			if(a.startsWith("f")){//���ڵ�
				a = a.substring(1); //�ص�f
				Long childCount = getChildSize(a);
				if(childCount>0) //�����ڡ���ҳ����û���ӽڵ��Ȩ��
					continue;
				
			}
			addAuthorityForRole(roleid,a); //����ڵ㶼���
		}
	} 
}
