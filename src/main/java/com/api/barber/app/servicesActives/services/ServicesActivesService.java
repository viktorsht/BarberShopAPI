package com.api.barber.app.servicesActives.services;

import com.api.barber.app.servicesActives.dto.ServicesActivesDTO;
import com.api.barber.app.servicesActives.entity.ServicesActiveEntity;
import com.api.barber.app.servicesActives.repository.ServicesActiveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesActivesService {
    final private ServicesActiveRepository servicesActiveRepository;

    public ServicesActivesService(ServicesActiveRepository servicesActiveRepository) {
        this.servicesActiveRepository = servicesActiveRepository;
    }

    public List<ServicesActiveEntity> listServices(){
        return servicesActiveRepository.findAll();
    }

    public Optional<ServicesActiveEntity> listServiceById(int serviceId){
        return servicesActiveRepository.findById(serviceId);
    }

    public ServicesActiveEntity createServices(ServicesActivesDTO servicesActivesDTO){
        var entity = new ServicesActiveEntity(
                servicesActivesDTO.name(),
                Integer.parseInt(servicesActivesDTO.duration()),
                servicesActivesDTO.price()
        );
        return servicesActiveRepository.save(entity);
    }

    public void updateService(int serviceId, ServicesActivesDTO servicesActivesDTO){
        var serviceEntity = servicesActiveRepository.findById(serviceId);
        if (serviceEntity.isPresent()){
            var service = serviceEntity.get();
            if(servicesActivesDTO.name() != null){
                service.setName(servicesActivesDTO.name());
            }
            if(servicesActivesDTO.duration() != null){
                service.setDurationMinutes(Integer.parseInt(servicesActivesDTO.duration()));
            }
            if(servicesActivesDTO.price() != null){
                service.setPrice(servicesActivesDTO.price());
            }
            servicesActiveRepository.save(service);
        }
    }

    public void deleteService(int serviceId){
        var serviceEntity = servicesActiveRepository.existsById(serviceId);
        if(serviceEntity) {
            servicesActiveRepository.deleteById(serviceId);
        }
    }
}
