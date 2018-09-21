package org.test.consultamovimiento.bean;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ResponseError")
public class ResponseError {
		
	private int code;
	private String  message;
	
	public ResponseError() {
		super();
	}

	public ResponseError(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	/*public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String mensaje = "{\"code\":115, \"message\":\"Error el parámetro msisdn es obligatorio\"}";
		ResponseError staff1 = mapper.readValue(mensaje, ResponseError.class);
		System.out.println(staff1);
		//String prueba = "{\"code\":115, \"message\":\"Error el parámetro msisdn es obligatorio\"}";
		
	}*/

}
