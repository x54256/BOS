package cn.x5456.bos.dao;

import cn.x5456.bos.dao.base.IBaseDao;
import cn.x5456.bos.domain.Function;

import java.util.List;

public interface IFunctionDao extends IBaseDao<Function> {
    @Override
    List<Function> findAll();

    List<Function> findByUserId(String id);

    List<Function> findMenuByUserId(String id);

    List<Function> findAllMenu();
}
