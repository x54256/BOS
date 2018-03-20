package cn.x5456.bos.service.impl;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.dao.IFunctionDao;
import cn.x5456.bos.domain.Function;
import cn.x5456.bos.service.IFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

    @Autowired
    private IFunctionDao functionDao;

    @Override
    public List<Function> listajax() {

        List<Function> list = functionDao.findAll();

        return list;
    }

    @Override
    public void add(Function model) {
        Function parentFunction = model.getParentFunction();

        if (parentFunction.getId() != null && parentFunction.getId().equals("")) {
            model.setParentFunction(null);
        }

        functionDao.save(model);
    }

    @Override
    public void pageQuery(PageUtils pageBean) {

        functionDao.pageQuery(pageBean);

    }
}
