package cn.x5456.bos.web.action;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.dao.IStaffDao;
import cn.x5456.bos.domain.Staff;
import cn.x5456.bos.service.IStaffService;
import cn.x5456.bos.web.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component     // 注册当前类到容器中
@Scope(scopeName = "prototype")   // 指定对象是单例还是多例
public class StaffAction extends BaseAction<Staff> {

    @Autowired
    private IStaffService staffService;

    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }


    public String add() throws Exception {


        staffService.save(super.model);

        return "list";
    }


    public String pageQuery() throws Exception {

        // 2.调用service层方法，传递工具类对象
        staffService.pageQuery(pageBean);

        super.writeJson(pageBean, new String[]{"currentPage", "detachedCriteria", "pageSize"});


        return "none";
    }


    public String deleteBatch() throws Exception {

        // 调用service层方法
        staffService.deleteBatch(ids);

        return "list";
    }


    public String edit() throws Exception {

        Staff staff = staffService.findById(model.getId());

        staff.setName(model.getName());
        staff.setDecidedzones(model.getDecidedzones());
        staff.setHaspda(model.getHaspda());
        staff.setTelephone(model.getTelephone());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());

        // 调用service层方法
        staffService.update(staff);

        return "list";
    }
}
