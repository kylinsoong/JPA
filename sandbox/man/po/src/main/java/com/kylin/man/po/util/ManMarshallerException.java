package com.kylin.man.po.util;

public class ManMarshallerException extends RuntimeException {

	private static final long serialVersionUID = -8977113957115905268L;

	public ManMarshallerException(Throwable t) {
		super("Marshaller meet error", t);
	}

}
