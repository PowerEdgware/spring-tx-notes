package com.study.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.stereotype.Service;

import com.study.entity.DemoEntity;
import com.study.entity.DemoRepostory;
import com.study.pojo.DemoReq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemoService {

	final DemoRepostory demoRepos;
	
	@Autowired
	DemoService demoService;
	
	@Transactional
	public DemoEntity save(String entityName,String remark){
		DemoEntity demoEntity=new DemoEntity();
		demoEntity.setEntityName(entityName);
		demoEntity.setRemark(remark);
		return demoRepos.save(demoEntity);
	}
	//ClassPathScanningCandidateComponentProvider
	@Transactional
	public DemoEntity save(DemoReq demoReq){
		DemoEntity target=new DemoEntity();
		BeanUtils.copyProperties(demoReq, target);
		try {
			demoService.saveWithError(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return demoRepos.save(target);
	}
	@Transactional
	public void saveWithError(DemoEntity entity) {
		demoRepos.save(entity);
		throw new RuntimeException();
	}
}
