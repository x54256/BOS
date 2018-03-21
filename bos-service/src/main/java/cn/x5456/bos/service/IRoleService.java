package cn.x5456.bos.service;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.domain.Role;

import java.util.List;

public interface IRoleService {
    void add(Role model, String functionIds);

    void pageQuery(PageUtils pageBean);

    List findAll();
}
