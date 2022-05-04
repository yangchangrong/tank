//package com.ycr.jse;
//
//import com.ycr.jse.netty.msg.TankMsg;
//import com.ycr.jse.netty.msg.TankMsgDecoder;
//import com.ycr.jse.netty.msg.TankMsgEncoder;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.embedded.EmbeddedChannel;
//import org.junit.Assert;
//import org.junit.Test;
//
//public class TankMsgCodecTest {
//
//    @Test
//    public void testTankMsgEncoder(){
//        TankMsg tankMsg = new TankMsg(10, 8);
//        EmbeddedChannel channel = new EmbeddedChannel(new TankMsgEncoder());
//        channel.writeOutbound(tankMsg);
//        //读取
//        ByteBuf bf = (ByteBuf)channel.readOutbound();
//        int x = bf.readInt();
//        int y = bf.readInt();
//
//        Assert.assertTrue(x == 10 && y == 8);
//
//    }
//
//    @Test
//    public void testTankMsgDecoder(){
//        TankMsg tankMsg = new TankMsg(10, 8);
//        ByteBuf buf = Unpooled.buffer();
//        buf.writeInt(tankMsg.getX());
//        buf.writeInt(tankMsg.getY());
//        EmbeddedChannel channel = new EmbeddedChannel(new TankMsgDecoder());
//        channel.writeInbound(buf);
//        //读取
//        TankMsg tankMsgread = (TankMsg) channel.readInbound();
//
//        Assert.assertTrue(tankMsgread.getX() == 10 && tankMsgread.getY() == 8);
//
//    }
//}
