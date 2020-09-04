package com.rmm.rmmservices.model.persistence.repository;

import com.rmm.rmmservices.model.persistence.entities.Device;
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
@Repository("customerDeviceRepository")
public interface CustomerDeviceRepository extends JpaRepository<Device, Long> {

    @Query(value = "SELECT * FROM device d WHERE d.system_name = :systemName AND d.device_type_id = " +
            "(SELECT dt.id FROM device_type dt WHERE dt.type_name = :deviceTypeName) AND d.customer_id = :customerId",
            nativeQuery = true)
    Optional<Device> findExistingDevicePerCustomer(String systemName,
                                                              String deviceTypeName,
                                                              Long customerId);

    @Query(value = "SELECT * FROM device d WHERE d.id = :deviceId AND " +
            "d.customer_id = (SELECT c.id FROM customer c WHERE c.username = :customerName)", nativeQuery = true)
    Optional<Device> findByDeviceIdAndCustomerName(Long deviceId, String customerName);


    @Query(value = "SELECT * FROM device d WHERE d.id= :deviceId AND d.customer_id = :customerId",
            nativeQuery = true)
    Optional<Device> findDevicePerIdAndCustomerId(Long deviceId, Long customerId);


    @Query(value = "SELECT * FROM device d WHERE d.customer_id = (SELECT c.id FROM customer c " +
            "WHERE username = :customerName)",
            nativeQuery = true)
    List<Device> findDevicesPerCustomerName(String customerName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM device d WHERE d.id = :id AND d.customer_id = (SELECT c.id FROM customer c WHERE " +
            "username= :username)", nativeQuery = true)
    void deleteByDeviceIdAndCustomer(Long id, String username);
}
