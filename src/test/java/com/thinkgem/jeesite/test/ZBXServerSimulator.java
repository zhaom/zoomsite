package com.thinkgem.jeesite.test;

import org.apache.commons.lang3.ArrayUtils;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by johnKee on 2016/7/10.
 */
public class ZBXServerSimulator {
    public static void main(String[] args) throws Exception {
        ChannelFactory factory =
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool());

        ServerBootstrap bootstrap = new ServerBootstrap(factory);

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() {
                return Channels.pipeline(new DiscardServerHandler());
            }
        });

        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);

        bootstrap.bind(new InetSocketAddress(10055));
    }
}

class DiscardServerHandler extends SimpleChannelHandler {

    private final ChannelBuffer buf = ChannelBuffers.dynamicBuffer();

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();
        Channel ch = e.getChannel();
        ch.close();
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        ChannelBuffer fragbuf = (ChannelBuffer) e.getMessage();

        buf.writeBytes(fragbuf);


        byte[] header = new byte[5];
        byte[] len = new byte[8];
        buf.getBytes(0, header, 0, 5);
        buf.getBytes(5, len, 0, 8);
        ArrayUtils.reverse(len);
        long contentLen = bytes2Long(len);
        if(contentLen  > buf.readableBytes()){
            System.out.println("received an fragment");
            return ;
        }

        byte[] content = new byte[(int) contentLen];
        buf.getBytes(13, content, 0, (int) contentLen);
        buf.clear();
        String jsonContent = new String(content);
        System.out.println("header-->" + new String(header));
        System.out.println("len-->" + contentLen);
        System.out.println("json-->" + jsonContent);

        String resJsonStr = "";
       if(jsonContent.contains("\"request\":\"active checks\"")) {
           resJsonStr = "{\"response\":\"success\",\"data\":[{\"key\":\"agent.hostname\",\"delay\":3600,\"lastlogsize\":0,\"mtime\":0},{\"key\":\"agent.ping\",\"delay\":60,\"lastlogsize\":0,\"mtime\":0},{\"key\":\"agent.version\",\"delay\":3600,\"lastlogsize\":0,\"mtime\":0},{\"key\":\"system.uname\",\"delay\":3600,\"lastlogsize\":0,\"mtime\":0},{\"key\":\"agent.hostname\",\"delay\":3600,\"lastlogsize\":0,\"mtime\":0},{\"key\":\"agent.version\",\"delay\":3600,\"lastlogsize\":0,\"mtime\":0},{\"key\":\"perf_counter[\\234(_Total)\\1402]\",\"delay\":60,\"lastlogsize\":0,\"mtime\":0},{\"key\":\"perf_counter[\\234(_Total)\\1404]\",\"delay\":60,\"lastlogsize\":0,\"mtime\":0},{\"key\":\"vm.memory.size[free]\",\"delay\":60,\"lastlogsize\":0,\"mtime\":0},{\"key\":\"proc.num[]\",\"delay\":60,\"lastlogsize\":0,\"mtime\":0},{\"key\":\"perf_counter[\\2\\250]\",\"delay\":60,\"lastlogsize\":0,\"mtime\":0},{\"key\":\"vm.memory.size[total]\",\"delay\":600,\"lastlogsize\":0,\"mtime\":0},{\"key\":\"system.swap.size[,total]\",\"delay\":600,\"lastlogsize\":0,\"mtime\":0}]}";
       }else if(jsonContent.contains("\"request\":\"agent data\"")){
           resJsonStr ="{\"response\":\"success\",\"info\":\"processed: 13; failed: 0; total: 13; seconds spent: 0.003534\"}";
       }
        byte[] respByte = resJsonStr.getBytes();
        long respLen = respByte.length;
        byte[] respLenByte = long2Bytes(respLen);
        ArrayUtils.reverse(respLenByte);
        System.out.println("response str len-->" + resJsonStr.length());
        System.out.println("response byte len-->" + respByte.length);

        ChannelBuffer channelBuffer = ChannelBuffers.buffer((int)respLen+13);

        byte[] respContent = new byte[(int) respLen + 13];
        System.arraycopy(header, 0, respContent, 0, 5);
        System.arraycopy(respLenByte, 0, respContent, 5, 8);
        System.arraycopy(respByte, 0, respContent, 13, (int) respLen);

        channelBuffer.writeBytes(respContent);

        Channel ch = e.getChannel();
        ChannelFuture f = ch.write(channelBuffer);

        f.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
                Channel ch = future.getChannel();
                ch.close();
            }
        });
    }


    private byte[] long2Bytes(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    private long bytes2Long(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }

}
