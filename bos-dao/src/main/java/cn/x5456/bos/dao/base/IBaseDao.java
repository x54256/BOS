package cn.x5456.bos.dao.base;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.domain.Region;
import cn.x5456.bos.domain.Staff;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * 持久（Dao）层通用接口
 *
 * @param <T>
 */
public interface IBaseDao<T> {

    public void save(T entity);

    public void delete(T entity);

    public void update(T entity);

    public T findById(Serializable id);     // ???

    public List<T> findAll();

    void executeUpdate(String s, Object... objects);

    void pageQuery(PageUtils pageBean);

    void saveOrUpdate(T entity);

    List<T> findAll(DetachedCriteria dc);
}
