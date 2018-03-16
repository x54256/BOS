package cn.x5456.bos.dao.impl;

import cn.x5456.bos.dao.IUserDao;
import cn.x5456.bos.dao.base.impl.BaseDaoImpl;
import cn.x5456.bos.domain.TUser;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<TUser> implements IUserDao {

    @Override
    public TUser findByTUser(String username, String password) {

        String hql = "FROM TUser WHERE username = ? AND password = ?";

        List<TUser> list = (List<TUser>) super.getHibernateTemplate().find(hql, username, password);

        if (list != null && list.size() != 0) {

            return list.get(0);

        }

        return null;
    }
}
