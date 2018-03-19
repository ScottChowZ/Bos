package com.scott.crm.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.scott.crm.domain.Customer;

/**  
 * ClassName:CustomerService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午5:47:42 <br/>       
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CustomerService {
    @GET
    @Path("/customer")    
   List<Customer>  findByFixedAreaIdIsNull();
    @GET
    @Path("/cu")    
    List<Customer>  findByFixedAreaIdIsNotNull();
    
    
}
  
