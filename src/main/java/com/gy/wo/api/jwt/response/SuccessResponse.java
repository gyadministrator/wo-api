package com.gy.wo.api.jwt.response;

import com.gy.wo.api.jwt.response.Response;

public class SuccessResponse implements Response {

	@Override
	public String getResponseCode() {
		return Response.CODE_SUCCESS;
	}

	@Override
	public String getResponseMsg() {
		return Response.MSG_SUCCESS;
	}

}
