package com.mfzhang.mayi.rabbitmq.service.impl;

import org.springframework.stereotype.Service;

import com.mfzhang.mayi.rabbitmq.service.RecvMessage;

@Service("recvMessage")
public class RecvMessageImpl implements RecvMessage {

	@Override
	public void recv(String msg) {
		System.err.println("recv msg: " + msg);
	}

}
