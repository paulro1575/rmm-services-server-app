package com.rmm.rmmservices.model.persistence.repository;

import com.rmm.rmmservices.model.persistence.entities.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Repository("customerServiceRepository")
public interface CustomerServiceRepository extends JpaRepository<CustomerService, Long> {

    @Query(value = "SELECT * FROM customer_service cs WHERE cs.customer_id = :customerId AND " +
            "cs.service_id = (SELECT s.id FROM service s WHERE s.service_name = :serviceName)", nativeQuery = true)
    Optional<CustomerService> findByCustomerIdAndServiceName(Long customerId, String serviceName);


    @Query(value = "SELECT * FROM customer_service cs WHERE cs.id = :serviceId AND " +
            "cs.customer_id = (SELECT c.id FROM customer c WHERE c.username = :customerName)", nativeQuery = true)
    Optional<CustomerService> findByServiceIdAndCustomerName(Long serviceId, String customerName);


    @Query(value = "SELECT * FROM customer_service cs WHERE cs.customer_id = (SELECT c.id FROM customer c " +
            "WHERE username = :customerName)",
            nativeQuery = true)
    List<CustomerService> findCustomerServicesPerCustomerName(String customerName);


    @Query(value = "SELECT * FROM customer_service cs WHERE d.id= :customerServiceId AND d.customer_id = :customerId",
            nativeQuery = true)
    Optional<CustomerService> findCustomerServicePerIdAndCustomerId(Long customerServiceId, Long customerId);


    @Query(value="select d.system_name, dt.type_name, s.service_name, sp.price\n" +
            "from device d, device_type dt, customer_service cs, service_price sp, service s\n" +
            "where \n" +
            "d.customer_id = :customerId and\n" +
            "d.device_type_id = dt.id and\n" +
            "cs.customer_id = d.customer_id and\n" +
            "sp.device_type_id = dt.id and \n" +
            "sp.service_id = cs.service_id and\n" +
            "s.id = sp.service_id", nativeQuery = true)
    List<Object[]> listBillServicesPrice(Long customerId);


    @Query(value="select count(d.id) as devices, COALESCE(SUM(dt.device_price), 0) as devices_cost from device d, " +
            "device_type dt where d.customer_id = :customerId and d.device_type_id = dt.id", nativeQuery = true)
    List<Object[]> listBillDevicesPrice(Long customerId);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM customer_service cs WHERE cs.id = :id AND cs.customer_id = (SELECT c.id FROM customer c WHERE " +
            "username= :username)", nativeQuery = true)
    void deleteByServiceIdAndCustomer(Long id, String username);
}
