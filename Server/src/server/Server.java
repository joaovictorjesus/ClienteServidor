package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
       /* 2.1 - Criar uma nova conexão;                        
          5 - Voltar para o passo 2, até que finalize o programa;
          6 - Fechar o ServerSocket
        */
        
    private ServerSocket serverSocket;
        //1 - Criar o servidor de conexões;
    private void criarServerSocket(int porta) throws IOException {        
        serverSocket = new ServerSocket(porta);               
    }
    
        
        /*    2 - Esperar um pedido de conexão;
            Outro processo  */
    private Socket esperaConexao() throws IOException {        
        Socket socket = serverSocket.accept();
        return socket;
    }
    
    private void fechaSocket(Socket s) throws IOException{
        s.close();
    }
    
    private void trataConexao(Socket socket) throws IOException {
        /*Protocolo da aplicação
            PONTO DE COMUNICAÇÃO -> cliente-----SOCKET-----servidor          */
        try{
            //3 - Criar streams de entrada e saida
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream() );
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream() );        
        /*ObjectInputStream input = new ObjectInputStream(socket.getInputStream() );
          ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream() );*/
        
        /*PROTOCOLO
            Cliente -> "Hello"
            Server <-  "Hello World" */
        
        //4 - Tratar a conversação entre clientes e servidor(tratar protocolo);
        String msg = input.readUTF();
        System.out.println("Mensagem Recebida...");
        output.writeUTF("Hello World!");
        output.flush();
        
           //4.1 - Fechar streams de entrada e saida
           input.close();
           output.close();
        
        } catch(IOException e){
            //TRATAMENTO DE FALHAS
            System.out.println("Proclema no tratamento da conexão com o cliente: " + socket.getInetAddress());
            System.out.println("Erro: " + e.getMessage());
        }finally{
            //Final do tratamento do protocolo                                   
            //4.2 - Fechar socket de comunicação entre servidor/cliente;
            fechaSocket(socket);
        }
    }    

    public static void main(String[] args) {
        
        try{
                   
        Server server = new Server();
        System.out.println("Aguardando Conexão...");
        server.criarServerSocket(5555);                
        Socket socket = server.esperaConexao(); //protocolo
        System.out.println("Cliente Conectado...");
        server.trataConexao(socket);
        System.out.println("Cliente Finalizado...");
        
        } catch (IOException e){
            //TRATAR EXCESSÃO
        }
        
    }
    
}
