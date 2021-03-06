package cn.x5456.bos.web.action;

import cn.x5456.bos.BOSUtils;
import cn.x5456.bos.MD5Utils;
import cn.x5456.bos.domain.TUser;
import cn.x5456.bos.service.IUserService;
import cn.x5456.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component     // 注册当前类到容器中
@Scope(scopeName = "prototype")   // 指定对象是单例还是多例
public class UserAction extends BaseAction<TUser> {

    // 接收一下前端传来的验证码
    private String checkcode;

    @Autowired
    private IUserService userService;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    /**
     * 用户登录,使用shiro框架提供的方式进行认证操作
     */
    public String login() {
        //从Session中获取生成的验证码
        String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //校验验证码是否输入正确
        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)) {
            //使用shiro框架提供的方式进行认证操作
            Subject subject = SecurityUtils.getSubject();//获得当前用户对象,状态为“未认证”
            AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));//创建用户名密码令牌对象
            try {
                subject.login(token);
            } catch (Exception e) {
                e.printStackTrace();
                return "login";
            }
            TUser user = (TUser) subject.getPrincipal();
            ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
            return "home";
        } else {
            //输入的验证码错误,设置提示信息，跳转到登录页面
            this.addActionError("输入的验证码错误！");
            return "login";
        }
    }

//    public String login() throws Exception {
//
//        // 1.获取session中的验证码
//        String realCode = (String) ActionContext.getContext().getSession().get("key");
//        // 2.进行验证码校验
//        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(realCode)) {
//            // 3.调用service，通过用户名和密码查询user对象
//            TUser u = userService.findUser(super.model);
//            // 4.判断返回值是否为null
//            if (u == null) {
//                // null设置异常，并返回
//                this.addActionError("用户名或者密码输入错误！");
//                return "login";
//            } else {
//                // 将user对象加入session中，并跳转到首页
//                ActionContext.getContext().getSession().put("loginUser", u);
//                return "home";
//            }
//        } else {
//            // 不同 ==> 抛出异常
//            super.addActionError("输入的验证码错误！");
//            return "login";
//        }
//    }

    // 退出登录
    public String logout() throws Exception {

        // 只清除指定的session
//        ActionContext.getContext().getSession().remove("loginUser");
        // 清除所有session
        ActionContext.getContext().getSession().clear();
        // 清除所有session
//        ServletActionContext.getRequest().getSession().invalidate();
        return "login";
    }


    // ajax请求修改密码
    public String editPassword() throws Exception {

        String flag = "1";

        // 获取当前用户的id
        String id = BOSUtils.getLoginUser().getId();
        // 获取前端传来的密码
        String password = super.model.getPassword();

        try {
            userService.editPassword(password, id);
        } catch (Exception ex) {
            flag = "0";
            ex.printStackTrace();
        }


//        ServletActionContext.getResponse().getWriter().write(flag);

//        使用print要加上这句话，否则前端会认为是一个对象而不是字符串
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(flag);

        return "none";
    }


    private String[] roleIds;

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public String add() throws Exception {

        userService.saveUser(super.model, roleIds);

        return "list";
    }

    public String pageQuery() throws Exception {

        userService.pageQuery(super.pageBean);

        super.writeJson(pageBean, new String[]{"noticebills", "roles"});

        return "none";
    }
}
