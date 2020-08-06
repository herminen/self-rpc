package com.liuhai.rpc.handler;

import com.liuhai.rpc.serialize.ISerialize;
import com.liuhai.rpc.transbean.RpcRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 2020/2/18
 * Time: 21:53
 * Description: No Description
 */
public class RpcRequestDecoder extends ByteToMessageDecoder {

    private ISerialize serialize;

    public RpcRequestDecoder(ISerialize serialize) {
        this.serialize = serialize;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readableBytes = in.readableBytes();
        // 数据错误
        if(readableBytes < 4){
            return;
        }
        int contentLength = in.readInt();

        //数据不够
        if(readableBytes - 4 < contentLength){
            return;
        }

        while(readableBytes >= contentLength){
            int readerIndex = in.readerIndex();
            ByteBuf frame = in.retainedSlice(readerIndex, contentLength);
            in.readerIndex(readerIndex + contentLength);
            byte[] content = new byte[contentLength];
            frame.readBytes(content);
            RpcRequest request = serialize.deSerialize(content, RpcRequest.class);
            out.add(request);
            readableBytes = in.readableBytes();
            if(readableBytes > 4){
                contentLength = in.readInt();
            }
        }


    }
}
