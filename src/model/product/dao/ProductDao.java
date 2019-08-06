package model.product.dao;

import java.util.List;

import model.product.bean.ProductBean;

public interface ProductDao {

	ProductBean select(Integer pId);

	ProductBean select(String pname);

	List<ProductBean> select();

	ProductBean insertProduct(ProductBean bean);

	Integer delete(Integer pId);
	
	long getRecordCounts();

	void close();

}