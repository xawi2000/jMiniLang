package priv.bajdcc.LALR1.interpret.module.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.Queue;

import static priv.bajdcc.LALR1.interpret.module.net.ModuleNetServer.CHANNEL_GROUP;

/**
 * 【模块】通讯客户端处理
 *
 * @author bajdcc
 */
public class ModuleNetClientHandler extends ChannelInboundHandlerAdapter {

    private Queue<String> msgQueue;

    public ModuleNetClientHandler(Queue<String> msgQueue) {
        this.msgQueue = msgQueue;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel ch = ctx.channel();
        ctx.writeAndFlush(String.format("{ \"addr\": \"%s\", \"type\": \"MSG \", \"content\": \"Hello, server!\" }\r\n",
                ch.localAddress().toString()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        msgQueue.add(String.valueOf(msg));
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        CHANNEL_GROUP.remove(ctx.channel());
        ctx.close();
    }
}