package jp.uozias.ecoappserver;

import javax.servlet.ServletException;

public class UnauthorizedException extends ServletException {

	private static final long serialVersionUID = 1L;
	public UnauthorizedException(){
		super();
	}
	public UnauthorizedException(String string) {
		super(string);
	}
	public UnauthorizedException(Throwable e){
		super(e);
	}
	public UnauthorizedException(String string, Throwable e){
		super(string, e);
	}

}
