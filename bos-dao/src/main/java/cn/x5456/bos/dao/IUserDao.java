package cn.x5456.bos.dao;

import cn.x5456.bos.dao.base.IBaseDao;
import cn.x5456.bos.domain.TUser;

public interface IUserDao extends IBaseDao<TUser> {
    TUser findByTUser(String username, String password);
}
