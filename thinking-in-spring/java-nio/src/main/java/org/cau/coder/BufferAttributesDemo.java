package org.cau.coder;

import java.nio.ByteBuffer;

public class BufferAttributesDemo {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        printBufferAttributes(byteBuffer,"没有操作");

        //  添加5个字节
        byteBuffer.put("12345".getBytes());
        printBufferAttributes(byteBuffer,"put");

        //  flip
        byteBuffer.flip();
        printBufferAttributes(byteBuffer,"flip");

        //  get
        byte[] dst = new byte[2];
        byteBuffer.get(dst);
        printBufferAttributes(byteBuffer,"get");

        //  clear
        byteBuffer.clear();
        printBufferAttributes(byteBuffer,"clear");

    }

    private static void printBufferAttributes(ByteBuffer byteBuffer,String operation){
        System.out.println("--------------------------------------------------");
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int capacity = byteBuffer.capacity();
        System.out.printf("%s => position = %d ; limit = %d ; capacity = %d \n",
                operation,position,limit,capacity);
    }

}
/**
 --------------------------------------------------
 没有操作 => position = 0 ; limit = 10 ; capacity = 10
 --------------------------------------------------
 put => position = 5 ; limit = 10 ; capacity = 10
 --------------------------------------------------
 flip => position = 0 ; limit = 5 ; capacity = 10
 --------------------------------------------------
 get => position = 2 ; limit = 5 ; capacity = 10
 --------------------------------------------------
 clear => position = 0 ; limit = 10 ; capacity = 10
 */