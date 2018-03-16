package cn.x5456.bos.dao.impl;

import cn.x5456.bos.dao.IRegionDao;
import cn.x5456.bos.dao.base.impl.BaseDaoImpl;
import cn.x5456.bos.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {
    @Override
    public List<Region> listajaxByQ(String q) {

        String hql = "from Region where province like ? or city like ? or district like ? or citycode like ? or shortcode like ?";

        List<Region> list = (List<Region>) super.getHibernateTemplate().find(hql, "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%");

        return list;
    }
}
