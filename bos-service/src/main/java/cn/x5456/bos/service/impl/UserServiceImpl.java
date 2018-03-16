package cn.x5456.bos.service.impl;

import cn.x5456.bos.MD5Utils;
import cn.x5456.bos.dao.IUserDao;
import cn.x5456.bos.domain.TUser;
import cn.x5456.bos.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public TUser findUser(TUser model) {

        String passwd = MD5Utils.md5(model.getPassword());

        return userDao.findByTUser(model.getUsername(), passwd);
    }

    @Override
    public void saveUser(TUser model) {

    }

    @Override
    public void editPassword(String password, Integer id) {

        String passwd = MD5Utils.md5(password);

        userDao.executeUpdate("user.editpassword", passwd, id);

    }


}
