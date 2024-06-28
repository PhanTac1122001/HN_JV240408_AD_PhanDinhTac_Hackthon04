package ra.run;

import ra.service.impl.ICartService;
import ra.service.impl.ICatalogService;

import java.util.Scanner;

public class MenuManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("================== MENU ==================\n" +
                    "1.\tQuản lý danh mục\n" +
                    "2.\tQuản lý sản phẩm\n" +
                    "3.\tDành cho người dùng\n" +
                    "4.\tThoát\n");
            System.out.println("Nhập vào lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    CatalogManagement.menuCatalog(scanner);
                    break;
                case 2:
                    if(ICatalogService.catalogList.isEmpty()){
                        System.err.println("Chưa có danh mục nào, Vui lòng thêm danh mục");

                    }else {
                        ProductManagement.menuProduct(scanner);
                    }

                    break;
                case 3:
                    CartManagement.menuCart(scanner);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn lại từ 1 -> 3");
            }
        } while (true);
    }
}
