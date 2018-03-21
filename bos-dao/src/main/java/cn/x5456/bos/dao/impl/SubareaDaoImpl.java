package cn.x5456.bos.dao.impl;

import cn.x5456.bos.dao.ISubareaDao;
import cn.x5456.bos.dao.base.impl.BaseDaoImpl;
import cn.x5456.bos.domain.Subarea;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements ISubareaDao {
    @Override
    public List<Object> findSubareasGroupByProvince() {
        String hql = "SELECT r.province ,count(*) FROM Subarea s LEFT OUTER JOIN s.region r Group BY r.province";

        return (List<Object>) this.getHibernateTemplate().find(hql);
    }
}
