package cn.x5456.bos.dao;

import cn.x5456.bos.dao.base.IBaseDao;
import cn.x5456.bos.domain.Region;

import java.util.List;

public interface IRegionDao extends IBaseDao<Region> {
    List<Region> listajaxByQ(String q);
}
