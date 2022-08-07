package com.surmin.recipe.controller;

import com.surmin.recipe.model.Product;
import com.surmin.recipe.model.ProductDto;
import com.surmin.recipe.service.ProductService;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{entityId}")
    public ProductDto get(@PathVariable("entityId") String entityId){
       return productService.get(entityId);
    }

    @GetMapping
    public Collection<ProductDto> getAll(){
        return productService.getAll();
    }

    @PostMapping("/list")
    public Collection<ProductDto> getAllByIds(@RequestBody Collection<String> entityIds) {
        return productService.getProductsByIds(entityIds);
    }

    @GetMapping("page/{pageId}")
    public Page<ProductDto> getPage(@PathVariable("pageId") Integer pageId) {
        return productService.getPage(pageId);
    }

    @PostMapping("page/{pageId}")
    public Page<ProductDto> getPageFilterByTagIds(@PathVariable("pageId") Integer pageId, @RequestBody Collection<String> entityIds) {
        return productService.getPageFilterByTagIds(pageId, entityIds);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @GetMapping("/tags/{tagId}")
    public Collection<ProductDto> getByTagId(@PathVariable("tagId") String entityId) {
        return productService.getProductsByTagId(entityId);
    }

    @GetMapping("/{entityId}/image")
    public ResponseEntity<byte[]> getImage(
            @PathVariable("entityId") String id
    ) {
        Product product = productService.getProduct(id);
        byte[] imageAsByteArray = product.getImageAsByteArray();
        return ResponseEntity.status(HttpStatus.OK).contentLength(imageAsByteArray.length).body(imageAsByteArray);
    }

    @PostMapping("/{entityId}/image")
    public ResponseEntity<Void> uploadImage(
            @PathVariable("entityId") String id,
            @RequestParam(value = "image") MultipartFile image
    ) throws IOException {
        byte[] imageAsByteArray = image.getBytes();
        productService.saveImage(id, imageAsByteArray);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto update(@RequestBody ProductDto productDto) {
        return productService.update(productDto);
    }

    @DeleteMapping("/{entityId}")
    public void delete(@PathVariable("entityId") String entityId) {
        ProductDto productDto = productService.get(entityId);
        productService.delete(productDto);
    }

}
