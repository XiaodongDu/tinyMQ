package com.shildon.tinymq.server.codec;

import com.shildon.tinymq.constant.Constant;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author shildon
 */
public class MessageFrameDecoder extends LengthFieldBasedFrameDecoder {

    public MessageFrameDecoder() {
        super(Integer.MAX_VALUE, 0, Constant.LENGTH_FIELD_LENGTH, 0, Constant.LENGTH_FIELD_LENGTH);
    }

}
