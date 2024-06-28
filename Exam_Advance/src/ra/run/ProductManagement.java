package ra.run;

import ra.model.Catalog;
import ra.model.Product;
import ra.service.ProductService;
import ra.service.impl.ICatalogService;
import ra.service.impl.IProductService;

import java.util.Scanner;



public class ProductManagement {
    private static final ProductService productService=new IProductService();
    public static void menuProduct(Scanner scanner) {
        boolean isExist;
        do {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ MENU ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.printf("┃			%30s				┃\n","1. Nhập số sản sản phẩm và nhập thông tin sản phẩm");
            System.out.printf("┃			%30s								┃\n","2. Hiển thị thông tin các sản phẩm");
            System.out.printf("┃			%30s							┃\n","3. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.printf("┃			%30s 									┃\n","4. Xóa sản phẩm theo mã");
            System.out.printf("┃			%30s									┃\n","5. Tìm kiếm sách theo tên sách");
            System.out.printf("┃			%30s						┃\n","6. Thay đổi thông tin của sách theo mã sách");
            System.out.printf("┃			%30s									┃\n","7. Quay lại");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("Mời bạn chọn: ");
            int choice =Integer.parseInt(scanner.nextLine());
            isExist = true;
            switch (choice){
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    showAll();
                    break;
                case 3:

                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    break;
                case 6:
                    updateProduct(scanner);
                    break;
                case 7:
                    isExist = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 ->7");

            }
        }while (isExist);

    }

    private static void updateProduct(Scanner scanner) {
        System.out.println("Chọn danh mục bạn muốn update theo ID:");
        String updateId=scanner.nextLine();
        String indexUpdate= productService.findById(updateId).getProductId();
        if(Integer.parseInt(indexUpdate)<0){
            System.err.println("Không tồn tại ID muốn cập nhật");
            return;
        }
        boolean isLoop=true;
        do {
            Product productUpdate=IProductService.productList.get(Integer.parseInt(indexUpdate));

            System.out.println("Tên danh mục muốn sửa:");
            productUpdate.setProductName(scanner.nextLine());


            productService.save(productUpdate);
        }while (isLoop);
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.println("Sản phẩm bạn muốn xóa:");
        String deleteId=scanner.nextLine();
        String indexDelete= productService.findById(deleteId).getProductId();
        if(Integer.parseInt(indexDelete)<0){
            System.err.println("Không tồn tại ID muốn xóa");
            return;
        }
        productService.delete(deleteId);
    }

    private static void showAll() {
        if(IProductService.productList.isEmpty()){
            System.err.println("Chưa có danh mục nào, Vui lòng thêm danh mục");
            return;
        }
        for (Product product: IProductService.productList){
            System.out.println(product.toString());
        }
    }

    private static void addProduct(Scanner scanner) {
        System.out.println("Nhập vào số lượng muốn thêm sản phẩm: ");
        int numbers=Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numbers; i++) {
            Product product=new Product();
            product.inputData(scanner);
            productService.save(product);
        }
    }
}
