package com.project.codinviec.mapper.payment;

import com.project.codinviec.dto.payment.ServiceProductDTO;
import com.project.codinviec.entity.Job;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.entity.payment.ServiceProduct;
import com.project.codinviec.request.payment.ServiceProductRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ServiceProductMapper {
    public ServiceProductDTO serviceProductDTO(ServiceProduct serviceProduct){
        return ServiceProductDTO.builder()
                .id(serviceProduct.getId())
                .name(serviceProduct.getName())
                .description(serviceProduct.getDescription())
                .price(serviceProduct.getPrice())
                .images(serviceProduct.getImages())
                .userId(serviceProduct.getUser().getId())
                .jobId(serviceProduct.getJob().getId())
                .createdDate(serviceProduct.getCreatedDate())
                .updatedDate(serviceProduct.getUpdatedDate())
                .build();
    }

    public ServiceProduct saveServiceProduct( ServiceProductRequest serviceProductRequest){
        return ServiceProduct.builder()
                .name(serviceProductRequest.getName())
                .description(serviceProductRequest.getDescription())
                .price(serviceProductRequest.getPrice())
                .images(serviceProductRequest.getImages())
                .user(User.builder()
                        .id(serviceProductRequest.getUserId())
                        .build())
                .job(Job.builder()
                        .id(serviceProductRequest.getJobId())
                        .build())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    public void updateServiceProduct(ServiceProduct serviceProduct, ServiceProductRequest serviceProductRequest){
        if (serviceProductRequest.getName() != null) {
            serviceProduct.setName(serviceProductRequest.getName());
        }

        if (serviceProductRequest.getDescription() != null) {
            serviceProduct.setDescription(serviceProductRequest.getDescription());
        }

        if (serviceProductRequest.getPrice() != 0) {
            serviceProduct.setPrice(serviceProductRequest.getPrice());
        }

        if (serviceProductRequest.getImages() != null) {
            serviceProduct.setImages(serviceProductRequest.getImages());
        }

        serviceProduct.setCreatedDate(serviceProduct.getCreatedDate());
        serviceProduct.setUpdatedDate(LocalDateTime.now());

        if (serviceProductRequest.getUserId() != null) {
            serviceProduct.setUser(User.builder()
                    .id(serviceProductRequest.getUserId())
                    .build());
        }

        if (serviceProductRequest.getJobId() != 0) {
            serviceProduct.setJob(Job.builder()
                    .id(serviceProductRequest.getJobId())
                    .build());
        }
     }

    public List<ServiceProductDTO> serviceProductDTOList(List<ServiceProduct> serviceProductList) { return serviceProductList.stream().map(this::serviceProductDTO).toList();}


}
