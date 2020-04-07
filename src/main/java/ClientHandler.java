import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import req.RequestData;
import res.ResponseData;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private ChannelHandlerContext ctx;
    private static int COUNTER = 1;
    private boolean started = false;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        ChannelFuture future = sendMessage("Ping", 0);
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println((ResponseData) msg);
        if (!started){
            startThread();
        }

    }

    private static RequestData createMessage(String s, int i) {
        RequestData msg = new RequestData();
        msg.setIntValue(i);
        msg.setStringValue(s);
        return msg;

    }

    public void close() {
        ctx.close();
    }

    public ChannelFuture sendMessage(String s, int i) {
        return ctx.writeAndFlush(createMessage(s, i));
    }

    public void startThread(){
        started = true;
        Thread thread = new Thread() {
            public void run() {
                boolean isOPen = true;
                while (isOPen) {
                    if (COUNTER < 100) {
                        sendMessage("Ping", COUNTER);
                        COUNTER += 1;
                        try {
                            Thread.sleep(1000, 0);
                        } catch (InterruptedException e) {
                            isOPen = false;
                            ctx.close();
                            System.out.println("Closed Thread");
                        }
                    }
                }
            }
        };

        thread.start();
    }
}

