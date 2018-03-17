package cn.x5456.bos.service.impl;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.dao.IDecidedzoneDao;
import cn.x5456.bos.dao.IStaffDao;
import cn.x5456.bos.dao.ISubareaDao;
import cn.x5456.bos.domain.Decidedzone;
import cn.x5456.bos.domain.Subarea;
import cn.x5456.bos.service.IDecidedzoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {

    @Autowired
    private IDecidedzoneDao decidedzoneDao;

    @Autowired
    private ISubareaDao subareaDao;

    @Override
    public void save(Decidedzone model, String[] subareaid) {
        // 此时保存的是decidedzone表中的数据，另一个表的还没保存
        decidedzoneDao.save(model);
        // 保存subarea表中的decidedzone_id字段
        for (String s : subareaid) {
            Subarea subarea = subareaDao.findById(s);
            subarea.setDecidedzone(model);  // 关联分区，由于是持久化状态，不用保存
        }

    }

    @Override
    public void pageQuery(PageUtils pageBean) {
        decidedzoneDao.pageQuery(pageBean);
    }
}
