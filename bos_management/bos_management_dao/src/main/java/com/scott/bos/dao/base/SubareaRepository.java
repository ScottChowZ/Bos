package com.scott.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.base.FixedArea;
import com.scott.bos.domain.base.SubArea;

/**  
 * ClassName:SubareaRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午5:52:14 <br/>       
 */
@Repository("subareaRepository")
public interface SubareaRepository extends JpaRepository<SubArea, Long> {
    @Query("from SubArea where fixedArea =null")
    List<SubArea> findByfixedAreaIsNull();
    @Query("from SubArea where fixedArea = ?")
    List<SubArea> findByfixedAreaNotNull(FixedArea fixedArea);
    @Modifying
    @Query("update SubArea set fixedArea=null where fixedArea=?")
    void delByfix(FixedArea fixedArea);
    @Modifying
    @Query("update SubArea set fixedArea=?1 where id=?2")
    void savefixareatoSubarea(FixedArea fixedArea, Long subAreaId);

}
  
