package edu.hm.cs.fs.scriptinat0r7.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.hm.cs.fs.scriptinat0r7.model.StudentOrder;
import edu.hm.cs.fs.scriptinat0r7.model.User;

public interface StudentOrderRepository extends PagingAndSortingRepository<StudentOrder, Integer> {

    @EntityGraph(value = "StudentOrder.scriptDocuments", type = EntityGraphType.LOAD)
    Collection<StudentOrder> findByOrderer(User user);

    Collection<StudentOrder> findByCopyShopOrderIsNull();

}
