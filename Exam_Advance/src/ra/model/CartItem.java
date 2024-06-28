package ra.model;

import ra.service.impl.ICartService;
import ra.service.impl.IProductService;

import java.util.Scanner;

public class CartItem extends Product implements IOData{
    private int cartItemId;
    private Product product;
    private double price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int cartItemId, Product product, double price, int quantity) {
        this.cartItemId = cartItemId;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.cartItemId=inputCartItemId();

        this.product=inputProduct(scanner);


        this.quantity=inputQuantity(scanner);
        this.price=inputPrice();
    }

    private double inputPrice() {
return this.quantity * this.product.getProductPrice();

    }

    private Product inputProduct(Scanner scanner) {
        for(Product p:IProductService.productList){

            System.out.printf("[ID: %s | Name: %s ]\n",p.getProductId(),p.getProductName());
        }
        System.out.println("Nhập vào Id muốn thêm:");
        do {
            String productIdChoice = scanner.nextLine();
            int indexProduct = findProductIndexById(productIdChoice);
            if (indexProduct >= 0) {
                return IProductService.productList.get(indexProduct);
            } else {
                System.err.println("không tìm thấy sản phẩm, vui lòng nhập lại id");
            }
        } while (true);
    }
    public int findProductIndexById(String productId) {
        for (int i = 0; i < IProductService.productList.size(); i++) {
            if(IProductService.productList.get(i).getProductId().equals(productId)){
                return i;
            }
        }
        return -1;
    }
    private int inputQuantity(Scanner scanner) {
        System.out.println("Mời nhập số lượng sản phẩm:");
        do {
            String quantity=scanner.nextLine();
            if(quantity.trim().isEmpty()){
                System.err.println("Số lượng không được để trống, vui lòng nhập lại");
            } else {
                    return Integer.parseInt(quantity);
                }

        }while (true);

    }

    private int inputCartItemId() {
        int maxId = 0;
        for (CartItem cartItem : ICartService.cartItemList) {
            if (maxId < cartItem.getCartItemId()) {
                maxId = cartItem.getCartItemId();
            }
        }
        return maxId + 1;
    }


    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", product=" + product.getProductName() +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
