package ExchangeMoneyBot.currencyGet.DTO;

public enum Currency {
    USD(840), EUR(978), UAH(980);
    private int code;

    Currency(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Currency getCurrencyByCode(int code) {
        for (Currency currency : Currency.values()) {
            if (currency.getCode() == code) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Invalid currency code: " + code);
    }
}
