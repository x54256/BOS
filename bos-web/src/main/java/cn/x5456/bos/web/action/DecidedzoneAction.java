package cn.x5456.bos.web.action;

import cn.x5456.bos.domain.Decidedzone;
import cn.x5456.bos.service.IDecidedzoneService;
import cn.x5456.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(scopeName = "prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

    @Autowired
    private IDecidedzoneService decidedzoneService;

    private String[] subareaid;

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
}
