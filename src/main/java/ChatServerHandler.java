import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by lawrencew on 11/10/2015.
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup channels = new DefaultChannelGroup("clients", GlobalEventExecutor.INSTANCE);

    public void handlerAdded(ChannelHandlerContext ctx) throws Exception{
        Channel incoming = ctx.channel();
        for(Channel channel : channels)
        {
            channel.write("[SERVER]"+ incoming.remoteAddress()+" has joined!\n");

        }
        channels.add(ctx.channel());
    }
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception
    {
        Channel incoming = ctx.channel();
        for(Channel channel : channels)
        {
            channel.write("[SERVER]"+ incoming.remoteAddress()+" has left!\n");

        }
        channels.remove(ctx.channel());
    }



    @Override
    protected void messageReceived(ChannelHandlerContext arg0, String s) throws Exception {
        Channel incoming = arg0.channel();

        for(Channel channel : channels)
        {
            if(channel != incoming)
            {
                channel.write("["+incoming.remoteAddress()+"]"+s +"\n");

            }
        }
        System.out.println("called");
    }
}
