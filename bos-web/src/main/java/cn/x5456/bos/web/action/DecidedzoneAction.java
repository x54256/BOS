package cn.x5456.bos.web.action;

import cn.x5456.bos.domain.Decidedzone;
import cn.x5456.bos.service.IDecidedzoneService;
import cn.x5456.bos.web.action.base.BaseAction;
import cn.x5456.crmClient.Customer;
import cn.x5456.crmClient.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope(scopeName = "prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

    @Autowired
    private IDecidedzoneService decidedzoneService;

    @Autowired
    private ICustomerService proxy;

    private String[] subareaid;     // 接受前端传来的subareaid

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    public String save() throws Exception {

        decidedzoneService.save(super.model, subareaid);

        return "list";
    }

    public String pageQuery() throws Exception {

        decidedzoneService.pageQuery(super.pageBean);

        super.writeJson(super.pageBean, new String[]{"DetachedCriteria", "pageSize", "currentPage", "subareas", "decidedzones"});

        return "none";
    }

    /**
     * 调用WebService获取所有没有绑定定区客户
     *
     * @return
     * @throws Exception
     */
    public String findListNotAssociation() throws Exception {

        List<Customer> listNotAssociation = proxy.findListNotAssociation();

        super.writeJson(listNotAssociation, new String[]{});

        return "none";
    }

    // 接收定区id
    private String decidedzoneId;


    public void setDecidedzoneId(String decidedzoneId) {
        this.decidedzoneId = decidedzoneId;
    }

    /**
     * 调用WebService获取当前定区的所有客户
     *
     * @return
     * @throws Exception
     */
    public String findListHasAssociation() throws Exception {

        List<Customer> listNotAssociation = proxy.findListHasAssociation(decidedzoneId);

        super.writeJson(listNotAssociation, new String[]{});

        return "none";
    }

    // Struts2集合和数组都可以封装
    private List<Integer> customerIds;

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    public String assigncustomerstodecidedzone() throws Exception {

        proxy.updateAssociation(decidedzoneId, customerIds);

        return "list";
    }

}
