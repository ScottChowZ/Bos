package com.scott.bos.service.base.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scott.bos.dao.base.AreaDAO;
import com.scott.bos.dao.base.CourierRepository;
import com.scott.bos.dao.base.FixedAreaRepository;
import com.scott.bos.dao.base.OrderRepository;
import com.scott.bos.dao.base.WorkbillRepository;
import com.scott.bos.domain.base.Area;
import com.scott.bos.domain.base.Courier;
import com.scott.bos.domain.base.FixedArea;
import com.scott.bos.domain.base.SubArea;
import com.scott.bos.domain.take_delivery.Order;
import com.scott.bos.domain.take_delivery.WorkBill;
import com.scott.bos.service.base.OrderService;

/**  
 * ClassName:OrderServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月23日 下午5:22:24 <br/>       
 */
@Service("orderServiceImpl")
@Transactional
public class OrderServiceImpl implements OrderService {
    @Resource(name="areaDAO")
    private AreaDAO areaDao;
    @Resource(name="orderRepository")
    private OrderRepository orderRepository;
    @Autowired
    private FixedAreaRepository fixedareaRepository;
    @Autowired
    private WorkbillRepository workbillRepository;
    @Override
    public void saveOrder(Order order) {
        if(order.getSendArea()!=null){//拥有持久化对象
     Area sendArea =areaDao.findArea(order.getSendArea().getProvince(),order.getSendArea().getCity(),order.getSendArea().getDistrict());
     order.setSendArea(sendArea);
        }
        if(order.getRecArea()!=null){
           Area  recArea=areaDao.findArea(order.getRecArea().getProvince(), order.getRecArea().getCity(), order.getRecArea().getDistrict());
        order.setRecArea(recArea);
        }
       
        orderRepository.save(order);//保存订单
        
        //=================自动分单
        
        String sendAddress = order.getSendAddress();
        
        if(sendAddress!=null){//根据地址查定区,根据定区查快递员
           String fixedId =WebClient.create("http://localhost:8180/CRMA/service/cs/findFixedId")
            .query("sendAddress", sendAddress)
            .type(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .get(String.class );
           if(fixedId!=null){
           FixedArea fixedArea = fixedareaRepository.findOne(Long.parseLong(fixedId));
           Set<Courier> couriers = fixedArea.getCouriers();//获得多个快递员
           if(couriers!=null){
               Iterator<Courier> iterator = couriers.iterator();
               Courier courier = iterator.next();
                 WorkBill workBill = new WorkBill();//生成工单
                 workBill.setAttachbilltimes(0);//催单次数
                 workBill.setBuildtime(new Date());//建表时间
                 workBill.setCourier(courier);//快递员
                 workBill.setOrder(order);//订单
                 workBill.setPickstate("新单");//订单状态
                 workBill.setRemark(order.getRemark());
                 workBill.setSmsNumber("111");//快递员号码
                 workBill.setType("新");//工单类型
                          workbillRepository.save(workBill) ;   //保存工单
                          order.setOrderType("自动分单");
                          return ;}
           }else{
              Area sendArea = order.getSendArea();//获得区域
              Set<SubArea> subareas = sendArea.getSubareas();//获得区域
              
             for (SubArea subArea : subareas) {
                if(sendAddress.contains(subArea.getAssistKeyWords())||sendAddress.contains(subArea.getKeyWords())){
                    FixedArea fixedArea = subArea.getFixedArea();
                    Set<Courier> couriers = fixedArea.getCouriers();
                   
                    Iterator<Courier> iterator = couriers.iterator();
                    Courier courier = iterator.next();
                    WorkBill workBill = new WorkBill();//生成工单
                    workBill.setAttachbilltimes(0);//催单次数
                    workBill.setBuildtime(new Date());//建表时间
                    workBill.setCourier(courier);//快递员
                    workBill.setOrder(order);//订单
                    workBill.setPickstate("新单");//订单状态
                    workBill.setRemark(order.getRemark());
                    workBill.setSmsNumber("222");//快递员号码
                    workBill.setType("新");//工单类型
                             workbillRepository.save(workBill) ;   //保存工单
                             order.setOrderType("自动分单");
                             return ;
                }
            }
              
               
           }
           
           order.setOrderType("手动分单");     
        }
    }

}
  
