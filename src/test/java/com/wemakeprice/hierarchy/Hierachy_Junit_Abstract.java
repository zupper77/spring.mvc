package com.wemakeprice.hierarchy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wemakeprice.hierarchy.dao.BaseDao;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/junit_test_config/dispacher-junit-test-servlet.xml"})
public abstract class Hierachy_Junit_Abstract {
	
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	protected BaseDao baseDao;
	
	@Before
	public abstract void start();
	
	@After
	public abstract void end();
	
	@Test
	public abstract void run_Test();
	
	
	
}
