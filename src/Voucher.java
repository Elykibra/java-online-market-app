 class Voucher {
  private String code;
  private double discount;

  public Voucher(String code, double discount) {
    this.code = code;
    this.discount = discount;
  }

  // Getters for code and discount
  public String getCode() {
    return code;
  }

  public double getDiscount() {
    return discount;
  }
}