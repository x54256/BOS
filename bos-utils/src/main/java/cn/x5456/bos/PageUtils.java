package cn.x5456.bos;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 分页的包装类
 * <p>
 * 使用：
 * 1）要传递多个参数，还不如直接封装到一个类中传
 * 2）要有通用性
 */
public class PageUtils {
    private Integer currentPage;    // 当前页码
    private Integer pageSize;   // 每页显示个数
    private DetachedCriteria detachedCriteria;  // 离线Criteria

    // 这两条要与前端需要的响应字段一至
    private Integer total;  // 总条数
    private List rows;      // 要展示的数据集合

    public PageUtils() {
    }

    public PageUtils(Integer currentPage, Integer pageSize, DetachedCriteria detachedCriteria) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.detachedCriteria = detachedCriteria;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageUtils{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", detachedCriteria=" + detachedCriteria +
                ", total=" + total +
                ", rows=" + rows +
                '}';
    }
}
