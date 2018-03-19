package cn.x5456.bos.web.action;

import cn.x5456.bos.domain.Staff;
import cn.x5456.bos.service.IStaffService;
import cn.x5456.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

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

        super.writeJson(pageBean, new String[]{"currentPage", "detachedCriteria", "pageSize", "decidedzones"});


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


    public String listajax() throws Exception {

        List<Staff> list = staffService.listajax();

        super.writeJson(list, new String[]{"decidedzones"});

        return "none";
    }
}
