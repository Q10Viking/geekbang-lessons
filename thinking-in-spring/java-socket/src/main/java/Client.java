import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String QUIT = "quit";
        final int SERVER_PORT = 9898;
        final String SERVER_ADDRESS = "127.0.0.1";

        Socket socket = null;
        BufferedWriter writer = null;

        try {
            //  创建socket
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("已成功连接到服务器");

            //  获取IO输入流
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            //  获取IO输出流
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );

            Scanner consoleReader = new Scanner(System.in);
            //  等待用户输入信息
            while(consoleReader.hasNext()){
                String msg = consoleReader.nextLine();
                //  发送消息给服务器
                writer.write(msg+"\n");
                //  发送的数据完成
                writer.flush();

                //  // 读取服务器返回的消息
                String feedBack = reader.readLine();
                System.out.printf("接收到服务器：%s\n",feedBack);

                //  查看用户是否退出
                if(QUIT.equals(msg)){
                    break;
                }
            }

        } catch (IOException e) {
            System.out.printf("连接服务器【%s:%d】失败",SERVER_ADDRESS,SERVER_PORT);
            e.printStackTrace();
        }finally {
            if(writer != null){
                try {
                    //  writer关闭也就关闭了Socket
                    writer.close();
                    if(socket != null){
                        //  true
                        System.out.printf("socket关闭了吗? %s\n",socket.isClosed());
                    }
                } catch (IOException e) {
                    System.out.println("客户端关闭异常");
                    e.printStackTrace();
                }
            }
            System.out.println("客户端退出");
        }
    }
}
/**
 已成功连接到服务器
 你好
 接收到服务器：你好
 quit
 接收到服务器：quit
 客户端退出
 */