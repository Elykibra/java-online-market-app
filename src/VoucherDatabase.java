class VoucherDatabase {
  private List<Voucher> codes;

  public VoucherDatabase() {
    codes = new ArrayList<>();
    codes.add(new Voucher("Pwd2024", 0.1)); // 10% discount
    
    }
    public List<Voucher> getVouchers() {
        return codes;
  }
}