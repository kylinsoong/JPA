package com.kylin.man.service;

import java.util.List;

import com.kylin.man.po.Man;

public interface FacadeService {
	
	public void addMan(Man man);
	public void addMans(List<Man> mans);
	
	public List<Man> getMans();
	public Man getMan(long id);
	
	public void removeMan(long id);
	
	public void updateMan(Man man);
	
	public String ping();

}
