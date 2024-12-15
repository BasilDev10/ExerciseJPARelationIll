package com.example.exercisejparelationill.Service;

import com.example.exercisejparelationill.ApiResponse.ApiException;
import com.example.exercisejparelationill.DTO.TeacherDTO_Out;
import com.example.exercisejparelationill.DTO.TeacherDTO_In;
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
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;

    public List<TeacherDTO_Out> getAllTeachers(){
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDTO_Out> teacherDTOS = new ArrayList<>();

        for (Teacher teacher : teachers) {
            teacherDTOS.add(new TeacherDTO_Out(teacher.getId(), teacher.getName(),teacher.getAge(),teacher.getEmail(),teacher.getSalary()   ));
        }
        return teacherDTOS;
    }

    public TeacherDTO_Out getTeacherById(Integer id){
        Teacher teacher = teacherRepository.findTeacherById(id);
        return new TeacherDTO_Out(teacher.getId(), teacher.getName(),teacher.getAge(),teacher.getEmail(),teacher.getSalary());
    }

    public void addTeacher(TeacherDTO_In TeacherDTO_In){
        Address address = new Address();
        Teacher teacher = new Teacher();
        teacher.setName(TeacherDTO_In.getName());
        teacher.setAge(TeacherDTO_In.getAge());
        teacher.setEmail(TeacherDTO_In.getEmail());
        teacher.setSalary(TeacherDTO_In.getSalary());
        teacher =teacherRepository.save(teacher);

        address.setTeacher(teacher);
        address.setArea(TeacherDTO_In.getArea());
        address.setStreet(TeacherDTO_In.getStreet());
        address.setBuildingNumber(TeacherDTO_In.getBuildingNumber());

        addressRepository.save(address);

    }

    public void updateAddress(TeacherDTO_In TeacherDTO_In){
        Teacher teacher = teacherRepository.findTeacherById(TeacherDTO_In.getId());
        if (teacher == null) throw new ApiException("Error: Teacher not found");
        teacher.setName(TeacherDTO_In.getName());
        teacher.setAge(TeacherDTO_In.getAge());
        teacher.setEmail(TeacherDTO_In.getEmail());
        teacher.setSalary(TeacherDTO_In.getSalary());
        teacherRepository.save(teacher);
        Address address = addressRepository.findAddressById(teacher.getAddress().getId());
        if (address == null) {
            address = new Address();
            address.setTeacher(teacher);
        }
        address.setArea(TeacherDTO_In.getArea());
        address.setStreet(TeacherDTO_In.getStreet());
        address.setBuildingNumber(TeacherDTO_In.getBuildingNumber());
        addressRepository.save(address);
    }

    public void deleteTeacher(Integer id){
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) throw new ApiException("Error: Teacher not found");
        teacherRepository.delete(teacher);
    }
}
