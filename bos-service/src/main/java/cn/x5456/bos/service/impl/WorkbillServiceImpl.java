package cn.x5456.bos.service.impl;

import cn.x5456.bos.dao.IDecidedzoneDao;
import cn.x5456.bos.dao.INoticebillDao;
import cn.x5456.bos.dao.IWorkbillDao;
import cn.x5456.bos.domain.Workbill;
import cn.x5456.bos.service.IWorkbillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkbillServiceImpl implements IWorkbillService {
    @Autowired
    private INoticebillDao noticebillDao;

    @Autowired
    private IWorkbillDao workbillDao;

    @Override
    public void handAdd(Workbill model) {
        workbillDao.save(model);

    }
}
