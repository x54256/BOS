package cn.x5456.bos.service;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.domain.Region;

import java.util.List;

public interface IRegionService {
    void saveBatch(List<Region> regions);

    void pageQuery(PageUtils pageBean);

    List<Region> listajax();

    List<Region> listajaxByQ(String q);
}
