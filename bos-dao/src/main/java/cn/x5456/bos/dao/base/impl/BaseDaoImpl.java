package cn.x5456.bos.dao.base.impl;

import cn.x5456.bos.PageUtils;
import cn.x5456.bos.dao.base.IBaseDao;
import cn.x5456.bos.domain.Region;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

    // ***********根据类型注入spring工厂中的会话工厂对象sessionFactory***********

    @Resource(name = "sessionFactory")
    public void setMySessionFactory(SessionFactory sessionFactory) {

        super.setSessionFactory(sessionFactory);
    }

    // ***************为什么要这样写，因为T是泛型类型，所以不可以用T.class等方法***************

    // 字节码对象
    Class<T> entityClass;


    public BaseDaoImpl() {

        // 获取当前类的字节码对象==>获取当前类的超类（类的泛型的父类）
        Type type = this.getClass().getGenericSuperclass();
        // 将超类转换成泛型
        ParameterizedType pt = (ParameterizedType) type;
        // 获取泛型数组（可能有多个泛型）
        Type[] typeArray = pt.getActualTypeArguments();
        // 将第一个转为字节码类型
        Class<T> clazz = (Class<T>) typeArray[0];

        this.entityClass = clazz;
    }


    @Override
    public void save(T entity) {

        super.getHibernateTemplate().save(entity);

    }

    @Override
    public void delete(T entity) {

        super.getHibernateTemplate().delete(entity);

    }

    @Override
    public void update(T entity) {

        super.getHibernateTemplate().update(entity);

    }

    @Override
    public T findById(Serializable id) {

        // get方法需要一个字节码对象和序列话的id
        return super.getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public List<T> findAll() {

        String hql = "from " + entityClass.getSimpleName(); // 通过字节码对象获取类名

        List<T> list = (List<T>) super.getHibernateTemplate().find(hql);

        return list;
    }

    /**
     * 为了写一个通用的更新方法，我们需要hibernate的executeUpdate方法
     * 1.获取sessionFactory ==> 获取session
     * 2.获取query对象
     * 3.为hql语句中的?赋值
     * 4.执行操作
     *
     * @param s
     * @param objects
     */
    @Override
    public void executeUpdate(String s, Object... objects) {
//        // 1.获取我们注入的sessionfactory，获取与当前线程绑定的session
//        Session currentSession = super.getSessionFactory().getCurrentSession();
//        // 2.书写hql语句
//        String hql = "update TUser set password=? where id=?";
//        // 3.获取query对象
//        Query query = currentSession.createQuery(hql);
//        // 4.填充参数
//        query.setParameter(0,objects[0]);
//        query.setParameter(1,objects[1]);
//        // 5.执行操作
//        query.executeUpdate();

        /*  以上这种方式是很好的示例，但我们要做的是一个通用的 更新 类；so上面的方法把参数都写定了
            不符合我们的需求。所以我们采用另一种方式
            将sql语句，写在TUser.xml文件中；并通过传来的 s 找到那条sql语句
            <query name="user.editpassword">
                update TUser set password=? where id=?
            </query>
         */

        // 1.获取我们注入的sessionfactory，获取与当前线程绑定的session
        Session currentSession = super.getSessionFactory().getCurrentSession();
        // 3.获取query对象
        Query query = currentSession.getNamedQuery(s);
        // 4.填充参数
        int i = 0;
        for (Object o : objects) {
            query.setParameter(i++, o);  // 先填冲再++
        }
        // 5.执行操作
        query.executeUpdate();

    }

    @Override
    public void pageQuery(PageUtils pageBean) {
        Integer currentPage = pageBean.getCurrentPage();
        Integer pageSize = pageBean.getPageSize();
        // 获取离线对象
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();

        // 1.查询总数，封装到total中
        // select * ... ==> select count(*) ...
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> countList = (List<Long>) super.getHibernateTemplate().findByCriteria(detachedCriteria);
        // Long型 ==> int型
        Long count = countList.get(0);
        pageBean.setTotal(count.intValue());
        // 2.分页查询，封装到rows中
        // 将上面的条件清空 select count(*) ... ==> select * ...
        detachedCriteria.setProjection(null);
        // 3.指定封装对象的方式
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);

        Integer firstResult = (currentPage - 1) * pageSize;
        Integer maxResults = pageSize;

        List list = super.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);

        pageBean.setRows(list);
    }

    @Override
    public void saveOrUpdate(T entity) {

        super.getHibernateTemplate().saveOrUpdate(entity);

    }
}
