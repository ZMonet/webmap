package cn.wmap.service;

import cn.wmap.dao.PolygonDao;
import cn.wmap.entity.PolygonWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class PolygonService {

    @Autowired
    private PolygonDao polygonDao;

    public void setPolygonDao(PolygonDao polygonDao) {
        this.polygonDao = polygonDao;
    }

    /**
     * 保存
     * @param polygon
     */
    public void savePolygon(PolygonWeb polygon){
        this.polygonDao.savePolygon(polygon);
    }

    /**
     * 更新数据
     */
    public void updatePolygon(PolygonWeb polygon){
        this.polygonDao.updatePolygon(polygon);
    }


    /**
     * 根据id更新
     * @return
     */
    public void updateById(String name,String location,String id){
        this.polygonDao.updateById(name,location,id);
    }


    /**
     * 根据id删除
     */
    public void deleteById(int id){
        this.polygonDao.deleteById(id);
    }



    /**
     * 查询数据
     * @return
     */
    public List<PolygonWeb> findAll(){
        return polygonDao.findAll();
    }

}
