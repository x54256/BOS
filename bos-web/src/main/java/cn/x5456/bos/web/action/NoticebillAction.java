package cn.x5456.bos.web.action;

import cn.x5456.bos.domain.Noticebill;
import cn.x5456.bos.service.INoticebillService;
import cn.x5456.bos.web.action.base.BaseAction;
import cn.x5456.crmClient.Customer;
import cn.x5456.crmClient.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(scopeName = "prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

    @Autowired
    private ICustomerService proxy;

    @Autowired
    private INoticebillService noticebillService;

    public String findCustomerByTelephone() throws Exception {

        // 1.获取前端传来的电话号
        String telephone = super.model.getTelephone();
        // 2.调用CRM WebService中的方法
        Customer customer = proxy.findCustomerByTelephone(telephone);
        // 3.将数据转成json，返回页面
        super.writeJson(customer, new String[]{});

        return "none";
    }


    public String add() throws Exception {

        noticebillService.save(super.model);

        return "add";
    }

}
