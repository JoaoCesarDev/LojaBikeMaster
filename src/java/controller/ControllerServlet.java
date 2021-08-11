/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import carrinho.CarrinhoDeCompra;
import entidade.Categoria;
import entidade.Produtos;
import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import javax.ejb.EJB;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessao.CategoriaFacade;
import sessao.GerenciadorPedido;
import sessao.ProdutosFacade;
import validacao.Validador;

/**
 *
 * @author jcrfm
 */
@WebServlet(name = "Controller", 
                    loadOnStartup = 1,
                    urlPatterns = {"/categoria", 
                                   "/adicionaraocarrinho", 
                                   "/visualizarcarrinho",
                                   "/atualizarcarrinho",
                                   "/checkout",
                                   "/purchase",
                                   "/chooseLanguage"}) 

public class ControllerServlet extends HttpServlet {

    private String sobretaxa;
    
    @EJB
    private ProdutosFacade produtosFacade;
    @EJB
    private CategoriaFacade categoriaFacade;
    @EJB
    private GerenciadorPedido gerenciadorPedido;
    
    @Override
    public void  init(ServletConfig servletConfig) throws ServletException{
        
        super.init(servletConfig);
        
        // inicializa o servlet com informação de configuração
        sobretaxa = servletConfig.getServletContext().getInitParameter("deliverySurcharge");
        
        // Armazena a lista de categoria no servlet context
        getServletContext().setAttribute("categorias", categoriaFacade.findAll());
    }
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        String userPath = request.getServletPath(); 
        HttpSession session = request.getSession();  
        Categoria categoriaselecionada;
        Collection<Produtos> categoriaprodutos;
        
        // Se a página categoria é requsitada
        if (userPath.equals("/categoria")) {
           // get categoryId da solicitação
             String categoriaid = request.getQueryString();
            
            if (categoriaid != null) {
                 //obter a categoria selecionada
                categoriaselecionada = categoriaFacade.find(Short.parseShort(categoriaid));
               
                session.setAttribute ("categoriaselecionada", categoriaselecionada); 
                
                categoriaprodutos = categoriaselecionada.getProdutosCollection();
                
                session.setAttribute("categoriaprodutos", categoriaprodutos);
        }
               
        // Se a página carrinho é requisitada
        } else if (userPath.equals("/visualizarcarrinho")) {
          
            String clear = request.getParameter("clear");
            
            if ((clear != null) && clear.equals("true")) {

                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) session.getAttribute("carrinho");
                carrinho.clear();
            }
            userPath = "/pedido";

        // Se a página Finalizar é requisitada
        } 
        else if (userPath.equals("/checkout")) 
        {
            CarrinhoDeCompra carrinho = (CarrinhoDeCompra) session.getAttribute("carrinho");
            // calculate total
            carrinho.calculaTotal(sobretaxa);  
            
            // prosseguir para página finalizar e switch to a secure channel
            
        // if user switches language
        } 
        else if (userPath.equals("/chooseLanguage")) 
        {
             // get language choice
            String language = request.getParameter("language");
            request.setAttribute("language", language);
            String userView = (String) session.getAttribute("View");
            
            if ((userView != null) && (!userView.equals("/index"))) 
            {     // index.jsp exists outside 'view' folder
                  // so must be forwarded separately
                 userPath = userView;
             } else {
             // forward request to welcome page
            try {
                 request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (Exception ex) {
                    ex.printStackTrace();
            }
                return;
            }
        }
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/View" + userPath + ".jsp";

        try 
        {
            request.getRequestDispatcher(url).forward(request, response);
        } 
        catch (IOException | ServletException ex) 
        {
            ex.printStackTrace();
        }
    
    }
/**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");  // ensures that user input is interpreted as
                                                // 8-bit Unicode (e.g., for Czech characters)
        String userPath = request.getServletPath();                                        
        HttpSession sessao = request.getSession();
        CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");
        Validador validador = new Validador();
        
        //se adicionar ao carrinho é chamado
        if (userPath.equals("/adicionaraocarrinho")) {
        // se usuário está adicionando a primeira vez
            // cria carrinho objeto e anexa-o a sessão do usuário
           if (carrinho == null) {

                carrinho = new CarrinhoDeCompra();
                sessao.setAttribute("carrinho", carrinho);
            } 
        // obtém a entrada do usuário da requisisição
            String produtoId = request.getParameter("produtoId");
            if (!produtoId.isEmpty()) {

                Produtos produto = produtosFacade.find(Integer.parseInt(produtoId));
                carrinho.adicionaritem(produto);
            }
            
            userPath = "/categoria";
            
        //se atualizarcarrinho ação é chamada    
           } else if (userPath.equals("/atualizarcarrinho")) {

            // get input from request
            String produtoId = request.getParameter("produtoId");
            String quantidade = request.getParameter("quantidade"); 
            
            boolean invalidEntry = validador.quantidadeValida(produtoId, quantidade);

                if (!invalidEntry) {

                Produtos produto = produtosFacade.find(Integer.parseInt(produtoId));
                carrinho.atualizar(produto, quantidade);
            }
            
                userPath = "/pedido";
                
        //se ação da compra é chamada
             } else if (userPath.equals("/purchase")) {

            if (carrinho != null) {
                
                //extrair dados do usuário da requisição
                String nome = request.getParameter("nome");
                String email = request.getParameter("email");
                String telefone = request.getParameter("telefone");
                String endereco = request.getParameter("endereco");
                String bairro = request.getParameter("bairro");
                String estado = request.getParameter("estado");
                String cep = request.getParameter("cep");
                
                // validar dados do usuário
                @SuppressWarnings("UnusedAssignment")
                boolean validacaoErrorFlag = false;
                validacaoErrorFlag = validador.validacaoform(nome, email, telefone, endereco, bairro, estado, cep, request);
                // if validation error found, return user to checkout
                if (validacaoErrorFlag == true) {
                    request.setAttribute("validacaoErrorFlag", validacaoErrorFlag);
                    userPath = "/checkout";
                
                    // De outra forma, salva pedido no database
                } else {

                    int pedidoId = gerenciadorPedido.lugarDoPedido(nome, email, telefone, endereco, bairro, estado, cep, carrinho);
                    
                    // se pedido processado com sucesso envia usuário à página de confirmação
                    
                     if (pedidoId != 0) {
                         
                        // caso o idioma tenha sido definido usando alternar, obtenha a escolha do idioma antes de destruir a sessão
                        Locale locale = (Locale) sessao.getAttribute ("javax.servlet.jsp.jstl.fmt.locale.session");        
                        // desassocia o carrinho de compras da sessão
                        String language = "";
                        
                        if (locale != null) {

                            language = (String) locale.getLanguage();
                        }
                        
                        carrinho = null;
                        
                        // fim da sessão
                        sessao.invalidate();
                        
                        if (!language.isEmpty()) {                       // if user changed language using the toggle,
                                                     // reset the language attribute - otherwise
                            request.setAttribute("language", language);  // language will be switched on confirmation page!
                         }
                        
                         // obtém detalhes do pedido
                        Map pedidoMap = gerenciadorPedido.getDetalhesPedido(pedidoId);
                        
                         // lugar dos detalhes do pedido no scopo da requisição
                        request.setAttribute("cliente", pedidoMap.get("cliente"));
                        request.setAttribute("produtos", pedidoMap.get("produtos"));
                        request.setAttribute("registroPedido", pedidoMap.get("registroPedido"));
                        request.setAttribute("produtosPedidos", pedidoMap.get("produtosPedidos"));
                        
                         userPath = "/confirmation";
                          // De outra forma, envia de volta a página de checkout e mostra error
                    } else {
                        userPath = "/checkout";
                        request.setAttribute("orderFailureFlag", true);
                    }
                }
            }           
                     
        }

        // usa o RequestDispatcher para designar pedido internamente
        String url = "/WEB-INF/View" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
