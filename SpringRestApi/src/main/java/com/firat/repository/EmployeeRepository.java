package com.firat.repository;

import com.firat.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    // Spring’ten enjekte ETMİYORUZ; kendi iç listemiz:
    private final List<Employee> store = new ArrayList<>();

    public EmployeeRepository() {
        // örnek veri
        store.add(new Employee(1L, "Alice"));
        store.add(new Employee(2L, "Bob"));
    }

    public List<Employee> findAll() {
        // dışarıya kopya döndür (koruma)
        return new ArrayList<>(store);
    }

    public Employee save(Employee e) {
        store.add(e);
        return e;
    }
}
