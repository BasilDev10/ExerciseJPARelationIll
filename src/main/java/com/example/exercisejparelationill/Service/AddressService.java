package com.example.exercisejparelationill.Service;

import com.example.exercisejparelationill.ApiResponse.ApiException;
import com.example.exercisejparelationill.DTO.AddressDTO_In;
import com.example.exercisejparelationill.DTO.AddressDTO_Out;
import com.example.exercisejparelationill.Model.Address;
import com.example.exercisejparelationill.Model.Teacher;
import com.example.exercisejparelationill.Repository.AddressRepository;
import com.example.exercisejparelationill.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;


    public List<AddressDTO_Out> getAllAddress(){
        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO_Out> addressDTOS = new ArrayList<>();

        for (Address address : addresses) {
            addressDTOS.add(new AddressDTO_Out(address.getId(), address.getArea(),address.getStreet(),address.getBuildingNumber()));
        }
        return addressDTOS;
    }

    public AddressDTO_Out getAddressById(Integer id){
        Address address = addressRepository.findAddressById(id);
        return new AddressDTO_Out(address.getId(), address.getArea(),address.getStreet(),address.getBuildingNumber());
    }

    public void addAddress(AddressDTO_In AddressDTO_In){
        Address address = new Address();
        Teacher teacher = teacherRepository.findTeacherById(AddressDTO_In.getTeacher_id());
        if (teacher == null) throw new ApiException("Error: Teacher not found");
        address.setArea(AddressDTO_In.getArea());
        address.setStreet(AddressDTO_In.getStreet());
        address.setBuildingNumber(AddressDTO_In.getBuildingNumber());
        address.setTeacher(teacher);
        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO_In AddressDTO_In){
        Address address = addressRepository.findAddressById(AddressDTO_In.getId());
        if (address == null) throw new ApiException("Error: Address not found");
        address.setArea(AddressDTO_In.getArea());
        address.setStreet(AddressDTO_In.getStreet());
        address.setBuildingNumber(AddressDTO_In.getBuildingNumber());
        addressRepository.save(address);
    }

    public void deleteAddress(Integer id){
        Address address = addressRepository.findAddressById(id);
        if (address == null) throw new ApiException("Error: Address not found");
        addressRepository.delete(address);
    }
}
