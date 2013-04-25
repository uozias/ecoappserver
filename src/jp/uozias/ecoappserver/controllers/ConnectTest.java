package jp.uozias.ecoappserver.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.uozias.ecoappserver.CommonParameter;
import jp.uozias.ecoappserver.HttpConnect;
import jp.uozias.ecoappserver.ServerResult;
import jp.uozias.ecoappserver.UnauthorizedException;



public class ConnectTest extends HttpServlet {

	private CommonParameter commonParameter = null;
	private String authKey = "4152f3a11be3e1b4f365e4f83c325984";

    public ConnectTest() {
        super();

    }
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException{

		//CommonParameter初期化　web.xmlに書いたパラメータ一式を持ってくる
		CommonParameter commonParameter = new CommonParameter();
		commonParameter.setUseProxy(Integer.parseInt(getInitParameter("useProxy")));
		commonParameter.setTargetHost(getInitParameter("targetHost"));
		commonParameter.setTargetPort(Integer.parseInt(getInitParameter("targetPort")));
		commonParameter.setTargetScheme(getInitParameter("targetScheme"));
		commonParameter.setTargetUri(getInitParameter("targetUri"));
		commonParameter.setProxyHost(getInitParameter("proxyHost"));
		commonParameter.setProxyPort(Integer.parseInt(getInitParameter("proxyPort")));
		commonParameter.setProxyScheme(getInitParameter("proxyScheme"));
		commonParameter.setApplicationId(getInitParameter("applicationId"));
		commonParameter.setMaxStayRange(Integer.parseInt(getInitParameter("maxStayRange")));
		commonParameter.setMaxStayAccuracy(Integer.parseInt(getInitParameter("maxStayAccuracy")));
		commonParameter.setMinStayTime(Integer.parseInt(getInitParameter("minStayTime")));
    	commonParameter.setOutputLog(getInitParameter("outputLog"));

    	ServletContext app = getServletContext();
    	app.setAttribute(CommonParameter.ATTRIBUTE_NAME, commonParameter);

		//パラメータ群 app_idとか
		commonParameter= (CommonParameter) getServletContext().getAttribute(CommonParameter.ATTRIBUTE_NAME);

		HttpConnect connect = new HttpConnect(commonParameter, authKey);

		Map<String, String> params = new HashMap<String, String>();
		params.put("count", "0");
		params.put("self", null);
		Class<ServerResult<List<LinkedHashMap<String, Object>>>> responseType = (Class<ServerResult<List<LinkedHashMap<String, Object>>>>) new ServerResult<List<LinkedHashMap<String, Object>>>().getClass();

		ServerResult<List<LinkedHashMap<String, Object>>> result = null;
		try{
			result = (ServerResult<List<LinkedHashMap<String, Object>>>)connect.get("pubs/comment", params, responseType);
		}catch(Exception e){
			throw new ServletException( this.getClass().toString()+"にて 通信エラー", e);
		}
		if(result == null){
			throw new ServletException(this.getClass().toString()+"Gにて　サーバからの応答取得失敗");
		}
		int code = result.getResultCode();
		if(code == -10003){
			// 認証エラー
			throw new UnauthorizedException(this.getClass().toString()+"にて　認証エラー：" + code + "/" + result.getResultMessage());
		}else if(code < 0){
			// 通信結果がエラー
			throw new ServletException(this.getClass().toString()+"にて　サーバからの応答がエラー：" + code + "/" + result.getResultMessage());
		}




		response.setContentType("text/html; charset=\"UTF-8\"");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.close();

	 }


}
