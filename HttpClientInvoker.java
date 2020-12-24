package com.example.electronicshowroomUI;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.example.pojo.Category;
import com.example.pojo.ProductDetails;
import com.example.pojo.SubCategory;
import com.springmvc.service.CategoryService;
@Configuration
public class HttpClientInvoker {

	@Bean
	public HttpInvokerProxyFactoryBean invoker() {
		HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
		invoker.setServiceUrl("http://localhost:8081/ElectronicShowroom/");
		invoker.setServiceInterface(CategoryService.class);
		return invoker;
	}
	WebTarget webtarget;

	public List<Category> viewCategory() {
		return webtarget.path("/categoryapi/viewcategory").request().accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Category>>() {
				});
	}

	public String addcategory(Category category) {
		System.out.println(category);
		String answer = webtarget.path("/categoryapi/addcategory").request().post(Entity.json(category), String.class);
		return answer;

	}

	public List<ProductDetails> viewProductDetails() {
		return webtarget.path("/productdetailsapi/viewproductdetails").request().accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<ProductDetails>>() {
				});
	}

	public List<SubCategory> viewSubCategory() {
		return webtarget.path("/subcategoryapi/viewsubcategory").request().accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<SubCategory>>() {
				});
	}

	String addSubCategory(SubCategory subCategory) {
		System.out.println(subCategory);
		String answer = webtarget.path("/subcategoryapi/addsubcategory").request().post(Entity.json(subCategory),
				String.class);
		return answer;

	}

	public String addProductDetails(ProductDetails productDetails) {
		System.out.println(productDetails);
		String answer = webtarget.path("/productdetailsapi/addproductdetails").request()
				.post(Entity.json(productDetails), String.class);
		return answer;

	}
}