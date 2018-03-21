package cn.x5456.bos.service;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.domain.TUser;

public interface IUserService {
    TUser findUser(TUser model);

    void saveUser(TUser model, String[] roleIds);

    void editPassword(String password, String id);

    void pageQuery(PageUtils pageBean);
}
