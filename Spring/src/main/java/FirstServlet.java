import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = Arrays.asList(new Product(1,"skoda", 10),
                new Product(2,"lexus", 15),
                new Product(3,"mazda", 20));

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().printf("<html><body>");
        for (Product pr:list) {
            resp.getWriter().printf("<h1> product id " + pr.getId() + "</h1>");
            resp.getWriter().printf("<h1> product title " + pr.getTitle() + "</h1>");
            resp.getWriter().printf("<h1> product cost " + pr.getCost() + "</h1>");
        }
        resp.getWriter().printf("</html></body>");
    }
}
