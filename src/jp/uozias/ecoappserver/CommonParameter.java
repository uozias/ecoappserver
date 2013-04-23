package jp.uozias.ecoappserver;

public class CommonParameter {

	/** 格納Context名 */
	public static final String ATTRIBUTE_NAME = "CommonParameter";

	/** 地球の半径 */
	public static final double RADIUS_OF_EARTH = 6378.137 * 1000;

	/**ライフログデータ活用システム*/
	public static final String MSG_E0001 = "E0001";// システムエラーが発生しました。
	public static final String MSG_E0002 = "E0002";// ログインに失敗しました。
	public static final String MSG_W0001 = "W0001";// %1は登録されていません。
	public static final String MSG_W0002 = "W0002";// %1を入力してください。
	public static final String MSG_W0003 = "W0003";// サービスを有効にする場合は、ワイヤレスネットワーク又はGPS機能を有効にしてください。
	public static final String MSG_W0004 = "W0004";// ユーザ名もしくはパスワードに誤りがあります。
	/**ロケーションプライバシー保護アプリ*/
	public static final String MSG_E9001 = "E9001";// システムエラーが発生しました。
	public static final String MSG_E9002 = "E9002";// ログインに失敗しました。
	public static final String MSG_W9002 = "W9002";// 検索結果が0件でした。
	public static final String MSG_W9003 = "W9003";// 入力住所が正しくありません。
	public static final String MSG_W9004 = "W9004";// %1を入力してください。
	public static final String MSG_W9005 = "W9005";// ユーザ名もしくはパスワードに誤りがあります。
	public static final String MSG_I9001 = "I9001";// %1件結果を取得しました。

	//処理ステータス
	public static final int STATUS_OK = 0;//正常
	public static final int STATUS_NG = 1;//異常
	public static final int STATUS_WARNING = 2;//警告


	/* Proxyの使用 */
	private int useProxy = 0;
	/* 接続先ホスト */
	private String targetHost = null;
	/* 接続先ポート */
	private int targetPort = 80;
	/* 接続先スキーマ */
	private String targetScheme = "http";
	/* 接続先URI */
	private String targetUri = null;
	/* proxyホスト */
	private String proxyHost = null;
	/* proxyポート */
	private int proxyPort = 80;
	/* proxyスキーマ */
	private String proxyScheme = "http";
	/* アプリケーションID */
	private String applicationId = null;
	/* 最大滞留点半径 */
	private int maxStayRange = 0;
	/* 最大滞留点平均誤差 */
	private int maxStayAccuracy = 0;
	/* 最小滞留時間 */
	private int minStayTime = 0;
	/* ログ出力先 */
	private String outputLog = null;

	/**
	 * @return useProxy
	 */
	public int getUseProxy() {
		return useProxy;
	}

	/**
	 * @param useProxy セットする useProxy
	 */
	public void setUseProxy(int useProxy) {
		this.useProxy = useProxy;
	}

	/**
	 * @return targetHost
	 */
	public String getTargetHost() {
		return targetHost;
	}

	/**
	 * @param targetHost セットする targetHost
	 */
	public void setTargetHost(String targetHost) {
		this.targetHost = targetHost;
	}

	/**
	 * @return targetPort
	 */
	public int getTargetPort() {
		return targetPort;
	}

	/**
	 * @param targetPort セットする targetPort
	 */
	public void setTargetPort(int targetPort) {
		this.targetPort = targetPort;
	}

	/**
	 * @return targetScheme
	 */
	public String getTargetScheme() {
		return targetScheme;
	}

	/**
	 * @param targetScheme セットする targetScheme
	 */
	public void setTargetScheme(String targetScheme) {
		this.targetScheme = targetScheme;
	}

	/**
	 * @return targetUri
	 */
	public String getTargetUri() {
		return targetUri;
	}

	/**
	 * @param targetUri セットする targetUri
	 */
	public void setTargetUri(String targetUri) {
		this.targetUri = targetUri;
	}

	/**
	 * @return proxyHost
	 */
	public String getProxyHost() {
		return proxyHost;
	}

	/**
	 * @param proxyHost セットする proxyHost
	 */
	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	/**
	 * @return proxyPort
	 */
	public int getProxyPort() {
		return proxyPort;
	}

	/**
	 * @param proxyPort セットする proxyPort
	 */
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	/**
	 * @return proxyScheme
	 */
	public String getProxyScheme() {
		return proxyScheme;
	}

	/**
	 * @param proxyScheme セットする proxyScheme
	 */
	public void setProxyScheme(String proxyScheme) {
		this.proxyScheme = proxyScheme;
	}

	/**
	 * @return applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId セットする applicationId
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return maxStayRange
	 */
	public int getMaxStayRange() {
		return maxStayRange;
	}

	/**
	 * @param maxStayRange セットする maxStayRange
	 */
	public void setMaxStayRange(int maxStayRange) {
		this.maxStayRange = maxStayRange;
	}

	/**
	 * @return maxStayAccuracy
	 */
	public int getMaxStayAccuracy() {
		return maxStayAccuracy;
	}

	/**
	 * @param maxStayAccuracy セットする maxStayAccuracy
	 */
	public void setMaxStayAccuracy(int maxStayAccuracy) {
		this.maxStayAccuracy = maxStayAccuracy;
	}

	/**
	 * @return minStayTime
	 */
	public int getMinStayTime() {
		return minStayTime;
	}

	/**
	 * @param minStayTime セットする minStayTime
	 */
	public void setMinStayTime(int minStayTime) {
		this.minStayTime = minStayTime;
	}

	/**
	 * @return outputLog
	 */
	public String getOutputLog() {
		return outputLog;
	}

	/**
	 * @param outputLog セットする outputLog
	 */
	public void setOutputLog(String outputLog) {
		this.outputLog = outputLog;
	}

}
