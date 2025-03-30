import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        String product = request.getParameter("product");
        if (product == null || product.trim().isEmpty()) {
            out.print("{\"success\": false, \"message\": \"Ürün bilgisi eksik\"}");
            return;
        }
        
        HttpSession session = request.getSession();
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        
        cart.put(product, cart.getOrDefault(product, 0) + 1);
        
        out.print("{\"success\": true, \"message\": \"Ürün sepete eklendi\"}");
    }
}