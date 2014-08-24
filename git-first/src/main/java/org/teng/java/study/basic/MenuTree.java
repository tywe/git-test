package org.teng.java.study.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

public class MenuTree {
	private static Logger logger = Logger.getLogger(MenuTree.class);
	public List<Map<String, Object>> treeNodes;
	public MenuTree(){
		treeNodes = new ArrayList<Map<String, Object>>();
		initTreeNodes();
	}
	
	public void initTreeNodes(){
		//一级菜单
		Map<String, Object> treeNode11 = new HashMap<String, Object>();
		treeNode11.put("menuId", 1);
		treeNode11.put("pMenuId", 0);
		treeNode11.put("isLeaf", 0);
		treeNodes.add(treeNode11);
		
		//二级菜单
		Map<String, Object> treeNode21 = new HashMap<String, Object>();
		treeNode21.put("menuId", 2);
		treeNode21.put("pMenuId", 1);
		treeNode21.put("isLeaf", 0);
		treeNodes.add(treeNode21);
		
		Map<String, Object> treeNode22 = new HashMap<String, Object>();
		treeNode22.put("menuId", 3);
		treeNode22.put("pMenuId", 1);
		treeNode22.put("isLeaf", 1);
		treeNodes.add(treeNode22);
		
		//三级菜单
		Map<String, Object> treeNode31 = new HashMap<String, Object>();
		treeNode31.put("menuId", 4);
		treeNode31.put("pMenuId", 2);
		treeNode31.put("isLeaf", 1);
		treeNodes.add(treeNode31);
		
		Map<String, Object> treeNode32 = new HashMap<String, Object>();
		treeNode32.put("menuId", 5);
		treeNode32.put("pMenuId", 2);
		treeNode32.put("isLeaf", 1);
		treeNodes.add(treeNode32);
		
	}
	
	public List<Map<String, Object>> searchLeafMenus(List<Map<String, Object>> menuList){
		if(null == menuList || menuList.size() == 0){
			logger.error("menuList is null in search leaf", new Exception("null pointer exception"));
			return null;
		}
		List<Map<String, Object>> leafMenus = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> menu : menuList){
			int isLeaf = (Integer)menu.get("isLeaf");
			
			if(isLeaf == 1){
				leafMenus.add(menu);
			}
		}
		
		return leafMenus;
	}
	
	public Map<Integer, Object> transMenuType(List<Map<String, Object>> menuList){
		if(null == menuList || menuList.size() == 0){
			logger.error("menuList is null in transMenuType", new Exception("null pointer exception"));
			return null;
		}
		
		Map<Integer, Object> idMenuMap = new TreeMap<Integer, Object>();
		
		for(Map<String, Object> menu : menuList){
			Integer menuId = (Integer) menu.get("menuId");
			
			idMenuMap.put(menuId, menu);
		}
		
		return idMenuMap;
	}
	
	
	public static void main(String[] args){
		
	}
}
