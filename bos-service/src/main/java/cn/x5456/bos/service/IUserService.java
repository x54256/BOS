package cn.x5456.bos.service;

import cn.x5456.bos.domain.TUser;

public interface IUserService {
    TUser findUser(TUser model);

    void saveUser(TUser model);

    void editPassword(String password, String id);
}
