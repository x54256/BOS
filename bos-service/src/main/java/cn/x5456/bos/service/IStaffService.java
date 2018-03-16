package cn.x5456.bos.service;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.domain.Staff;

public interface IStaffService {
    void save(Staff model0);

    void pageQuery(PageUtils pageBean);

    void deleteBatch(String ids);

    void update(Staff staff);

    Staff findById(String id);
}
