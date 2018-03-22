package com.scott.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.base.TakeTime;

/**  
 * ClassName:TakeTimeRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月21日 下午8:26:24 <br/>       
 */
@Repository("takeTimeRepository")
public interface TakeTimeRepository  extends JpaRepository<TakeTime, Long>{
    

}
  
