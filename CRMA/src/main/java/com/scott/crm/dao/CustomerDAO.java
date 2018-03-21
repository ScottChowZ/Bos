package com.scott.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scott.crm.domain.Customer;

/**  
 * ClassName:CustomerDAO <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午6:01:55 <br/>       
 */
@Repository("customerDao")
public interface CustomerDAO extends JpaRepository<Customer, Long> {

    List<Customer> findByFixedAreaIdIsNull();

    List<Customer> findByFixedAreaIdIsNotNull();
    @Modifying
    @Query("update Customer set fixedAreaId=null where fixedAreaId=?")
    void relive(String Id);
    
    @Modifying
    @Query("update Customer set fixedAreaId=?2 where id=?1")
    void relevance(Long long1,String fixedAreaId);
    

    
    

}
  
