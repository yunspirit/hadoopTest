package copy;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;


public class ClientTest1 {
    public void connect(String host ,int port) throws Exception{
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            /**
             *EventLoop的组
             */
            b.group(worker);
            /**
             * 用于构造socketchannel工厂
             */
            b.channel(NioSocketChannel.class);
            /**设置选项
             * 参数：Socket的标准参数（key，value），可自行百度
             保持呼吸，不要断气！
             * */
            // b.option(ChannelOption.SO_KEEPALIVE, true);
            /**
             * 自定义客户端Handle（客户端在这里搞事情）
             */
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientHandlerDemo1());
                }
            });
            /** 开启客户端监听*/
            final ChannelFuture f = b.connect(host, port).sync();
            f.addListener(new GenericFutureListener<Future<? super Void>>() {
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if(future.isSuccess() ){
                        System.out.println("云谦测试future Listener成功");
                    }else {
                        Throwable e = f.cause();
                        System.out.println("yunqian error is"+ e);
                        System.out.println("云谦测试future Listener失败");
                    }
                }
            });
            /**等待数据直到客户端关闭*/
            f.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
        }
    }



    public static void main(String[] args) throws Exception {
        ClientTest1 client=new ClientTest1();
        client.connect("127.0.0.1", 9999);

    }
}
