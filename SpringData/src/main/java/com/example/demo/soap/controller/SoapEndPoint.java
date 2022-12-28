package com.example.demo.soap.controller;

import com.example.demo.service.ProductRepositoryService;
import com.example.demo.soap.GetAllProductsRequest;
import com.example.demo.soap.GetAllProductsResponse;
import com.example.demo.soap.GetProductRequest;
import com.example.demo.soap.GetProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class SoapEndPoint {
    private static final String NAMESPACE_URI = "http://example.com/demo/soap";

    @Autowired
    private ProductRepositoryService productRepository;

/*
<x:Envelope xmlns:x="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://example.com/demo/soap">
    <x:Header/>
    <x:Body>
        <f:getProductRequest>
            <f:id>27</f:id>
        </f:getProductRequest>
    </x:Body>
</x:Envelope>
 */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProducts(@RequestPayload GetProductRequest request)  {
        GetProductResponse response = new GetProductResponse();
        response.setProduct(productRepository.getProductById(request.getId()));
        return response;
    }

    /*
<x:Envelope xmlns:x="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://example.com/demo/soap">
   <x:Header/>
   <x:Body>
       <f:getAllProductsRequest/>
   </x:Body>
</x:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getProduct(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productRepository.getAllProduct().forEach(response.getProducts()::add);
        return response;
    }
}
