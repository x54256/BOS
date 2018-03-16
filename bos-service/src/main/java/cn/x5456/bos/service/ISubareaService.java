package cn.x5456.bos.service;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.domain.Subarea;

import java.util.List;

public interface ISubareaService {

    void add(Subarea model);

    void pageQuery(PageUtils pageBean);

    List<Subarea> findAll();
}
