package com.liuhai.rpc.handler;

import com.liuhai.rpc.serialize.ISerialize;
import com.liuhai.rpc.transbean.RpcResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 2020/2/18
 * Time: 22:16
 * Description: No Description
 */
public class RpcResponseEnCoder extends MessageToByteEncoder<RpcResponse> {

    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    private ISerialize serialize;

    public RpcResponseEnCoder(ISerialize serialize) {
        this.serialize = serialize;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcResponse msg, ByteBuf out) throws Exception {
        int startIdx = out.writerIndex();

        ByteBufOutputStream bout = new ByteBufOutputStream(out);
        try {
            bout.write(LENGTH_PLACEHOLDER);
            byte[] serialize = this.serialize.serialize(msg);
            bout.write(serialize);
            bout.flush();
        } finally {
            bout.close();
        }

        int endIdx = out.writerIndex();

        out.setInt(startIdx, endIdx - startIdx - 4);
    }
}
