package by.htp.courses.service.exception;

public class ServiceException extends Exception{
	private static final long serialVersionUID = -607239192536945296L;

	public ServiceException(){
		super();
	}
	
	public ServiceException(String msg){
		super(msg);
	}
	
	public ServiceException(Exception e){
		super(e);
	}
	
	public ServiceException(String msg, Exception e){
		super(msg, e);
	}

}
