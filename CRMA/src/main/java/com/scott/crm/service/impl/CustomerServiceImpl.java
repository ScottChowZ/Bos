package com.scott.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scott.crm.dao.CustomerDAO;
import com.scott.crm.domain.Customer;
import com.scott.crm.service.CustomerService;

/**  
 * ClassName:CustomerServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午5:48:06 <br/>       
 */
@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService{
    @Resource(name="customerDao")
    private CustomerDAO customerDao;
    @Override
    public List<Customer> findByFixedAreaIdIsNull() {
          
      
        return customerDao.findByFixedAreaIdIsNull();
    }
    @Override
    public List<Customer> findByFixedAreaId(String fixedAreaId) {
          
         
        return customerDao.findByFixedAreaId(fixedAreaId);
    }
    @Transactional
    @Override
    public void save(Long[] id, String fixedAreaId) {
          customerDao.relive(fixedAreaId);
          
         
         
        for(int i=0;i<id.length;i++){
            
            customerDao.relevance(id[i],fixedAreaId);
                    
        }
        
    }
    @Override
    public void saveCustomer(Customer customer) {
        
        customerDao.save(customer);
        
        
    }
    @Transactional
    @Override
    public void active(String tel) {
          customerDao.active(tel);
         
        
    }
    @Override
    public Customer login(String tel, String password) {
        
        
        return customerDao.login(tel,password);
    }
    @Override
    public String findFixedId(String sendAddress) {
          
       
        return customerDao.findFixedAreadId(sendAddress);
    }
   

    
}
  
