package org.cau.coder.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {
    public static void main(String[] args) throws IOException {
        //  获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //  绑定端口
        serverSocketChannel.bind(new InetSocketAddress(9898));
        //  切换到非阻塞
        serverSocketChannel.configureBlocking(false);
        //  获取选择器
        Selector selector = Selector.open();
        //  将通道注册到选择器上, 并且指定“监听接收事件”
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //  轮询式的获取选择器上已经“准备就绪”的事件
        while(selector.select()>0){
            System.out.println("监听到可用的selector");
            System.out.println("keys = "+selector.keys().size());
            System.out.println("selectedkeys = "+selector.selectedKeys().size());

            //  获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while(keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //  切换为非阻塞式
                    socketChannel.configureBlocking(false);
                    System.out.println("注册连接到客户端的channel");
                    //  将该通道注册到选择器上
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    //  读取数据
                    SocketChannel socketChannel = (SocketChannel)key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel.read(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(),0,buffer.limit()));
                }
                //  取消选择键 SelectionKey 不然第二客户端发送数据来时，selectedKey除了read的selectedKey外，还有accept的key
                //  而此时并不是accept触发的事件，通过 serverSocketChannel.accept();返回的则是空
                keyIterator.remove();
            }
        }
        serverSocketChannel.close();

    }
}
/**
 监听到可用的selector
 keys = 1
 selectedkeys = 1
 注册连接到客户端的channel
 监听到可用的selector
 keys = 2
 selectedkeys = 1
 2021-02-06T13:26:41.982
 你好吗
 监听到可用的selector
 keys = 2
 selectedkeys = 1
 2021-02-06T13:27:07.621
 天道酬勤，事在人为，加油
 */