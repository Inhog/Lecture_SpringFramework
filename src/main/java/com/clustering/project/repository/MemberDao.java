package com.clustering.project.repository;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
/*	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	*/
	
	public Object getObject(String sqlMapId, Object dataMap) {
		Object result = sqlSession.selectOne(sqlMapId, dataMap);
		return result;
	}
	
	public Object getList(Object dataMap, String sqlMapId) {
		Object result = sqlSession.selectList(sqlMapId, dataMap);
		return result;
	}
	
	public Object getUUID(String sqlMapId, Object dataMap) {
		String value = "";
		Object resultQuery= sqlSession.selectOne(sqlMapId,dataMap);
		if(resultQuery==null) {
			value ="none";
			resultQuery = new HashMap<String,Object>();
			((Map) resultQuery).put("MEMBER_SEQ",(Object)value);
		}
		return resultQuery;
	}
	
	public Object saveObject(String sqlMapId, Object dataMap) {
		Integer result = sqlSession.insert(sqlMapId,dataMap);
		return result;
	}
	
	public Object deleteObject(String sqlMapId, Object dataMap){
		Integer result =sqlSession.delete(sqlMapId,dataMap);
		return result;
	}

}