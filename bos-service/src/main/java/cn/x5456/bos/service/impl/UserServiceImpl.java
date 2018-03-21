package cn.x5456.bos.service.impl;

import cn.x5456.bos.MD5Utils;
import cn.x5456.bos.PageUtils;
import cn.x5456.bos.dao.IUserDao;
import cn.x5456.bos.domain.Role;
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
    public void saveUser(TUser model, String[] roleIds) {

        // 将密码转成md5格式
        String password = model.getPassword();
        password = MD5Utils.md5(password);
        model.setPassword(password);

        userDao.save(model);

        for (String roleId : roleIds) {
            Role role = new Role();
            role.setId(roleId);

            model.getRoles().add(role);
        }

    }

    @Override
    public void editPassword(String password, String id) {

        String passwd = MD5Utils.md5(password);

        userDao.executeUpdate("user.editpassword", passwd, id);

    }

    @Override
    public void pageQuery(PageUtils pageBean) {
        userDao.pageQuery(pageBean);
    }


}
