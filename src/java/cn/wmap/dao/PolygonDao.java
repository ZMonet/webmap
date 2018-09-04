package cn.wmap.dao;

import cn.common.dao.GenericEntityDao;
import cn.wmap.entity.PolygonWeb;
import org.hibernate.query.Query;
import javax.inject.Named;
import java.util.List;

@Named("PolygonDao")
public class PolygonDao extends GenericEntityDao<PolygonWeb,String> {

    @Override
    protected Class<PolygonWeb> entityClass() {
        return PolygonWeb.class;
    }

    /**
     * 插入数据
     * @param polygon
     */
    public void savePolygon(PolygonWeb polygon) {
        this.getCurrentSession().save(polygon);
    }


    /**
     * 更新数据
     */
    public void updatePolygon(PolygonWeb polygon){
        this.getCurrentSession().update(polygon);
    }


    /**
     * 根据id更新数据
     * @param name,location,id
     */
    public void updateById(String name,String location,String id){
        String hql="update PolygonWeb set name=?1,location=?2 where pid=?3";
        Query query=this.getCurrentSession().createQuery(hql).setParameter(1,name).setParameter(2,location).setParameter(3,id);
        query.executeUpdate();
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(int id){
        String hql="Delete from PolygonWeb where pid=:ID";
        Query query=this.getCurrentSession().createQuery(hql).setParameter("ID",id);
        query.executeUpdate();
    }



    public List<PolygonWeb> findAll() {
       List<PolygonWeb> list=this.getCurrentSession().createQuery("from PolygonWeb ").list();
       return list;
    }

}
