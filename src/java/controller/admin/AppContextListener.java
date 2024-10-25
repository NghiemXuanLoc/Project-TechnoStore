/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.OrderChecker;

@WebListener
public class AppContextListener implements ServletContextListener {
    private OrderChecker orderChecker;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Khởi tạo và bắt đầu OrderChecker khi ứng dụng bắt đầu
        orderChecker = new OrderChecker();
        orderChecker.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Dừng OrderChecker khi ứng dụng dừng
        if (orderChecker != null) {
            orderChecker.stop();
        }
    }
}