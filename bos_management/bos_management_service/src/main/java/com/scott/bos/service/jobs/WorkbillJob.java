package com.scott.bos.service.jobs;  
/**  
 * ClassName:WorkbillJob <br/>  
 * Function:  <br/>  
 * Date:     2018年3月30日 下午3:17:11 <br/>       
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scott.bos.dao.base.WorkbillRepository;
import com.scott.bos.domain.take_delivery.WorkBill;
import com.scott.bos.utils.MailUtils;
 @Component("emailJob") public class WorkbillJob {
    @Autowired
    private WorkbillRepository workbillRepository;
    public void sendMail(){
        String content="";
        List<WorkBill> list = workbillRepository.findAll();
        for (WorkBill workBill : list) {
            content=workBill.getPickstate();
        }
        
        //MailUtils.sendMail("工单信息统计", content, "1014261476@qq.com");
        
    }

    
}
