package com.example.exercisejparelationill.Controller;

import com.example.exercisejparelationill.ApiResponse.ApiResponse;
import com.example.exercisejparelationill.DTO.AddressDTO_In;
import com.example.exercisejparelationill.DTO.AddressDTO_Out;
import com.example.exercisejparelationill.Service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    @GetMapping("/get")
    public ResponseEntity<List<AddressDTO_Out>> getAllAddress() {
        List<AddressDTO_Out> addresses = addressService.getAllAddress();
        return ResponseEntity.ok(addresses);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<AddressDTO_Out> getAddressById(@PathVariable Integer id) {
        AddressDTO_Out address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addAddress(@RequestBody AddressDTO_In addressDTO_In) {
        addressService.addAddress(addressDTO_In);
        return ResponseEntity.ok(new ApiResponse("Address added successfully!"));
    }


    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateAddress(@RequestBody AddressDTO_In addressDTO_In) {
        addressService.updateAddress(addressDTO_In);
        return ResponseEntity.ok(new ApiResponse("Address updated successfully!"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok(new ApiResponse("Address deleted successfully!"));
    }
}