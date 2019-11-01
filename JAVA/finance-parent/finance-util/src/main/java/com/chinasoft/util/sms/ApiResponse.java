package com.chinasoft.util.sms;

public class ApiResponse {
	
	private String code;
	
	private String error;
	
	private boolean success;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ApiResponse [code=" + code + ", error=" + error + ", success=" + success + "]";
	}

	
}
