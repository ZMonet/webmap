package cn.wmap.action;

import cn.common.action.GenericActionSupport;
import cn.wmap.entity.PolygonWeb;
import cn.wmap.service.PolygonService;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static com.opensymphony.xwork2.Action.SUCCESS;


public class RedrawAction extends GenericActionSupport implements ServletRequestAware{
    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    private String result;
    private PolygonWeb polygonWeb;
    private PolygonService polygonService;


    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public void setPolygonService(PolygonService polygonService) {
        this.polygonService = polygonService;
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
