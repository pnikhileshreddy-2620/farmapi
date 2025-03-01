package com.farmweb.api.repository;

import com.farmweb.api.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer>
{
    @Query("SELECT h FROM History h WHERE h.customer.customerId = :customerId ORDER BY h.transactionDate DESC")
    List<History> findTop10ByCustomerIdOrderByTransactionDateDesc(Integer customerId);
}
