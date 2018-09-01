package cn.wmap.action;

import cn.wmap.entity.PolygonWeb;
import cn.wmap.service.PolygonService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;


public class SaveAction extends ActionSupport implements ServletRequestAware, ModelDriven<PolygonWeb> {
    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    private PolygonService polygonService;
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

    public String save() {
        try {
            String type = request.getParameter("type");
            String location = request.getParameter("location");
            polygon.setName(type);
            polygon.setLocation(location);
            polygonService.savePolygon(polygon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }


}

