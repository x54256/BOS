package cn.x5456.bos.web;

import cn.x5456.bos.domain.Workbill;
import cn.x5456.bos.service.IWorkbillService;
import cn.x5456.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(scopeName = "prototype")
public class WorkbillAction extends BaseAction<Workbill> {

    @Autowired
    private IWorkbillService workbillService;

    public String handAdd() throws Exception {

        workbillService.handAdd(super.model);

        return "none";
    }
}
