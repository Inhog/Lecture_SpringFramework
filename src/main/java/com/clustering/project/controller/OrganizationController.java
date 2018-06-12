/**
 * It's Designed For incubation Center
 * @author ohsanghun
 * @version     %I%, %G%
 * @since       1.0
 */
package com.clustering.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.clustering.project.javabean.OrganizationBean;
import com.clustering.project.service.OrganizationService;
import com.clustering.project.util.CommonUtil;

// ? delete @Controller, 
//@Controller
@Component
@RequestMapping(value = "/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationService service;
	
	@Autowired
	private CommonUtil commonutil;
	
	// delete
	@RequestMapping(value ="/delete",method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView delete(@RequestParam Map<String, Object> paramMap, ModelAndView modelandView) {
		service.deleteObject("", paramMap);
		String viewName = "/organization/list";
		List<Object> resultList = new ArrayList<Object>();
		resultList = (List<Object>)service.getList("organization.list",paramMap);
		modelandView.setViewName(viewName);
		modelandView.addObject("resultList", resultList);
		return modelandView;
	}
	
	// ? add View class
	@RequestMapping(value = "/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView edit(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, ModelAndView modelandView) {
		String viewName = "/organization/edit";
		paramMap.put("commonutil", commonutil.getUniqueSequence());
		modelandView.setViewName(viewName);
		modelandView.addObject("resultMap", paramMap);
		return modelandView;
	}
	
	// ? delete only method = RequestMethod.GET in @RequestMapping 
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView read(OrganizationBean paramMap, ModelAndView modelandView) {
		String viewName = "/organization/read";
		OrganizationBean result;
		result = (OrganizationBean)service.getObject("organization.read", paramMap);
		modelandView.setViewName(viewName);
		modelandView.addObject("paramMap", result);
		return modelandView;
	}

	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(@RequestParam Map<String, Object> paramMap, ModelAndView modelandView) {
		
		String viewName = "/organization/list";
		List<Object> resultList = new ArrayList<Object>();
		resultList = (List<Object>)service.getList("organization.list",paramMap);
		modelandView.setViewName(viewName);
		modelandView.addObject("resultList", resultList);
		return modelandView;
	}
	
	
	@RequestMapping(value = "/submit", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submit(@RequestParam Map<String, Object> paramMap, ModelAndView modelandView) {
		if((boolean) service.getUUID("organization.check", paramMap)) {
			service.saveObject("organization.update", paramMap);
		}else {
			service.saveObject("organization.insert", paramMap);
		}
		String viewName = "/organization/list";
		List<Object> resultList = new ArrayList<Object>();
		resultList = (List<Object>)service.getList("organization.list",paramMap);
		modelandView.setViewName(viewName);
		modelandView.addObject("resultList", resultList);
		return modelandView;
	}
}
