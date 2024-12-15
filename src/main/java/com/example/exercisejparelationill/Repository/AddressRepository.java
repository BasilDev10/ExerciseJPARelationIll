package com.example.exercisejparelationill.Repository;

import com.example.exercisejparelationill.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {

    Address findAddressById(Integer id);
}
