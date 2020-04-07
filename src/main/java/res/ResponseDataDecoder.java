package res;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {
    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        ResponseData data = new ResponseData(0, "NotDecoded");
        data.setIntValue(in.readInt());
        int strLen = in.readInt();
        data.setsValue(in.readCharSequence(strLen, charset).toString());
        out.add(data);
    }
}