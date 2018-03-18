package cn.x5456.bos.service.impl;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.dao.ISubareaDao;
import cn.x5456.bos.domain.Staff;
import cn.x5456.bos.domain.Subarea;
import cn.x5456.bos.service.ISubareaService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

    @Autowired
    public ISubareaDao subareaDao;

    @Override
    public void add(Subarea model) {
        subareaDao.save(model);
    }

    @Override
    public void pageQuery(PageUtils pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    @Override
    public List<Subarea> findAll() {
        return subareaDao.findAll();
    }

    /**
     * 加载分区信息
     *
     * @return
     */
    @Override
    public List<Subarea> listajax() {

        DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);

        // 添加条件decidedzone_id为空，找出还没分配的分区
        dc.add(Restrictions.isNull("decidedzone"));

        return subareaDao.findAll(dc);

    }

    @Override
    public List<Subarea> findByDecidedzoneId(String decidedzoneId) {
        // 1.创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
        // 2.添加条件；由于查询的字段在本表中有，所以就不用起别名的内种方法了
        dc.add(Restrictions.eq("decidedzone.id", decidedzoneId));
        // 3.查询并返回
        return subareaDao.findAll(dc);
    }
}
