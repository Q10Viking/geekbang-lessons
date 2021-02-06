import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args){
        final String CLIENT_QUIT = "quit";
        final int DEFAULT_PORT = 9898;
        ServerSocket serverSocket = null;

        try {
            //  绑定监听端口
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.printf("服务器已成功启动，监听端口%d\n",DEFAULT_PORT);

            while(true){
                //  等待客户端连接
                Socket clientSocket = serverSocket.accept();
                System.out.printf("客户端【%d】已连接\n",clientSocket.getPort());

                //  获取输入流
                BufferedReader clientReader = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())
                );

                //  获取输出流
                BufferedWriter clientWriter = new BufferedWriter(
                        new OutputStreamWriter(clientSocket.getOutputStream())
                );

                String msg = null;
                //  会阻塞，直到读取到数据
                while((msg = clientReader.readLine()) != null){
                    //  读取客户端数据
                    System.out.printf("客户端【%d】: %s\n",clientSocket.getPort(),msg);

                    //  回复客户发送的消息
                    clientWriter.write(msg+"\n");
                    clientWriter.flush();   //  完成写入操作

                    //  查看客户端是否退出
                    if(CLIENT_QUIT.equals(msg)){
                        System.out.printf("客户端【%d】已退出\n",clientSocket.getPort());
                    }
                }
            }
        } catch (IOException e) {
            System.out.printf("【服务器在端口%d启动失败】",DEFAULT_PORT);
            e.printStackTrace();
        }finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                    System.out.println("服务器关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
/**
 服务器已成功启动，监听端口9898
 客户端【7659】已连接
 客户端【7659】: hello World
 客户端【7659】: quit
 客户端【7659】已退出
 客户端【7752】已连接
 客户端【7752】: 你好
 客户端【7752】: quit
 客户端【7752】已退出
 */