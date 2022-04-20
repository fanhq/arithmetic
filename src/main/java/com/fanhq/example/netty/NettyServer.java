package com.fanhq.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Hachel on 2018/3/8
 */
public class NettyServer {


    public static void main(String args[]) throws Exception {
        // 分发任务
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 实际处理的
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 启动服务
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("testServerInHandler", new TestServerInHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = serverBootstrap.bind(15000).sync();
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("success");
                } else {
                    System.out.println("fail");
                }
            });
            channelFuture.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static class TestServerInHandler extends SimpleChannelInboundHandler<ByteBuf> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            System.out.println(ctx.name());
            byte[] data = new byte[msg.readableBytes()];
            msg.readBytes(data);
            System.out.println("client request: " + bytesToHex(data));
            // ByteBuf content = Unpooled.copiedBuffer("i am server", CharsetUtil.UTF_8);
            ctx.writeAndFlush(data);
        }


        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            //System.out.println("execute channelActive");
            super.channelActive(ctx);
        }

        /**
         * 字节数组转16进制
         *
         * @param bytes 需要转换的byte数组
         * @return 转换后的Hex字符串
         */
        public String bytesToHex(byte[] bytes) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(bytes[i] & 0xFF);
                if (hex.length() < 2) {
                    sb.append(0);
                }
                sb.append(hex);
            }
            return sb.toString().toUpperCase();
        }
    }

}
