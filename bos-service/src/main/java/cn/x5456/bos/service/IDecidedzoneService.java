package cn.x5456.bos.service;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.domain.Decidedzone;

public interface IDecidedzoneService {
    void save(Decidedzone model, String[] subareaid);

    void pageQuery(PageUtils pageBean);
}
