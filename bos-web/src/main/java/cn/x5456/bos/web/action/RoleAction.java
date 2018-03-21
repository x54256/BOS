package cn.x5456.bos.web.action;

import cn.x5456.bos.domain.Role;
import cn.x5456.bos.service.IRoleService;
import cn.x5456.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope(scopeName = "prototype")
public class RoleAction extends BaseAction<Role> {

    @Autowired
    private IRoleService roleService;

    private String functionIds;

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    public String add() throws Exception {

        roleService.add(super.model, functionIds);

        return "list";
    }

    public String pageQuery() throws Exception {

        roleService.pageQuery(pageBean);

        super.writeJson(pageBean, new String[]{"functions", "users"});

        return "none";
    }

    public String listajax() throws Exception {

        List<Role> list = roleService.findAll();

        super.writeJson(list, new String[]{"functions", "users"});

        return "none";
    }

}
