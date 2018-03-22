package com.scott.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.base.Courier;

/**  
 * ClassName:CourierRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午10:39:48 <br/>       
 */
@Repository("courierRepository")
public interface CourierRepository extends JpaRepository<Courier, Long>,JpaSpecificationExecutor<Courier>{
    @Modifying
    @Query("update Courier set deltag = 1 where id = ?")
    void updateByID(long parseLong);
     @Query("from Courier where deltag= 0 ")
    List<Courier> findAllCourier();

}
  
