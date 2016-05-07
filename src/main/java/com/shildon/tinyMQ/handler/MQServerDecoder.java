package com.shildon.tinyMQ.handler;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shildon.tinyMQ.util.SerializeUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 
 * @author shildon<shildondu@gmail.com>
 * @date May 3, 2016
 */
public class MQServerDecoder extends ByteToMessageDecoder {
	
	private Class<?> clazz;
	
	private final Log log = LogFactory.getLog(MQServerDecoder.class);
	
	public MQServerDecoder(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		log.debug("enter mq server decode ...");
		int size = in.readableBytes();
		byte[] data = new byte[size];
		in.readBytes(data);
		Object obj = SerializeUtil.deserialize(data, clazz);
		out.add(obj);
	}

}
