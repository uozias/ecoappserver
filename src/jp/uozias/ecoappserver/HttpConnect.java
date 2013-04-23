package jp.uozias.ecoappserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;

public class HttpConnect {

	/** Httpヘッダ:X-Application-id */
	public static final String HTTP_HEADER_APPLICATION_ID = "X-Application-id";
	/** Httpヘッダ:X-Auth-key */
	public static final String HTTP_HEADER_AUTH_KEY = "X-Auth-key";

	/* 共通パラメータ */
	private CommonParameter commonParameter = null;
	/* 認証キー */
	private String authKey = null;

	/**
	 * コンストラクタ
	 * @param params 共通パラメータ
	 * @param applicationId アプリケーションID
	 */
	public HttpConnect(CommonParameter commonParameter){
		this.commonParameter = commonParameter;
	}

	/**
	 * コンストラクタ
	 * @param params 共通パラメータ
	 * @param applicationId アプリケーションID
	 * @param authKey 認証キー
	 */
	public HttpConnect(CommonParameter commonParameter, String authKey){
		this.commonParameter = commonParameter;
		this.authKey = authKey;
	}

	/**
	 * POSTリクエストの送信.
	 * リクエストボディはJSON形式となる
	 * @param resourceName リソース名
	 * @param requestObject リクエストパラメータが格納されたObject
	 * @param responseType レスポンスパラメータを格納するClass
	 * @return レスポンスObject
	 * @throws ClientProtocolException 接続エラー
	 * @throws IOException 接続エラー、レスポンス取得エラー
	 * @throws URISyntaxException リクエストURIの文法エラー
	 */
	public Object post(String resourceName, Object requestObject, Class<?> responseType) throws ClientProtocolException, IOException, URISyntaxException{

		String path = "/" + commonParameter.getTargetUri() + "/" + resourceName;
		URI uri = new URI(commonParameter.getTargetScheme(), null, commonParameter.getTargetHost(), commonParameter.getTargetPort(), path, null, null);
		HttpPost request = new HttpPost(uri);

		// ボディ部の生成
		request.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(requestObject), ContentType.APPLICATION_JSON));
		return connect(request, responseType);
	}

	/**
	 * GETリクエストの送信.
	 * @param resourceName リソース名
	 * @param params リクエストパラメータが格納されたMap
	 * @param responseType レスポンスパラメータを格納するClass
	 * @return レスポンスObject
	 * @throws ClientProtocolException 接続エラー
	 * @throws IOException 接続エラー、レスポンス取得エラー
	 * @throws URISyntaxException リクエストURIの文法エラー
	 */
	public Object get(String resourceName, Map<String, String> params, Class<?> responseType) throws ClientProtocolException, IOException, URISyntaxException{

		String path = "/" + commonParameter.getTargetUri() + "/" + resourceName;
		String query = null;
		if (params != null) {
			List<NameValuePair> httpparams = new ArrayList<NameValuePair>();
			Set<Entry<String, String>> entries = params.entrySet();
			for (Entry<String, String> entry : entries) {
				httpparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			query = URLEncodedUtils.format(httpparams, "UTF-8");
		}
		URI uri = new URI(commonParameter.getTargetScheme(), null, commonParameter.getTargetHost(), commonParameter.getTargetPort(), path, query, null);
		HttpGet request = new HttpGet(uri);

		return connect(request, responseType);
	}

	/**
	 * DELETEリクエストの送信.
	 * @param resourceName リソース名
	 * @param resourceId リソースID
	 * @param responseType レスポンスパラメータを格納するClass
	 * @return レスポンスObject
	 * @throws ClientProtocolException 接続エラー
	 * @throws IOException 接続エラー、レスポンス取得エラー
	 * @throws URISyntaxException リクエストURIの文法エラー
	 */
	public Object delete(String resourceName, String resourceId, Class<?> responseType) throws ClientProtocolException, IOException, URISyntaxException{

		String path = "/" + commonParameter.getTargetUri() + "/" + resourceName + "/" + resourceId;
		URI uri = new URI(commonParameter.getTargetScheme(), null, commonParameter.getTargetHost(), commonParameter.getTargetPort(), path, null, null);
		HttpDelete request = new HttpDelete(uri);

		return connect(request, responseType);
	}

	private Object connect(HttpRequestBase request, Class<?> responseType) throws ClientProtocolException, IOException{

		DefaultHttpClient httpClient = null;
		InputStream is = null;
		Object responseObject = null;

		try{

			httpClient = new DefaultHttpClient();

			if(commonParameter.getUseProxy() != 0){
				HttpHost proxy = new HttpHost(commonParameter.getProxyHost(), commonParameter.getProxyPort(), commonParameter.getProxyScheme());
				httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			}

			if(commonParameter.getApplicationId() != null){
				request.addHeader(HTTP_HEADER_APPLICATION_ID, commonParameter.getApplicationId());
			}
			if(authKey != null){
				request.addHeader(HTTP_HEADER_AUTH_KEY, authKey);
			}

			HttpResponse response = httpClient.execute(request);
			if(response.getStatusLine().getStatusCode() == 200){
				HttpEntity entity = response.getEntity();

				if(entity != null){
					is = entity.getContent();

					responseObject = new ObjectMapper().readValue(is, responseType);
				}
			}

		}finally{
			httpClient.getConnectionManager().shutdown();

			if(is != null){
				is.close();
			}
		}

		return responseObject;
	}

	/**
	 * @return authKey
	 */
	public String getAuthKey() {
		return authKey;
	}

	/**
	 * @param authKey セットする authKey
	 */
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
}
