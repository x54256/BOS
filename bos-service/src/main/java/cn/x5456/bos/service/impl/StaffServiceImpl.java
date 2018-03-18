package cn.x5456.bos.service.impl;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.dao.IStaffDao;
import cn.x5456.bos.domain.Staff;
import cn.x5456.bos.service.IStaffService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

    @Autowired
    private IStaffDao staffDao;

    @Override
    public void save(Staff model) {

        staffDao.save(model);

    }

    @Override
    public void pageQuery(PageUtils pageBean) {

        staffDao.pageQuery(pageBean);

    }

    @Override
    public void deleteBatch(String ids) {
        // 1.字符串切割成数组
        String[] strings = ids.split(",");
        // 2.循环
        for (String s : strings) {
            staffDao.executeUpdate("staff.delete", s);
        }

    }

    @Override
    public void update(Staff staff) {

        staffDao.update(staff);

    }

    @Override
    public Staff findById(String id) {

        return staffDao.findById(id);

    }

    @Override
    public List<Staff> listajax() {

        DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
        dc.add(Restrictions.eq("deltag", "0"));

        return staffDao.findAll(dc);

    }
}
