package model.product;

import java.util.List;

public interface ProductDao {

	ProductBean select(Integer pId);

	ProductBean select(String pname);

	List<ProductBean> select();

	ProductBean insertProduct(ProductBean bean);

	Integer delete(Integer pId);

	void close();

}