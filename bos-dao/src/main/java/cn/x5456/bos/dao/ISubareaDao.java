package cn.x5456.bos.dao;

import cn.x5456.bos.dao.base.IBaseDao;
import cn.x5456.bos.domain.Subarea;

import java.util.List;

public interface ISubareaDao extends IBaseDao<Subarea> {
    List<Object> findSubareasGroupByProvince();
}
