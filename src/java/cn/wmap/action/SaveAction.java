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
            String type = request.getParameter("type");     //获取类型数组
            String location = request.getParameter("location");   //获取坐标数组

            JSONArray ptype = JSONArray.fromObject(type);    //转换成JSON数组
            JSONArray plocation = JSONArray.fromObject(location);

            for (int i = 0; i < ptype.size(); i++) {
                polygon.setName(ptype.get(i).toString());
                polygon.setLocation(plocation.get(i).toString());
                polygonService.savePolygon(polygon);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }


}

