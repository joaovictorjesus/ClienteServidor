package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) {
       
        
        try {
            /*1 - Estabelecer conexão com o servidor
            2 - Trocar mensagens com o servidor
            */
            
            //HELLO

            Socket socket = new Socket("localhost", 5555);
            
            //Criação dos streams de entrada e saida
            
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream() );
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream() );        
            /*ObjectInputStream input = new ObjectInputStream(socket.getInputStream() );
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream() );*/
            
            System.out.println("Enviando mensagem...");
            String msg = "Hello";
            output.writeUTF(msg);
            output.flush(); //libera buffer para envio
            
            System.out.println(msg);
            System.out.println("Mensagem enviada com sucesso!");
            
            msg = input.readUTF();
            System.out.println("RESPOSTA: " + msg);
            
            input.close();
            output.close();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
