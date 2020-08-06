package com.liuhai.rpc.handler;

import com.liuhai.rpc.serialize.ISerialize;
import com.liuhai.rpc.transbean.RpcRequest;
import com.liuhai.rpc.transbean.RpcResponse;
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
public class RpcResposeDecoder extends ByteToMessageDecoder {

    private ISerialize serialize;

    public RpcResposeDecoder(ISerialize serialize) {
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
            RpcResponse response = serialize.deSerialize(content, RpcResponse.class);
            out.add(response);
            readableBytes = in.readableBytes();
            if(readableBytes > 4){
                contentLength = in.readInt();
            }
        }
//        int readerIndex = in.readerIndex();
//        int actualFrameLength = readableBytes - 4;
//        ByteBuf frame = in.retainedSlice(readerIndex, actualFrameLength);
//        in.readerIndex(readerIndex + actualFrameLength);
//        byte[] content = new byte[actualFrameLength];
//        frame.readBytes(content);
//        RpcResponse request = serialize.deSerialize(content, RpcResponse.class);
//        out.add(request);
    }
}
