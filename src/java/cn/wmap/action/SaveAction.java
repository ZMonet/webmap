package cn.wmap.action;

import cn.wmap.entity.PolygonWeb;
import cn.wmap.service.PolygonService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;


public class SaveAction extends ActionSupport implements ServletRequestAware, ModelDriven<PolygonWeb> {
    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    private JSONObject resultObj;
    private PolygonService polygonService;

    public JSONObject getResultObj() {
        return resultObj;
    }

    public void setResultObj(JSONObject resultObj) {
        this.resultObj = resultObj;
    }

    private PolygonWeb polygon = new PolygonWeb();

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public void setPolygonService(PolygonService polygonService) {
        this.polygonService = polygonService;
    }

    @Override
    public PolygonWeb getModel() {
        return this.polygon;
    }

    //接收前端数据

    public String save(){
        try {

            //保存线信息
            String lpath=request.getParameter("lpath");
            JSONArray lArray=JSONArray.fromObject(lpath);
            for(int i=0;i<lArray.size();i++){
                polygon.setName("polyline");
                polygon.setLocation(lArray.get(i).toString());
                polygonService.savePolygon(polygon);
            }

            //保存矩形信息
            String rpath=request.getParameter("rpath");
            JSONArray rArray=JSONArray.fromObject(rpath);
            for(int i=0;i<rArray.size();i++){
                polygon.setName("rectangle");
                polygon.setLocation(rArray.get(i).toString());
                polygonService.savePolygon(polygon);
            }

            //保存多边形信息
            String gpath=request.getParameter("gpath");
            JSONArray gArray=JSONArray.fromObject(gpath);
            for(int i=0;i<gArray.size();i++){
                polygon.setName("polygon");
                polygon.setLocation(gArray.get(i).toString());
                polygonService.savePolygon(polygon);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }


}

