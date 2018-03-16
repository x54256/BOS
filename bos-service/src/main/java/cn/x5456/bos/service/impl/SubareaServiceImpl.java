package cn.x5456.bos.service.impl;

import cn.x5456.bos.dao.ISubareaDao;
import cn.x5456.bos.domain.Subarea;
import cn.x5456.bos.service.ISubareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

    @Autowired
    public ISubareaDao subareaDao;

    @Override
    public void add(Subarea model) {
        subareaDao.save(model);
    }
}