package com.mainiway.cloudcut.api.exchange.test;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
IC 是web层接口
 */

@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public interface ICUserTest {
	
	public String hello();
	
	public String hello3(TestEntity entity);

}
