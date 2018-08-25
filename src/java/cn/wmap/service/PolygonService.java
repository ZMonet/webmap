package cn.wmap.service;

import cn.wmap.dao.PolygonDao;
import cn.wmap.entity.PolygonWeb;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class PolygonService {

    private PolygonDao polygonDao;

    public void setPolygonDao(PolygonDao polygonDao) {
        this.polygonDao = polygonDao;
    }

    public void savePolygon(PolygonWeb polygon){
        this.polygonDao.savePolygon(polygon);
    }

    public void deletePolygon(PolygonWeb polygon){
        this.polygonDao.deletePolygon(polygon);
    }

    public List<PolygonWeb> findAll(){
        return polygonDao.findAll();
    }

}
