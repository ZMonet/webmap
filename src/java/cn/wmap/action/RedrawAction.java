package cn.wmap.action;

import cn.wmap.entity.PolygonWeb;
import cn.wmap.service.PolygonService;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static com.opensymphony.xwork2.Action.SUCCESS;


public class RedrawAction implements ServletRequestAware, ModelDriven<PolygonWeb> {
    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    private String result;
    private PolygonService polygonService;
    private JSONObject resultObj;
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public JSONObject getResultObj() {
        return resultObj;
    }
    public void setResultObj(JSONObject resultObj) {
        this.resultObj = resultObj;
    }
    //封装数据
    private PolygonWeb polygonWeb = new PolygonWeb();
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }


    public void setPolygonService(PolygonService polygonService) {
        this.polygonService = polygonService;
    }

    @Override
    public PolygonWeb getModel() {
        return this.polygonWeb;
    }


    public String redraw(){
        //将要被返回到客户端的对象
        List<PolygonWeb> list = this.polygonService.findAll();
        JSONObject json = new JSONObject();
        String rejson = "";
        int nCount = list.size();
        for(int i = 0; i < nCount; i++){
            String type =  list.iterator().next().getName();
            String path = list.iterator().next().getLocation();
            list.remove(0);
            rejson += type + ":" + path+";";
        }
        System.out.println(rejson);
        json.put("allpath",rejson);
        this.setResult(json.toString());
        //将数据以json字符串形式传到请求页面end
        return SUCCESS;
    }
}
