package jp.uozias.ecoappserver;

public class ServerResult<T> {

	/**
	 * 結果コード
	 */
	private int resultCode;

	/**
	 * 結果メッセージ
	 */
	private String resultMessage;

	/**
	 * 処理件数
	 */
	private int resultCount;

	/**
	 * 結果リソース
	 */
	private T result;

	/**
	 * @return the resultCode
	 */
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the resultMessage
	 */
	public String getResultMessage() {
		return resultMessage;
	}

	/**
	 * @param resultMessage the resultMessage to set
	 */
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	/**
	 * @return the resultCount
	 */
	public int getResultCount() {
		return resultCount;
	}

	/**
	 * @param resultCount the resultCount to set
	 */
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}

	/**
	 * @param resullt the result to set
	 */
	public void setResult(T result) {
		this.result = result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Result [resultCode=" + resultCode + ", resultMessage="
				+ resultMessage + ", resultCount=" + resultCount + ", result="
				+ result + "]";
	}
}
