package cn.x5456.bos.service.impl;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.dao.IRoleDao;
import cn.x5456.bos.domain.Function;
import cn.x5456.bos.domain.Role;
import cn.x5456.bos.service.IRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public void add(Role model, String functionIds) {

        // 1.先将角色保存到数据库
        roleDao.save(model);
        // 2.为角色添加权限（一定要坚持映射文件中是否inverse）
        if (StringUtils.isNotBlank(functionIds)) {
            String[] functionIdList = functionIds.split(",");
            for (String fid : functionIdList) {
                // 可以自己new一个Function对象，也可以用functionId获取Function对象
                Function function = new Function();
                function.setId(fid);
                model.getFunctions().add(function);
            }
        }

    }

    @Override
    public void pageQuery(PageUtils pageBean) {
        roleDao.pageQuery(pageBean);
    }

    @Override
    public List findAll() {
        return roleDao.findAll();
    }


}
