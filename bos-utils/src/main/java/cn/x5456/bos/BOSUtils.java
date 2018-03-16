package cn.x5456.bos;

import cn.x5456.bos.domain.TUser;
import com.opensymphony.xwork2.ActionContext;

import java.util.Map;

/**
 * BOS项目的工具类
 */
public class BOSUtils {

    // 获取session域对象
    public static Map<String, Object> getHttpSession() {
        return ActionContext.getContext().getSession();
    }

    // 获取用户登录的对象
    public static TUser getLoginUser() {
        return (TUser) getHttpSession().get("loginUser");
    }

}
