package cn.x5456.bos.dao.impl;

import cn.x5456.bos.dao.IFunctionDao;
import cn.x5456.bos.dao.base.impl.BaseDaoImpl;
import cn.x5456.bos.domain.Function;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {
    @Override
    public List<Function> findAll() {

        String hql = "from Function where parentFunction is null";

        List<Function> list = (List<Function>) super.getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public List<Function> findByUserId(String id) {

        // distinct==》去重
        String hql = "select distinct f from Function f inner join f.roles r inner join r.users u where u.id=?";

        List<Function> list = (List<Function>) super.getHibernateTemplate().find(hql, id);

        return list;
    }

    @Override
    public List<Function> findMenuByUserId(String id) {
        // distinct==》去重
        String hql = "select distinct f from Function f inner join f.roles r inner join r.users u where u.id=? and f.generatemenu = '1' order by f.zindex desc ";

        List<Function> list = (List<Function>) super.getHibernateTemplate().find(hql, id);

        return list;
    }

    @Override
    public List<Function> findAllMenu() {
        String hql = "from Function f where f.generatemenu = '1' order by f.zindex desc ";

        List<Function> list = (List<Function>) super.getHibernateTemplate().find(hql);

        return list;
    }
}
