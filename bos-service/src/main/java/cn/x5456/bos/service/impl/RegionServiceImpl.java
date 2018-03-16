package cn.x5456.bos.service.impl;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.dao.IRegionDao;
import cn.x5456.bos.domain.Region;
import cn.x5456.bos.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private IRegionDao regionDao;

    @Override
    public void saveBatch(List<Region> regions) {
        for (Region r : regions) {
            // 由于我们的主键使用的是手动输入类型，所以主键重复时会抛出异常
            // regionDao.save(r);

            // 所以我们要在BaseDao中扩展一个saveOrUpdate方法
            regionDao.saveOrUpdate(r);
        }
    }

    @Override
    public void pageQuery(PageUtils pageBean) {
        regionDao.pageQuery(pageBean);
    }


    @Override
    public List<Region> listajax() {

        return regionDao.findAll();

    }

    @Override
    public List<Region> listajaxByQ(String q) {

        return regionDao.listajaxByQ(q);

    }
}
