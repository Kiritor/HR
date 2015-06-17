package com.test.bpm;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lcore.hr.core.entity.Root;
import com.lcore.hr.menu.organization.service.OrganizationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-common.xml" })
public class TestBpm{
    @Resource
    private OrganizationService organizationService;
    
    @Test
    public void test01(){
      List<Root> roots =  organizationService.getOuList(0, -1, null, null, null);
      System.out.println(roots.size());
      for(Root root:roots){
    	  System.out.println(root.toString());
      }
    }
    
}
