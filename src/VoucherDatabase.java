import java.util.ArrayList;
import java.util.List;


public class VoucherDatabase {
  private List<Voucher> codes;

    public VoucherDatabase() {
        codes = new ArrayList<>();
        codes.add(new Voucher("Pwd2024", 0.1)); // 10% discount
        codes.add(new Voucher("SALE50", 0.5)); // 50% discount
    }


    public List<Voucher> getVouchers() {
        return codes;
  }

    public Voucher validateVoucher(String code) {
        for (Voucher voucher : codes) {
            if (voucher.getCode().equals(code)) {
                return voucher;
            }
        }
        return null; // Return null if the code is not found
    }
}