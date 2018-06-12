/**
 * It's Designed For incubation Center
 * @author ohsanghun
 * @version     %I%, %G%
 * @since       1.0
 */
package com.clustering.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.clustering.project.service.MemberService;
import com.clustering.project.util.CommonUtil;

@Controller
public class MemberController {
	private final static String MAPPING = "/member";
	@Autowired
	private MemberService service;
	
	@Autowired
	private CommonUtil commonutil;
	
	@RequestMapping(value = MAPPING, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView actionMethod(@RequestParam Map<String, Object> paramMap, ModelAndView modelandView) {

		String viewName = MAPPING + "/";
		String action = (String) paramMap.get("action") ;
		
		Map<String, Object> resultMap = new HashMap<String, Object>() ;
		List<Object> resultList = new ArrayList<Object>();

		// divided depending on action value
		if ("edit".equalsIgnoreCase(action)) {
			viewName = viewName + action;
			paramMap.put("commonutil", commonutil.getUniqueSequence());
		} else if ("read".equalsIgnoreCase(action)) {
			viewName = viewName + action;
			
			resultMap =(Map<String, Object>)service.getObject(paramMap,"member.read");
		} else if ("list".equalsIgnoreCase(action)) {
			viewName = viewName + action;

			resultList=(List<Object>)service.getList(paramMap,"member.list");
		} else if ("delete".equalsIgnoreCase(action)){
			service.deleteObject("", paramMap);
			viewName = viewName + "list";
			resultList = (List<Object>)service.getList(paramMap,"member.list");
			
		} else if("submit".equalsIgnoreCase(action)) {
			viewName = viewName + "list";
			paramMap.put("REGISTER_SEQ","UUID-1111-1111111");
			paramMap.put("MODIFIER_SEQ","UUID-1111-1111111");
			paramMap.put("ORGANIZATION_SEQ","UUID-11-CIPI9A");
			if((boolean) service.getUUID("member.check", paramMap)) {
				service.saveObject("member.update", paramMap);
			}else {
				service.saveObject("member.insert", paramMap);
			}
			resultList = (List<Object>)service.getList(paramMap,"member.list");
		}

		modelandView.setViewName(viewName);

		modelandView.addObject("paramMap", paramMap);
		modelandView.addObject("resultMap", resultMap);
		modelandView.addObject("resultList", resultList);

		return modelandView;
	}
}