package com.firat.starter;

import com.firat.dto.dtoEmployee;
import com.firat.model.Employee;
import com.firat.service.IEmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ExceptionManagementApplicationStarter.class) //bazen test edeceği maini bulamadığı için burda yolu belirtiyoruz
class ExceptionManagementApplicationTests {

    @Autowired
    private IEmployeeService employeeService;

    @BeforeEach
    public void beforeEach() {//before each önce çalışır sonra test metodu en son aftereach çalışır
        System.out.println("beforeEach çalıştı!!");//örnek olarak test metodu çalışmadan önce bir veritabanına bağlanmamız gerekebilir sonrasında ise bağlantıyı aftereach ile kapatırız
    }

    @Test
    public void testFindEmployeeById() {
        dtoEmployee employee = employeeService.findEmployeeById(1L);//L yi değişkeni long belirttiğim için yazıyorum
        assertEquals(4,4); //4 bekliyorum senden 4 geliyor ten farklı bir değer hata verir
        assertNotNull(employee); //employyem boş değilse bana true dön
    }

    @AfterEach
    public void afterEach() {
        System.out.println("afterEach çalıştı!!");
    }


}
