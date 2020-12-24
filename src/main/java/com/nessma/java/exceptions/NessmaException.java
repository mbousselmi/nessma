package com.nessma.java.exceptions;

import com.nessma.java.Enum.ExceptionEnum;

public class NessmaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4157302787486683948L;
	private Integer code;

	public NessmaException(ExceptionEnum exceptionEnum) {
		super(exceptionEnum.getMessage());
		this.code = exceptionEnum.getCode();
	}
}
