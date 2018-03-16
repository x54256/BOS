package cn.x5456.bos.web.action;

import cn.x5456.bos.domain.Subarea;
import cn.x5456.bos.service.ISubareaService;
import cn.x5456.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
@Scope(scopeName = "prototype")
public class SubareaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService subareaService;

    public String add() throws Exception {

        subareaService.add(super.model);

        return "list";
    }
}
