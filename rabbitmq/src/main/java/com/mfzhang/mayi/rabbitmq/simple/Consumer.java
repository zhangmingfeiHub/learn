package com.mfzhang.mayi.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		Consumer2.main(args);
		Consumer1.main(args);
	}

}
