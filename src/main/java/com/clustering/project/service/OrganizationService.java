package com.clustering.project.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clustering.project.repository.OrganizationDao;

@Service
public class OrganizationService {
	@Autowired
	private OrganizationDao dao;
		
	public Object getList(String sqlMapId,Object dataMap){
		Object resultObject = dao.getList(dataMap,sqlMapId);
		return resultObject;
	}
	
	public Object getObject(String sqlMapId,Object dataMap) {
		Object resultObject = dao.getObject(sqlMapId,dataMap);
		return resultObject;
	}
	
	public Object getUUID(String sqlMapId,Object dataMap) {
		Object resultObject = dao.getUUID(sqlMapId, dataMap);
		
		String test = (String) ((Map<String, Object>)dataMap).get("ORGANIZATION_SEQ");
		
		if(test == ((String)((Map<String, Object>)resultObject).get("ORGANIZATION_SEQ"))){
			return true;
		}
		return false;
	}
	
	public Object saveObject(String sqlMapId, Object dataMap) {
		Object resultObject = dao.saveObject(sqlMapId, dataMap);
		return resultObject;
	}
	
	public Object deleteObject(String sqlMapId, Object dataMap) {
		// sqlMapId 부분은 service에서 내용을 적어주는게 좋다
		Object resultObject = dao.deleteObject("organization.delete", dataMap);
		return resultObject;
	}

}
