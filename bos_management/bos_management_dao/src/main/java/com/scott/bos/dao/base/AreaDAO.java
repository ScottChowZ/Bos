package com.scott.bos.dao.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.base.Area;

/**  
 * ClassName:AreaDAO <br/>  
 * Function:  <br/>  
 * Date:     2018年3月15日 下午9:24:34 <br/>       
 */
@Repository("areaDAO")
public interface AreaDAO extends JpaRepository<Area, Long> {
    @Query("from Area where province= ?1 or city like ?1 or district like ?1 or postcode like ?1 or citycode like ?1 or shortcode like ?1")
    List<Area> finAll(String q);
    @Query("from Area where province =?1 and city=?2 and district=?3")
    Area findArea(String province, String city, String district);
   
   

}
  
