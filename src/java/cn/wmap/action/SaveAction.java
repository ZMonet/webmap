package cn.wmap.action;

import cn.common.action.GenericActionSupport;
import cn.wmap.entity.PolygonWeb;
import cn.wmap.service.PolygonService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class SaveAction extends GenericActionSupport implements ServletRequestAware {
    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    private PolygonWeb polygon = new PolygonWeb();
    private HashMap<String,Object> hashMap=new LinkedHashMap<>();

    @Autowired
    private PolygonService polygonService;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public void setPolygonService(PolygonService polygonService) {
        this.polygonService = polygonService;
    }

    //接收前端数据
    public String save() {
        try {

            String action=request.getParameter("action");

            if(action.equals("add")){   //添加
                String type = request.getParameter("type");
                String location = request.getParameter("location");
                polygon.setName(type);
                polygon.setLocation(location);
                polygonService.savePolygon(polygon);

                int id=polygon.getPid();   //返回客户端的id
                hashMap.put("id",id);
                super.writeJson(hashMap);
            }
            else if(action.equals("update")){  //更新
                String id = request.getParameter("id");
                String type = request.getParameter("type");
                String location = request.getParameter("location");
                int pid = Integer.valueOf(id);
                polygon.setPid(pid);
                polygon.setName(type);
                polygon.setLocation(location);
                polygonService.updatePolygon(polygon);
            }
            else if(action.equals("delete")){   //删除
                String id = request.getParameter("id");
                int pid=Integer.valueOf(id);
                polygonService.deleteById(pid);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
}

