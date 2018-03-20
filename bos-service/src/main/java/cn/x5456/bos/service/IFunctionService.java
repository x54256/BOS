package cn.x5456.bos.service;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.domain.Function;

import java.util.List;

public interface IFunctionService {
    List<Function> listajax();

    void add(Function model);

    void pageQuery(PageUtils pageBean);
}
