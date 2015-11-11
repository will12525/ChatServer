import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by lawrencew on 11/10/2015.
 */
public class ChatServer {

    public static void main(String[] args) throws Exception{
        new ChatServer(8000).run();
    }

    private final int port;
    public ChatServer(int port)
    {
        this.port=port;
    }
    public void run() throws Exception
    {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap bootS = new ServerBootstrap().group(bossGroup,workGroup).channel(NioServerSocketChannel.class).childHandler(new ChatServerInitializer());

            bootS.bind(port).sync().channel().closeFuture().sync();
        }
        finally{
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
