package cn.common.model;

import java.util.ArrayList;
import java.util.List;

public class PageModel {

    private Integer pageIndex;//当前页码
    private Integer pageTotal;//总页码
    private Integer rowsTotal;//总条数
    private Integer pageSize;//每页显示条数
    private String hql;//分页语句
    private List list;//返回的数据集合

    public PageModel() {
        super();
    }
    public String getHql() {
        return hql;
    }
    public void setHql(String hql) {
        this.hql = hql;
    }
    public Integer getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getPageTotal() {
        return pageTotal;
    }
    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }
    public Integer getRowsTotal() {
        return rowsTotal;
    }
    public void setRowsTotal(int rowsTotal) {
        this.rowsTotal = rowsTotal;
        pageTotal = rowsTotal%pageSize==0 ? rowsTotal/pageSize : rowsTotal/pageSize+1;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public List getList() {
        return list;
    }
    public void setList(List list) {
        this.list = list;
    }

    /**
     * @param pageIndex
     * @param rowsTotal
     * @param pageSize
     * @param hql
     * @param list
     */
    public PageModel(Integer pageIndex, Integer rowsTotal, Integer pageSize, String hql, List list) {
        this.pageIndex = pageIndex;
        this.rowsTotal = rowsTotal;
        this.pageSize = pageSize;
        this.hql = hql;
        this.list = list;
        this.pageTotal = rowsTotal%pageSize==0 ? rowsTotal/pageSize : rowsTotal/pageSize+1;
    }

    /**
     * @param hql
     * @param result
     */
    public PageModel(String hql,Object result){
        this.list = new ArrayList();
        this.list.add(result);
        this.hql = hql;
        this.pageIndex = 1;
        this.rowsTotal = 1;
        this.pageSize = 1;
        this.pageTotal = 1;
    }

    @Override
    public String toString() {
        return "{Page:{list:" + list.toString() + ", pageIndex:" + pageIndex + ", pageTotal:"
                + pageTotal + ", pageSize:" + pageSize + ", rowsTotal:" + rowsTotal
                + "}}";
    }

}