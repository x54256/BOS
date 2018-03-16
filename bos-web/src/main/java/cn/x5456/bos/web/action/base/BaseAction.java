package cn.x5456.bos.web.action.base;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.domain.Region;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    // 由于T是泛型对象，所以不能实例化，要我们用构造给它传递对象
    protected T model;

    protected Integer page;
    protected Integer rows;

    protected PageUtils pageBean = new PageUtils();

    public void setPage(Integer page) {
//        this.page = page;
        pageBean.setCurrentPage(page);
    }

    public void setRows(Integer rows) {
//        this.rows = rows;
        pageBean.setPageSize(rows);
    }

    // 初始化方法，子类实例化，会调用父类的初始化方法
    public BaseAction() {

        // 获取泛型
        ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 获取泛型数组
        Type[] tList = pType.getActualTypeArguments();
        // 取出数组中第一个值，转成字节码对象
        Class<T> clazz = (Class<T>) tList[0];
        // 由于model要的是一个对象，所以要将字节码对象通过反射转换成对象
        try {
            this.model = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 1.创建离线对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
        // 装进pageBean
        pageBean.setDetachedCriteria(detachedCriteria);
    }

    public void writeJson(List list, String[] strings) {
        // 4.将pagebean转成json格式，返回
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(strings);
        String json = JSONArray.fromObject(list, jsonConfig).toString();

        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJson(Object o, String[] strings) {
        // 4.将pagebean转成json格式，返回
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(strings);
        String json = JSONObject.fromObject(o, jsonConfig).toString();

        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public T getModel() {
        return model;
    }
}
