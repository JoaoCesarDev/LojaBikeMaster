/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacao;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jcrfm
 */
public class Validador {
    // Garante que quantidade inserida é um numero entre 0 e 99;
    // aplica a quantidade campo na página do carrinho
    
    public boolean quantidadeValida(String produtoId, String quantidade){
        
        boolean errorFlag = false;
        
        if(!produtoId.isEmpty() && !quantidade.isEmpty()){
            
            int i = -1;
            
            try{
               i = Integer.parseInt(quantidade);
            }catch(NumberFormatException nfe){
                System.out.println("Usuário não digitou um número no campo quantidade");
            }
            
            if(i < 0 || i > 99){
                
                errorFlag = true;
            }
        }
        return errorFlag;
    }
    
    // Executa simples valicação na verificação de saída
    public boolean validacaoform(String nome,String email,String telefone,String endereco,String bairro,String estado,String cep,HttpServletRequest request){
        
        boolean errorFlag = false;
        boolean nomeError;
        boolean emailError;
        boolean telefoneError;
        boolean enderecoError;
        boolean bairroError;
        boolean estadoError;
        boolean cepError;
        
        if (nome == null
                || nome.equals("")
                || nome.length() > 55) {
            errorFlag = true;
            nomeError = true;
            request.setAttribute("nomeError", nomeError);
        }
        if (email == null
                || email.equals("")
                || !email.contains("@")) {
            errorFlag = true;
            emailError = true;
            request.setAttribute("emailError", emailError);
        }
        if (telefone == null
                || telefone.equals("")
                || telefone.length() < 10) {
            errorFlag = true;
            telefoneError = true;
            request.setAttribute("telefoneError", telefoneError);
        }
        if (endereco == null
                || endereco.equals("")
                || endereco.length() > 55) {
            errorFlag = true;
            enderecoError = true;
            request.setAttribute("enderecoError", enderecoError); 
        }
        if (bairro == null
                || bairro.equals("")
                || bairro.length() > 45) {
            errorFlag = true;
            bairroError = true;
            request.setAttribute("bairroError", bairroError);   
        }
        if (estado == null
                || estado.equals("")
                || estado.length() > 2) {
            errorFlag = true;
            estadoError = true;
            request.setAttribute("estadoError", estadoError); 
        }
        if (cep == null
                || cep.equals("")
                || cep.length() > 10) {
            errorFlag = true;
            cepError = true;
            request.setAttribute("cepError", cepError);
        }   
        return errorFlag;
        
    }
}
