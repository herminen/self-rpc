package com.liuhai.testrpc;

import com.liuhai.rpc.handler.RpcResposeDecoder;
import com.liuhai.rpc.handler.RpcRequestEnCoder;
import com.liuhai.rpc.serialize.impl.Protobuf;
import com.liuhai.rpc.transbean.RpcRequest;
import com.liuhai.rpc.transbean.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 2020/2/18
 * Time: 22:40
 * Description: No Description
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup(1);

        Protobuf protobuf = new Protobuf();
        ChannelFuture future = bootstrap.group(boss)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast("rpcDecoder", new RpcResposeDecoder(protobuf))
                                .addLast("rpcEncode", new RpcRequestEnCoder(protobuf))
                                .addLast(new ChannelInboundHandlerAdapter() {

                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        for (int i = 0; i < 10; i++) {

                                            RpcRequest request = new RpcRequest();
                                            request.setRequestId("007");
                                            request.setService("com.silk.dao.UserDAO");
                                            request.setMethod("queryUser");
                                            ctx.writeAndFlush(request);
                                        }
                                    }

                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        RpcResponse<String> response = (RpcResponse<String>) msg;
                                        System.out.println(response.getInvokeResult());
//                                        ctx.close().addListener(ChannelFutureListener.CLOSE);
                                    }
                                });
                    }
                }).connect("localhost", 8866).sync();

        future.channel().closeFuture().sync();
    }
}
