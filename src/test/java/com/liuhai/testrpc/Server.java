package com.liuhai.testrpc;

import com.liuhai.rpc.handler.RpcRequestDecoder;
import com.liuhai.rpc.handler.RpcResponseEnCoder;
import com.liuhai.rpc.serialize.impl.Protobuf;
import com.liuhai.rpc.transbean.RpcRequest;
import com.liuhai.rpc.transbean.RpcResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 2020/2/18
 * Time: 22:24
 * Description: No Description
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup(4);

        Protobuf protobuf = new Protobuf();
        ChannelFuture future = serverBootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast("rpcDecoder", new RpcRequestDecoder(protobuf))
                                .addLast("rpcEncode", new RpcResponseEnCoder(protobuf))
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        RpcRequest request = (RpcRequest) msg;
                                        System.out.println(request);

                                        RpcResponse<String> response = new RpcResponse<String>();
                                        response.setResponseId("008");
                                        response.setGnvokeResult("hello xiao wan!");
                                        ctx.writeAndFlush(response);
                                    }
                                });
                    }
                }).bind(8866).sync();
        future.channel().closeFuture().sync();
    }
}
