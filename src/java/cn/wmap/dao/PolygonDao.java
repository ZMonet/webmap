package cn.wmap.dao;

import cn.wmap.entity.PolygonWeb;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class PolygonDao extends HibernateDaoSupport {

    public void savePolygon(PolygonWeb polygon) {
        getHibernateTemplate().save(polygon);
    }


    public void deletePolygon(PolygonWeb polygon) {
      getHibernateTemplate().delete(polygon);
    }


    public List<PolygonWeb> findAll() {
        return (List<PolygonWeb>) getHibernateTemplate().find("from PolygonWeb ");
    }

}
