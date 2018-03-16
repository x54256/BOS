package cn.x5456.bos.web.interceptor;

import cn.x5456.bos.BOSUtils;
import cn.x5456.bos.domain.TUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {

        TUser user = BOSUtils.getLoginUser();

        if (user == null) {
            return "login";
        }

        // 放行
        return actionInvocation.invoke();
    }
}
