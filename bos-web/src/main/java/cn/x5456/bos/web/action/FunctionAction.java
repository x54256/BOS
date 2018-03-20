package cn.x5456.bos.web.action;

import cn.x5456.bos.domain.Function;
import cn.x5456.bos.service.IFunctionService;
import cn.x5456.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope(scopeName = "prototype")
public class FunctionAction extends BaseAction<Function> {

    @Autowired
    private IFunctionService functionService;

    public String listajax() throws Exception {

        List<Function> list = functionService.listajax();

        super.writeJson(list, new String[]{"roles", "children", "parentFunction"});

        return "none";
    }

    public String add() throws Exception {

        functionService.add(super.model);

        return "add";
    }

    public String pageQuery() throws Exception {

        String page = super.model.getPage();
        super.pageBean.setCurrentPage(Integer.parseInt(page));

        functionService.pageQuery(super.pageBean);

        super.writeJson(pageBean, new String[]{"roles", "children", "parentFunction", "currentPage", "pageSize", "pageSize"});

        return "none";
    }
}
