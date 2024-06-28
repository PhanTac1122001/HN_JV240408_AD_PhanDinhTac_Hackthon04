package ra.run;

import ra.model.CartItem;
import ra.model.Catalog;
import ra.model.Product;
import ra.service.CartService;

import ra.service.ProductService;
import ra.service.impl.ICartService;
import ra.service.impl.ICatalogService;
import ra.service.impl.IProductService;


import java.util.Scanner;

public class CartManagement {
    private static final CartService cartService=new ICartService();

    public  static void menuCart(Scanner scanner) {

        boolean isExist;
        do {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ Cart-Management ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.printf("┃			%30s			                ┃\n", "1. Hiển thị danh sách tất cả sản phẩm đang được bán của cửa hàng .");
            System.out.printf("┃			%30s            ┃\n", "2. Thêm mới 1 sản phẩm vào giỏ hàng dựa theo mã sản phẩm mà người dùng nhập vào.");
            System.out.printf("┃			%30s									                        ┃\n", "3.\tHiển thị danh sách giỏ hàng .");
            System.out.printf("┃			%30s 		                    ┃\n", "4.\tCập nhật số lượng sản phẩm muốn mua theo trường cartItemId .");
            System.out.printf("┃			%30s 		                                ┃\n", "5.\tXóa 1 sản phẩm ra khỏi giỏ hàng theo cartItemId .");
            System.out.printf("┃			%30s 		                                        ┃\n", "6.\tXóa toàn bộ sản phẩm trong trong giỏ hàng.");
            System.out.printf("┃			%30s	                                                            ┃\n", "7.Quay lại");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("Mời bạn chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            isExist = true;
            switch (choice) {
                case 1:
                    showProduct();
                    break;
                case 2:
                    addCart(scanner);
                    break;
                case 3:
                    showCart();
                    break;
                case 4:
                    updateCart(scanner);
                    break;
                case 5:
                    deleteCart(scanner);
                    break;
                case 6:
                    deleteAll();
                    break;
                case 7:
                    isExist = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 ->7");

            }
        } while (isExist);
    }

    private static void updateCart(Scanner scanner) {
        System.out.println("nhập vào id giỏ hàng muốn update: ");
        int idUpdate = Integer.parseInt(scanner.nextLine());
        int indexUpdate = cartService.findById(idUpdate).getCartItemId();
        if (indexUpdate >= 0) {
            CartItem cartItemUpdate = ICartService.cartItemList.get(indexUpdate);
            boolean isLoop = true;

        do {

            System.out.println("Số lượng bạn muốn sửa:");
            cartItemUpdate.setQuantity(Integer.parseInt(scanner.nextLine()));

            cartService.save(cartItemUpdate);
            System.out.println("Sửa thành công");
        }while (isLoop);
        } else {
            System.err.println("Không tồn tại giỏ hàng đó");
        }
    }

    private static void deleteAll() {
       cartService.deleteAllCart();
        System.out.println("Đã xóa tất cả");
    }

    private static void deleteCart(Scanner scanner) {
        System.out.println("Giỏ hàng bạn muốn xóa:");
        int deleteId=Integer.parseInt(scanner.nextLine());
        int indexDelete= cartService.findById(deleteId).getCartItemId();
        if(indexDelete<0){
            System.err.println("Không tồn tại ID muốn xóa");
            return;
        }
        cartService.delete(deleteId);
        System.out.println("Xóa thành công");
    }

    private static void addCart(Scanner scanner) {
            CartItem cartItem=new CartItem();
            cartItem.inputData(scanner);
            cartService.save(cartItem);
    }


    private static void showCart() {
        if(ICartService.cartItemList.isEmpty()){
            System.err.println("Chưa có sản phẩm nào trong giỏ hàng, Vui lòng thêm sản phẩm vào giỏ hàng");
            return;
        }
        for (CartItem cartItem: ICartService.cartItemList){
            System.out.println(cartItem.toString());
        }
    }

    private static void showProduct() {
        if(IProductService.productList.isEmpty()){
            System.err.println("Chưa có sản phẩm nào, Vui lòng thêm sản phẩm");
            return;
        }
        for (Product product: IProductService.productList){
            System.out.println(product.toString());
        }
    }

}
